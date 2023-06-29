package com.example.boot_project.controller;

import com.example.boot_project.helper.helper;
import com.example.boot_project.pojo.StudentInfo;
import com.example.boot_project.pojo.StudentInfoDto;
import com.example.boot_project.pojo.UserInfoDto;
import com.example.boot_project.pojo.Users;
import com.example.boot_project.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@Api(value = " 个人中心模块接口 ", tags = " 个人中心模块接口 ")
@RequestMapping("/ym_server/user")
public class UserController {
    @Autowired
    UserService userservice;

    @ApiOperation(" 学生认证接口 ")
    @GetMapping("/strInfo/{strNumber}")
    public StudentInfo ControllerInfo(@PathVariable Long strNumber) {
        Integer integer = 0;
        StudentInfoDto studentInfoDto1 = userservice.selectStudentInfoDto(strNumber);
        StudentInfo studentInfo1 = userservice.selectStudentInfo(studentInfoDto1);
        if (studentInfo1 == null) {
            integer = userservice.insertStudentInfo(studentInfoDto1);
            if (integer != 0) {
                StudentInfo studentInfo = userservice.selectStudentInfo(studentInfoDto1);
                return studentInfo;
            }
        }
        if (studentInfo1 != null) {
            return studentInfo1;
        } else {
            return null;   //Todo 后续改为抛出添加错误
        }
    }

    @ApiOperation(" 查询用户个人信息 ")
    @GetMapping("/personalInformation/{Id}")
    //TODO: 查询用户详情包括信息和数据, 需要完成
    public UserInfoDto GetPersonalInformation(@PathVariable Long Id) {
        return userservice.selectUserInfoDto(Id);
    }

    @ApiOperation(" 查询用户个人数据 ")
    @GetMapping("/num/{Id}")
    //TODO: 获取用户个人数据
    public void GetUserNum(@PathVariable Long UserId) {

    }

    @ApiOperation(" 关注取关用户 ")
    @PostMapping("/fans/addFollow")
    //TODO: 关注取关用户, 需要完成
    public void FansFollow() {

    }

//    type Demo struct {
//        Success bool   `json:"success"`
//        Code    int    `json:"code"`
//        Message string `json:"message"`
//        Data    struct {
//            User struct {
//                UserId      string      `json:"userId"`
//                Id          int         `json:"id"`
//                UserName    string      `json:"userName"`
//                ClassId     interface{} `json:"classId"`
//                PhoneNumber string      `json:"phoneNumber"`
//                FansNum     int         `json:"fansNum"`
//                Sex         string      `json:"sex"`
//                IsDeleted   string      `json:"isDeleted"`
//                Avatar      interface{} `json:"avatar"`
//                Signature   string      `json:"signature"`
//                CreateTime  string      `json:"createTime"`
//                UpdateTime  string      `json:"updateTime"`
//                Token       string      `json:"token"`
//                Badge       interface{} `json:"badge"`
//            } `json:"user"`
//        } `json:"data"`
//    }


    @ApiOperation(" 用户登录 ")
    @PostMapping("/account/auth")
    //TODO: 用户登录, 需要完成
    public Users UserLogin(@RequestBody Users users, HttpServletRequest request) {
        // 这里按照需求需要获取到用户全部数据,这里做个示范,就获取了几个数据
//        Users users = new Users();
//        long id = Long.parseLong(params.get("id"));
//        String userName = params.get("userName");
//        String captcha = params.get("captcha");
//        users.setId(id);
//        users.setUserName(userName);
//        HttpSession session = request.getSession();
//        if (session.getAttribute("captcha").toString().equals(captcha)) {
            // 验证码正确, 接着查用户
            // 查询用户是否存在,不存在直接抛出
            Integer integer = userservice.userLoginAuth(users);
            if (integer > 0) {
                return userservice.selectUserInfo(users.getUserId());
            } else {
                return null;//Todo 后续改为抛出添加错误
            }
//        } else {
//            return null; //Todo 后续改为抛出添加错误
//        }
    }

    @ApiOperation(" 获取验证码 ")
    @GetMapping("/Yzm/code")
    public String GetYzmCode(HttpServletRequest request) {
        String code = helper.RandCode();// 获取随机生成的验证码
        HttpSession session = request.getSession();// 创建 Session
        session.setAttribute("code", code);  // 将生成的验证码存入 Session 中
        return code;
    }

    @ApiOperation(" 模糊查询自己的粉丝和关注 ")
    @GetMapping("/fans/query/{UserId}")
    public void FansSearch(@PathVariable Long UserId) {

    }
}
