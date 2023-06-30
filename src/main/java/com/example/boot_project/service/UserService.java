package com.example.boot_project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.boot_project.pojo.*;

import java.util.List;

public interface UserService extends IService<Users> {
    public StudentInfoDto selectStudentInfoDto(Long strNumber);
    public Integer insertStudentInfo(StudentInfoDto studentInfoDto);
    public StudentInfo selectStudentInfo(StudentInfoDto studentInfoDto);
    public List<SchoolInfo> selectSchool();
    public List<Department> selectDepartment();
    public Integer updateStudentInfo(UpdateStudentInfo updateStudentInfo);

    public StudentInfo selectUserInfo(String userId);

    public UserInfoDtoQuery selectUserInfoDto(long Id);

    public Integer userLoginAuth(Users user);
}
