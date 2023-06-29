package com.example.boot_project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.boot_project.pojo.*;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

public interface UserService extends IService<Users> {
    //查询模拟的学校表
    public StudentInfoDto selectStudentInfoDto(Long strNumber);
    //在已认证学生表中加入信息
    public Integer insertStudentInfo(StudentInfoDto studentInfoDto);
    //查询已认证学生表
    public StudentInfo selectStudentInfo(StudentInfoDto studentInfoDto);
    //修改已认证学生表
    public Integer updateStudentInfo(UpdateStudentInfo upstudentInfo);
    //查询学校
    public ArrayList<SchoolInfo> selectSchoolInfo();
    //查询用户是否已认证
    public StudentInfo selectUserInfo(String userId);
    //查询系部
    public List<Department> selectDepartment();
}
