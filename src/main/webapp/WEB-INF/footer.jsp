<%--
  Created by IntelliJ IDEA.
  User: lexink
  Date: 25.09.2022
  Time: 17:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
        <div class="lex-container app-footer">
            <footer class="lex-footer">
                    <nav class="lex-footer-nav">
                        <ul class="lex-nav-list">
                            <li class="nav-item-footer"><a href="${pageContext.request.contextPath}/" class="nav-link px-2 text-muted">Домой</a></li>
                            <li class="nav-item-footer"><a href="${pageContext.request.contextPath}/quests" class="nav-link px-2 text-muted">Квесты</a></li>
                            <c:if test="${not empty sessionScope.user}">
                                <c:if test="${sessionScope.user.role!='GUEST'}">
                                    <li class="nav-item-footer"><a href="${pageContext.request.contextPath}/constructor" class="nav-link px-2 text-muted">Конструктор</a></li>
                                </c:if>
                                    <c:if test="${sessionScope.user.role=='ADMINISTRATOR'}">
                                    <li class="nav-item-footer"><a href="${pageContext.request.contextPath}/users" class="nav-link px-2 text-muted">Пользователи</a></li>
                                </c:if>
                            </c:if>
                            <li class="nav-item-footer"><a href="${pageContext.request.contextPath}/statistic" class="nav-link px-2 text-muted">Статистика</a></li>
                        </ul>
                    </nav>
                    <p class="text-center text-muted">© 2022 Lexink, Inc</p>
            </footer>
        </div>
<div class="cd-user-modal"> <!-- this is the entire modal form, including the background -->
    <div class="cd-user-modal-container"> <!-- this is the container wrapper -->
        <ul class="cd-switcher">
            <li><a href="#0">Вход</a></li>
            <li><a href="#0">Новый аккаунт</a></li>
        </ul>

        <div id="cd-login"> <!-- log in form -->
            <form class="cd-form" id="login-form">
                <p class="fieldset">
                    <label class="image-replace cd-login-input" for="signin-login">Логин</label>
                    <input class="full-width has-padding has-border" id="signin-login" name="login" type="text" placeholder="Login">
                    <span class="cd-error-message">Ошибка! Не заполнен логин!</span>
                </p>

                <p class="fieldset">
                    <label class="image-replace cd-password" for="signin-password">Пароль</label>
                    <input class="full-width has-padding has-border" id="signin-password" name="password" type="password"  placeholder="Password">
                    <a href="#0" class="hide-password">Show</a>
                    <span class="cd-error-message">Ошибка! Не заполнен пароль!</span>
                </p>

                <p class="fieldset">
                    <input type="checkbox" id="remember-me" name="rememberMe">
                    <label for="remember-me">Запомнить меня</label>
                </p>

                <p class="fieldset">
                    <input class="full-width" type="submit" value="Войти">
                </p>
            </form>
        </div> <!-- cd-login -->

        <div id="cd-signup"> <!-- sign up form -->
            <form class="cd-form" id="signup-form">
                <p class="fieldset">
                    <label class="image-replace cd-username" for="signup-username">Имя</label>
                    <input class="full-width has-padding has-border" id="signup-username" name="name" type="text" placeholder="Имя пользователя">
                    <span class="cd-error-message">Необходимо указать имя пользователя!</span>
                </p>

                <p class="fieldset">
                    <label class="image-replace cd-login-input" for="signup-login">Логин</label>
                    <input class="full-width has-padding has-border" id="signup-login" name="login" type="text" placeholder="Логин">
                    <span class="cd-error-message">Необходимо указать логин!</span>
                </p>

                <p class="fieldset">
                    <label class="image-replace cd-password" for="signup-password">Пароль</label>
                    <input class="full-width has-padding has-border" id="signup-password" name="password" type="password"  placeholder="Пароль">
                    <a href="#0" class="hide-password">Show</a>
                    <span class="cd-error-message">Поле с паролем не может быть пустым!</span>
                </p>

                <p class="fieldset">
                    <input type="checkbox" id="accept-terms">
                    <label for="accept-terms">Я соглашаюсь с какими-то условиями</label>
                </p>

                <p class="fieldset">
                    <input id="create-user-input" class="full-width has-padding" type="submit" value="Создать" disabled>
                </p>
            </form>

            <!-- <a href="#0" class="cd-close-form">Close</a> -->
        </div> <!-- cd-signup -->

        <div id="cd-reset-password"> <!-- reset password form -->
            <p class="cd-form-message">Lost your password? Please enter your email address. You will receive a link to create a new password.</p>

            <form class="cd-form">
                <p class="fieldset">
                    <label class="image-replace cd-email" for="reset-email">E-mail</label>
                    <input class="full-width has-padding has-border" id="reset-email" type="email" placeholder="E-mail">
                    <span class="cd-error-message">Error message here!</span>
                </p>

                <p class="fieldset">
                    <input class="full-width has-padding" type="submit" value="Reset password">
                </p>
            </form>

            <p class="cd-form-bottom-message"><a href="#0">Back to log-in</a></p>
        </div> <!-- cd-reset-password -->
        <a href="#0" class="cd-close-form">Close</a>
        <div id="error-login" class="is-hide">
            <svg xmlns="http://www.w3.org/2000/svg" class="alert-icon" style="display: none;">
                <symbol id="exclamation-triangle-fill" viewBox="0 0 16 16">
                    <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
                </symbol>
            </svg>
            <div class="alert alert-danger d-flex align-items-center" role="alert">
                <svg class="bi flex-shrink-0 me-2" role="img" aria-label="Danger:"><use xlink:href="#exclamation-triangle-fill"/></svg>
                <div>
                    Неверно указаны логин или пароль.
                </div>
            </div>
        </div>
        <div id="error-submit" class="is-hide">
            <svg xmlns="http://www.w3.org/2000/svg" class="alert-icon" style="display: none;">
                <symbol id="exclamation-triangle-fill" viewBox="0 0 16 16">
                    <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
                </symbol>
            </svg>
            <div class="alert alert-danger d-flex align-items-center" role="alert">
                <svg class="bi flex-shrink-0 me-2" role="img" aria-label="Danger:"><use xlink:href="#exclamation-triangle-fill"/></svg>
                <div>
                    Неверно введены данные или пользователь с таким логином уже существует.
                </div>
            </div>
        </div>
    </div> <!-- cd-user-modal-container -->
</div> <!-- cd-user-modal -->

        <!-- JavaScript  -->
        <script src="../js/aeroplane.js"></script>
        <script src="../js/three.js"></script>
        <script src="../js/myScript.js"></script>
    </body>
</html>
