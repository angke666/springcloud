package com.qianjin.ssm.userservice.api;

import com.netflix.discovery.converters.Auto;
import com.qianjin.ssm.sccommon.common.CommonConstants;
import com.qianjin.ssm.sccommon.common.R;
import com.qianjin.ssm.sccommon.entity.Role;
import com.qianjin.ssm.sccommon.entity.User;
import com.qianjin.ssm.userservice.config.RabbitMQConfig;
import com.qianjin.ssm.userservice.logic.component.DemoSender;
import com.qianjin.ssm.userservice.logic.service.IRoleService;
import com.qianjin.ssm.userservice.logic.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private DemoSender sender;

    @PostMapping
    public Map<String, Object> add(@RequestBody User user) {
        Map<String, Object> map = new HashMap<>();

        try {
            int ret = userService.save(user);
            if (ret == 1) {
                map.put("code", 200);
                map.put("msg", "操作成功");
            } else {
                map.put("code", 500);
                map.put("msg", "操作失败");
            }
        } catch (Exception e) {
            log.error("操作失败", e);
            map.put("code", 500);
            map.put("msg", "操作失败");
        }

        return map;
    }

    @PutMapping
    public Map<String, Object> update(@RequestBody User user) {
        Map<String, Object> map = new HashMap<>();

        try {
            int ret = userService.update(user);
            if (ret == 1) {
                map.put("code", 200);
                map.put("msg", "操作成功");
            } else {
                map.put("code", 500);
                map.put("msg", "操作失败");
            }
        } catch (Exception e) {
            log.error("操作失败", e);
            map.put("code", 500);
            map.put("msg", "操作失败");
        }

        return map;
    }

    @GetMapping("/findUser/{id}")
    public R<Map<String, Object>> findUser(@PathVariable Integer id) {
        try {
            User user = userService.getEntity(id);
            Role role = roleService.getEntity(user.getRoleId());

            Map<String, Object> map = new HashMap<>();
            map.put("user", user);
            map.put("role", role);

            return new R<>(CommonConstants.CODE_SUCCESS, "操作成功", map);
        } catch (Exception e) {
            log.error("操作失败", e);
            return null;
        }
    }

    @GetMapping("/sendCode/{mobile}")
    public Map<String, Object> sendCode(@PathVariable String mobile) {
        Map<String, Object> map = new HashMap<>();
        try {
            String msg = "【" + mobile + "】发送验证码：666666";
            rabbitTemplate.convertAndSend(RabbitMQConfig.TOPIC_EXCHANGE, "topic.a", msg);
//            rabbitTemplate.convertAndSend(RabbitMQConfig.TEST_QUEUE, msg);

            map.put("code", 200);
            map.put("msg", "发送成功");
        } catch (Exception e) {
            log.error("发送短信验证码失败", e);
            map.put("code", 500);
            map.put("msg", "发送失败");
        }
        return map;
    }

    @GetMapping("/login")
    public String login(HttpSession session) {
        session.setAttribute("name", "钱哥");
        String content = "这是一条mq消息 ";

        for (int i = 0; i < 10; i++) {
            sender.sendMsg(content + i);
        }
        return "ok";
    }

    @GetMapping("/getUser")
    public Map<String, Object> getUser(HttpSession session) {
        String name = (String) session.getAttribute("name");

        Map<String, Object> map = new HashMap<>();
        map.put("userName", name);
        map.put("sessionId", session.getId());
        return map;
    }

}
