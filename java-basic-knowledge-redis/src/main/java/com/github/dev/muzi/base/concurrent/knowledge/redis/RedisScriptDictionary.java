package com.github.dev.muzi.base.concurrent.knowledge.redis;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 【字典】redis各类脚本
 * 【kv描述】 key：脚本名称   value：脚本字符串
 * Create by muzi on 2019-11-18
 */
@Component("redisScriptDictionary")
public class RedisScriptDictionary {
    // 日志对象
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisScriptDictionary.class);
    // 脚本标识和相对路径配置
    private static List<LuaEntry> pathEntryList = new ArrayList<>();

    static {
        pathEntryList.add(new LuaEntry("ILB", Thread.currentThread().getContextClassLoader().getResource("lua/interface_limited_bucket.conf").getPath()));
    }

    // 字典容量
    private static final Integer DICT_INITIAL_CAPACITY = 16;

    @Autowired
    private RedisService redisServiceRpc;
    /**
     * name:sha 字典容器
     */
    private Map<String, String> dictionary = new HashMap<>(DICT_INITIAL_CAPACITY);


    @PostConstruct
    private void initValue() {
        LOGGER.info("Create redis script Dictionary begin!");
        for (LuaEntry pathEntry : pathEntryList) {
            LOGGER.info("path:{}", pathEntry.path);
            String script = extractScript(pathEntry.path);
            if (StringUtils.isNotBlank(script)) {
                // 保存script
                pathEntry.script = script;

                String sha = redisServiceRpc.loadScript(script);
                if (StringUtils.isNotBlank(sha)) {
                    dictionary.put(pathEntry.name, sha);
                }
            }
        }
        LOGGER.info("Create redis script Dictionary end! -> Data :{}", JSON.toJSONString(this.dictionary));
    }

    // 提取脚本内容
    private String extractScript(String filePath) {
        StringBuilder builder = new StringBuilder();
        List<String> dataList = DictionaryUtil.loadList(filePath);
        LOGGER.info("dataList:{}", JSON.toJSONString(dataList));
        for (String line : dataList) {
            builder.append(line);
            builder.append("\n");
        }
        return builder.toString();
    }

    private static class LuaEntry {
        public String name;
        public String path;
        public String script;

        public LuaEntry(String name, String path) {
            this.name = name;
            this.path = path;
        }
    }

    public String getScriptSha(String scriptName) {
        if (StringUtils.isBlank(scriptName)) {
            return null;
        }
        return dictionary.get(scriptName);
    }
}
