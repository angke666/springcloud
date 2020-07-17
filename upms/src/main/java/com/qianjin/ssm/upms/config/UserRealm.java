package com.qianjin.ssm.upms.config;

import com.qianjin.ssm.sccommon.entity.User;
import com.qianjin.ssm.upms.logic.service.IUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 授权认证
 */
public class UserRealm extends AuthorizingRealm {

    private static final ByteSource salt = ByteSource.Util.bytes("qianjin");// 加盐凭证

    @Autowired
    private IUserService userService;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Integer userId = (Integer) principalCollection.getPrimaryPrincipal();
        User user = userService.getEntity(userId);

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo ();
        authorizationInfo.addRole("administrator");
        authorizationInfo.addStringPermission("user:add");
        authorizationInfo.addStringPermission("user:edit");
        return authorizationInfo;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
        String username = upToken.getUsername();

        // 查询用户信息
        User user = userService.findByName(username);
        // 用户不存在
        if (user == null) {
            throw new UnknownAccountException("用户不存在!");
        }

        return new SimpleAuthenticationInfo(user.getId(), user.getPassword(), salt, getName());
    }

    public static void main(String[] args) {
        SimpleHash simpleHash = new SimpleHash("md5", "123456", salt, 100);
        System.out.println(simpleHash.toString());
    }
}
