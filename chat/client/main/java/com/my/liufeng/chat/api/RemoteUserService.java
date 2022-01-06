package com.my.liufeng.chat.api;

import com.my.liufeng.chat.vo.LoginVO;
import com.my.liufeng.rpc.annotation.MethodStub;

@MethodStub(serverAddresses = {"127.0.0.1:9080"}, className = "com.my.liufeng.chat.provider.UserService", serverName = "A")
public interface RemoteUserService {
    boolean login(LoginVO loginVO);

    boolean register(LoginVO loginVO);
}
