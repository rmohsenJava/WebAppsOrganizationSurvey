package com.blackstone.webappsorganizationsurvey.service;

import com.blackstone.webappsorganizationsurvey.dto.UserRequest;
import com.blackstone.webappsorganizationsurvey.dto.UserResponse;
import com.blackstone.webappsorganizationsurvey.entity.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface IUserService {

    User getUserByUserName(String userName) throws UsernameNotFoundException;

    UserResponse registerUser(UserRequest userRequest);
}
