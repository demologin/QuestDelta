package ua.com.javarush.quest.khmelov.entity;

import lombok.*;

import java.util.ArrayList;
import java.util.Collection;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "with")
public class Quest extends Entity {
    Long id;
    Long authorId;
    String name;
    String text;
    Long startQuestionId;
    final Collection<Question> questions = new ArrayList<>();
}
