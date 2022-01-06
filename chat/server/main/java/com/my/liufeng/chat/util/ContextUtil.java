package com.my.liufeng.chat.util;

import com.my.liufeng.chat.constants.Constants;
import com.my.liufeng.util.Conditions;
import io.netty.channel.Channel;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

public class ContextUtil {
    // Spring应用上下文环境
    private static ApplicationContext applicationContext;

    private static InternalLogger log = InternalLoggerFactory.getInstance(ContextUtil.class);

    // 用户信息上下文
    private static ThreadLocal<Channel> userContext = new ThreadLocal<>();

    /**
     * set 上下文
     */

    /**
     * 获取bean
     */
    public static <T> T getBean(Class<T> clazz) {
        long startTime = System.currentTimeMillis();
        String simpleName = clazz.getSimpleName();
        try {
            System.out.println(String.format("get bean:%s startTime:%s", simpleName, startTime));
            System.out.println(String.format("get bean:%s beans:%s", simpleName, Arrays.toString(applicationContext.getBeanDefinitionNames())));
            T bean = applicationContext.getBean(clazz);
            System.out.println(String.format("get bean:%s time:%s", simpleName, System.currentTimeMillis() - startTime));
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

    public static void setApplicationContext(ApplicationContext applicationContext) {
        ContextUtil.applicationContext = applicationContext;
        System.out.println("applicationContext init ==========> ");
    }


//    @Override
//    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
//        applicationContext = configurableListableBeanFactory;
//    }

}
