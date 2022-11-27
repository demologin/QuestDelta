package ua.com.javarush.quest.gribanov.questdelta.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Collection;

@SuppressWarnings("unused")
@Builder
@EqualsAndHashCode(callSuper = false)
public class Question extends AbstractEntity {
    private Long questID;
    private String questionText;
    private boolean isFirst;
    private boolean isLast;
    private boolean isWin;

    Collection<Answer> answers;
    private String image;

    public Question(Long questID, String questionText, boolean isFirst, boolean isLast, boolean isWin, Collection<Answer> answers, String image) {
        this.questID = questID;
        this.questionText = questionText;
        this.isFirst = isFirst;
        this.isLast = isLast;
        this.isWin = isWin;
        this.answers = answers;
        this.image = image;
    }

    public Question() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getQuestID() {
        return questID;
    }

    public void setQuestID(Long questID) {
        this.questID = questID;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public boolean isLast() {
        return isLast;
    }

    public void setLast(boolean last) {
        isLast = last;
    }

    public boolean isWin() {
        return isWin;
    }

    public void setWin(boolean win) {
        isWin = win;
    }

    public boolean isFirst() {
        return isFirst;
    }

    public void setFirst(boolean aFirst) {
        isFirst = aFirst;
    }

    public Collection<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Collection<Answer> answers) {
        this.answers = answers;
    }
    public void setAnswer(Answer answer){
        if (this.answers == null) {
            this.answers = new ArrayList<>();
        }
        this.answers.add(answer);
    }
}
