package com.sisheng.yygh.hosp.controller;

import com.sisheng.yygh.common.result.Result;
import com.sisheng.yygh.hosp.service.DepartmentService;
import com.sisheng.yygh.hosp.utils.HttpRequestHelper;
import com.sisheng.yygh.vo.hosp.DepartmentVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;



@RestController
@RequestMapping("/api/hosp")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;


    @PostMapping("/saveDepartment")
    public Result saveDepartment(HttpServletRequest request) {
        Map<String, Object> map = HttpRequestHelper.switchMap(request.getParameterMap());
        departmentService.saveDepartment(map);
        return Result.ok();
    }

//    @GetMapping("getDepartLIst/{hoscode}")
//    public Result getDepartList(@PathVariable String hoscode){
//        List<DepartmentVo> departmentVoList = departmentService.findDeptTree(hoscode);
//        return Result.ok(departmentVoList);
//    }
}
