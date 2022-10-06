package ua.com.javarush.quest.ogarkov.questdelta.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.com.javarush.quest.ogarkov.questdelta.entity.Role;
import ua.com.javarush.quest.ogarkov.questdelta.entity.User;
import ua.com.javarush.quest.ogarkov.questdelta.util.Jsp;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static ua.com.javarush.quest.ogarkov.questdelta.settings.Default.*;


@WebFilter({ROOT, USERS, SIGNUP, PROFILE, LOGIN, PLAY, PROFILE_EDIT, USER_EDIT, LOGOUT, USER, USERS, EDIT_QUEST, QUESTION_CREATE, EDIT_QUEST_CONTENT, EDIT_QUESTS})
public class RoleSelector implements Filter {

    private final Map<Role, List<String>> uriMap = Map.of(
            Role.GUEST, List.of(ROOT, LOGIN, SIGNUP),
            Role.USER, List.of(ROOT, PROFILE, LOGOUT, PROFILE_EDIT, PLAY, EDIT_QUEST, QUESTION_CREATE, EDIT_QUEST_CONTENT),
            Role.ADMIN, List.of(ROOT, USERS, PROFILE, LOGOUT, USER, USER_EDIT, PROFILE_EDIT, EDIT_QUEST, QUESTION_CREATE, EDIT_QUEST_CONTENT, PLAY, EDIT_QUESTS)
    );

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String command = Jsp.getCommand(request);
        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");

        Role role = (Objects.isNull(user))
                ? Role.GUEST
                : ((User) user).getRole();

        session.setAttribute("role", role.name());
        if (uriMap.get(role).contains(command)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            Jsp.redirect(request, response, LOGIN);
        }
    }
}
