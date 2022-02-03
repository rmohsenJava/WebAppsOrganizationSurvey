package com.blackstone.webappsorganizationsurvey.service;


import com.blackstone.webappsorganizationsurvey.dto.UserRequest;
import com.blackstone.webappsorganizationsurvey.dto.UserResponse;
import com.blackstone.webappsorganizationsurvey.entity.User;
import com.blackstone.webappsorganizationsurvey.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public User getUserByUserName(String userName) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(userName)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Fount !"));
    }

    @Override
    public UserResponse registerUser(UserRequest userRequest) {

        User user = this.userRepository.save(User
                .builder().email(userRequest.getEmail())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .username(userRequest.getUsername()).build());

        return UserResponse.builder().registeredAt(user.getRegisteredAt())
                .email(user.getEmail())
                .username(user.getUsername())
                .build();

    }
}
