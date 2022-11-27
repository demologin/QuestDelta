<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="header.jsp" %>

<div class="app-body">
    <div class="question-form-wrapper">
        <jsp:useBean id="game" scope="request" type="ua.com.javarush.quest.gribanov.questdelta.dto.GameDTO"/>
        <div class="question-image-wrap exmpl">
            <img id="question-image" src="quest_images/${requestScope.question.image}">
        </div>
        <div class="question-body">
            <div class="lex-question">${requestScope.question.questionText}</div>
            <c:if test = "${not empty requestScope.question.answers}">
                <form id="answerForm" class="answerForm" action="${pageContext.request.contextPath}/game?gameId=${game.id}" method="post">
                    <div class="answers-wrap">
                        <c:forEach var="answer" items="${requestScope.question.answers}">
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="answer" id="answer${answer.id}" value="${answer.id}">
                                <label class="form-check-label" for="answer${answer.id}">
                                        ${answer.answerText}
                                </label>
                            </div>
                        </c:forEach>
                    </div>
                    <div class="submit-answer-wrap">
                        <input class="submit-answer" type="submit" id="answer-submit" value="Ответить" disabled>
                    </div>
                </form>
            </c:if>
            <c:if test="${requestScope.question.isLast==true}">
                <div class="submit-answer-wrap">
                    <a href="${pageContext.request.contextPath}/game?questId=${requestScope.question.questID}" class="submit-answer" type="button" id="restartGame">Начать сначала</a>
                </div>
            </c:if>
        </div>
    </div>
</div>


<%@include file="footer.jsp" %>
