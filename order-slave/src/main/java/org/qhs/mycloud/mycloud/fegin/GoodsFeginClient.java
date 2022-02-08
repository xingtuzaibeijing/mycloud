package org.qhs.mycloud.mycloud.fegin;

import org.qhs.mycloud.mycloud.fegin.impl.GoodsFeginClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@FeignClient(value = "GOODS",fallback = GoodsFeginClientImpl.class)
public interface GoodsFeginClient {

    @GetMapping("/test")
    public String test();

    @GetMapping("/testTimeOut")
    public String testTimeOut();
}
