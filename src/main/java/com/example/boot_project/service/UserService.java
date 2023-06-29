package com.example.boot_project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.boot_project.pojo.StudentInfo;
import com.example.boot_project.pojo.StudentInfoDto;
import com.example.boot_project.pojo.UserInfoDto;
import com.example.boot_project.pojo.Users;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserService extends IService<Users> {
    public StudentInfoDto selectStudentInfoDto(Long strNumber);
    public Integer insertStudentInfo(StudentInfoDto studentInfoDto);
    public StudentInfo selectStudentInfo(StudentInfoDto studentInfoDto);
    public UserInfoDto selectUserInfoDto(long Id);

    public Users selectUserInfo(String userId);
    public Integer userLoginAuth(Users user);
}
