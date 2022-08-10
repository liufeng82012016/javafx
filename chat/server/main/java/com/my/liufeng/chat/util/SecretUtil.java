package com.my.liufeng.chat.util;

import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

/**
 * 加密工具类
 *
 * @author liufeng
 */
public class SecretUtil {
    public static String encryptPwd(String pwd) {
        return DigestUtils.md5DigestAsHex((pwd + "xinyi").getBytes(StandardCharsets.UTF_8));
    }
}
