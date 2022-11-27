package ua.com.javarush.quest.gribanov.questdelta.entity;

import lombok.EqualsAndHashCode;

import java.time.ZonedDateTime;

@EqualsAndHashCode(callSuper = false)
public class Game extends AbstractEntity {
    private ZonedDateTime startingDate;
    private Long userID;
    private Long currentQuestionID;
    private Long questID;
    private GameState state;

    public Game(Long userID, Long currentQuestionID, Long questID, GameState state) {
        this.userID = userID;
        this.currentQuestionID = currentQuestionID;
        this.questID = questID;
        this.state = state;
    }

    public Game(){

    }

    public static GameBuilder builder(){
        return new GameBuilder();
    }

    public ZonedDateTime getStartingDate() {
        return startingDate;
    }
    public void setStartingDate() {
        startingDate = ZonedDateTime.now();
    }



    public Long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public Long getCurrentQuestionID() {
        return currentQuestionID;
    }

    public void setCurrentQuestionID(long currentQuestionID) {
        this.currentQuestionID = currentQuestionID;
    }

    public Long getQuestID() {
        return questID;
    }

    public void setQuestID(long questID) {
        this.questID = questID;
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }


    public static class GameBuilder {
        private Long userID;
        private Long currentQuestionID;
        private Long questID;
        private GameState state;

        GameBuilder() {
        }

        public GameBuilder userID(long userID){
            this.userID = userID;
            return this;
        }

        public GameBuilder currentQuestionID(long currentQuestionID){
            this.currentQuestionID = currentQuestionID;
            return this;
        }

        public GameBuilder questID(long questID){
            this.questID = questID;
            return this;
        }

        public GameBuilder state(GameState state){
            this.state = state;
            return this;
        }

        public Game build(){
            return new Game(userID, currentQuestionID, questID, state);
        }
    }
}
