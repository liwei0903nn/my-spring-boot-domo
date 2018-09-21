package com.leon.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leon.demo.dao.SysUserMapper;
import com.leon.demo.entity.SysUser;
import com.leon.demo.enums.ResultEnum;
import com.leon.demo.exception.CustomException;
import com.leon.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private SysUserMapper userMapper;

	@Override
	public List<SysUser> getAll() {
		return userMapper.selectByExample(null);
	}

	@Override
	public SysUser getById(Integer id) {
		if (id == -1) {
			throw new CustomException(ResultEnum.USER_NOT_EXIST);
		}

		return userMapper.selectByPrimaryKey(id);
	}

}
