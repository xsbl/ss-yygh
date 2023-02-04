//package com.sisheng.yygh.hosp.service.impl;
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
//import com.sisheng.yygh.common.exception.YyghException;
//import com.sisheng.yygh.common.result.ResultCodeEnum;
//import com.sisheng.yygh.hosp.mapper.HospitalSetMapper;
//import com.sisheng.yygh.hosp.service.HospitalSetService;
//import com.sisheng.yygh.model.hosp.HospitalSet;
//import com.sisheng.yygh.vo.order.SignInfoVo;
//import org.springframework.stereotype.Service;
//
///**
// * @author bobochang
// * @description
// * @created 2022/6/29-14:49
// **/
//
//@Service
//public class HospitalSetServiceImpl extends ServiceImpl<HospitalSetMapper, HospitalSet> implements HospitalSetService {
//
//    @Override
//    public String getSignKey(String hoscode) {
//        QueryWrapper<HospitalSet> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("hoscode", hoscode);
//        HospitalSet hospitalSet = baseMapper.selectOne(queryWrapper);
//        return hospitalSet.getSignKey();
//    }
//
//    //获取医院签名信息
//    @Override
//    public SignInfoVo getSignInfoVo(String hoscode) {
//        QueryWrapper<HospitalSet> wrapper = new QueryWrapper<>();
//        wrapper.eq("hoscode", hoscode);
//        HospitalSet hospitalSet = baseMapper.selectOne(wrapper);
//        if (null == hospitalSet) {
//            throw new YyghException(ResultCodeEnum.HOSPITAL_OPEN);
//        }
//        SignInfoVo signInfoVo = new SignInfoVo();
//        signInfoVo.setApiUrl(hospitalSet.getApiUrl());
//        signInfoVo.setSignKey(hospitalSet.getSignKey());
//        return signInfoVo;
//    }
//}
