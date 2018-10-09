package amf.user.search.results.web.portlet.command;

import java.util.Collections;
import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import amf.user.search.results.web.constants.AmfUserSearchResultsWebPortletKeys;
import amf.user.search.service.dynamicquery.UserSearchByZipDynamicQueryGenerator;
import amf.user.search.service.permission.UserSearchPermissionChecker;

@Component(immediate = true,
property = {
		   "javax.portlet.name=" + AmfUserSearchResultsWebPortletKeys.AmfUserSearchResultsWeb,
		   "mvc.command.name=/", "mvc.command.name=/user/search/results"
},
service = MVCRenderCommand.class)
public class UserSearchResultsMVCRenderCommand implements MVCRenderCommand {
	
	private static final String VIEW_PATH = "/view.jsp";
	
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws PortletException {	

		String zipCode = ParamUtil.getString(renderRequest, "zipCode");
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		long groupId = themeDisplay.getScopeGroupId();
		PermissionChecker permissionChecker = themeDisplay.getPermissionChecker();
		
		if (zipCode == null || zipCode.isEmpty()
			|| !hasPermissionToViewSearchResults(groupId, permissionChecker))
		{
			//TODO: use searchResultsVisible to toggle the Search Results for header
			renderRequest.setAttribute("searchResultsVisible", false);
			return VIEW_PATH;
		}
		
		renderRequest.setAttribute("searchResultsVisible", true);
		renderRequest.setAttribute("zipCode", zipCode);
		
		DynamicQuery userSearchByZipCodeDynamicQuery = new UserSearchByZipDynamicQueryGenerator()
															.generateForUserSearchByZip(zipCode);
		
		DynamicQuery userSearchByZipCodeDynamicQueryForCount = new UserSearchByZipDynamicQueryGenerator()
				.generateForUserSearchByZip(zipCode);
		
		SearchContainer userSearchContainer = createUserSearchContainer(renderRequest, renderResponse,
												groupId, zipCode, 5, "asc", userSearchByZipCodeDynamicQuery,
												userSearchByZipCodeDynamicQueryForCount);
		renderRequest.setAttribute("userSearchContainer", userSearchContainer);	
		
		return VIEW_PATH;
	}
	
	//TODO: make creation of Search Container a shared method 
	//TODO: pass in interface/lambda that loads the users so this method isn't responsible for both loading
	//		users & creating Search Container
	//TODO: pass in iterator url
	private SearchContainer createUserSearchContainer(RenderRequest request, RenderResponse response,
			long groupId, String zipCode, int pageSize, String orderByType, DynamicQuery dynamicQuery,
			DynamicQuery dynamicQueryForCount) {
		
		String emptyResultsMessage = "No results found. Please try a different search criteria.";
		PortletURL iteratorURL = createUserIteratorUrl(zipCode, response);
		List<String> headerNames = Collections.emptyList();
		
		SearchContainer<User> userSearchContainer = new SearchContainer<User>(
				request, iteratorURL, headerNames, emptyResultsMessage);
		
		userSearchContainer.setDelta(pageSize);
		userSearchContainer.setOrderByType(orderByType);
		
		int userStartIndex = userSearchContainer.getStart();
		int userEndIndex = userSearchContainer.getEnd();
		
		List<User> usersByZipCode = UserLocalServiceUtil.dynamicQuery(dynamicQuery, 
																	  userStartIndex, userEndIndex);
		if (usersByZipCode != null)
			userSearchContainer.setResults(usersByZipCode);
		
		int total = (int)UserLocalServiceUtil.dynamicQueryCount(dynamicQueryForCount);
		userSearchContainer.setTotal(total);
		
		return userSearchContainer;
	}
	
	private PortletURL createUserIteratorUrl(String zipCode, RenderResponse renderResponse) {
		
		PortletURL userIteratorUrl = renderResponse.createRenderURL();	
		userIteratorUrl.setParameter("zipCode", zipCode);
		return userIteratorUrl;
	}
	
	private boolean hasPermissionToViewSearchResults(long groupId, PermissionChecker permissionChecker) {
		
		try {
			UserSearchPermissionChecker.check(permissionChecker, 
									groupId, UserSearchPermissionChecker.VIEW_USER_SEARCH);
			return true;
		} catch (PortalException e) {
			_log.fatal(String.format("UserSearchResultsMVCRenderCommand.hasPermissionToViewSearchResults: "
					+ "Exception=%s", e.getMessage()));
		}
		return false;
	}
	
	private static final Log _log = LogFactoryUtil.getLog(UserSearchResultsMVCRenderCommand.class);
}
