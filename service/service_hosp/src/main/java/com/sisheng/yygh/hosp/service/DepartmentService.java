package com.sisheng.yygh.hosp.service;

import com.sisheng.yygh.model.hosp.Department;
import com.sisheng.yygh.vo.hosp.DepartmentQueryVo;
import com.sisheng.yygh.vo.hosp.DepartmentVo;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface DepartmentService {
//    void saveDepartment(Map<String, Object> paramMap);

//    Page<Department> findDepartment(int page, int limit, DepartmentQueryVo departmentQueryVo);

//    void remove(String hoscode, String depcode);

//    List<DepartmentVo> findDeptTree(String hoscode);

//    String getDepName(String hoscode, String depcode);

    Department saveDepartment(Map<String, Object> map);

    Page<Department> getDepartmentPage(Map<String, Object> map);
}
