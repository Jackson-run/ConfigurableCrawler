package ustc.sse.crawler.pipeline;

import ustc.sse.crawler.Config;
import ustc.sse.crawler.ResultModel;
import ustc.sse.crawler.utils.DB.SqlExecuter;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public class MysqlPipeline implements Pipeline{

    @Override
    public void storage(ResultModel resultModel, Config config){
        Map<String,String> dbMappingMap = config.getDbMap();
        String tableName = dbMappingMap.get("tableName");
        SqlExecuter sqlExecuter = new SqlExecuter();
        //表的列数
        int rowLen = dbMappingMap.size()-1;
        //列名的占位符"？"
        String zwstr = "";
        for(int i = 0;i < rowLen-1; i++){
            zwstr+="?,";
        }
        zwstr+="?";
        String sqlStr = "insert into news("+zwstr+") values("+zwstr+");";
        PreparedStatement preparedStatement = sqlExecuter.preProcess(sqlStr);
        for(Map.Entry<String, String> result:resultModel.getElementMap().entrySet()) {
            String tabName = result.getKey();
            String tabValue = result.getValue();
            String rowName = dbMappingMap.get(tabName);
            for (int i = 1; i<=rowLen;i++) {
                try {
                    preparedStatement.setString(i, rowName);
                    preparedStatement.setString(i+rowLen,tabValue);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            int changeSize = preparedStatement.executeUpdate();
            if(changeSize>0){
                System.out.println("succes，插入成功");
            }
            else{
                System.out.println("failed！！changesize 为零，无更新，插入失败");
            }
        }
        catch (SQLException e){
            System.out.println("sql异常，插入失败");
            e.printStackTrace();
        }

    }
}
