package com.lx.app.service.impl;

import com.lx.app.mapper.UserMapper;
import com.lx.app.model.User;
import com.lx.app.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @Author: lx
 * @Date: 2019/9/3 0:01
 */
@Service
public class UserServiceImpl implements UserService {
    private UserMapper userMapper;

    @Override
    public User selectByNameAndPwd(User user) {
        return userMapper.selectByNameAndPwd(user);
    }

    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    public int update(User user) {
        return userMapper.update(user);
    }

    @Override
    public int selectIsName(User user) {
        return userMapper.selectIsName(user);
    }

    @Override
    public String selectPasswordByName(User user) {
        return userMapper.selectPasswordByName(user);
    }

    @Override
    public User selectByName(String userName) {
        return userMapper.selectByName(userName);
    }
}
