package com.wuw.controller;

import com.wuw.core.AbstractSvcController;
import com.wuw.service.RedisTestAdd;
import com.wuw.service.request.RedisTestAddReq;
import com.wuw.service.response.RedisTestAddRes;
import com.wuw.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisTestAddController extends AbstractSvcController<RedisTestAddReq, RedisTestAddRes> {

    @Autowired
    RedisTestAdd redisTestAdd;

    @Override
    @PostMapping("/RedisTestAdd")
    public Result<RedisTestAddRes> service(@RequestBody RedisTestAddReq request) {
        return redisTestAdd.doSvc(request, new RedisTestAddRes());
    }

}
