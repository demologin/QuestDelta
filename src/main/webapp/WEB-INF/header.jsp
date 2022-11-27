<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html lang="en">
    <head>
        <title>Quest</title>
        <script src=https://code.jquery.com/jquery-3.6.0.min.js></script>

        <!-- Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&family=Roboto+Slab:wght@400;700&display=swap" rel="stylesheet">

        <!-- CSS only -->
        <link href="../css/normalize.css" rel="stylesheet">
        <link rel="stylesheet" href="../css/font-awesome.min.css"/>
        <link rel="icon" href="../icons/favicon.png" type="image/png">
        <link href="../css/myStyle.css" rel="stylesheet">

     </head>
     <body>
        <div class="lex-container app-header">
            <header class="lex-header">
                 <a href="/" class="lex-logo">
                     <div class="lex-logo-wrap">
                         <img src="../icons/favicon.png" alt="Lex-logo" class="lex-logo-img">
                         <div class="lex-logo-text">КВЕСТЫ</div>
                     </div>

                 </a>
                 <nav class="lex-header-nav">
                     <ul class="lex-nav-list">
                         <li class="lex-nav-item"><a href="${pageContext.request.contextPath}/" class="nav-link px-2 text-secondary">Домой</a></li>
                         <li class="lex-nav-item"><a href="${pageContext.request.contextPath}/quests" class="nav-link px-2 text-white">Квесты</a></li>
                         <c:if test="${not empty sessionScope.user}">
                             <c:if test="${sessionScope.user.role!='GUEST'}">
                                 <li class="lex-nav-item"><a href="${pageContext.request.contextPath}/constructor" class="nav-link px-2 text-white">Конструктор</a></li>
                             </c:if>
                             <c:if test="${sessionScope.user.role=='ADMINISTRATOR'}">
                                 <li class="lex-nav-item"><a href="${pageContext.request.contextPath}/users" class="nav-link px-2 text-white">Пользователи</a></li>
                             </c:if>
                         </c:if>
                         <li class="lex-nav-item"><a href="${pageContext.request.contextPath}/statistic" class="nav-link px-2 text-white">Статистика</a></li>
                     </ul>
                 </nav>
                 <div class="lex-auth">
                     <c:choose>
                         <c:when test="${empty sessionScope.user}">
                             <nav class="lex-main-nav">
                                 <ul>
                                     <!-- inser more links here -->
                                     <li><a class="cd-signin" href="#0">Войти в систему</a></li>
                                     <li><a class="cd-signup" href="#0">Зарегистрироваться</a></li>
                                 </ul>
                             </nav>
                         </c:when>
                         <c:otherwise>
                             <nav class="lex-main-nav">
                                 <ul>
                                    <li><a class="lex-head-profile" href="${pageContext.request.contextPath}/user">${sessionScope.user.name}</a></li>
                                    <li><a class="cd-quit" href="${pageContext.request.contextPath}/logout">Выйти</a></li>
                                 </ul>
                             </nav>
                         </c:otherwise>
                     </c:choose>
                 </div>

            </header>
        </div>