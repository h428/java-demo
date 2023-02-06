package com.hao.demo.shiro.service;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hao.demo.shiro.entity.SysUser;
import com.hao.demo.shiro.mapper.SysUserMapper;
import com.hao.demo.shiro.pojo.dto.SysUserLoginDTO;
import com.hao.demo.shiro.pojo.vo.SysUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    public SysUserVO loginByUsername(SysUserLoginDTO sysUserLoginDTO) {

        final LambdaQueryWrapper<SysUser> wrapper = Wrappers.<SysUser>lambdaQuery()
            .eq(SysUser::getUsername, sysUserLoginDTO.getUsername())
            .eq(SysUser::getPassword, sysUserLoginDTO.getPassword());

        final SysUser sysUser = sysUserMapper.selectOne(wrapper);

        if (sysUser == null) {
            return null;
        }

        final SysUserVO sysUserVO = new SysUserVO();
        BeanUtil.copyProperties(sysUser, sysUserVO);

        return sysUserVO;
    }
}
