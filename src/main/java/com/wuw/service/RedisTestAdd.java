package com.wuw.service;

import com.wuw.core.AbstractSvcLogic;
import com.wuw.enums.ResultFields;
import com.wuw.redis.model.TestRedisModel;
import com.wuw.service.request.RedisTestAddReq;
import com.wuw.service.response.RedisTestAddRes;
import com.wuw.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisTestAdd extends AbstractSvcLogic<RedisTestAddReq, RedisTestAddRes> {

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public Result<RedisTestAddRes> doSvc(RedisTestAddReq reqModel, RedisTestAddRes resModel) {

        TestRedisModel model = new TestRedisModel();
        model.setQ1("q1");
        model.setQ2("q2");
        model.setQ3("q3");
        TestRedisModel.TestRedisModelII modelII = new TestRedisModel.TestRedisModelII();
        modelII.setQq1("qq1");
        modelII.setQq2("qq2");
        modelII.setQq3("qq3");
        model.getTestRedisModelIIList().add(modelII);

        redisTemplate.opsForValue().set(reqModel.getKey()+ ":QQ", model, 10, TimeUnit.MINUTES);
        return new Result<>(ResultFields.SUCCEDD, resModel);
    }

}
