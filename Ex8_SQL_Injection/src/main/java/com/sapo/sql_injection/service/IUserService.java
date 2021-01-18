package com.sapo.sql_injection.service;

import com.sapo.sql_injection.dto.UserDTO;

public interface IUserService {
    UserDTO findUserByUsername(String username);
}
