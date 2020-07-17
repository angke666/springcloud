package com.qianjin.ssm.userconsumer.feign.fallback;

import com.qianjin.ssm.sccommon.common.R;
import com.qianjin.ssm.sccommon.entity.User;
import com.qianjin.ssm.userconsumer.feign.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class UserServiceFallback implements UserService {

    @Override
    public Map<String, Object> add(User user) {
        log.error("调用{}异常", "add");
        return null;
    }

    @Override
    public Map<String, Object> update(User user) {
        log.error("调用{}异常", "update");
        return null;
    }

    @Override
    public R<Map<String, Object>> findUser(Integer id) {
        log.error("调用{}异常：{}", "findUser", id);
        return null;
    }

    @Override
    public Map<String, Object> sendCode(String mobile) {
        log.error("调用{}异常：{}", "sendCode", mobile);
        return null;
    }
}
