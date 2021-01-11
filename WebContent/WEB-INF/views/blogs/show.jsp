<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${blog != null}">
                <h2>${blog.title}</h2>

                <table>
                    <tbody>
                        <tr>
                            <th>
                                <pre><c:out value="${blog.content}" /></pre>
                            </th>
                        </tr>
                    </tbody>
                </table>

                <h3>作成者：<c:out value="${blog.user.name}" />　　　　　　　　　　　　　　　　　　　　　　　作成日時：<fmt:formatDate value="${blog.created_at}" pattern="yyyy-MM-dd HH:mm:ss" />　　更新日時：<fmt:formatDate value="${blog.updated_at}" pattern="yyyy-MM-dd HH:mm:ss" /></h3>

            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <c:choose>
            <c:when test = "${goods != null}">
                   <form method="POST" action="<c:url value='/goods/destroy' />">
                       <input type="hidden" name="_token" value="${_token}" />
                    <input type="hidden" name="good_id" value="${goods.id}" />
                    <input type="hidden" name="blog_id" value="${blog.id}" />
                    <button type="submit">❤</button>
                </form>
            </c:when>
            <c:otherwise>
                  <form method="POST" action="<c:url value='/goods/create' />">
                    <input type="hidden" name="blogId" value="${blog.id}" />
                    <input type="hidden" name="_token" value="${_token}" />
                    <button type="submit">♡</button>
                </form>
            </c:otherwise>
        </c:choose>
        <c:if test="${sessionScope.login_user.id == blog.user.id}">
                    <p><a href="<c:url value="/blogs/edit?id=${blog.id}" />">この記事を編集する</a></p>
                </c:if>
        <p><a href="<c:url value="/blogs/index" />">一覧に戻る</a></p>
    </c:param>
</c:import>