package com.sisheng.yygh.hosp.controller.user;

import com.sisheng.yygh.common.result.Result;
import com.sisheng.yygh.hosp.service.DepartmentService;
import com.sisheng.yygh.vo.hosp.DepartmentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/department")
public class UserDepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/all/{hoscode}")
    public Result listHospital(@PathVariable String hoscode) {
        List<DepartmentVo> list = departmentService.findDeptTree(hoscode);
        return Result.ok(list);
    }

}
