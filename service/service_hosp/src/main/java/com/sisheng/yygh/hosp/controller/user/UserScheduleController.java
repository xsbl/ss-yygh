package com.sisheng.yygh.hosp.controller.user;

import com.sisheng.yygh.common.result.Result;
import com.sisheng.yygh.hosp.service.ScheduleService;
import com.sisheng.yygh.model.hosp.Hospital;
import com.sisheng.yygh.model.hosp.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user/schedule")
public class UserScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/{page}/{limit}/{hoscode}/{depcode}")
    public Result getBookingScheduleRule(@PathVariable Integer page,
                                                                 @PathVariable Integer limit,
                                                                 @PathVariable String hoscode,
                                                                 @PathVariable String depcode) {
        Map<String, Object> hospital = scheduleService.getBookingScheduleRule(page, limit, hoscode, depcode );

        return Result.ok(hospital);
    }

    @GetMapping("/{hoscode}/{depcode}/{workdate}")
    public Result getScheduleDetail(@PathVariable String hoscode,
                                                       @PathVariable String depcode,
                                                       @PathVariable String workdate) {
        List< Schedule > schedules = scheduleService.getDetailSchedule(hoscode, depcode, workdate );

        return Result.ok(schedules);
    }

}
