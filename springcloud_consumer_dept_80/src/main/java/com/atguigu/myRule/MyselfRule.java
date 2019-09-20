package com.atguigu.myRule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
自定义负载均衡策略
注意要求不能被主启动类的comnpontscan扫描到，不能在主启动类的同级包或者子包下面
 */
@Configuration
public class MyselfRule {

    @Bean
    public IRule myRule(){
        //return new RandomRule();
        return new My_RandomRule();
    }
}
