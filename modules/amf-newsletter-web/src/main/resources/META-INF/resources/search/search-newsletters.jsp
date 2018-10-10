<%@ include file="/init.jsp" %>

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

<h1>Search Results for ${searchKeyword}</h1>

<div>
	<liferay-ui:search-container searchContainer="${newsletterSearchContainer}">
		<liferay-ui:search-container-row
			className="com.liferay.training.amf.newsletter.model.NewsletterIssue"
			modelVar="newsletterIssue"
		>
			<p>Issue: #${newsletterIssue.get_issueNumber()}, <fmt:formatDate pattern="MMMM dd, yyyy" value="${newsletterIssue.get_issueDate()}" /></p>

			<portlet:actionURL
				name="/newsletter/issue/redirect"
				var="viewNewsletterIssue"
			>
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

			<hr />
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator markupView="lexicon" paginate="true" />
	</liferay-ui:search-container>
</div>