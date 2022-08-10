package com.my.liufeng.chat.util;

import com.my.liufeng.chat.constants.Constants;
import com.my.liufeng.util.Conditions;
import io.netty.channel.Channel;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ContextUtil {
    /**
     * Spring 上下文
     */
    private static ApplicationContext applicationContext;


    private static InternalLogger log = InternalLoggerFactory.getInstance(ContextUtil.class);

    /**
     * 用户信息上下文
     */
    private static ThreadLocal<Channel> userContext = new ThreadLocal<>();


    /**
     * 获取bean
     */
    public static <T> T getBean(Class<T> clazz) {
        long startTime = System.currentTimeMillis();
        String simpleName = clazz.getSimpleName();
        try {
            if (log.isDebugEnabled()) {
                log.debug("get bean:{} startTime:{}", simpleName, startTime);
            }
            T bean = applicationContext.getBean(clazz);
            if (log.isDebugEnabled()) {
                log.debug("get bean:{} time:{}", simpleName, System.currentTimeMillis() - startTime);
            }
            return bean;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * set channel
     */
    public static void setChannel(Channel channel) {
        userContext.set(channel);
    }

    /**
     * 获取channel
     */
    public static Channel getChannel() {
        Channel channel = userContext.get();
        Conditions.expectNonNull(channel, Constants.CHANNEL_NULL);
        return channel;
    }

    /**
     * 移除channel
     */
    public static void removeChannel() {
        userContext.remove();
    }

    /**
     * 获取userId
     */
    public static Integer getUserId() {
        Attribute<Object> attr = getChannel().attr(AttributeKey.valueOf(Constants.ATTR_USER_ID));
        Conditions.expectNonNull(attr, Constants.NOT_LOGIN);
        Conditions.expectNonNull(attr.get(), Constants.NOT_LOGIN);
        return (Integer) attr.get();
    }

    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext) {
        ContextUtil.applicationContext = applicationContext;
        System.out.println("applicationContext init ==========> ");
    }

}
