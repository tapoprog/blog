<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="UTF-8">
        <title>Articls</title>
        <link rel="stylesheet" href="<c:url value='/css/reset.css' />">
        <link rel="stylesheet" href="<c:url value='/css/style.css' />">
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <div id="header_menu">
                    <h1><a href="<c:url value='/' />">Articls</a></h1>&nbsp;&nbsp;&nbsp;
                    <c:if test="${sessionScope.login_user != null}">
                        <a href="<c:url value='/users/index' />">全ユーザー</a>&nbsp;
                        <a href="<c:url value='/blogs/index' />">全記事</a>&nbsp;
                    </c:if>
                </div>
                <c:if test="${sessionScope.login_user != null}">
                    <div id="user_name">
                        <a href="<c:url value='/users/show?id=${login_user.id}' />">ようこそ <c:out value="${sessionScope.login_user.name}" />&nbsp;さん</a>&nbsp;&nbsp;&nbsp;
                        <a href="<c:url value='/blogs/new' />">新規記事作成</a>&nbsp;&nbsp;&nbsp;
                        <a href="<c:url value='/logout' />">ログアウト</a>
                    </div>
                </c:if>
            </div>
            <div id="content">
                ${param.content}
            </div>
            <div id="footer">
                by tapoprog
            </div>
        </div>
    </body>
</html>