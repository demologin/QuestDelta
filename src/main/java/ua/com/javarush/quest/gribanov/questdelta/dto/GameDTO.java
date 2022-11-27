package ua.com.javarush.quest.gribanov.questdelta.dto;

import lombok.Builder;
import lombok.Data;
import ua.com.javarush.quest.gribanov.questdelta.entity.GameState;

import java.time.ZonedDateTime;

@Data
@Builder
public class GameDTO {
    Long id;
    ZonedDateTime startingDate;
    Long userID;
    QuestionDTO currentQuestion;
    Long questID;
    GameState state;
}
