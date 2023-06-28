package com.example.boot_project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.boot_project.mapper.UsersMapper;
import com.example.boot_project.pojo.Users;
import com.example.boot_project.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UsersMapper, Users> implements UserService {
}
