package ua.com.javarush.quest.gribanov.questdelta.repository;

import ua.com.javarush.quest.gribanov.questdelta.entity.Answer;
import java.util.Comparator;
import java.util.stream.Stream;

public class AnswerRepository extends AbstractRepository<Answer> {
    private static final AnswerRepository answerRepository = new AnswerRepository();

    private AnswerRepository() {
    }

    public static AnswerRepository get(){
        return answerRepository;
    }

    @Override
    public Stream<Answer> find(Answer template) {
        return repository.values().stream()
                .filter(entity->isCoincide(template, entity, Answer::getQuestionID)
                        &&isCoincide(template, entity, Answer::getAnswerText)
                        &&isCoincide(template, entity, Answer::getId))
                .sorted(Comparator.comparing(Answer::getId));
    }
}
