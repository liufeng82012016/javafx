package com.my.liufeng.chat.util;

import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

public class SecretUtil {
    public static String encryptPwd(String pwd) {
        return DigestUtils.md5DigestAsHex((pwd + "xinyi").getBytes(StandardCharsets.UTF_8));
    }
}
