<%@ include file="/init.jsp" %>

<portlet:renderURL var="portletURL"></portlet:renderURL>

<liferay-ui:tabs names="${newsletterIssueYearsCommaDelimitedString}" refresh="<%= false %>" url="<%= portletURL.toString() %>">
	<c:forEach var="newsletterIssueYear" items="${newsletterIssueYears}">
		<liferay-ui:section>
			<c:forEach var="newsletterIssue" items="${newsletterIssuePerYearMap.get(newsletterIssueYear)}">	
				<c:forEach var="month" items="${monthsInDescendingOrder}">

					<hr/>

					<h1><p style="text-align: center">${month}</p></h1>
					
					<c:if test="${newsletterIssue.get_issueMonth().equalsIgnoreCase(month)}">
					
						<p>Issue: #${newsletterIssue.get_issueNumber()}, <fmt:formatDate pattern="MMMM dd, yyyy" value="${newsletterIssue.get_issueDate()}" /></p> 
					
					  	<portlet:actionURL var="viewNewsletterIssue"
										   name="/newsletter/issue/redirect">
							<portlet:param name="redirect" value="${param.redirect}" />
							<portlet:param name="newsletterIssueNumber"
										   value="${newsletterIssue.get_issueNumber()}"/>
						</portlet:actionURL>

						<h1>
							<a href="${viewNewsletterIssue}">
								${newsletterIssue.get_title()}
							</a>
						</h1>
						
						<c:forEach var="newsletterArticle" items="${newsletterIssue.get_newsletterArticles()}">
							<h3 style="padding-left:1em">${newsletterArticle.get_title()}</h3>
						</c:forEach>
					</c:if>
					
				</c:forEach>
			</c:forEach>
		</liferay-ui:section>
	</c:forEach>
</liferay-ui:tabs>