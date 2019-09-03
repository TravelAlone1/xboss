package com.lx.app.shiro;

        import com.lx.app.mapper.MenuMapper;
        import com.lx.app.mapper.UserMapper;
        import com.lx.app.model.User;
        import org.apache.shiro.authc.*;
        import org.apache.shiro.authz.AuthorizationInfo;
        import org.apache.shiro.authz.SimpleAuthorizationInfo;
        import org.apache.shiro.realm.AuthorizingRealm;
        import org.apache.shiro.subject.PrincipalCollection;
        import org.apache.shiro.util.ByteSource;
        import org.springframework.beans.factory.annotation.Autowired;

        import java.util.Set;

/**
 * @Author: lx
 * @Date: 2019/9/2 20:18
 */
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuMapper menuMapper;




    /**
     *  定义如何获取用户的角色和权限的逻辑，给shiro做权限判断
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("权限配置");

        User userInfo = (User) principals.getPrimaryPrincipal();
        Set<String> perms;
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        if (userInfo.getIsSystem()==1){
            perms=menuMapper.getALLMenuCode();
            System.out.println("获取用户所有权限");
        }else {
            perms=menuMapper.findMenuCodeByUserId(String.valueOf(userInfo.getId()));
            System.out.println("获取某个用户的权限");
        }
        simpleAuthorizationInfo.setStringPermissions(perms);
        //获取当前用户的角色与权限，让simpleAuthorizationInfo去验证
//        for (SysRole sysRole : userInfo.getRoleList()) {
//            simpleAuthorizationInfo.addRole(sysRole.getRoleName());
//            for (SysPerm sysPermission : sysRole.getSysPermList()) {
//                simpleAuthorizationInfo.addStringPermission(sysPermission.getPermName());
//            }
//        }
        return simpleAuthorizationInfo;
    }

    /**
     * 定义如何获取用户信息的业务逻辑，给shiro做登录
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("用户登录");
        String username = (String) token.getPrincipal();
        //String password = new String((char[]) token.getCredentials());
        User user = userMapper.selectByName(username);
        if (user == null) {
            throw new UnknownAccountException("用户名或密码错误！");
        }
        System.out.println(user.getState());
        String lock="0";
        if (lock.equals(String.valueOf(user.getState()))){
            throw new LockedAccountException();
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(user.getCredentialsSalt()),user.getUserName());
        System.out.println("输入的密码"+user.getPassword());
        System.out.println("数据库中项目"+ByteSource.Util.bytes(user.getCredentialsSalt()));
        return info;
    }
}
