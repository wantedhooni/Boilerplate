package com.revy.api_server.common.util;

import com.revy.common.utils.BigDecimalUtil;
import io.jsonwebtoken.lang.Assert;

import java.math.BigDecimal;

public class BigDecimalUtilTest {
    public static void main(String[] args) {
        System.out.println("BigDecimalUtilTest");
        Assert.isTrue(BigDecimalUtil.isGreaterThanOrEqualTo(BigDecimal.ZERO, BigDecimal.ZERO));
        Assert.isTrue(!BigDecimalUtil.isGreaterThan(BigDecimal.ZERO, BigDecimal.ZERO));
    }
}
