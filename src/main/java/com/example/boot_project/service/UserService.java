package com.example.boot_project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.boot_project.pojo.*;

import java.util.List;

public interface UserService extends IService<Users> {
    StudentInfoDto selectStudentInfoDto(Long strNumber);

    Integer insertStudentInfo(StudentInfoDto studentInfoDto);

    StudentInfo selectStudentInfo(StudentInfoDto studentInfoDto);

    List<SchoolInfo> selectSchool();

    List<Department> selectDepartment();

    Integer updateStudentInfo(UpdateStudentInfo updateStudentInfo);

    // 查询用户个人信息
    StudentInfo selectUserInfo(String userId);

    UserInfoDtoQuery selectUserInfoDto(long Id);

    // 用户登录
    Integer userLoginAuth(Users user);

    // 查询用户数据 (关注数, 粉丝数, 文章数)
    UserData selectUserData(String userid);

    // 用户关注
    void UserFollow(String userId);
    // 取消关注
    void UserUnfollow(String userId);
}
