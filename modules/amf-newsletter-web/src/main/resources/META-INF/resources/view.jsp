<%@ include file="/init.jsp" %>

<portlet:renderURL var="portletURL"></portlet:renderURL>

<portlet:actionURL
	name="/newsletters/search/redirect"
	var="searchNewslettersUrl"
>
	<portlet:param name="redirect" value="${param.redirect}" />
</portlet:actionURL>

<aui:form action="${searchNewslettersUrl}" name="searchNewslettersForm">
	<div class="search-form">
		<span class="aui-search-bar">
			<aui:input
				inlineField="<%= true %>"
				label=""
				name="searchKeyword"
				size="30"
				title="search-entries"
				type="text"
			/>

			<aui:button type="submit" value="search" />
		</span>
	</div>
</aui:form>

<liferay-ui:tabs names="${newsletterIssueYearsCommaDelimitedString}" refresh="<%= false %>" url="<%= portletURL.toString() %>">
	<c:forEach items="${newsletterIssueYears}" var="newsletterIssueYear">
		<liferay-ui:section>
			<c:forEach items="${newsletterIssuePerYearMap.get(newsletterIssueYear)}" var="newsletterIssue">
				<c:forEach items="${monthsInDescendingOrder}" var="month">
					<hr />

					<h1><p style="text-align: center">${month}</p></h1>

					<c:if test="${newsletterIssue.get_issueMonth().equalsIgnoreCase(month)}">

						<p>Issue: #${newsletterIssue.get_issueNumber()}, <fmt:formatDate pattern="MMMM dd, yyyy" value="${newsletterIssue.get_issueDate()}" /></p>
						<portlet:actionURL
							name="/newsletter/issue/redirect"
							var="viewNewsletterIssue"
						>
							<portlet:param name="redirect" value="${param.redirect}" />

							<portlet:param
								name="newsletterIssueNumber"
								value="${newsletterIssue.get_issueNumber()}"
							/>
						</portlet:actionURL>

						<h1>
							<a href="${viewNewsletterIssue}">
								${newsletterIssue.get_title()}
							</a>
						</h1>

						<c:forEach items="${newsletterIssue.get_newsletterArticles()}" var="newsletterArticle">
							<h3 style="padding-left:1em">${newsletterArticle.get_title()}</h3>
						</c:forEach>
					</c:if>
				</c:forEach>
			</c:forEach>
		</liferay-ui:section>
	</c:forEach>
</liferay-ui:tabs>