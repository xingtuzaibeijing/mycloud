package org.qhs.mycloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.qhs.mycloud.fegin.GoodsFeginClient;
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
	private DiscoveryClient discoveryClient;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private GoodsFeginClient goodsFeginClient;

	@GetMapping("/test")
	public String test(){
		List<String> nameList=discoveryClient.getServices();
		for (String s : nameList) {
			System.out.println(s);
		}
		List<ServiceInstance> serviceInstanceList=discoveryClient.getInstances("order");
		for (ServiceInstance serviceInstance : serviceInstanceList) {
			System.out.println(serviceInstance.getHost()+"==="
					+serviceInstance.getPort()+"===="+serviceInstance.getUri());
		}
		return "成功";
	}

	@GetMapping("/testRest")
	@HystrixCommand(fallbackMethod="fallbackMethod",commandProperties={
			@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "2000")})
	public String testRest(){
//		return restTemplate.getForObject("http://GOODS/test",String.class);
		return goodsFeginClient.test();
	}



	@GetMapping("/testTimeOut")
//	@HystrixCommand(fallbackMethod="fallbackMethod",
//			commandProperties = {
//					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
//			}
//	)
	public String testTimeOut(){
//		return restTemplate.getForObject("http://GOODS/test",String.class);
		String str="";
//		try {
			str=goodsFeginClient.testTimeOut();
//		}catch (Exception e){
//			e.printStackTrace();
//		}
		return str;
	}

	public String fallbackMethod(){
		return "失败o_O";
	}

	public String fallbackMethod(Integer id){
		return "失败o_O";
	}


	@GetMapping("/testCircuitBreaker/{id}")
	@HystrixCommand(fallbackMethod = "fallbackMethod", commandProperties = {
			@HystrixProperty(name = "circuitBreaker.enabled", value = "true"),/* 是否开启断路器*/
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),// 失败次数
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), //多久后恢复
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),// 错误百分比
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")// 超时处理
	})
	public String testCircuitBreaker(@PathVariable("id") Integer id){
		if(id%2==0){
			int i=1/0;
		}else{
			int i=1;
			return "成功";
		}
		return "";
	}
}
