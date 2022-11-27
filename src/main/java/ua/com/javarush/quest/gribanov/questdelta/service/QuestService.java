package ua.com.javarush.quest.gribanov.questdelta.service;

import ua.com.javarush.quest.gribanov.questdelta.dto.QuestDTO;
import ua.com.javarush.quest.gribanov.questdelta.entity.Answer;
import ua.com.javarush.quest.gribanov.questdelta.entity.Quest;
import ua.com.javarush.quest.gribanov.questdelta.entity.Question;
import ua.com.javarush.quest.gribanov.questdelta.mapper.Mapper;
import ua.com.javarush.quest.gribanov.questdelta.repository.AbstractRepository;
import ua.com.javarush.quest.gribanov.questdelta.repository.QuestRepository;
import ua.com.javarush.quest.gribanov.questdelta.repository.UserRepository;

import java.util.*;
import java.util.stream.Collectors;

public class QuestService {

    private final QuestRepository questRepository = QuestRepository.get();

    private static final QuestService questService = new QuestService();

    private QuestService (){

    }
    public static QuestService get(){
        return questService;
    }
    public Collection<QuestDTO> getAll() {
        return questRepository.getAll().stream()
                .map(Mapper.quest::getDTO)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    public Optional<Map<Long, String>> getAuthors(Collection<QuestDTO> quests){
        UserRepository userRepository = UserRepository.get();
        Map<Long, String> authors = new HashMap<>();
        quests.forEach(q-> authors.put(q.getAuthorId(), userRepository.getByID(q.getAuthorId()).getName()));
        return authors.isEmpty() ? Optional.empty() : Optional.of(authors);
    }

    public void normalizeQuest(Quest quest){
        Long newQuestId = AbstractRepository.id.incrementAndGet();
        quest.setId(newQuestId);
        List<Answer> answers = new ArrayList<>();
        Collection<Question> questions = quest.getQuestions();

        for (Question question : questions) {
            question.setQuestID(newQuestId);
            Collection<Answer> questionAnswers = question.getAnswers();
            if (questionAnswers != null){
                for (Answer questionAnswer : questionAnswers) {
                    Long newAnswerId = AbstractRepository.id.incrementAndGet();
                    questionAnswer.setId(newAnswerId);
                    answers.add(questionAnswer);
                }
            }
        }

        for (Question question : questions) {
            Long oldID = question.getId();
            Long newID = AbstractRepository.id.incrementAndGet();
            if (question.isFirst()){
                quest.setFirstQuestionID(newID);
            }
            question.setId(newID);

            for (Answer answer : answers) {
                if (answer.getQuestionID().equals(oldID)){
                    answer.setQuestionID(newID);
                }
                if (Objects.equals(answer.getNextQuestionID(), oldID)){
                    answer.setNextQuestionID(newID);
                }
            }
        }
    }
}
