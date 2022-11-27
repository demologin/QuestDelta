package ua.com.javarush.quest.gribanov.questdelta.dto;

import lombok.Builder;
import lombok.Data;
import ua.com.javarush.quest.gribanov.questdelta.entity.Role;


@Data
@Builder
public class UserDTO {
    Long id;
    String name;
    String login;
    Role role;
    String avatar;
}
