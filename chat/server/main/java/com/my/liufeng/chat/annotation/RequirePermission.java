package com.my.liufeng.chat.annotation;

import com.my.liufeng.chat.enums.Permission;

/**
 * 权限注解
 * @author liufeng
 */
public @interface RequirePermission {

    Permission permission();
}
