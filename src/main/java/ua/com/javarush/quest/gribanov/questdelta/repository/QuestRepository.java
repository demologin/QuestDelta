package ua.com.javarush.quest.gribanov.questdelta.repository;

import ua.com.javarush.quest.gribanov.questdelta.entity.Quest;
import java.util.Comparator;
import java.util.stream.Stream;

public class QuestRepository extends AbstractRepository<Quest> {

    private static final QuestRepository questRepository = new QuestRepository();
    public static QuestRepository get(){
        return questRepository;
    }
    private QuestRepository(){

    }
    @Override
    public Stream<Quest> find(Quest template) {
        return repository.values().stream()
                .filter(entity->isCoincide(template, entity, Quest::getId)
                        &&isCoincide(template, entity, Quest::getName)
                        &&isCoincide(template, entity, Quest::isActive)
                        &&isCoincide(template, entity, Quest::getAuthorID)
                        &&isCoincide(template, entity, Quest::getQuestions)
                        &&isCoincide(template, entity, Quest::getImage))
                .sorted(Comparator.comparing(Quest::getId));
    }
}
