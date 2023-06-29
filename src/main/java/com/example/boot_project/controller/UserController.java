package com.example.boot_project.controller;

import com.example.boot_project.pojo.StudentInfo;
import com.example.boot_project.pojo.StudentInfoDto;
import com.example.boot_project.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

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
        }
        if (integer!=0){
            StudentInfo studentInfo = userservice.selectStudentInfo(studentInfoDto1);
            return studentInfo;
        }else {
         return null;   //Todo 后续改为抛出添加错误
        }
    }
}
