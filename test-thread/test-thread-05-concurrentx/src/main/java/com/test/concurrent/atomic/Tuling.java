package com.test.concurrent.atomic;

import lombok.Data;

/**
 * @author ：杨过
 * @date ：Created in 2020/8/21
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description:
 **/
@Data
public class Tuling {

    private Integer sequence;

    public Tuling(Integer seq) {
        sequence = seq;
    }
}


