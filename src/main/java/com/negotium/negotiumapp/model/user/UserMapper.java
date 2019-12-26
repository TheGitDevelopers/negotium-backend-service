package com.negotium.negotiumapp.model.user;

public class UserMapper {

    public static UserDto toDto(User user){
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setEmail(user.getEmail());
        dto.setRoles(user.getRoles());
        return dto;
    }

    public static User toEntity(UserDto user){
        User entity = new User();
        entity.setId(user.getId());
        entity.setUsername(user.getUsername());
        entity.setPassword(user.getPassword());
        entity.setEmail(user.getEmail());
        entity.setRoles(user.getRoles());
        return entity;
    }
}
