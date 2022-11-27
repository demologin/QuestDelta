package repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ua.com.javarush.quest.gribanov.questdelta.entity.Role;
import ua.com.javarush.quest.gribanov.questdelta.entity.User;
import ua.com.javarush.quest.gribanov.questdelta.repository.UserRepository;
import ua.com.javarush.quest.gribanov.questdelta.service.LoaderService;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserRepositoryTest {
    @BeforeAll
    public static void load(){
        LoaderService loaderService = LoaderService.get();
        loaderService.load();
    }

    public static Stream<Arguments> getSamplePatternsForSearch() {

        User userWithId = User.builder().build();
        userWithId.setId(0L);
        return Stream.of(
                Arguments.of(User.builder().login("admin").password("admin").build(), 1),
                Arguments.of(User.builder().login("admin").password("123").build(), 0),
                Arguments.of(User.builder().login("admin").build(), 1),

                Arguments.of(User.builder().login("admin").build(), 1),
                Arguments.of(User.builder().password("admin").build(), 1),
                Arguments.of(User.builder().role(Role.ADMINISTRATOR).build(), 1),

                Arguments.of(User.builder().build(), 1),
                Arguments.of(userWithId, 0)
        );
    }

    @ParameterizedTest
    @MethodSource("getSamplePatternsForSearch")
    @DisplayName("Check find for users with not null fields")
    public void find(User user, int count) {
        UserRepository repository = UserRepository.get();
        long actualCount = repository.find(user).count();
        assertEquals(count, actualCount);
    }
}
