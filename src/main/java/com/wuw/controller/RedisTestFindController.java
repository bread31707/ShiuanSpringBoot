package com.wuw.controller;

import com.wuw.core.AbstractSvcController;
import com.wuw.service.RedisTestFind;
import com.wuw.service.request.RedisTestFindReq;
import com.wuw.service.response.RedisTestFindRes;
import com.wuw.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisTestFindController extends AbstractSvcController<RedisTestFindReq, RedisTestFindRes> {

    @Autowired
    RedisTestFind redisTestFind;

    @Override
    @PostMapping("/RedisTestFind")
    public Result<RedisTestFindRes> service(@RequestBody RedisTestFindReq request) {
        return redisTestFind.doSvc(request, new RedisTestFindRes());
    }
}
