package ua.com.javarush.quest.gribanov.questdelta.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collection;

@Data
@Builder
@EqualsAndHashCode
public class QuestionDTO {
    Long id;
    Long questID;
    String questionText;
    Boolean isFirst;
    Boolean isLast;
    Boolean isWin;
    String image;
    Collection<AnswerDTO> answers;
}
