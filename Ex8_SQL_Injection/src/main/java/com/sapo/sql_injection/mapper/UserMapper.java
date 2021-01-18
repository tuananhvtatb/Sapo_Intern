package com.sapo.sql_injection.mapper;

import com.sapo.sql_injection.dto.UserDTO;
import com.sapo.sql_injection.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class UserMapper {

    private final ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserDTO convertEntityToDTO(UserEntity userEntity){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper
                .map(userEntity, UserDTO.class);
    }

    public UserEntity convertDTOtoEntity(UserDTO userDTO){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper
                .map(userDTO, UserEntity.class);
    }
}
