package ua.com.javarush.quest.gribanov.questdelta.repository;

import ua.com.javarush.quest.gribanov.questdelta.entity.Game;
import java.util.stream.Stream;

public class GameRepository extends AbstractRepository<Game>{
    private static final GameRepository gameRepository = new GameRepository();
    public static GameRepository get(){
        return gameRepository;
    }
    private GameRepository(){

    }
    @Override
    public Stream<Game> find(Game template) {

        return repository.values().stream()
                .filter(entity->isCoincide(template, entity, Game::getId)
                        &&isCoincide(template, entity, Game::getStartingDate)
                        &&isCoincide(template, entity, Game::getUserID)
                        &&isCoincide(template, entity, Game::getCurrentQuestionID)
                        &&isCoincide(template, entity, Game::getQuestID)
                        &&isCoincide(template, entity, Game::getState));
    }
}
