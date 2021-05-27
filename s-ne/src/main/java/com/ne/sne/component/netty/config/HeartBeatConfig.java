package com.ne.sne.component.netty.config;

import com.ne.sne.component.netty.domain.CustomProtocol;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 心跳检测参数
 */
@Configuration
public class HeartBeatConfig {
    @Value("${channel.id}")
    private long id ;
    @Bean(value = "heartBeat")
    public CustomProtocol heartBeat(){
        return new CustomProtocol(id,"ping") ;
    }
}