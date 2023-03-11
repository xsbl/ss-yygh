package com.sisheng.yygh.hosp.controller.api;

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
        String logoData = (String) map.get("logoData");
        String result = logoData.replaceAll(" ", "+");
        map.put("logoData", result);
        hospitalService.saveHospital(map);
        return Result.ok();
    }

    @PostMapping("/hospital/show")
    public Result<Hospital> getHospitalInfo(HttpServletRequest request) {
        Map<String, Object> map = HttpRequestHelper.switchMap(request.getParameterMap());
        String hoscode = (String) map.get("hoscode");
        Hospital hospital = hospitalService.getHospitalByHoscode(hoscode);
        return Result.ok(hospital);
    }
}
