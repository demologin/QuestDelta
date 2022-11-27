package ua.com.javarush.quest.gribanov.questdelta.service;

import ua.com.javarush.quest.gribanov.questdelta.dto.GameDTO;
import ua.com.javarush.quest.gribanov.questdelta.entity.*;
import ua.com.javarush.quest.gribanov.questdelta.mapper.Mapper;
import ua.com.javarush.quest.gribanov.questdelta.repository.*;

import java.util.Optional;

public class GameService {

    private static final GameService gameService = new GameService();

    private GameService (){

    }
    public static GameService get(){
        return gameService;
    }
    public Optional<GameDTO> getGame(long userID, long questID){
        GameRepository gameRepository = GameRepository.get();
        UserRepository userRepository = UserRepository.get();
        QuestRepository questRepository = QuestRepository.get();
        User user = userRepository.getByID(userID);
        Quest quest = questRepository.getByID(questID);
        Game gameTemplate = Game.builder()
                .userID(userID)
                .questID(questID)
                .state(GameState.IN_PROGRESS)
                .build();
        Game existingGame = gameRepository.find(gameTemplate).findFirst().orElse(null);
        if (existingGame != null){
            return Mapper.game.getDTO(existingGame);
        } else {
            gameRepository.add(gameTemplate);
            gameTemplate.setQuestID(questID);
            gameTemplate.setCurrentQuestionID(quest.getFirstQuestionID());
            gameTemplate.setStartingDate();
            user.setPlayingGame(gameTemplate);
            return Mapper.game.getDTO(gameTemplate);
        }
    }

    public Optional<GameDTO> updateGame(Long gameID, Long answerID){
        Game existingGame = GameRepository.get().getByID(gameID);
        Answer answer = AnswerRepository.get().getByID(answerID);
        Question nextQuestion = QuestionRepository.get().getByID(answer.getNextQuestionID());
        if (existingGame != null){
            existingGame.setCurrentQuestionID(answer.getNextQuestionID());
            if (nextQuestion.isLast() && nextQuestion.isWin()){
                existingGame.setState(GameState.WIN);
            } else if (nextQuestion.isLast() && !nextQuestion.isWin()){
                existingGame.setState(GameState.LOST);
            } else {
                existingGame.setState(GameState.IN_PROGRESS);
            }
            return Mapper.game.getDTO(existingGame);
        } else {
            return Optional.empty();
        }
    }
}
