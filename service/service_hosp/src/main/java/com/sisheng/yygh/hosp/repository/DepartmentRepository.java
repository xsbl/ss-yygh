package com.sisheng.yygh.hosp.repository;

import com.sisheng.yygh.model.hosp.Department;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends MongoRepository<Department, String> {
    Department findByHoscodeAndDepcode(String hoscode, String depcode);
}
