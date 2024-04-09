package com.wuw.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wuw.core.AbstractSvcLogic;
import com.wuw.enums.ResultFields;
import com.wuw.handler.AppException;
import com.wuw.redis.model.TestRedisModel;
import com.wuw.service.request.RedisTestFindReq;
import com.wuw.service.response.RedisTestFindRes;
import com.wuw.utils.JacksonUtils;
import com.wuw.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisTestFind extends AbstractSvcLogic<RedisTestFindReq, RedisTestFindRes> {

    @Autowired
    RedisTemplate redisTemplate;
    JacksonUtils jacksonUtils = new JacksonUtils();


    @Override
    public Result<RedisTestFindRes> doSvc(RedisTestFindReq reqModel, RedisTestFindRes resModel){

        Object o = redisTemplate.opsForValue().get("key!!:QQ");
        TestRedisModel testRedisModel = null;
        try {
            testRedisModel = jacksonUtils.readValue(jacksonUtils.getObjectMapper().writeValueAsString(o), TestRedisModel.class).get();
        } catch (JsonProcessingException e) {
            throw new AppException(ResultFields.FAIL_JSON_CONVERT);
        }
        resModel.setQ1(testRedisModel.getQ1());
        resModel.setQ2(testRedisModel.getQ2());
        resModel.setQ3(testRedisModel.getQ3());
        testRedisModel.getTestRedisModelIIList().forEach(node->{
            RedisTestFindRes.RedisTestFindResII redisTestFindResII = new RedisTestFindRes.RedisTestFindResII();
            redisTestFindResII.setQq1(node.getQq1());
            redisTestFindResII.setQq2(node.getQq2());
            redisTestFindResII.setQq3(node.getQq3());
            resModel.getRedisTestFindResIIList().add(redisTestFindResII);
        });

        return new Result<>(ResultFields.SUCCEDD, resModel);
    }
}
