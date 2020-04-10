package com.ruoyi.common.config;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.ruoyi.project.smt.dzl.domain.SmtDzl;
import com.ruoyi.project.smt.dzl.service.ISmtDzlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目启动执行操作类
 *
 * @author popo
 */
@Slf4j
@Component
public class StartConfig implements CommandLineRunner {


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private ISmtDzlService smtDzlService;

    @Override
    public void run(String... args) throws Exception {
        //log.info("------------------------------------启动完成，开始执行缓存操作------------------------------------");
       // initDzl();
       // getDzl();
    }

    public void initDzl() {
        log.info("------------------开始缓存dzl信息------------------");
        ListOperations<String, Object> listOperations = redisTemplate.opsForList();
        redisTemplate.delete("dzlList");
        List<SmtDzl> dzlList = smtDzlService.selectSmtDzlList(new SmtDzl());
        for (SmtDzl dzl : dzlList) {
            DzlVO vo = new DzlVO();
            vo.setDzlId(dzl.getId());
            vo.setDzlName(dzl.getDzlName());
            vo.setDzlType(dzl.getTypeName());
            listOperations.leftPush("dzlList", vo);
        }
        log.info("------------------dzl信息完毕------------------");
    }

    public void getDzl() {
        if (redisTemplate.hasKey("dzlList")) {
            HashOperations<String, Object, Object> dzlNameMap = redisTemplate.opsForHash();
            HashOperations<String, Object, Object> dzlTypeMap = redisTemplate.opsForHash();
            redisTemplate.delete("dzlIdAndNameMaps");
            redisTemplate.delete("dzlIdAndTypeMaps");
            Object dzlList = redisTemplate.opsForList().range("dzlList", 0, -1);
            List<Map<String,String>> voList = (List<Map<String,String>>) dzlList;
            for (Map map: voList) {
                dzlNameMap.put("dzlIdAndNameMaps",map.get("dzlId").toString(),map.get("dzlName"));
                dzlTypeMap.put("dzlIdAndTypeMaps",map.get("dzlId").toString(),map.get("dzlType"));
            }
        } else {
            log.info("没有电子料缓存信息，需要重新查询");
        }

    }
}
