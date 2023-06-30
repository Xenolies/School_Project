package com.example.boot_project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.boot_project.pojo.UserDataDto;
import com.example.boot_project.pojo.UserInfoDto;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: Xenolies
 * @Date: 2023/6/30 10:58
 * @Version: 0.1
 */
@Mapper
public interface UserDataDotMapper extends BaseMapper<UserDataDto> {
}
