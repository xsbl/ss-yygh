package com.sisheng.yygh.hosp.client;

import com.sisheng.yygh.vo.hosp.ScheduleOrderVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author bobochang
 * @description
 * @created 2022/7/16-15:11
 **/
@FeignClient(value = "service-hosp")
@Service
public interface HospitalFeignClient {
    /**
     * 根据排班id获取预约下单数据
     */
    @GetMapping("/user/schedule/order/{scheduleId}")
    public ScheduleOrderVo getScheduleOrder(@PathVariable(value = "scheduleId") String scheduleId);

    /**
     * 获取医院签名信息
     */
//    @GetMapping("/api/hosp/hospital/inner/getSignInfoVo/{hoscode}")
//    SignInfoVo getSignInfoVo(@PathVariable("hoscode") String hoscode);
}

