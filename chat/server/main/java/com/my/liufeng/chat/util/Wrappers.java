package com.my.liufeng.chat.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

public class Wrappers {
    public static <T> QueryWrapper<T> create(Class<T> clazz) {
        return new QueryWrapper<T>();
    }
}
