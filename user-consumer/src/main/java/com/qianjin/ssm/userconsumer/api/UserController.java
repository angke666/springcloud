package com.qianjin.ssm.userconsumer.api;

import com.qianjin.ssm.sccommon.common.R;
import com.qianjin.ssm.sccommon.entity.User;
import com.qianjin.ssm.userconsumer.feign.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public Map<String, Object> add(@RequestBody User user) {
        Map<String, Object> map = userService.add(user);
        return map;
    }

    @PutMapping("/updateUser")
    public Map<String, Object> update(@RequestBody User user) {
        Map<String, Object> map = userService.update(user);
        return map;
    }

    @GetMapping("/findUser")
    public R<Map<String, Object>> findUser(Integer id) {
        R<Map<String, Object>> r = userService.findUser(id);
        return r;
    }

    @GetMapping("/sendSMS")
    public Map<String, Object> send(String tel) {
        Map<String, Object> map = userService.sendCode(tel);
        return map;
    }

}
