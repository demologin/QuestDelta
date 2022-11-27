<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="header.jsp" %>
<div class="app-body">
    <div class="lex-content-wrapper">
        <div class="lex-content">
            <div class="lex-profile-intro">
                <h1>Профиль</h1>
                <div class="lex-intro-description">Здесь вы можете настроить свой профиль</div>
            </div>
            <div class="lex-profile-forms-wrap">
                <form class="lex-profile-image-form" enctype=multipart/form-data method="post"
                      action="${pageContext.request.contextPath}/user_images?userId=${sessionScope.user.id}">
                    <div class="lex-profile-image-wrapper">
                        <img id="lex-profile-image" class="lex-profile-image" src="/user_images/${sessionScope.user.avatar}" alt="no image found">
                    </div>
                    <div class="edit-user-image-wrap">
                        <button id="edit-user-image-btn" class="edit-user-image-btn" type="button">
                            <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                        </button>
                    </div>
                    <div class="lex-image-btn-group is-hide">
                        <input type="file" name="image" class="lex-image-input" id="image" required>
                        <button id="submit" type="submit" class="lex-image-upload-btn">Загрузить</button>
                    </div>
                </form>
                <form action="${pageContext.request.contextPath}/user?userId=${sessionScope.user.id}&action=update" method="post" class="lex-profile-form">
                    <div class="lex-details">
                        <div class="lex-details-edit-wrap">
                            <h4>Подробности</h4>
                            <div class="edit-user-wrap">
                                <button id="edit-user-btn" class="edit-user-btn" type="button">
                                    <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                </button>
                            </div>
                        </div>
                        <div class="lex-table-details-wrap">
                            <table class="lex-table-details">
                                <tbody>
                                <tr>
                                    <td>Имя:</td>
                                    <td class="lex-details-data" td-name="name">${sessionScope.user.name}</td>
                                </tr>
                                <tr>
                                    <td>Логин:</td>
                                    <td class="lex-details-data" td-name="login">${sessionScope.user.login}</td>
                                </tr>
                                <tr>
                                    <td>Роль:</td>
                                    <td data-tooltip="Роль изменяется администратором">${sessionScope.user.role}</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="lex-change-pass-wrap is-hide">
                            <button id="change-pass-btn" class="lex-change-pass-btn" type="button">Сменить пароль
                            </button>
                            <div id="change-pass-inputs" class="lex-pswd-wrap is-hide">
                                <div class="lex-pass-inputs-wrap">
                                    <label for="new-pass">Новый пароль:</label>
                                    <input id="new-pass" type="password" name="password"
                                           placeholder="Введите новый пароль" required disabled="disabled">
                                </div>
                                <div class="lex-pass-inputs-wrap">
                                    <label for="repeat-pass">Еще раз пароль:</label>
                                    <input id="repeat-pass" type="password" placeholder="Введите пароль еще раз"
                                           required disabled="disabled">
                                </div>
                            </div>
                        </div>
                        <div class="lex-confirm-btn-wrap is-hide">
                            <button id="save-edit-btn" class="lex-save-cancel-btn" type="submit">Сохранить</button>
                            <button id="cancel-edit-btn" class="lex-save-cancel-btn" type="button">Отменить</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <c:if test="${sessionScope.user.login != 'admin' && sessionScope.user.role!='ADMINISTRATOR'}">
            <form class="lex-remove-account" action="${pageContext.request.contextPath}/user?userId=${sessionScope.user.id}&action=delete" method="post">
                <button class="lex-remove-account-btn" type="submit">
                    Удалить аккаунт
                </button>
            </form>
        </c:if>
    </div>
</div>

<%@include file="footer.jsp" %>
