package com.lx.app.service;

import com.lx.app.model.User;
import org.springframework.stereotype.Service;

/**
 * @Author: lx
 * @Date: 2019/9/3 8:43
 */

public interface UserService {

    User selectByNameAndPwd(User user);

    int insert(User user);

    int update(User user);

    int selectIsName(User user);

    String selectPasswordByName(User user);

    User selectByName(String userName);
}
