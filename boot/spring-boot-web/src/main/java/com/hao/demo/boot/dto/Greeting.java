package com.hao.demo.boot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;

@AllArgsConstructor
@Getter
@Accessors(chain = true)
@Builder
@ToString
public class Greeting {

    private final long id;
    private final String content;

}
