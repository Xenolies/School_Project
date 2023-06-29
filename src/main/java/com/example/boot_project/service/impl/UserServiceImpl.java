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
    public UserInfoDto selectUserInfoDto(long Id) {
        // 创建查询的 LambdaQueryWrapper 对象
        LambdaQueryWrapper<UserInfoDto> usersLambdaQueryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<UserInfoDto> eq = usersLambdaQueryWrapper.eq(UserInfoDto::getId, Id);
        // TODO: 这里需要修改成专美查询 UserInfoDtoQuery 的
//        BeanUtils.copyProperties(UserInfoDtoQuery,UserInfoDto);
        if (eq == null) {
            return null; //TODO: 这里需要改为抛出异常用来提醒数据库中没有改用户 ID
        } else {
            // 查询结构不为空则返回 Users 对象
            return userInfoDtoMapper.selectOne(eq);
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


}
