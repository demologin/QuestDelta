package ua.com.javarush.quest.gribanov.questdelta.mapper;

import ua.com.javarush.quest.gribanov.questdelta.dto.AnswerDTO;
import ua.com.javarush.quest.gribanov.questdelta.entity.Answer;

import java.util.Optional;

public class AnswerMapper implements Mapper<Answer, AnswerDTO> {
    @Override
    public Optional<AnswerDTO> getDTO(Answer answer) {
        return answer != null ? Optional.of(AnswerDTO.builder()
                .id(answer.getId())
                .answerText(answer.getAnswerText())
                .questionID(answer.getQuestionID())
                .nextQuestionID(answer.getNextQuestionID())
                .build())
                : Optional.empty();
    }
}
