package ljs.spring.controller;

import ljs.spring.entities.User;
import ljs.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/addUserTest")
    public String addUserTest(){
        User user = new User();
        user.setName("ljs");
        user.setAge(18);
        int i = userService.addUserTest(user);
        if(i > 0){
            return "success";
        }
        return "false";
    }
    @RequestMapping("/addUserSid")
    public String addUserSid(){
        User user = new User();
        user.setName("jack");
        user.setAge(18);
        int i = userService.addUserSid(user);
        if(i > 0){
            return "success";
        }
        return "false";
    }

}
