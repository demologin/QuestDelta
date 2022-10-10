package ua.com.javarush.quest.ogarkov.questdelta.util;

import lombok.experimental.UtilityClass;
import ua.com.javarush.quest.ogarkov.questdelta.entity.Answer;
import ua.com.javarush.quest.ogarkov.questdelta.entity.Quest;
import ua.com.javarush.quest.ogarkov.questdelta.entity.Question;
import ua.com.javarush.quest.ogarkov.questdelta.entity.User;
import ua.com.javarush.quest.ogarkov.questdelta.repository.AnswerRepository;
import ua.com.javarush.quest.ogarkov.questdelta.repository.QuestRepository;
import ua.com.javarush.quest.ogarkov.questdelta.repository.QuestionRepository;
import ua.com.javarush.quest.ogarkov.questdelta.repository.UserRepository;

import java.util.Collection;
import java.util.List;

import static ua.com.javarush.quest.ogarkov.questdelta.entity.GameState.*;
import static ua.com.javarush.quest.ogarkov.questdelta.entity.Language.RU;
import static ua.com.javarush.quest.ogarkov.questdelta.entity.Role.ADMIN;
import static ua.com.javarush.quest.ogarkov.questdelta.entity.Role.USER;

@UtilityClass
public class RepositoryLoader {
    //Need to update with load string
    //Need make saveMethod
    private final UserRepository userRepository = UserRepository.getInstance();
    private final QuestRepository questRepository = QuestRepository.getInstance();
    private final QuestionRepository questionRepository = QuestionRepository.getInstance();
    private final AnswerRepository answerRepository = AnswerRepository.getInstance();

    public void load() {
        defaultInit();
        save();
    }

    public void save() {
    }

    private void defaultInit() {
        //---------------------------- USERS -----------------------------------
        User admin = User.with()
                .id(1L)
                .login("admin")
                .password("admin")
                .role(ADMIN)
                .language(RU)
                .build();
        userRepository.create(admin);

        userRepository.create(User.with()
                .login("user").password("user").role(USER).build());

//        ---------------------------- /USERS -----------------------------------


        //---------------------------- QUESTS -----------------------------------
        Quest jrQuest = Quest.with()
                .id(1L)
                .authorId(admin.getId())
                .name("JavaRush quest")
                .text("Добро пожаловать на первый текстовый квест от команды JavaRush. Будет очень интересно)")
                .image("quests/1/quest_image.jpg")
                .build();
        questRepository.create(jrQuest);

        admin.getQuests().add(jrQuest);

        //---------------------------- /QUESTS -----------------------------------

        //---------------------------- QUESTIONS -----------------------------------
        Long jrQuestId = jrQuest.getId();
        Question question1 = Question.with()
                .questId(jrQuestId).gameState(PLAY)
                .name("Старт. Потеря памяти")
                .text("Ты потерял память.\nПринять вызов НЛО?")
                .image("quests/1/1.jpg").build();

        Question question2 = Question.with()
                .questId(jrQuestId).gameState(PLAY)
                .name("Принятие вызова")
                .text("Ты принял вызов.\nПоднимешься на мостик к капитану?")
                .image("quests/1/2.jpg").build();

        Question question3 = Question.with()
                .questId(jrQuestId).gameState(LOSE)
                .name("Отказ от вызова")
                .text("Ты отклонил вызов.\nПоражение.")
                .image("quests/1/3.jpg").build();

        Question question4 = Question.with()
                .questId(jrQuestId).gameState(PLAY)
                .name("На мостике")
                .text("Ты поднялся на мостик.\nТы кто?")
                .image("quests/1/4.jpg").build();

        Question question5 = Question.with()
                .questId(jrQuestId).gameState(LOSE)
                .name("Отказ подниматься на мостик")
                .text("Ты не пошёл на переговоры.\nПоражение.")
                .image("quests/1/5.jpg").build();

        Question question6 = Question.with()
                .questId(jrQuestId).gameState(WIN)
                .name("Рассказал о себе")
                .text("Тебя вернули домой\nПобеда.")
                .image("quests/1/6.jpg").build();

        Question question7 = Question.with()
                .questId(jrQuestId).gameState(LOSE)
                .name("Скрыл данные о себе")
                .text("Твою ложь разоблачили.\nПоражение.")
                .image("quests/1/7.jpg").build();

        Collection<Question> jrQuestQuestions = jrQuest.getQuestions();
        jrQuestQuestions.add(question1);
        jrQuestQuestions.add(question2);
        jrQuestQuestions.add(question3);
        jrQuestQuestions.add(question4);
        jrQuestQuestions.add(question5);
        jrQuestQuestions.add(question6);
        jrQuestQuestions.add(question7);

        questionRepository.create(question1);
        questionRepository.create(question2);
        questionRepository.create(question3);
        questionRepository.create(question4);
        questionRepository.create(question5);
        questionRepository.create(question6);
        questionRepository.create(question7);

        jrQuest.setFirstQuestionId(question1.getId());

        //---------------------------- /QUESTIONS -----------------------------------


        //---------------------------- ANSWERS -----------------------------------

        Answer answer1 = Answer.with()
                .text("Принять вызов")
                .questionId(question1.getId())
                .nextQuestionId(question2.getId())
                .build();

        Answer answer2 = Answer.with()
                .text("Отклонить вызов")
                .questionId(question1.getId())
                .nextQuestionId(question3.getId())
                .build();

        Answer answer3 = Answer.with()
                .text("Подняться на мостик")
                .questionId(question2.getId())
                .nextQuestionId(question4.getId())
                .build();

        Answer answer4 = Answer.with()
                .text("Отказаться подниматься на мостик")
                .questionId(question2.getId())
                .nextQuestionId(question5.getId())
                .build();

        Answer answer5 = Answer.with()
                .text("Рассказать правду о себе")
                .questionId(question4.getId())
                .nextQuestionId(question6.getId())
                .build();

        Answer answer6 = Answer.with()
                .text("Солгать о себе")
                .questionId(question4.getId())
                .nextQuestionId(question7.getId())
                .build();

        answerRepository.create(answer1);
        answerRepository.create(answer2);
        answerRepository.create(answer3);
        answerRepository.create(answer4);
        answerRepository.create(answer5);
        answerRepository.create(answer6);

        List<Answer> answersQ1 = question1.getAnswers();
        List<Answer> answersQ2 = question2.getAnswers();
        List<Answer> answersQ4 = question4.getAnswers();
        answersQ1.add(answer1);
        answersQ1.add(answer2);
        answersQ2.add(answer3);
        answersQ2.add(answer4);
        answersQ4.add(answer5);
        answersQ4.add(answer6);
        //---------------------------- ANSWERS -----------------------------------

    }
}
