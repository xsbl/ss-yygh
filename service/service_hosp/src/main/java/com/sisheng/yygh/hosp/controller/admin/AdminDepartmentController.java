package com.sisheng.yygh.hosp.controller.admin;

import com.sisheng.yygh.common.result.Result;
import com.sisheng.yygh.hosp.service.DepartmentService;
import com.sisheng.yygh.hosp.service.HospitalService;
import com.sisheng.yygh.model.hosp.Hospital;
import com.sisheng.yygh.vo.hosp.DepartmentVo;
import com.sisheng.yygh.vo.hosp.HospitalQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/hospital/department")
public class AdminDepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/{hoscode}")
    public Result getDepartmentList(@PathVariable String hoscode) {
        List<DepartmentVo> list = departmentService.findDeptTree(hoscode);
        return Result.ok(list);
    }

}
