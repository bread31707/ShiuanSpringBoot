package com.wuw.service.response;

import com.wuw.core.model.SvcResModel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RedisTestFindRes extends SvcResModel {

    private String q1;
    private String q2;
    private String q3;
    private List<RedisTestFindResII> redisTestFindResIIList = new ArrayList<>();

    @Getter
    @Setter
    public static class RedisTestFindResII{
        private String qq1;
        private String qq2;
        private String qq3;
    }

}
