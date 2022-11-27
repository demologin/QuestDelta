package ua.com.javarush.quest.gribanov.questdelta.entity;

import lombok.*;

import java.util.ArrayList;
import java.util.Collection;

@SuppressWarnings("unused")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder
public class User extends AbstractEntity {
    private String name;
    private String login;
    private String password;
    private Role role;
    private String avatar;

    private Collection<Game> playingGames;

    private Collection<Quest> createdQuests;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Collection<Game> getPlayingGames() {
        return playingGames;
    }

    public void setPlayingGames(Collection<Game> playingGames) {
        this.playingGames = playingGames;
    }
    public void setPlayingGame(Game playingGame) {
        if (this.playingGames == null){
            this.playingGames = new ArrayList<>();
        }
        this.playingGames.add(playingGame);
    }

    public Collection<Quest> getCreatedQuests() {
        return createdQuests;
    }

    public void setCreatedQuests(Collection<Quest> createdQuests) {
        this.createdQuests = createdQuests;
    }
    public void setCreatedQuest(Quest createdQuest) {
        if (this.createdQuests == null){
            this.createdQuests = new ArrayList<>();
        }
        this.createdQuests.add(createdQuest);
    }

}
