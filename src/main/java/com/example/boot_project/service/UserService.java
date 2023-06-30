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

    // 查询用户个人信息
    public StudentInfo selectUserInfo(String userId);
    public UserInfoDtoQuery selectUserInfoDto(long Id);

    // 用户登录
    public Integer userLoginAuth(Users user);

    // 查询用户数据(关注数,粉丝数,文章数)
    public UserData selectUserData(String userid);
}
