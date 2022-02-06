package org.qhs.mycloud.fegin.impl;

import org.qhs.mycloud.fegin.GoodsFeginClient;
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
