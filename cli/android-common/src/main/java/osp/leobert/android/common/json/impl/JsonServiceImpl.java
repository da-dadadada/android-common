package osp.leobert.android.common.json.impl;

import com.alibaba.fastjson.JSON;

import java.util.List;

import osp.leobert.android.common.json.JsonService;

/**
 * <p><b>Package:</b> osp.leobert.android.common.json.impl </p>
 * <p><b>Project:</b> cli </p>
 * <p><b>Classname:</b> JsonServiceImpl </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/10/24.
 */

public class JsonServiceImpl implements JsonService{
    @Override
    public <T> T parseObject(String text, Class<T> clazz) {
        return JSON.parseObject(text,clazz);
    }

    @Override
    public <T> List<T> parseArray(String text, Class<T> clazz) {
        return JSON.parseArray(text,clazz);
    }

    @Override
    public String toJsonString(Object instance) {
        return JSON.toJSONString(instance);
    }
}
