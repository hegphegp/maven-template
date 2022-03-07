package org.example;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import cn.hutool.db.ds.DSFactory;
import cn.hutool.db.ds.hikari.HikariDSFactory;
import cn.hutool.db.ds.tomcat.TomcatDSFactory;
import cn.hutool.setting.Setting;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class App {
    private static Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws Exception {
//        Setting setting = new Setting();
//        setting.set("url", "jdbc:mysql://localhost:3306/magic-0.4.8?useSSL=false&nullCatalogMeansCurrent=true&useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC&zeroDateTimeBehavior=convertToNull&autoReconnect=true&failOverReadOnly=false");
//        setting.set("username", "root");
//        setting.set("password", "root");
//        setting.set("driver", "com.mysql.jdbc.Driver");
//        setting.set("showSql", "true");
//        setting.set("formatSql", "false");
//        setting.set("showParams", "true");
//        setting.set("sqlLevel", "debug");
//        setting.set("connectionTestQuery", "SELECT 1");
//        setting.set("minimumIdle", "10");
//        setting.set("maximumPoolSize", "10");
//        setting.set("readOnly", "false");
//        DSFactory.setCurrentDSFactory(new HikariDSFactory(setting));
//        DataSource ds = DSFactory.get();

        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl("jdbc:mysql://localhost:3306/magic-0.4.8?useSSL=false&nullCatalogMeansCurrent=true&useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC&zeroDateTimeBehavior=convertToNull&autoReconnect=true&failOverReadOnly=false");
        ds.setUsername("root");
        ds.setPassword("root");
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setAutoCommit(true);
        ds.setConnectionTimeout(30000);
        ds.setIdleTimeout(600000);
        ds.setMaxLifetime(1800000);
        ds.setConnectionTestQuery("SELECT 1");
        ds.setMinimumIdle(3);
        ds.setMaximumPoolSize(6);
        ds.setReadOnly(true);
        List<Entity> list = Db.use(ds).findAll("magic_api_info");
        System.out.println(list);
        System.out.println(list);
    }

}
