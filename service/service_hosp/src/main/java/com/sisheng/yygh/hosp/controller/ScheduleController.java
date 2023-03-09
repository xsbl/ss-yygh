package com.sisheng.yygh.hosp.controller;

import com.sisheng.yygh.common.result.Result;
import com.sisheng.yygh.hosp.service.ScheduleService;
import com.sisheng.yygh.hosp.utils.HttpRequestHelper;
import com.sisheng.yygh.model.hosp.Schedule;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/hosp")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @PostMapping("/saveSchedule")
    public Result saveSchedule(HttpServletRequest request) {
        Map<String, Object> map = HttpRequestHelper.switchMap(request.getParameterMap());
        scheduleService.save(map);
        return Result.ok();
    }
}
