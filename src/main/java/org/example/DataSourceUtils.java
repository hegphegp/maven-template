package org.example;

import com.sun.rowset.CachedRowSetImpl;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class DataSourceUtils {
    public static NamedParameterJdbcTemplate jdbcTemplate = null;

	//在静态代码块中初始化连接池
    static {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres?useSSL=false");
        ds.setUsername("sde");
        ds.setPassword("postgres");
        ds.setDriverClassName("org.postgresql.Driver");
        jdbcTemplate = new NamedParameterJdbcTemplate(new JdbcTemplate(ds));

    }

    public static void test() {
        List list = jdbcTemplate.queryForList("SELECT * FROM gs_sql_template", new HashMap<String, Object>());
        System.out.println(list);
    }

}