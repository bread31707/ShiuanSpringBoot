package com.wuw.service;

import com.wuw.core.AbstractSvcLogic;
import com.wuw.enums.ResultFields;
import com.wuw.service.request.LoginReq;
import com.wuw.service.response.LoginRes;
import com.wuw.utils.JWTUtils;
import com.wuw.vo.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class Login extends AbstractSvcLogic<LoginReq, LoginRes> {

    @Autowired
    HttpServletResponse httpServletResponse;

    @Override
    public Result<LoginRes> doSvc(LoginReq reqModel, LoginRes resModel) {

        Map<String, String> payload = new HashMap<>();
        payload.put("userId", reqModel.getUserId());
        payload.put("userName", reqModel.getUserName());
        String token = JWTUtils.getToken(payload);
        httpServletResponse.setHeader("token", token); // 將 token 放入 response header 中
        return new Result<>(ResultFields.SUCCEDD, resModel);
    }
}
