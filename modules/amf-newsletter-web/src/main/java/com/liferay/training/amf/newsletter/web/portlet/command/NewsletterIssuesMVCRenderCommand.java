package com.liferay.training.amf.newsletter.web.portlet.command;

import amf.newsletter.web.constants.AmfNewsletterWebPortletKeys;

import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.training.amf.newsletter.model.NewsletterArticle;
import com.liferay.training.amf.newsletter.model.NewsletterIssue;
import com.liferay.training.amf.newsletter.service.NewsletterArticleSaxReaderMapper;
import com.liferay.training.amf.newsletter.service.NewsletterIssueComparator;
import com.liferay.training.amf.newsletter.service.NewsletterIssueContainer;
import com.liferay.training.amf.newsletter.service.NewsletterIssueSaxReaderMapper;

import java.time.Month;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true,
property = {
		"javax.portlet.name=" + AmfNewsletterWebPortletKeys.AmfNewsletterWeb, "mvc.command.name=/",
		"mvc.command.name=/newsletter/issues"
},
service = MVCRenderCommand.class)
public class NewsletterIssuesMVCRenderCommand implements MVCRenderCommand {

	//TODO: fix casing inconsistency (should be newsletter, not newsLetter)

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		NewsletterIssueContainer.clear();

		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

		long groupId = themeDisplay.getScopeGroupId();

		//TODO: look up structure instead of hard-coded structure key
		List<JournalArticle> newsletterArticleJournalArticles = getJournalArticles(groupId, "45731");
		List<NewsletterArticle> newsletterArticles = getNewsLetterArticles(newsletterArticleJournalArticles);

		List<JournalArticle> newsletterIssueJournalArticles = getJournalArticles(groupId, "45727");
		List<NewsletterIssue> newsletterIssues = getNewsLetterIssues(
			newsletterIssueJournalArticles, newsletterArticles);

		Collections.sort(newsletterIssues, new NewsletterIssueComparator(false));

		NewsletterIssueContainer.setIssues(newsletterIssues);

		Map<String, List<NewsletterIssue>> newsletterIssuePerYearMap = getNewsletterIssuesGroupedByYear(
			newsletterIssues);

		Set<String> newsletterIssueYears = newsletterIssuePerYearMap.keySet();
		List<String> newsletterIssueYearsDesc = sortYears(newsletterIssueYears);
		String newsletterIssueYearsCommaDelimitedString = String.join(",", newsletterIssueYearsDesc);

		List<Month> monthsInDescendingOrder = getMonthsInDescendingOrder();

		renderRequest.setAttribute("monthsInDescendingOrder", monthsInDescendingOrder);
		renderRequest.setAttribute("newsletterIssuePerYearMap", newsletterIssuePerYearMap);
		renderRequest.setAttribute("newsletterIssueYears", newsletterIssueYearsDesc);
		renderRequest.setAttribute(
			"newsletterIssueYearsCommaDelimitedString", newsletterIssueYearsCommaDelimitedString);

		return VIEW_PATH;
	}

	private List<JournalArticle> getJournalArticles(long groupId, String ddmStructureKey) {
		List<JournalArticle> journalArticles = JournalArticleLocalServiceUtil.getArticles(groupId);

		List<JournalArticle> journalArticlesToInclude = new ArrayList<>();

		for (JournalArticle journalArticle : journalArticles) {
			String ddmStructureKeyFromJournalArticle = journalArticle.getDDMStructureKey();

			if (journalArticle.isApproved() && !journalArticle.isInTrash() &&
				ddmStructureKeyFromJournalArticle.equalsIgnoreCase(ddmStructureKey)) {

				journalArticlesToInclude.add(journalArticle);
			}
		}

		return journalArticlesToInclude;
	}

	private List<Month> getMonthsInDescendingOrder() {
		Month[] monthsInAscendingOrder = Month.values();
		List<Month> months = Arrays.asList(monthsInAscendingOrder);
		Collections.reverse(months);

		return months;
	}

	//TODO: move this method out to a separate class
	private List<NewsletterArticle> getNewsletterArticles(
		List<NewsletterArticle> newsletterArticles, Integer issueNumber) {

		List<NewsletterArticle> newsLetterArticlesForIssueNumber = new ArrayList<>();

		for (NewsletterArticle newsLetterArticle : newsletterArticles) {
			Integer issueNumberFromNewsletterArticle = newsLetterArticle.get_issueNumber();

			if (issueNumberFromNewsletterArticle.equals(issueNumber))
				newsLetterArticlesForIssueNumber.add(newsLetterArticle);
		}

		return newsLetterArticlesForIssueNumber;
	}

	//TODO: move this method out to a separate class
	private List<NewsletterArticle> getNewsLetterArticles(List<JournalArticle> journalArticles) {
		List<NewsletterArticle> newsLetterArticles = new ArrayList<>();
		//TODO: use interface here instead of concrete implementation
		NewsletterArticleSaxReaderMapper newsletterArticleMapper = new NewsletterArticleSaxReaderMapper();

		for (JournalArticle journalArticle : journalArticles) {
			newsLetterArticles.add(newsletterArticleMapper.map(journalArticle));
		}

		return newsLetterArticles;
	}

	//TODO: move this method out to a separate class
	private List<NewsletterIssue> getNewsLetterIssues(
		List<JournalArticle> journalArticles, List<NewsletterArticle> newsletterArticles) {

		List<NewsletterIssue> newsLetterIssues = new ArrayList<>();
		//TODO: use interface here instead of concrete implementation
		NewsletterIssueSaxReaderMapper newsletterIssueMapper = new NewsletterIssueSaxReaderMapper();

		for (JournalArticle journalArticle : journalArticles) {
			NewsletterIssue newsletterIssue = newsletterIssueMapper.map(journalArticle);

			Integer newsletterIssueNumber = newsletterIssue.get_issueNumber();

			List<NewsletterArticle> newsletterArticlesForIssue = getNewsletterArticles(newsletterArticles,
																			newsletterIssueNumber);
			newsletterIssue.set_newsletterArticles(newsletterArticlesForIssue);
			newsLetterIssues.add(newsletterIssue);
		}

		return newsLetterIssues;
	}

	//TODO: move this method out to a separate class
	private Map<String, List<NewsletterIssue>> getNewsletterIssuesGroupedByYear(
		List<NewsletterIssue> newsletterIssues) {

		Map<String, List<NewsletterIssue>> newsletterIssuePerYearMap = new HashMap<>();

		for (NewsletterIssue issue : newsletterIssues) {
			String year = issue.get_issueYear();

			List<NewsletterIssue> newsLetterIssuesForYear =
					newsletterIssuePerYearMap.containsKey(year)
							? newsletterIssuePerYearMap.get(year)
							: new ArrayList<NewsletterIssue>();

			newsLetterIssuesForYear.add(issue);
			newsletterIssuePerYearMap.put(year, newsLetterIssuesForYear);
		}

		return newsletterIssuePerYearMap;
	}

	private List<String> sortYears(Set<String> years) {
		List<String> sortedList = new ArrayList<>(years);

		Collections.sort(sortedList);
		Collections.reverse(sortedList);

		return sortedList;
	}

	private static final String VIEW_PATH = "/view.jsp";

	private static final Log _log = LogFactoryUtil.getLog(NewsletterIssuesMVCRenderCommand.class);

}