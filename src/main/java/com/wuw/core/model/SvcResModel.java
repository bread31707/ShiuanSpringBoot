package com.wuw.core.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize // 讓該POJO沒有值的話，序列化時為空物件而不是NULL
public class SvcResModel {
}
