package com.sisheng.yygh.hosp.service;

import com.sisheng.yygh.model.hosp.Hospital;
import com.sisheng.yygh.vo.hosp.HospitalQueryVo;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author bobochang
 * @description
 * @created 2022/7/2-14:15
 **/
@Service
public interface HospitalService {
    //上传医院接口
    void saveHospital(Map<String, Object> paramMap);

    Hospital getHospitalByHoscode(String hoscode);

//    Hospital showHospitalByHoscode(String hoscode);
//
    Page<Hospital> list(Integer page, Integer limit, HospitalQueryVo hospitalQueryVo);

    void updateHospStatus(String id, Integer status);

    Hospital detail(String id);
//
//    void updateStatus(String id, Integer status);
//
//    Map<String, Object> getHospDetailById(String id);
//
    String getHospName(String hoscode);
//
    List<Hospital> findByHosname(String hosname);
//
    Hospital findHospDetailByHoscode(String hoscode);
}
