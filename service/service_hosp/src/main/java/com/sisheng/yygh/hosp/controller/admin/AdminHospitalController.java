package com.sisheng.yygh.hosp.controller.admin;


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
@RequestMapping("/admin/hospital")
public class AdminHospitalController {

    @Autowired
    private HospitalService hospitalService;

    @GetMapping("/list/{pageNo}/{limit}")
    public Result listHospital(@PathVariable Integer pageNo,
                               @PathVariable Integer limit,
                               HospitalQueryVo hospitalQueryVo) {
        Page<Hospital> page = hospitalService.list(pageNo, limit, hospitalQueryVo);
        return Result.ok(page);
    }

    @GetMapping("/{id}/{status}")
    public Result updateHospStatus(@PathVariable String id, @PathVariable Integer status) {
        hospitalService.updateHospStatus(id, status);
        return Result.ok();
    }

}
