package com.example.boot_project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.boot_project.mapper.*;
import com.example.boot_project.pojo.*;
import com.example.boot_project.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UsersMapper, Users> implements UserService {
    @Autowired
    StudentInfoMapper studentInfoMapper;
    @Autowired
    StudentInfoDtoMapper studentInfoDtoMapper;
    @Autowired
    SchoolInfoMapper schoolInfoMapper;
    @Autowired
    DepartmentMapper departmentMapper;
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
    public Integer updateStudentInfo(UpdateStudentInfo upStudentInfo) {
        StudentInfo studentInfo = new StudentInfo();
        BeanUtils.copyProperties(upStudentInfo,studentInfo);
        long strNumber = studentInfo.getStrNumber();
        LambdaQueryWrapper<StudentInfo> queryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<StudentInfo> eq = queryWrapper.eq(StudentInfo::getStrNumber, strNumber);
        int update = studentInfoMapper.update(studentInfo, eq);
        return update;
    }

    @Override
    public ArrayList<SchoolInfo> selectSchoolInfo() {
        ArrayList<SchoolInfo> schoolInfos = (ArrayList<SchoolInfo>) schoolInfoMapper.selectList(null);
        return schoolInfos;
    }

    @Override
    public StudentInfo selectUserInfo(String userId) {//Todo 用户功能开发完成后改为联查
        LambdaQueryWrapper<StudentInfo> queryWrapper =new LambdaQueryWrapper<>();
        LambdaQueryWrapper<StudentInfo> eq = queryWrapper.eq(StudentInfo::getStrAttestId, userId);
        StudentInfo studentInfo = studentInfoMapper.selectOne(eq);
        return studentInfo;
    }

    @Override
    public List<Department> selectDepartment() {
        List<Department> departments = departmentMapper.selectList(null);
        return departments;
    }


}
