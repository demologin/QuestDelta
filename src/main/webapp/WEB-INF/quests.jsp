<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="header.jsp" %>

<div class="app-body">
    <audio autoplay loop >
        <source src="../audio/mystic-audio.mp3" type="audio/mpeg" >
    </audio>
    <div class="lex-bulb-bg">
        <div class="bulb1"></div>
        <div class="bulb2"></div>
        <div class="bulb3"></div>
    </div>

    <div class="lex-content-wrapper">
        <div class="lex-content">
            <div class="lex-col">
            <c:forEach var="quest" items="${requestScope.quests}">
                <div class="lex-card">
                    <div class="lex-quest-image-wrap">
                        <div class="lex-quest-image" style="background-image: url(quest_images/${quest.image});"></div>
                    </div>
                    <div class="lex-card-body">
                        <div class="card-main">
                            <div class="card-body">
                                <h5 class="card-title">${quest.name}</h5>
                                <div class="lex-quest-icons">
                                    <div class="lex-question-icon">
                                        <i class="fa fa-question-circle-o" aria-hidden="true"></i>
                                        <span> - ${quest.numberOfQuestions}</span>
                                    </div>
                                    <div class="lex-duration-icon">
                                        <i class="fa fa-clock-o" aria-hidden="true"></i>
                                        <span> ~ ${quest.duration}</span>
                                    </div>
                                </div>
                                <p class="card-text">${quest.description}</p>
                            </div>
                            <div class="text-muted">
                                <c:if test="${not empty requestScope.authors}">
                                    Автор: ${requestScope.authors.get(quest.authorId)}
                                </c:if>
                            </div>
                            <div class="lex-play-button">
                                <c:choose>
                                    <c:when test="${not empty sessionScope.user}">
                                        <a href="${pageContext.request.contextPath}/game?questId=${quest.id}" class="lex-quest-play">Играть</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a id="quest-not-play" class="lex-quest-play" href="#0">Играть</a>
                                    </c:otherwise>
                                </c:choose>
                            </div>

                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        </div>
    </div>
</div>


<%@include file="footer.jsp" %>