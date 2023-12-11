package com.wuw.redis.model;

import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class TestRedisModel{

    private String q1;
    private String q2;
    private String q3;
    private List<TestRedisModelII> testRedisModelIIList = new ArrayList<>();

    @Setter
    @Getter
    public static class TestRedisModelII{

        private String qq1;
        private String qq2;
        private String qq3;
    }
}
