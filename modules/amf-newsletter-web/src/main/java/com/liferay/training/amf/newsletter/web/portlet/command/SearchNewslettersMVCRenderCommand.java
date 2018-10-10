package com.liferay.training.amf.newsletter.web.portlet.command;

import amf.newsletter.web.constants.AmfNewsletterWebPortletKeys;

import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.training.amf.newsletter.model.NewsletterIssue;
import com.liferay.training.amf.newsletter.model.NewsletterSearchResult;
import com.liferay.training.amf.newsletter.service.NewsletterSearchService;

import java.util.Collections;
import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true,
property = {
		"javax.portlet.name=" + AmfNewsletterWebPortletKeys.AmfNewsletterSearchResultsWeb,
		"mvc.command.name=/newsletters/search"
},
service = MVCRenderCommand.class)
public class SearchNewslettersMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

		long groupId = themeDisplay.getScopeGroupId();

		String searchKeyword = ParamUtil.getString(renderRequest, "searchKeyword");
		renderRequest.setAttribute("searchKeyword", searchKeyword);

		SearchContainer newsletterSearchContainer = createNewsletterSearchContainer(renderRequest, renderResponse,
			groupId, searchKeyword, 5, "asc");
		renderRequest.setAttribute("newsletterSearchContainer", newsletterSearchContainer);

		return VIEW_PATH;
	}

	private PortletURL createNewsletterIssueIteratorUrl(RenderResponse renderResponse, String searchKeyword) {
		PortletURL newsletterIssueIteratorUrl = renderResponse.createRenderURL();

		newsletterIssueIteratorUrl.setParameter("searchKeyword", searchKeyword);

		return newsletterIssueIteratorUrl;
	}

	//TODO: make creation of Search Container a shared method
	//TODO: pass in interface/lambda that loads the newsletters so this method isn't responsible for both loading
	//		newsletters & creating Search Container
	//TODO: pass in iterator url
	private SearchContainer createNewsletterSearchContainer(RenderRequest renderRequest, RenderResponse renderResponse,
			long groupId, String searchKeyword, int pageSize, String orderByType) {

		PortletURL iteratorURL = createNewsletterIssueIteratorUrl(renderResponse, searchKeyword);
		List<String> headerNames = Collections.emptyList();
		String emptyResultsMessage = "No results found. Please try searching with other keywords.";

		SearchContainer<NewsletterIssue> newsletterSearchContainer = new SearchContainer<>(
			renderRequest, iteratorURL, headerNames, emptyResultsMessage);

		newsletterSearchContainer.setDelta(pageSize);
		newsletterSearchContainer.setOrderByType(orderByType);

		int newsletterIssueStartIndex = newsletterSearchContainer.getStart();
		int newsletterIssueEndIndex = newsletterSearchContainer.getEnd();

		NewsletterSearchResult newsletterSearchResult = new NewsletterSearchService()
					.search(renderRequest, searchKeyword, newsletterIssueStartIndex,
											newsletterIssueEndIndex);

		if (newsletterSearchResult != null)
		{
			List<NewsletterIssue> newsletterIssues = newsletterSearchResult.get_newsletterIssues();

			if (newsletterIssues.size() > 0)
				newsletterSearchContainer.setResults((List<NewsletterIssue>)newsletterIssues);
		}

		newsletterSearchContainer.setTotal(newsletterSearchResult.get_totalCount());

		return newsletterSearchContainer;
	}

	private static final String VIEW_PATH = "/search/search-newsletters.jsp";

	private static final Log _log = LogFactoryUtil.getLog(NewsletterIssuesMVCRenderCommand.class);

}