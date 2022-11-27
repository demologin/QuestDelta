package ua.com.javarush.quest.gribanov.questdelta.entity;

import lombok.EqualsAndHashCode;

@SuppressWarnings("unused")
@EqualsAndHashCode(callSuper = false)
public class Answer extends AbstractEntity {
    private Long questionID;
    private Long nextQuestionID;
    private String answerText;

    public static AnswerBuilder builder(){
        return new AnswerBuilder();
    }

    public Answer() {
    }

    public Answer(Long questionID, Long nextQuestionID, String answerText) {
        this.questionID = questionID;
        this.nextQuestionID = nextQuestionID;
        this.answerText = answerText;
    }

    public Long getQuestionID() {
        return questionID;
    }

    public void setQuestionID(Long questionID) {
        this.questionID = questionID;
    }

    public Long getNextQuestionID() {
        return nextQuestionID;
    }

    public void setNextQuestionID(Long nextQuestionID) {
        this.nextQuestionID = nextQuestionID;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }


    public static class AnswerBuilder {
        private Long questionID;
        private Long nextQuestionID;
        private String answerText;

        AnswerBuilder() {
        }

        public AnswerBuilder questionID(Long questionID){
            this.questionID = questionID;
            return  this;
        }

        public AnswerBuilder answerText(String answerText){
            this.answerText = answerText;
            return this;
        }

        public AnswerBuilder nextQuestionID(Long nextQuestionID){
            this.nextQuestionID = nextQuestionID;
            return this;
        }

        public Answer build(){
            return new Answer(questionID, nextQuestionID, answerText);
        }
    }
}
