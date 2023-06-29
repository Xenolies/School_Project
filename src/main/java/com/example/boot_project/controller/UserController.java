package com.example.boot_project.controller;

import com.example.boot_project.pojo.*;
import com.example.boot_project.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@Slf4j
@RestController
@Api(value = "个人中心模块接口",tags = "个人中心模块接口")
@RequestMapping("/ym_server/user")
public class UserController {
    @Autowired
    UserService userservice;
    @ApiOperation("学生认证接口")
    @GetMapping("/strInfo/{strNumber}")
    public StudentInfo ControllerInfo(@PathVariable Long strNumber){
        Integer integer=0;
        StudentInfoDto studentInfoDto1 = userservice.selectStudentInfoDto(strNumber);
        StudentInfo studentInfo1 = userservice.selectStudentInfo(studentInfoDto1);
        if (studentInfo1==null){
            integer = userservice.insertStudentInfo(studentInfoDto1);
            if (integer!=0){
                StudentInfo studentInfo = userservice.selectStudentInfo(studentInfoDto1);
                return studentInfo;
            }
        }
        if (studentInfo1!=null){
            return studentInfo1;
        }else {
         return null;   //Todo 后续改为抛出添加错误
        }
    }

    @ApiOperation("查询学校接口")
    @GetMapping("/schoolInfo")
    public List<SchoolInfo> schoolInfoList(){
        List<SchoolInfo> schoolInfos = userservice.selectSchool();
        return schoolInfos;
    }
    @ApiOperation("查询系部接口")
    @GetMapping("/department")
    public List<Department> departmentList(){
        List<Department> departments = userservice.selectDepartment();
        return departments;
    }

    @ApiOperation("查询用户是否认证接口")
    @GetMapping("/auth/{userId}")
    public void selectUserInfo(@PathVariable String userId){
        StudentInfo studentInfo = userservice.selectUserInfo(userId);
        if (studentInfo==null){
            System.out.println("用户未认证"); //TODO 后续改为抛出用户未认证异常
        }
    }
    @ApiOperation("修改用户认证信息接口")
    @PutMapping("/strInfo")
    public void updateUserInfo(@RequestBody UpdateStudentInfo updateStudentInfo){
        Integer integer = userservice.updateStudentInfo(updateStudentInfo);
        if (integer==0){
            System.out.println("用户认证信息修改失败");
        }
    }
}
