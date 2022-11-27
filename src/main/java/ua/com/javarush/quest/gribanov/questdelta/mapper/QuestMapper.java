package ua.com.javarush.quest.gribanov.questdelta.mapper;

import ua.com.javarush.quest.gribanov.questdelta.dto.QuestDTO;
import ua.com.javarush.quest.gribanov.questdelta.entity.Quest;

import java.util.Optional;

public class QuestMapper implements Mapper<Quest, QuestDTO> {
    @Override
    public Optional<QuestDTO> getDTO(Quest quest) {
        return quest != null
                ? Optional.of(QuestDTO.builder()
                .id(quest.getId())
                .name(quest.getName())
                .description(quest.getDescription())
                .duration(quest.getDuration())
                .numberOfQuestions(quest.getNumberOfQuestions())
                .authorId(quest.getAuthorID())
                .image(quest.getImage())
                .build()
        ) : Optional.empty();
    }
}
