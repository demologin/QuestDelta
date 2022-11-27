package ua.com.javarush.quest.gribanov.questdelta.repository;

import ua.com.javarush.quest.gribanov.questdelta.entity.Question;

import java.util.Comparator;
import java.util.stream.Stream;

public class QuestionRepository extends AbstractRepository<Question> {

    private static final QuestionRepository questionRepository = new QuestionRepository();
    public static QuestionRepository get(){
        return questionRepository;
    }
    private QuestionRepository(){

    }
    @Override
    public Stream<Question> find(Question template) {
        return this.repository.values().stream()
                .filter(entity->isCoincide(template, entity, Question::getId)
                        &&isCoincide(template, entity, Question::getQuestID)
                        &&isCoincide(template, entity, Question::getQuestionText)
                        &&isCoincide(template, entity, Question::getAnswers)
                        &&isCoincide(template, entity, Question::isLast)
                        &&isCoincide(template, entity, Question::getImage))
                .sorted(Comparator.comparing(Question::getId));
    }
}
