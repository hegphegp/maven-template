package top.codingfly.config;

import cn.hutool.db.Db;
import cn.hutool.db.sql.SqlLog;
import cn.hutool.log.level.Level;
import com.zaxxer.hikari.HikariDataSource;

public class Config {

    public static Db db = null;

    static {
        SqlLog.INSTANCE.init(true, false, true, Level.DEBUG);
        configDB();
    }

    public static void configDB() {
        HikariDataSource ds = new HikariDataSource();
        if (EnvParamConf.useEnvParam) {
            String dbHost = EnvParamConf.getParam("db.host");
            Integer dbPort = EnvParamConf.getIntParam("db.port");
            String dbName = EnvParamConf.getParam("db.name");
            String dbUsername = EnvParamConf.getParam("db.username");
            String dbPassword = EnvParamConf.getParam("db.password");
            ds.setJdbcUrl("jdbc:mysql://"+dbHost+":"+dbPort+"/"+dbName+"?useSSL=false&nullCatalogMeansCurrent=true&useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC&zeroDateTimeBehavior=convertToNull&autoReconnect=true&failOverReadOnly=false");
            ds.setUsername(dbUsername);
            ds.setPassword(dbPassword);
        } else {
            ds.setJdbcUrl("jdbc:mysql://localhost:3306/magic-0.4.8?useSSL=false&nullCatalogMeansCurrent=true&useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC&zeroDateTimeBehavior=convertToNull&autoReconnect=true&failOverReadOnly=false");
            ds.setUsername("root");
            ds.setPassword("root");
        }
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setAutoCommit(true);
        ds.setConnectionTimeout(30000);
        ds.setIdleTimeout(600000);
        ds.setMaxLifetime(1800000);
        ds.setConnectionTestQuery("SELECT 1");
        ds.setMinimumIdle(3);
        ds.setMaximumPoolSize(6);
        ds.setReadOnly(false);
        db = Db.use(ds);
    }

}
