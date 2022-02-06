package org.qhs.myrule;

import com.netflix.loadbalancer.RandomRule;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

@Configurable
public class MyRuleConfig {
    @Bean
    public RandomRule myRule(){
        return new RandomRule();
    }
}
