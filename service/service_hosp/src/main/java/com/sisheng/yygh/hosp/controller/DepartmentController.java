package com.sisheng.yygh.hosp.controller;

import com.sisheng.yygh.common.result.Result;
import com.sisheng.yygh.hosp.service.DepartmentService;
import com.sisheng.yygh.hosp.utils.HttpRequestHelper;
import com.sisheng.yygh.model.hosp.Department;
import com.sisheng.yygh.vo.hosp.DepartmentVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @PostMapping("/department/list")
    public Result<Page> getDepartmentPage(HttpServletRequest request) {
        Map<String, Object> map = HttpRequestHelper.switchMap(request.getParameterMap());
        Page<Department> page = departmentService.getDepartmentPage(map);
        return Result.ok(page);
    }

    @PostMapping("/department/remove")
    public Result remove(HttpServletRequest request) {
        Map<String, Object> map = HttpRequestHelper.switchMap(request.getParameterMap());
        departmentService.remove(map);
        return Result.ok();
    }

}
