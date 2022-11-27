<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="header.jsp" %>
<div class="app-body">
    <div class="is-statistics"></div>
    <div class="lex-content-wrapper">
        <div class="lex-content">
            <div class="lex-table-wrapper">
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">Имя</th>
                            <th scope="col">Всего игр</th>
                            <th scope="col">В процессе</th>
                            <th scope="col">Выиграно</th>
                            <th scope="col">Проиграно</th>
                            <th scope="col">Создано квестов</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="statUser" items="${requestScope.statistic.users}">
                            <tr>
                                <td>${statUser.name}</td>
                                <td>${statUser.allGames}</td>
                                <td>${statUser.inProgressGames}</td>
                                <td>${statUser.winGames}</td>
                                <td>${statUser.lostGames}</td>
                                <td>${statUser.createdQuests}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<%@include file="footer.jsp" %>