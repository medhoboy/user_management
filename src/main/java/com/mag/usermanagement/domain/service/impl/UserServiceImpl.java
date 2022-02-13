package com.mag.usermanagement.domain.service.impl;

import com.mag.usermanagement.data.model.User;
import com.mag.usermanagement.data.repository.UserRepository;
import com.mag.usermanagement.domain.dto.UserDto;
import com.mag.usermanagement.domain.dto.UserResponse;
import com.mag.usermanagement.domain.service.UserService;
import com.mag.usermanagement.domain.service.mapper.UserMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
          this.userRepository = userRepository;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        // convert DTO to entity
        User user = UserMapper.mapToEntity(userDto);
        User newUser = userRepository.save(user);

        // convert entity to DTO
        UserDto userResponse = UserMapper.mapToDTO(newUser);
        return userResponse;
    }

    @Override
    public UserResponse getAllUsers(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<User> users = userRepository.findAll(pageable);

        // get content for page object
        List<User> listOfUsers = users.getContent();

        List<UserDto> content= listOfUsers.stream().map(user -> UserMapper.mapToDTO(user)).collect(Collectors.toList());

        UserResponse userResponse = new UserResponse();
        userResponse.setContent(content);
        userResponse.setPageNo(users.getNumber());
        userResponse.setPageSize(users.getSize());
        userResponse.setTotalElements(users.getTotalElements());
        userResponse.setTotalPages(users.getTotalPages());
        userResponse.setLast(users.isLast());

        return userResponse;
    }


}