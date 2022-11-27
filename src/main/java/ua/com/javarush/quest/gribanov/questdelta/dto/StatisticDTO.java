package ua.com.javarush.quest.gribanov.questdelta.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collection;

@Data
@Builder
@EqualsAndHashCode
public class StatisticDTO {
    Collection<StatUserDTO> users;
}
