package com.qianjin.ssm.userconsumer.feign;

import com.qianjin.ssm.sccommon.common.R;
import com.qianjin.ssm.sccommon.entity.User;
import com.qianjin.ssm.userconsumer.feign.fallback.UserServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name = "user-service", fallback = UserServiceFallback.class)
public interface UserService {

    @PostMapping("/user")
    Map<String, Object> add(@RequestBody User user);

    @PutMapping("/user")
    Map<String, Object> update(@RequestBody User user);

    @GetMapping("/user/findUser/{id}")
    R<Map<String, Object>> findUser(@PathVariable("id") Integer id);

    @GetMapping("/user/sendCode/{mobile}")
    Map<String, Object> sendCode(@PathVariable("mobile") String mobile);

}
