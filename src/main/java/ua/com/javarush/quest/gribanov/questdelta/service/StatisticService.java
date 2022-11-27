package ua.com.javarush.quest.gribanov.questdelta.service;

import ua.com.javarush.quest.gribanov.questdelta.dto.StatUserDTO;
import ua.com.javarush.quest.gribanov.questdelta.dto.StatisticDTO;
import ua.com.javarush.quest.gribanov.questdelta.entity.Game;
import ua.com.javarush.quest.gribanov.questdelta.entity.GameState;
import ua.com.javarush.quest.gribanov.questdelta.entity.User;
import ua.com.javarush.quest.gribanov.questdelta.repository.UserRepository;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


public class StatisticService {

    private static final StatisticService STATISTIC_SERVICE = new StatisticService();
    private final UserRepository userRepository = UserRepository.get();

    private StatisticService (){

    }
    public static StatisticService get(){
        return STATISTIC_SERVICE;
    }

    public Optional<StatisticDTO> getStatistic(){
        Collection<User> users = userRepository.getAll();
        return !users.isEmpty()? Optional.of(StatisticDTO.builder().users(getStatUsers(users)).build()):Optional.empty();
    }

    private Collection<StatUserDTO> getStatUsers(Collection<User> users){
        return users.stream()
                .map(StatisticService::mapUser)
                .collect(Collectors.toList());
    }

    private static StatUserDTO mapUser(User user){
        int allGamesCount = 0;
        int winGamesCount = 0;
        int lostGamesCount = 0;
        int inProgressGamesCount = 0;

        Collection<Game> games = user.getPlayingGames();
        if (null != games && !games.isEmpty()) {
            allGamesCount = games.size();

            for (Game game : games) {
                if (game.getState().equals(GameState.WIN)) {
                    winGamesCount++;
                } else if (game.getState().equals(GameState.LOST)) {
                    lostGamesCount++;
                } else {
                    inProgressGamesCount++;
                }
            }
        }
        return StatUserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .allGames(allGamesCount)
                .winGames(winGamesCount)
                .lostGames(lostGamesCount)
                .inProgressGames(inProgressGamesCount)
                .createdQuests(Objects.nonNull(user.getCreatedQuests())?user.getCreatedQuests().size():0)
                .build();
    }
}
