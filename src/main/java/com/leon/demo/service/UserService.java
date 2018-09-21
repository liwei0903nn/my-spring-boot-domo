package com.leon.demo.service;

import java.util.List;

import com.leon.demo.entity.SysUser;

public interface UserService {

	List<SysUser> getAll();

	SysUser getById(Integer id);

}
