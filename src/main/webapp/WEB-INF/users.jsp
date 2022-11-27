<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="header.jsp" %>

<div class="app-body">
    <div class="lex-content-wrapper">
        <div class="lex-content">
            <div class="lex-table-wrapper">
                <table class="table">
                    <thead>
                    <tr class="is-sticky">
                        <th scope="col">Аватар</th>
                        <th scope="col">Имя</th>
                        <th scope="col">Логин</th>
                        <th scope="col">Роль</th>
                        <th scope="col">Действия</th>

                    </tr>
                    </thead>
                    <tbody>
                    <c:if test="${not empty requestScope.users}">
                        <c:forEach var="user" items="${requestScope.users}">
                            <tr>
                                <td>
                                    <img class="lex-user-image" src="/user_images/${user.avatar}" alt="no image found">
                                </td>
                                <td>${user.name}</td>
                                <td>${user.login}</td>
                                <td>
                                    <select id="user-role-${user.id}" name="role" class="">
                                        <c:forEach items="${applicationScope.roles}" var="role">
                                            <option value="${role}" ${role==user.role?"selected":""}>
                                                    ${role}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <div class="action-buttons-wrap">
                                        <button id="save-user-${user.id}" class="save-user-btn" onclick="updateUsers('${pageContext.request.contextPath}/users?userId=${user.id}&action=update', ${user.id})">Изменить</button>
                                        <button id="delete-user-${user.id}" class="delete-user-btn" onclick="deleteUsers('${pageContext.request.contextPath}/users?userId=${user.id}&action=delete')">Удалить</button>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<%@include file="footer.jsp" %>