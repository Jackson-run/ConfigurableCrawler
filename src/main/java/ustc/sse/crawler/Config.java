package ustc.sse.crawler;

/**
 * 实现可配置的重要组件，通过此类实现Download和Processor自定义规则
 * @author wangrun
 * @version 0.1
 */

public class Config {
    //网页字符集
    private String charset = "gb2312";
    //休眠时间
    private int sleepTime = 5000;
    //重新爬取间隔时间
    private int retryTime = 0;
    //爬取超时时间
    private int timeOut = 5000;

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public int getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
    }

    public int getRetryTime() {
        return retryTime;
    }

    public void setRetryTime(int retryTime) {
        this.retryTime = retryTime;
    }

    public int getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==null||obj.getClass()!=getClass()){
            return false;
        }
        if(obj==this){
            return true;
        }
        Config config = (Config)obj;
        if(config.retryTime!=retryTime||config.sleepTime!=sleepTime||config.timeOut!=timeOut){
            return false;
        }
        if(config.charset!=null?!config.charset.equals(charset):charset!=null){
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String result ="Config{" +
                "Charset:"+charset+", "+
                "SleepTime:"+sleepTime+", "+
                "RetryTime:"+retryTime+", "+
                "TimeOut:"+timeOut+"}";
        return result;
    }
}
