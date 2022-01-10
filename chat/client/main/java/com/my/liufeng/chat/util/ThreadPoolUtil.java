package com.my.liufeng.chat.util;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolUtil {
    private static Executor executor = new ThreadPoolExecutor(1, 3, 1, TimeUnit.MINUTES, new LinkedBlockingDeque<>(127));

    public static void submit(Runnable runnable) {
        executor.execute(runnable);
    }
}
