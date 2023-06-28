package com.example.boot_project.controller;

import com.example.boot_project.pojo.StudentInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Slf4j
@RestController
@Api(value = "个人中心模块接口",tags = "个人中心模块接口")
@RequestMapping("/ym_server/user")
public class UserController {

    @ApiOperation("学生认证接口")
    @PostMapping("/strInfo")
    public void info(@RequestBody StudentInfo studentInfo){

    }
}
