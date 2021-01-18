package com.sapo.sql_injection.service.impl;

import com.sapo.sql_injection.dto.UserDTO;
import com.sapo.sql_injection.entity.UserEntity;
import com.sapo.sql_injection.mapper.UserMapper;
import com.sapo.sql_injection.repository.UserRepository;
import com.sapo.sql_injection.service.IGenericService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Component
public class UserService implements IGenericService<UserDTO> {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDTO> getAllList() {
        List<UserDTO> listUserDTO = new ArrayList<>();
        for (UserEntity ue : userRepository.findAll()) {
            listUserDTO.add(userMapper.convertEntityToDTO(ue));
        }
        return listUserDTO;
    }

    @Override
    public UserDTO findById(Integer id) {
        return null;
    }

    @Override
    public void save(UserDTO userDTO) {

    }

    @Override
    public List<UserDTO> paginationAndSort(int page) {
        return null;
    }
}
