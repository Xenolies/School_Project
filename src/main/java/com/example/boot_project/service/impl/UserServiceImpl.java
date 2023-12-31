package com.example.boot_project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.boot_project.helper.helper;
import com.example.boot_project.mapper.*;
import com.example.boot_project.pojo.*;
import com.example.boot_project.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UsersMapper, Users> implements UserService {
    @Autowired
    StudentInfoMapper studentInfoMapper;
    @Autowired
    StudentInfoDtoMapper studentInfoDtoMapper;
    @Autowired
    DepartmentMapper departmentMapper;
    @Autowired
    SchoolInfoMapper schoolInfoMapper;

    @Resource
    UsersMapper usersMapper;

    @Resource
    UserInfoDtoMapper userInfoDtoMapper;

    @Resource
    UserDataDotMapper userDataDotMapper;

    @Resource
    ArticleUserMapper articleUserMapper;

    @Override
    public StudentInfoDto selectStudentInfoDto(Long strNumber) {
        LambdaQueryWrapper<StudentInfoDto> wrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<StudentInfoDto> eq1 = wrapper.eq(StudentInfoDto::getStrNumber, strNumber);
        StudentInfoDto studentInfoDto = studentInfoDtoMapper.selectOne(eq1);
        if (studentInfoDto==null){
            return null; //Todo 后续改为学号错误或学生信息不存在
        }
        String department = studentInfoDto.getDepartment();
        String school = studentInfoDto.getSchool();
        String strName = studentInfoDto.getStrName();
        LambdaQueryWrapper<StudentInfoDto> queryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<StudentInfoDto> eq = queryWrapper.eq(StudentInfoDto::getSchool, school)
                .eq(StudentInfoDto::getDepartment, department)
                .eq(StudentInfoDto::getStrName, strName);
        if (eq==null){
           return  null;//Todo 后续改为抛出认证信息错误异常
        }
        return studentInfoDto;
    }

    @Override
    @Transactional
    public Integer insertStudentInfo(StudentInfoDto studentInfoDto) {
        //没法直接添加所以考虑使用BeanUtils的copy
        //1.生成StudentInfo对象
        StudentInfo studentInfo = new StudentInfo();
        BeanUtils.copyProperties(studentInfoDto,studentInfo);
        int insert = studentInfoMapper.insert(studentInfo);
        return insert;
    }

    @Override
    public StudentInfo selectStudentInfo(StudentInfoDto studentInfoDto) {
        long strNumber = studentInfoDto.getStrNumber();
        LambdaQueryWrapper<StudentInfo> studentInfoLambdaQueryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<StudentInfo> eq = studentInfoLambdaQueryWrapper.eq(StudentInfo::getStrNumber, strNumber);
            StudentInfo studentInfo = studentInfoMapper.selectOne(eq);
            return studentInfo;
    }

    @Override
    public List<SchoolInfo> selectSchool() {
        List<SchoolInfo> schoolInfos = schoolInfoMapper.selectList(null);
        return schoolInfos;
    }

    @Override
    public List<Department> selectDepartment() {
        List<Department> departments = departmentMapper.selectList(null);
        return departments;
    }

    @Override
    public Integer updateStudentInfo(UpdateStudentInfo updateStudentInfo) {

        long strNumber = updateStudentInfo.getStrNumber();

        LambdaQueryWrapper<StudentInfo> queryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<StudentInfo> eq = queryWrapper.eq(StudentInfo::getStrNumber, strNumber);
        StudentInfo studentInfo = studentInfoMapper.selectOne(eq);
        BeanUtils.copyProperties(updateStudentInfo,studentInfo);
        int update = studentInfoMapper.update(studentInfo,eq);
        return update;
    }

    @Override
    public StudentInfo selectUserInfo(String userId) {
        LambdaQueryWrapper<StudentInfo> queryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<StudentInfo> eq = queryWrapper.eq(StudentInfo::getStrAttestId, userId);
        StudentInfo studentInfo = studentInfoMapper.selectOne(eq);
        return studentInfo;
    }

    @Override
    // selectUserInfo 查询用户个人信息
    public UserInfoDtoQuery selectUserInfoDto(long Id) {
        // 创建查询的 LambdaQueryWrapper 对象
        LambdaQueryWrapper<UserInfoDto> usersLambdaQueryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<UserInfoDto> eq = usersLambdaQueryWrapper.eq(UserInfoDto::getId, Id);
        UserInfoDtoQuery userInfoDtoQuery = new UserInfoDtoQuery();
        // 将 UserInfoDto 中的复制到 UserInfoDtoQuery
        BeanUtils.copyProperties(userInfoDtoMapper.selectOne(eq), userInfoDtoQuery);
        // 添加 UserInfoDtoQuery 中的 QueryId
        userInfoDtoQuery.setQueryId(Id);
        if (eq == null) {
            return null; //TODO: 这里需要改为抛出异常用来提醒数据库中没有改用户 ID
        } else {
            // 查询结构不为空则返回 Users 对象
            return userInfoDtoQuery;
        }
    }


    @Override
    // 用户登录
    @Transactional
    public Integer userLoginAuth(Users user) {
        String uuid = helper.GenerateUUID();
        user.setUserId(uuid);
        return usersMapper.insert(user);
    }

    @Override
    public User Login(User user) {
        User user1 = new User();
        String userId = user.getUserId();
        LambdaQueryWrapper<Users> queryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<Users> eq = queryWrapper.eq(Users::getUserId, userId);
        Users users1 = usersMapper.selectOne(eq);
        if (users1!=null){
            user.setUserId(users1.getUserId());
            user.setUserName(users1.getUserName());
            user.setId(users1.getId());
            return user;
        }else {
            return null; //todo 后续改为无此用户异常
        }
    }

    @Override
    public UserData selectUserData(String userId) {
        // 创建查询的 LambdaQueryWrapper 对象
        // 查询出来粉丝数和关注数
        LambdaQueryWrapper<Users> usersLambdaQueryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<Users> eq = usersLambdaQueryWrapper.eq(Users::getUserId, userId);
        UserDataDto userDataDto = new UserDataDto();
        // 将用户表中查询到的关注数和粉丝数 BeanUtils.copyProperties 到 UserDataDto
        BeanUtils.copyProperties(usersMapper.selectOne(eq),userDataDto );
//        BeanUtils.copyProperties(usersMapper.selectOne(eq), userDataDto);
        // 查询计算文章数量
        LambdaQueryWrapper<ArticleUser> articleUserLambdaQueryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<ArticleUser> eq1 = articleUserLambdaQueryWrapper.eq(ArticleUser::getUserId, userId);
        Integer ArticleNum = articleUserMapper.selectCount(eq1);  // 文章数量
        // 整合
        UserData userData = new UserData();
        BeanUtils.copyProperties(userDataDto, userData);
        userData.setArticleNum(ArticleNum);
        return userData;
    }

    @Override
    @Transactional  // 开启事务
    //TODO: 编写用户关注
    public void UserFollow(String userId) {

    }

    @Override
    @Transactional  // 开启事务
    //TODO: 编写用户取关
    public void UserUnfollow(String userId) {

    }

}
