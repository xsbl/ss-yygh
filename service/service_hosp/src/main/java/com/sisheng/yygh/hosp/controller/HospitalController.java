package com.sisheng.yygh.hosp.controller;

import com.sisheng.yygh.common.result.Result;
import com.sisheng.yygh.hosp.service.HospitalService;
import com.sisheng.yygh.hosp.utils.HttpRequestHelper;
import com.sisheng.yygh.model.hosp.Hospital;
import com.sisheng.yygh.vo.hosp.HospitalQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
@RestController
@RequestMapping("/api/hosp")
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    @PostMapping("/saveHospital")
    public Result saveHospital(HttpServletRequest request) {
        Map<String, Object> map = HttpRequestHelper.switchMap(request.getParameterMap());
        hospitalService.saveHospital(map);
        return Result.ok();
    }
}
