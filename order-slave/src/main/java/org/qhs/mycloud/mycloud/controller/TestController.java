package org.qhs.mycloud.mycloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.qhs.mycloud.mycloud.fegin.GoodsFeginClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author QianHuaSheng
 * @version V1.0
 * @description
 * @date 2022/2/1
 */
@RestController
//@DefaultProperties(defaultFallback = "fallbackMethod",commandProperties ={
//		@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "50000")
//} )
public class TestController {


	@Autowired
	private GoodsFeginClient goodsFeginClient;


	@GetMapping("/testRest")
	@HystrixCommand(fallbackMethod="fallbackMethod",commandProperties={
			@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "2000")})
	public String testRest(){
//		return restTemplate.getForObject("http://GOODS/test",String.class);
		return goodsFeginClient.test();
	}

}
