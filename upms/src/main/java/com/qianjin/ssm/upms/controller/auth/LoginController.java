package com.qianjin.ssm.upms.controller.auth;

import com.mysql.cj.exceptions.PasswordExpiredException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class LoginController {

    @GetMapping("login")
    public String login() {
        //如果已经认证通过，直接跳转到首页
        if (SecurityUtils.getSubject().isAuthenticated()) {
            return "index";
        }
        return "login";
    }

    @PostMapping("/login")
    public String login(Model model, String name, String password, Boolean rememberMe) {
        // 通过shiro获取当前用户
        Subject currentUser = SecurityUtils.getSubject();
        // 判断是否登录
        if (!currentUser.isAuthenticated()) {
            // 把搜集到用户名和密码创建为UsernamePasswordToken对象
            UsernamePasswordToken token = new UsernamePasswordToken(name, password);
            // 记住账号密码
            token.setRememberMe(rememberMe);
            try {
                // 登录
                currentUser.login(token);
            } catch (UnknownAccountException e) {
                log.error("账号不存在", e);
                model.addAttribute("msg", "账号不存在");
                return "login";
            } catch (IncorrectCredentialsException e) {
                log.error("密码错误", e);
                model.addAttribute("msg", "密码错误");
                return "login";
            } catch (AuthenticationException e) {
                e.printStackTrace();
                model.addAttribute("msg", "未知错误");
                return "login";
            }
        }

        model.addAttribute("name", "钱进");
        model.addAttribute("id", currentUser.getPrincipal());
        return "index";
    }

}
