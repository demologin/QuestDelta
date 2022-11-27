package ua.com.javarush.quest.gribanov.questdelta.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnswerDTO {
    Long id;
    Long questionID;
    Long nextQuestionID;
    String answerText;
}
