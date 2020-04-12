package ljs.spring.service.impl;

import ljs.spring.entities.User;
import ljs.spring.mapper.sid.UserMapperSid;
import ljs.spring.mapper.test.UserMapperTest;
import ljs.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapperSid userMapperSid;

    @Autowired
    private UserMapperTest userMapperTest;


    @Override
    public int addUserSid(User user) {
        return userMapperSid.insertSelective(user);
    }

    @Override
    public int addUserTest(User user) {
        return userMapperTest.insertSelective(user);
    }
}
