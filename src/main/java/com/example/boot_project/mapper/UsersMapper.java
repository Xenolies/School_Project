package com.example.boot_project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.boot_project.pojo.Users;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UsersMapper extends BaseMapper<Users> {
}
