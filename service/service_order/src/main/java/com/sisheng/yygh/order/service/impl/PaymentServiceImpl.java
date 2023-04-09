package com.sisheng.yygh.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sisheng.yygh.enums.PaymentStatusEnum;
import com.sisheng.yygh.model.order.OrderInfo;
import com.sisheng.yygh.model.order.PaymentInfo;
import com.sisheng.yygh.order.mapper.PaymentMapper;
import com.sisheng.yygh.order.service.PaymentService;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

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
 }