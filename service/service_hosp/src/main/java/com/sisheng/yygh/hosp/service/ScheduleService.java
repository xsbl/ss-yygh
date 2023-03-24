package com.sisheng.yygh.hosp.service;

import com.sisheng.yygh.model.hosp.Schedule;
import com.sisheng.yygh.vo.hosp.ScheduleOrderVo;
import com.sisheng.yygh.vo.hosp.ScheduleQueryVo;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface ScheduleService {
    void save(Map<String, Object> paramMap);

//    Page<Schedule> findSchedule(int page, int limit, ScheduleQueryVo scheduleQueryVo);
    Page<Schedule> getSchedulePage(Map<String, Object> map);

    void remove(Map<String, Object> map);

    List<Schedule> getDetailSchedule(String hoscode, String depcode, String workDate);

    Map<String,Object> getBookingScheduleRule(Integer page, Integer limit, String hoscode, String depcode);

    Map<String, Object> findScheduleRule(int page, int limit, String hoscode, String depcode);

    Schedule getScheduleInfo(String scheduleId);

    ScheduleOrderVo getScheduleOrderVo(String scheduleId);

//    void update(Schedule schedule);
}
