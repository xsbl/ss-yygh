package com.sisheng.yygh.order.service.impl;

import com.github.wxpay.sdk.WXPayUtil;
import com.sisheng.yygh.common.exception.YyghException;
import com.sisheng.yygh.enums.PaymentTypeEnum;
import com.sisheng.yygh.model.order.OrderInfo;
import com.sisheng.yygh.order.prop.WeiPayProperties;
import com.sisheng.yygh.order.service.OrderInfoService;
import com.sisheng.yygh.order.service.PaymentService;
import com.sisheng.yygh.order.service.WeixinService;
import com.sisheng.yygh.order.util.HttpClient;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class WeixinServiceImpl implements WeixinService {
    @Autowired
    private OrderInfoService orderService;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private WeiPayProperties weiPayProperties;
    /**
     * 根据订单号下单，生成支付链接
     */
    @Override
    public String createNative(Long orderId) {
        //1.根据订单id获取订单信息
        OrderInfo orderInfo = orderService.getById(orderId);
        //2.保存支付记录
        paymentService.savePaymentInfo(orderInfo, PaymentTypeEnum.WEIXIN.getStatus());
        //3.请求微信服务器获取二维码
        HttpClient client = new HttpClient("https://api.mch.weixin.qq.com/pay/unifiedorder");

        Map paramMap = new HashMap();
        paramMap.put("appid", weiPayProperties.getAppid());
        paramMap.put("mch_id", weiPayProperties.getPartner());
        paramMap.put("nonce_str", WXPayUtil.generateNonceStr());
        Date reserveDate = orderInfo.getReserveDate();
        String reserveDateString = new DateTime(reserveDate).toString("yyyy/MM/dd");
        String body = reserveDateString + "就诊"+ orderInfo.getDepname();
        paramMap.put("body", body);
        paramMap.put("out_trade_no", orderInfo.getOutTradeNo());
        paramMap.put("total_fee", orderInfo.getAmount().multiply(new BigDecimal("100")).longValue()+"");
        paramMap.put("total_fee", "1");//为了测试, 以分为单位,真实金额较大，所以用1分做测试
        paramMap.put("spbill_create_ip", "127.0.0.1");//这个暂时用不到
        paramMap.put("notify_url", "http://guli.shop/api/order/weixinPay/weixinNotify"); //这个暂时用不到
        paramMap.put("trade_type", "NATIVE");
        //client设置参数
        try {
            client.setXmlParam(WXPayUtil.generateSignedXml(paramMap, weiPayProperties.getPartnerkey()));
            client.setHttps(true);
            client.post();
            String xml = client.getContent();
            Map<String, String> resultMap = WXPayUtil.xmlToMap(xml);
            System.out.println("=== " + resultMap.get("code_url") );
            return resultMap.get("code_url");
        } catch (Exception e) {
            return "";
        }
    }

    @Override
    public Map<String, String> queryPayStatus(Long orderId) {
        try {
            OrderInfo orderInfo = orderService.getById(orderId);
            //1、封装参数
            Map paramMap = new HashMap<>();
            paramMap.put("appid", weiPayProperties.getAppid());
            paramMap.put("mch_id", weiPayProperties.getPartner());
            paramMap.put("nonce_str", WXPayUtil.generateNonceStr());
            paramMap.put("out_trade_no", orderInfo.getOutTradeNo());
            //2、设置请求
            HttpClient client = new HttpClient("https://api.mch.weixin.qq.com/pay/orderquery");
            client.setXmlParam(WXPayUtil.generateSignedXml(paramMap, weiPayProperties.getPartnerkey()));
            client.setHttps(true);
            client.post();
            //3、返回第三方的数据，转成Map
            String xml = client.getContent();
            Map<String, String> resultMap = WXPayUtil.xmlToMap(xml);
            //4、返回
            return resultMap;
        } catch (Exception e) {
            return null;
        }
    }
}
