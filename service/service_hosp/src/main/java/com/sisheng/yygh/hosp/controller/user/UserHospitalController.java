package com.sisheng.yygh.hosp.controller.user;

import com.sisheng.yygh.common.result.Result;
import com.sisheng.yygh.hosp.service.HospitalService;
import com.sisheng.yygh.model.hosp.Hospital;
import com.sisheng.yygh.vo.hosp.HospitalQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/hospital")
public class UserHospitalController {

    @Autowired
    private HospitalService hospitalService;

    @GetMapping("/list")
    public Result listHospital(HospitalQueryVo hospitalQueryVo) {
        Page<Hospital> page = hospitalService.list(1, 1000000, hospitalQueryVo);
        return Result.ok(page);
    }


}
