package com.desjf.dsjr.utils;

import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

/**
 * @author YinL
 * @Describe json格式检查
 */

public class JsonUtils {

    public static boolean isGoodJson(String json) {
        if (StringUtil.isBlank(json)) {
            return false;
        }
        try {
            new JsonParser().parse(json);
            return true;
        } catch (JsonParseException e) {
            return false;
        }
    }
}
