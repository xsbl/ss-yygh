package com.sisheng.yygh.order.api;

import com.sisheng.yygh.common.result.Result;
import com.sisheng.yygh.enums.PaymentTypeEnum;
import com.sisheng.yygh.order.service.PaymentService;
import com.sisheng.yygh.order.service.WeixinService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/order/weixin")
public class WeiPayController {
    @Autowired
    private WeixinService weixinService;

    @Autowired
    private PaymentService paymentService;
    /**
     * 下单 生成二维码
     */
    @GetMapping("createNative/{orderId}")
    public Result createNative(@PathVariable Long orderId) {
        String url = weixinService.createNative(orderId);
        return Result.ok(url);
    }

    @ApiOperation(value = "查询支付状态")
    @GetMapping("/queryPayStatus/{orderId}")
    public Result queryPayStatus(@PathVariable Long orderId) {
        //调用查询接口
        Map<String, String> resultMap = weixinService.queryPayStatus(orderId);
        System.out.println("resultMap === " + resultMap);
        if (resultMap == null) {//出错
            return Result.fail("支付出错");
        }
        if ("SUCCESS".equals(resultMap.get("trade_state"))) {//如果成功
            //更改订单状态，处理支付结果
            String out_trade_no = resultMap.get("out_trade_no");
            paymentService.paySuccess(out_trade_no, PaymentTypeEnum.WEIXIN.getStatus(), resultMap);
            return Result.ok("支付成功");
        }
        return Result.ok("支付中");
    }
}
