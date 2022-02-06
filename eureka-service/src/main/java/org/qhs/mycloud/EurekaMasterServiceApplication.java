package org.qhs.mycloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * exclude = DataSourceAutoConfiguration.class 取消数据源的自动创建,
 * 读取自定义的DataSourceProxyConfig.class类，使用Seata对数据源进行代理
 * <p/>
 *
 * @author lixiaolong
 * @EnableDiscoveryClient 该注解用于向使用consul或者zookeeper作为注册中心时注册服务
 * @date 2020/12/18 16:05
 * @description 商品
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaMasterServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaMasterServiceApplication.class, args);
        System.out.println("启动成功");
    }

}
