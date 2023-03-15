package com.sisheng.yygh.hosp.controller.api;

import com.sisheng.yygh.common.result.Result;
import com.sisheng.yygh.hosp.service.ScheduleService;
import com.sisheng.yygh.hosp.utils.HttpRequestHelper;
import com.sisheng.yygh.model.hosp.Schedule;
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
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @PostMapping("/saveSchedule")
    public Result saveSchedule(HttpServletRequest request) {
        Map<String, Object> map = HttpRequestHelper.switchMap(request.getParameterMap());
        scheduleService.save(map);
        return Result.ok();
    }

    @PostMapping("/schedule/list")
    public Result<Page> getSchedulePage(HttpServletRequest request) {
        Map<String, Object> map = HttpRequestHelper.switchMap(request.getParameterMap());
        Page<Schedule> page = scheduleService.getSchedulePage(map);
        return Result.ok(page);
    }

    @PostMapping("/schedule/remove")
    public Result remove(HttpServletRequest request) {
        Map<String, Object> map = HttpRequestHelper.switchMap(request.getParameterMap());
        scheduleService.remove(map);
        return Result.ok();
    }
}