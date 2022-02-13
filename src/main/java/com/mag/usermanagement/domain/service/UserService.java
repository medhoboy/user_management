package com.mag.usermanagement.domain.service;

import com.mag.usermanagement.domain.dto.UserDto;
import com.mag.usermanagement.domain.dto.UserResponse;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserResponse getAllUsers(int pageNo, int pageSize, String sortBy, String sortDir);
}
