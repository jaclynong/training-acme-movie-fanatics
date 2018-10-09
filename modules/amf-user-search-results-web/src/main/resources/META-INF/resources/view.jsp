<%@ include file="/init.jsp" %>


<h1>Search Results for ${zipCode}</h1>

<div>
	<liferay-ui:search-container searchContainer="${userSearchContainer}">
		<liferay-ui:search-container-row
			className="com.liferay.portal.kernel.model.User"
			keyProperty="userId"
			modelVar="user">

			<liferay-ui:search-container-column-text>
				${user.firstName} ${user.lastName.charAt(0)}. (${user.screenName}) - (${user.emailAddress})
			</liferay-ui:search-container-column-text>

		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator markupView="lexicon" paginate="true"/>

	</liferay-ui:search-container>
</div>