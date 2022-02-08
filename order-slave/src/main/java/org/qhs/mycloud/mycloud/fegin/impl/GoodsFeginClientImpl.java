package org.qhs.mycloud.mycloud.fegin.impl;

import org.qhs.mycloud.mycloud.fegin.GoodsFeginClient;
import org.springframework.stereotype.Component;

@Component
public class GoodsFeginClientImpl implements GoodsFeginClient
{
    @Override
    public String test() {
        return "失败";
    }

    @Override
    public String testTimeOut() {
        return "失败";
    }
}
