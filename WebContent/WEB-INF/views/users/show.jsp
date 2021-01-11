<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${user != null}">
            	<c:choose>
                	<c:when test="${user.id == loginUser.id}">
                		<h2>マイページ</h2>
                	</c:when>
                	<c:otherwise>
						<h2>
							<c:out value="${user.name}" /> さんの詳細ページ
							<c:choose>
                            	<c:when test = "${follows != null}">
                                	<form method="POST" action="<c:url value='/follows/destroy' />">
                                    	<input type="hidden" name="_token" value="${_token}" />
                                        <input type="hidden" name="follow_id" value="${follows.id}" />
                                        <input type="hidden" name="userId" value="${user.id}" />
                                        <button type="submit" onclick="return confirm('フォローを外しますか？');">フォロー中</button>
                                    </form>
                                </c:when>
                                <c:otherwise>
                                	<form method="POST" action="<c:url value='/follows/create' />">
                                    	<input type="hidden" name="userId" value="${user.id}" />
                                        <input type="hidden" name="_token" value="${_token}" />
                                        <button type="submit">フォロー</button>
                                    </form>
                                </c:otherwise>
                        	</c:choose>
						</h2>

                	</c:otherwise>
                </c:choose>

                <h3>@<c:out value="${user.code}" /></h3><br>

                <a href="<c:url value='/goods/index?id=${user.id}' />">いいねリスト</a>

                <p>アカウント作成日時：<fmt:formatDate value="${user.created_at}" pattern="yyyy-MM-dd HH:mm:ss" /><br><br>


                <a href="<c:url value='/follows/index?id=${user.id}' />">フォローリスト</a>
                /<a href="<c:url value='/followers/index?id=${user.id}' />">フォロワーリスト</a>


                <h3>【${user.name}の記事一覧】</h3>
        			<table id="blog_list">
            			<tbody>
                			<tr>
                    			<th class="blog_title">タイトル</th>
                    			<th class="blog_date">日付</th>
                    			<th class="blog_action">操作</th>
                			</tr>
                		<c:forEach var="blog" items="${blogs}" varStatus="status">
                    		<tr class="row${status.count % 2}">
                        		<td class="blog_title">${blog.title}</td>
                        		<td class="blog_date"><fmt:formatDate value='${blog.blog_date}' pattern='yyyy-MM-dd' /></td>
                        		<td class="blog_action"><a href="<c:url value='/blogs/show?id=${blog.id}' />">詳細を見る</a></td>
                    		</tr>
                		</c:forEach>
            		</tbody>
        		</table>

        		<div id="pagination">
      				(全 ${blogs_count} 件）<br />
            		<c:forEach var="i" begin="1" end="${((blogs_count - 1) / 15) + 1}" step="1">
                		<c:choose>
                    		<c:when test="${i == page}">
                        		<c:out value="${i}" />&nbsp;
                    		</c:when>
                    		<c:otherwise>
                        		<a href="<c:url value='/?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                   			</c:otherwise>
                		</c:choose>
            		</c:forEach>
        		</div>
				<c:choose>
                	<c:when test="${user.id == loginUser.id}">
                		<p><a href="<c:url value='/users/edit?id=${user.id}' />">このユーザー情報を編集する</a></p>
                	</c:when>
                	<c:otherwise></c:otherwise>
                </c:choose>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <p><a href="<c:url value='/users/index' />">一覧に戻る</a></p>
    </c:param>
</c:import>