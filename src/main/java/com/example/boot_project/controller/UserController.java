package com.example.boot_project.controller;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.boot_project.helper.JWTUtils;
import com.example.boot_project.helper.helper;
import com.example.boot_project.pojo.*;
import com.example.boot_project.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @ApiOperation(" 查询用户个人信息 ")
    @GetMapping("/personalInformation/{Id}")
    //TODO: 查询用户详情包括信息和数据, 需要完成
    public UserInfoDtoQuery GetPersonalInformation(@PathVariable long Id) {
        UserInfoDtoQuery userInfoDtoQuery = userservice.selectUserInfoDto(Id);
        // 设置响应信息
        return userInfoDtoQuery;
    }

    @ApiOperation(" 查询用户个人数据(关注数,粉丝数,文章数) ")
    @GetMapping("/num/{UserId}")
    //TODO: 获取用户个人数据
    public UserData GetUserNum(@PathVariable String UserId) {
        return userservice.selectUserData(UserId);
    }

    @ApiOperation(" 关注用户接口 ")
    @PostMapping("/fans/addFollow/")
    //TODO: 关注取关用户, 需要完成
    public void FansFollow(@RequestBody Map<String,String> params) {
        String followUserId = params.get("followUserId"); // 欲关注的UserId
        userservice.UserFollow(followUserId);

    }

    @ApiOperation(" 取关用户接口 ")
    @PostMapping("/fans/Unfollow/")
    //TODO: 关注取关用户, 需要完成
    public void FansUnfollow(@RequestBody Map<String,String> params) {
        String unfollowUserId = params.get("UnfollowUserId"); // 欲关注的UserId
        userservice.UserUnfollow(unfollowUserId);

    }



    @ApiOperation(" 用户登录 ")
    @PostMapping("/account/auth")
    //TODO: 用户登录, 需要完成
    public UserInfoDto UserLogin(@RequestBody Users users, HttpServletRequest request) {
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
            return userservice.selectUserInfoDto(users.getId());
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

    @ApiOperation("token接口")
    @GetMapping("/login")
    public Map<String,Object> login(User user){
        Map<String,Object> map = new HashMap<>();
        try {
            User login = userservice.Login(user);
            Map<String, String> payload = new HashMap<>();
            payload.put("userid",login.getUserId());
            payload.put("name",login.getUserName());
            payload.put("id",login.getId().toString());
            String token = JWTUtils.getToken(payload);
            map.put("state",true);
            map.put("msg","认证成功！");
            map.put("token",token);  // 响应token
        }catch (Exception e){
            map.put("state",false);
            map.put("msg",e.getMessage());
        }
        return map;
    }

    @ApiOperation("token解析接口")
    @PostMapping("/parse")
  public Map<String,Object> parse(HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        // 验证令牌  交给拦截器去做
        // 只需要在这里处理自己的业务逻辑
        String token = request.getHeader("token");
        DecodedJWT verify = JWTUtils.verify(token);
        log.info("用户id：[{}]",verify.getClaim("id").asString());
        log.info("用户userid：[{}]",verify.getClaim("userid").asString());
        log.info("用户name：[{}]",verify.getClaim("name").asString());
        map.put("look",verify.getClaim("name"));
        map.put("state", true);
        map.put("msg", "请求成功");
        return map;
    }
}
