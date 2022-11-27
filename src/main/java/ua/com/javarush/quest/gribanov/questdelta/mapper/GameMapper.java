package ua.com.javarush.quest.gribanov.questdelta.mapper;

import ua.com.javarush.quest.gribanov.questdelta.dto.GameDTO;
import ua.com.javarush.quest.gribanov.questdelta.dto.QuestionDTO;
import ua.com.javarush.quest.gribanov.questdelta.entity.Game;
import ua.com.javarush.quest.gribanov.questdelta.repository.QuestionRepository;


import java.util.Optional;

public class GameMapper implements Mapper<Game, GameDTO> {
    @Override
    public Optional<GameDTO> getDTO(Game game){

        long questionID = game.getCurrentQuestionID();
        QuestionRepository questionRepository = QuestionRepository.get();
        QuestionDTO questionDTO = Mapper.question.getDTO(questionRepository.getByID(questionID)).orElse(null);
        return Optional.of(GameDTO.builder()
                        .id(game.getId())
                        .startingDate(game.getStartingDate())
                        .questID(game.getQuestID())
                        .userID(game.getUserID())
                        .currentQuestion(questionDTO)
                        .build()
                );
    }
}


