package com.example.boot_project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.boot_project.pojo.Department;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DepartmentMapper extends BaseMapper<Department> {

}
