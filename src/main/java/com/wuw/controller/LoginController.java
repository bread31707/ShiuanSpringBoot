package com.wuw.controller;

import com.wuw.core.AbstractSvcController;
import com.wuw.service.Login;
import com.wuw.service.request.LoginReq;
import com.wuw.service.response.LoginRes;
import com.wuw.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController extends AbstractSvcController<LoginReq, LoginRes> {

    @Autowired
    Login login;

    @Override
    @PostMapping("/Login")
    public Result<LoginRes> service(@RequestBody LoginReq request) {
        return login.doSvc(request, new LoginRes());
    }
}
