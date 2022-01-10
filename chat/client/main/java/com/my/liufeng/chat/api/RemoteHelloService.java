package com.my.liufeng.chat.api;

import com.my.liufeng.chat.entity.Heart;
import com.my.liufeng.rpc.annotation.MethodStub;

/**
 * 模拟api方法 -- 实际上应该放到独立的包
 */
@MethodStub(serverAddresses = {"127.0.0.1:10030"}, className = "com.my.liufeng.chat.provider.HelloService", serverName = "A")
public interface RemoteHelloService {


    Heart ping();
}
