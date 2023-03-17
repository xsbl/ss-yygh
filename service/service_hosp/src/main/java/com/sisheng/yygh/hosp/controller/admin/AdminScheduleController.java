package com.sisheng.yygh.hosp.controller.admin;

import com.sisheng.yygh.common.result.Result;
import com.sisheng.yygh.hosp.service.ScheduleService;
import com.sisheng.yygh.vo.hosp.DepartmentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/hospital/schedule")
public class AdminScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/{pageNo}/{limit}/{hoscode}/{depcode}")
    public Result getSchedulePage(@PathVariable Integer pageNo,
                                  @PathVariable Integer limit,
                                  @PathVariable String hoscode,
                                  @PathVariable String depcode) {
        Map<String, Object> map = scheduleService.findScheduleRule(pageNo,limit, hoscode,depcode);
        return Result.ok(map);
    }

}
