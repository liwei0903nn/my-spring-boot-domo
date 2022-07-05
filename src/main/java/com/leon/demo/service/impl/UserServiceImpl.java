package com.leon.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leon.demo.dao.SysUserMapper;
import com.leon.demo.entity.SysUser;
import com.leon.demo.enums.ResultEnum;
import com.leon.demo.exception.CustomException;
import com.leon.demo.service.UserService;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private SysUserMapper userMapper;

    @Override
    public List<SysUser> getAll() {
        log.info("没有连接数据库");

        return getALLNODB();
        //return userMapper.selectByExample(null); //查询数据库
    }


    /**
     * 模拟数据查询的数据
     *
     * @return
     */
    private List<SysUser> getALLNODB() {
        List<SysUser> list = new ArrayList<>();
        SysUser user1 = new SysUser();
        user1.setId(1);
        user1.setUsername("test1");
        user1.setPassword("123456");
        list.add(user1);

        SysUser user2 = new SysUser();
        user2.setId(2);
        user2.setUsername("test2");
        user2.setPassword("123456");
        list.add(user2);
        SysUser user3 = new SysUser();
        user3.setId(3);
        user3.setUsername("test3");
        user3.setPassword("123456");
        list.add(user3);
        SysUser user4 = new SysUser();
        user4.setId(4);
        user4.setUsername("test1");
        user4.setPassword("123456");
        list.add(user4);
        SysUser user5 = new SysUser();
        user5.setId(5);
        user5.setUsername("test5");
        user5.setPassword("123456");
        list.add(user5);
        return list;
    }


    @Override
    public SysUser getById(Integer id) {
        if (id == -1) {
            throw new CustomException(ResultEnum.USER_NOT_EXIST);
        }
        return userMapper.selectByPrimaryKey(id);
    }

}
