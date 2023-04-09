package com.sisheng.yygh.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sisheng.yygh.enums.OrderStatusEnum;
import com.sisheng.yygh.enums.PaymentStatusEnum;
import com.sisheng.yygh.model.order.OrderInfo;
import com.sisheng.yygh.model.order.PaymentInfo;
import com.sisheng.yygh.order.mapper.PaymentMapper;
import com.sisheng.yygh.order.service.OrderInfoService;
import com.sisheng.yygh.order.service.PaymentService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, PaymentInfo> implements PaymentService {
/**
 * 保存交易记录
 * @param orderInfo
 * @param paymentType 支付类型（1：微信 2：支付宝）
 */
    @Override
    public void savePaymentInfo(OrderInfo orderInfo, Integer paymentType) {
        //有可能生成二维码的时候没有支付，关闭了弹窗；然后又一次打开了弹窗 -- 防止对同一个订单生成多个支付记录
         QueryWrapper<PaymentInfo> queryWrapper = new QueryWrapper<>();
         queryWrapper.eq("order_id", orderInfo.getId());
         Integer count = baseMapper.selectCount(queryWrapper);
         if(count >0) return;
         // 保存交易记录
         PaymentInfo paymentInfo = new PaymentInfo();
//         paymentInfo.setCreateTime(new Date());
         paymentInfo.setOrderId(orderInfo.getId());
         paymentInfo.setPaymentType(paymentType);
         paymentInfo.setOutTradeNo(orderInfo.getOutTradeNo());
         paymentInfo.setPaymentStatus(PaymentStatusEnum.UNPAID.getStatus());
         String subject = new DateTime(orderInfo.getReserveDate()).toString("yyyy-MM-dd")+"|"+orderInfo.getHosname()+"|"+orderInfo.getDepname()+"|"+orderInfo.getTitle();
         paymentInfo.setSubject(subject);
         paymentInfo.setTotalAmount(orderInfo.getAmount());
         baseMapper.insert(paymentInfo);
         }

     @Autowired
     private OrderInfoService orderInfoService;

     @Override
     public void paySuccess(String out_trade_no, Integer status, Map<String, String> resultMap) {
          //1 更新订单状态
          QueryWrapper<OrderInfo> wrapperOrder = new QueryWrapper<>();
          wrapperOrder.eq("out_trade_no",out_trade_no);
          OrderInfo orderInfo = orderInfoService.getOne(wrapperOrder);
          //状态已经支付
          orderInfo.setOrderStatus(OrderStatusEnum.PAID.getStatus());
          orderInfoService.updateById(orderInfo);

          //2 更新支付记录状态
          QueryWrapper<PaymentInfo> wrapperPayment = new QueryWrapper<>();
          wrapperPayment.eq("out_trade_no",out_trade_no);
          PaymentInfo paymentInfo = baseMapper.selectOne(wrapperPayment);
          //设置状态
          paymentInfo.setPaymentStatus(PaymentStatusEnum.PAID.getStatus());
          paymentInfo.setTradeNo(resultMap.get("transaction_id"));
          paymentInfo.setCallbackTime(new Date());
          paymentInfo.setCallbackContent(resultMap.toString());
          baseMapper.updateById(paymentInfo);
     }
}