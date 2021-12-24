package com.my.liufeng.ui.util;

import java.util.Random;

public class RandomUtil {
    private static final Random random = new Random();
    private static char[] chars = new char[62];

    static {
        char asciiIndex = 1;
        for (int i = 0; i < chars.length && asciiIndex < 128; asciiIndex++) {
            if (between('0', '9', asciiIndex)
                    || between('a', 'z', asciiIndex)
                    || between('A', 'Z', asciiIndex)) {
                chars[i] = asciiIndex;
                i++;
            }
        }
    }

    private static boolean between(char min, char max, char value) {
        return min <= value && value <= max;
    }

    public static String randomStr(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(chars[random.nextInt(chars.length)]);
        }
        return sb.toString();
    }

    public static int randomInt(int bound) {
        return random.nextInt(bound);
    }
}
