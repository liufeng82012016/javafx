package com.my.liufeng.ui.util;

public class ObjectUtils {
    /**
     * 判断对象是否为空，为空返回默认值
     */
    public static <T> T defaultValue(T value, T defaultValue) {
        return value != null ? value : defaultValue;
    }

    /**
     * 判断对象是否为空或者空字符串
     */
    public static boolean isBlank(String s) {
        return s == null || s.length() == 0;
    }

    /**
     * 判断对象是否为空
     */
    public static boolean isNull(Object obj) {
        return obj == null;
    }
}
