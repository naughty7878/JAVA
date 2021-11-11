package com.test.springsecurity.service;

import com.test.springsecurity.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserDao userDao;

    @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 调用 userDao查询数据库
        com.test.springsecurity.entity.User user = userDao.getByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在!");
        }

        List<GrantedAuthority> auths =
                AuthorityUtils.commaSeparatedStringToAuthorityList("role");
        if (user.getName().equals("zzz")) {
            // 添加test角色，权限
//            auths = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_test");
            auths = AuthorityUtils.commaSeparatedStringToAuthorityList("test,ROLE_test");
        }

        return new User(user.getName(), passwordEncoder.encode(user.getPassword()), auths);
    }
}
