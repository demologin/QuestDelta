package ua.com.javarush.quest.gribanov.questdelta.mapper;

import ua.com.javarush.quest.gribanov.questdelta.dto.AnswerDTO;
import ua.com.javarush.quest.gribanov.questdelta.dto.ClientData;
import ua.com.javarush.quest.gribanov.questdelta.dto.QuestionDTO;
import ua.com.javarush.quest.gribanov.questdelta.entity.Question;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class QuestionMapper implements Mapper<Question, QuestionDTO> {
    @Override
    public Optional<QuestionDTO> getDTO(Question question) {
        Collection<AnswerDTO> answersDTO = null;
        if (question.getAnswers() != null) {
            answersDTO = question.getAnswers().stream()
                    .map(a -> Mapper.answer.getDTO(a).get())
                    .collect(Collectors.toList());
        }
        return question != null ? Optional.of(QuestionDTO.builder()
                .id(question.getId())
                .questionText(question.getQuestionText())
                .questID(question.getQuestID())
                .isALast(question.isALast())
                .image(question.getImage())
                .answers(answersDTO)
                .build())
                :Optional.empty();
    }

    @Override
    public Question create(ClientData data) {
        return null;
    }
}
