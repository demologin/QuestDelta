package ua.com.javarush.quest.gribanov.questdelta.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ua.com.javarush.quest.gribanov.questdelta.entity.GameState;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
public class GameCreateDTO {
    ZonedDateTime startingDate;
    Long userID;
    Long currentQuestionID;
    Long questID;
    GameState state;
}
