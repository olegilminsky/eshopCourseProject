package com.olegilminsky.service;

import com.olegilminsky.controller.UserDto;
import com.olegilminsky.controller.UserListParams;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDto> findAll();

    Page<UserDto> findWithFilter(UserListParams userListParams);

    Optional<UserDto> findById(Long id);

    void save(UserDto user);

    void deleteById(Long id);

}
