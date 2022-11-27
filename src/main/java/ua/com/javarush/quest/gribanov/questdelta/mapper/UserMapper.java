package ua.com.javarush.quest.gribanov.questdelta.mapper;

import ua.com.javarush.quest.gribanov.questdelta.dto.UserDTO;
import ua.com.javarush.quest.gribanov.questdelta.entity.User;

import java.util.Optional;

public class UserMapper implements Mapper<User, UserDTO> {
    @Override
    public Optional<UserDTO> getDTO(User user) {
        return user != null ? Optional.of(UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .login(user.getLogin())
                .role(user.getRole())
                .avatar(user.getAvatar())
                .build())
                : Optional.empty();
    }
}
