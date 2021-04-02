package com.test.concurrent.pool.schedule.edu;

import lombok.Data;

/**
 * @author ：杨过
 * @date ：Created in 2020/7/17
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description:
 **/
@Data
public class HeartBeat {
    private String ip;
    private int port;
    private String appName;
    private String instanceId;
}
