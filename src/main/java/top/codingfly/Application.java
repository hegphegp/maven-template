package top.codingfly;

import cn.hutool.db.Entity;
import top.codingfly.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Application {
    private static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws Exception {
        List<Entity> list = Config.db.findAll("magic_api_info");
    }

}
