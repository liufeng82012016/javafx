package com.my.liufeng.chat.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

/**
 * @author liufeng
 */
public class QueryUtil {
    public static QueryWrapper create(){
        return new QueryWrapper();
    }
}
