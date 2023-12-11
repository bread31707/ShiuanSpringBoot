package com.wuw.service.response;

import com.wuw.core.model.SvcResModel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class TestInfoFindRes extends SvcResModel {

    private List<Info> infoList = new ArrayList<>();

    @Getter
    @Setter
    public static class Info{
        private String uuid;
        private String startTime;
        private String value;
    }

}
