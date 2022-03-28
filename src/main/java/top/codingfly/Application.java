package top.codingfly;

import cn.hutool.core.map.MapUtil;
import cn.hutool.db.Entity;
import top.codingfly.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class Application {
    private static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 10; i++) {
            logger.debug("debug===debug===debug===debug===debug");
            logger.info("info===info===info===info===info");
            logger.warn("warn===warn===warn===warn===warn");
            logger.error("error===error===error===error===error");
        }
        List<Entity> list = Config.db.findAll("magic_api_info");
        while (true) {
            Thread.sleep(300);
            String sql = "select * from magic_api_info where id in (:ids)";
            Map<String, Object> paramMap = MapUtil.of("ids", new String[]{"033239e63a2a42b987567a37a2efdd32", "104219ceb2e34de38c1d4389cb0a094e", "180524e850124de7956d855bc94bcac9"});
            paramMap.put("aaa", "bbb");
            List<Entity> data = Config.db.query(sql, paramMap);
            logger.info("==============>>>>>>>>>>>>>>>>>>"+System.currentTimeMillis());
        }
    }

}
