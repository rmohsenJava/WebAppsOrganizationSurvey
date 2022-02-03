package com.blackstone.webappsorganizationsurvey.config;

import com.blackstone.webappsorganizationsurvey.entity.User;
import com.blackstone.webappsorganizationsurvey.security.MyUserPrincipal;
import com.blackstone.webappsorganizationsurvey.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private IUserService userService;

    @Autowired
    public void setUserService(@Lazy IUserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = this.userService.getUserByUserName(userName);
        return new MyUserPrincipal(user);
    }
}
