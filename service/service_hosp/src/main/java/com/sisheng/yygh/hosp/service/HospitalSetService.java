package com.sisheng.yygh.hosp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sisheng.yygh.model.hosp.HospitalSet;
import com.sisheng.yygh.vo.order.SignInfoVo;

/**
 * @author bobochang
 * @description
 * @created 2022/6/29-14:41
 **/

public interface HospitalSetService extends IService<HospitalSet> {
    String getSignKey(String hoscode);

    SignInfoVo getSignInfoVo(String hoscode);
}
