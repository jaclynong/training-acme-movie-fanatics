<%@ include file="/init.jsp" %>

<!-- TODO: make this a shared jsp since it's being displayed the same way in the other view -->
<p>Issue: #${newsletterIssue.get_issueNumber()}, <fmt:formatDate pattern="MMMM dd, yyyy" value="${newsletterIssue.get_issueDate()}" /></p>

<h1>${newsletterIssue.get_title()}</h1>
<p>${newsletterIssue.get_byline()}</p>

<p>${newsletterIssue.get_description()}</p>

<c:forEach var="newsletterArticle" items="${newsletterIssue.get_newsletterArticles()}">
	<h3 style="padding-left:1em">${newsletterArticle.get_title()}</h3>
	<p>${newsletterArticle.get_content()}</p>
</c:forEach>