package ua.com.javarush.quest.gribanov.questdelta.mapper;

import ua.com.javarush.quest.gribanov.questdelta.dto.*;
import ua.com.javarush.quest.gribanov.questdelta.entity.*;
import java.util.Optional;

public interface Mapper <E extends AbstractEntity, R> {

    Optional<R> getDTO(E entity);

    Mapper<User, UserDTO> user = new UserMapper();
    Mapper<Quest, QuestDTO> quest = new QuestMapper();
    Mapper<Question, QuestionDTO> question = new QuestionMapper();
    Mapper<Answer, AnswerDTO> answer = new AnswerMapper();
    Mapper<Game, GameDTO> game = new GameMapper();

}
