package ustc.sse.crawler.pipeline;

import ustc.sse.crawler.Config;
import ustc.sse.crawler.ResultModel;
import ustc.sse.crawler.scheduler.ResultModelScheduler;
import ustc.sse.crawler.utils.DB.SqlExecuter;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wangrun
 * @version 0.1
 */
public class MysqlPipeline implements Pipeline,Runnable{
    private ResultModelScheduler resultModelScheduler = null;
    private Config config = null;
    private int threadNum = 1;
    private SqlExecuter sqlExecuter = null;
    @Override
    public void storage(ResultModelScheduler resultModelScheduler, Config config) {
        this.resultModelScheduler = resultModelScheduler;
        this.config = config;
        sqlExecuter = new SqlExecuter();
        ExecutorService exec = Executors.newFixedThreadPool(threadNum);
        for (int i =0;i<threadNum;i++){
            exec.execute(this);
        }
    }

    @Override
    public void setThreadNum(int num) {
        threadNum = num;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            Map<String, String> dbMappingMap = config.getDbMap();
            String tableName = dbMappingMap.get("tableName");
            //表的列数
            int rowLen = dbMappingMap.size() - 1;
            //列名的占位符"？"
            String zwstr = "";
            for (int i = 0; i < rowLen - 1; i++) {
                zwstr += "?,";
            }
            zwstr += "?";
            //实属无奈之举啊/(ㄒoㄒ)/~~
            // String sqlStr = "insert into news("+zwstr+") values("+zwstr+");";
            String sqlStr = "";
            ResultModel resultModel = null;
            resultModel = resultModelScheduler.poll();
            if(resultModel!=null) {
                sqlStr = "insert into news(";

                for (Map.Entry<String, String> result : resultModel.getElementMap().entrySet()) {
                    String tabName = result.getKey().trim();
                    String rowName = dbMappingMap.get(tabName);
                    sqlStr += rowName + ",";
                }
                sqlStr = sqlStr.substring(0, sqlStr.length() - 1) + ") values(" + zwstr + ");";
                PreparedStatement preparedStatement = sqlExecuter.preProcess(sqlStr);
                int i = 0;
                for (Map.Entry<String, String> result : resultModel.getElementMap().entrySet()) {
                    i++;
                    String tabValue = result.getValue().trim();
                    try {
                        preparedStatement.setString(i, tabValue);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    int changeSize = preparedStatement.executeUpdate();
                    if (changeSize > 0) {
                        System.out.println("succes，插入成功");
                    } else {
                        System.out.println("failed！！changesize 为零，无更新，插入失败");
                    }
                } catch (SQLException e) {
                    System.out.println("sql异常，插入失败");
                    e.printStackTrace();
                }

            }
        }
    }
}
