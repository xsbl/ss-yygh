package com.sisheng.yygh.hosp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.sisheng.yygh.cmn.client.DictFeignClient;
import com.sisheng.yygh.hosp.repository.HospitalRepository;
import com.sisheng.yygh.hosp.service.HospitalService;
import com.sisheng.yygh.model.hosp.Hospital;
import com.sisheng.yygh.vo.hosp.HospitalQueryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author bobochang
 * @description
 * @created 2022/7/2-14:16
 **/
@Service
public class HospitalServiceImpl implements HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private DictFeignClient dictFeignClient;

    @Override
    public void saveHospital(Map<String, Object> paramMap) {
        Hospital hospital = JSONObject.parseObject(JSONObject.toJSONString(paramMap), Hospital.class);

        String hoscode = hospital.getHoscode();
        Hospital hospitalExist = hospitalRepository.getHospitalByHoscode(hoscode);
        if (hospitalExist != null) {
            hospital.setId(hospitalExist.getId());
            hospital.setStatus(hospitalExist.getStatus());
            hospital.setCreateTime(hospitalExist.getCreateTime());
            hospital.setUpdateTime(new Date());
            hospital.setIsDeleted(hospitalExist.getIsDeleted());
        } else {
            hospital.setStatus(0);
            hospital.setCreateTime(new Date());
            hospital.setUpdateTime(new Date());
            hospital.setIsDeleted(0);
        }
        hospitalRepository.save(hospital);
    }

    @Override
    public Hospital getHospitalByHoscode(String hoscode) {
        return hospitalRepository.getHospitalByHoscode(hoscode);
    }


    @Override
    public Page<Hospital> list(Integer page, Integer limit, HospitalQueryVo hospitalQueryVo) {

        Hospital hospital = new Hospital();
        BeanUtils.copyProperties(hospitalQueryVo, hospital);
        ExampleMatcher matcher = ExampleMatcher.matching()
//                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)  // 所有字段都模糊查询
                .withMatcher("hosname",ExampleMatcher.GenericPropertyMatchers.contains())  // hosname字段模糊查询
                .withIgnoreCase(true);
        Example<Hospital> example = Example.of(hospital, matcher);

        Pageable pageable = PageRequest.of(page - 1, limit);

        Page<Hospital> pageInfo = hospitalRepository.findAll(example, pageable);

        pageInfo.getContent().stream().forEach(item -> {
            this.setHospitalHosType(item);
        });

        return pageInfo;
    }

    private void setHospitalHosType(Hospital hospital) {
        String hostypeString = dictFeignClient.getName("Hostype", hospital.getHostype());
        String provinceString = dictFeignClient.getName(hospital.getProvinceCode());
        String cityString = dictFeignClient.getName(hospital.getCityCode());
        String districtString = dictFeignClient.getName(hospital.getDistrictCode());

        hospital.getParam().put("hostypeString", hostypeString);
        hospital.getParam().put("fullAddress", provinceString + cityString + districtString);
    }

    @Override
    public void updateHospStatus(String id, Integer status) {
        if(status == 0 || status == 1) {
            Hospital hospital = hospitalRepository.findById(id).get();
            hospital.setStatus(status);
            hospital.setUpdateTime(new Date());
            hospitalRepository.save(hospital);
        }
    }

    @Override
    public Hospital detail(String id) {
        Hospital hospital = hospitalRepository.findById(id).get();
        //得到省市区和医院等级的文字
        this.setHospitalHosType(hospital);
        return hospital;
    }

//    @Override
//    public String getHospName(String hoscode) {
//        Hospital hospital = hospitalRepository.getHospitalByHoscode(hoscode);
//        String hosname = hospital.getHosname();
//        if (hosname != null) {
//            return hosname;
//        }
//        return null;
//    }
//
//    @Override
//    public List<Hospital> findByHosname(String hosname) {
//        return hospitalRepository.findHospitalByHosnameLike(hosname);
//    }
//
//    @Override
//    public Map<String, Object> findHospDetailByHoscode(String hoscode) {
//        Map<String, Object> result = new HashMap<>();
//        //医院详情
//        Hospital hospital = this.setHospitalHosType(this.showHospitalByHoscode(hoscode));
//        result.put("hospital", hospital);
//        //预约规则
//        result.put("bookingRule", hospital.getBookingRule());
//        //不需要重复返回
//        hospital.setBookingRule(null);
//        return result;
//    }


}
