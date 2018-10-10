package amf.newsletter.search.index.postprocessor;

import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.IndexerPostProcessor;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.training.amf.newsletter.model.JournalArticleType;
import com.liferay.training.amf.newsletter.model.NewsletterArticle;
import com.liferay.training.amf.newsletter.model.NewsletterIssue;
import com.liferay.training.amf.newsletter.service.NewsletterArticleSaxReaderMapper;
import com.liferay.training.amf.newsletter.service.NewsletterIssueSaxReaderMapper;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;

@Component(
	immediate = true, property = "indexer.class.name=com.liferay.journal.model.JournalArticle",
	service = IndexerPostProcessor.class
)
public class JournalArticlePostProcessor implements IndexerPostProcessor {

	@Override
	public void postProcessContextBooleanFilter(BooleanFilter booleanFilter, SearchContext searchContext)
		throws Exception {
	}

	@Override
	public void postProcessContextQuery(BooleanQuery contextQuery, SearchContext searchContext) throws Exception {
	}

	@Override
	public void postProcessDocument(Document document, Object obj) throws Exception {
		JournalArticle journalArticle = (JournalArticle)obj;

		//TODO: might want to make the Newsletter* classes implement an interface & create a factory that will return whichever type

		if (isNewsletterIssue(journalArticle))
		{
			NewsletterIssue newsletterIssue = new NewsletterIssueSaxReaderMapper().map(journalArticle);

			document.add(new Field("issueNumber", newsletterIssue.get_issueNumber().toString()));
			document.addDate("issueDate", newsletterIssue.get_issueDate());
			document.add(new Field("journalArticleType", JournalArticleType.NEWSLETTER_ISSUE.getDdmStructureKey()));
			document.add(new Field("newsletterIssueTitle", newsletterIssue.get_title()));
		}
		else if (isNewsletterArticle(journalArticle))
		{
			NewsletterArticle newsletterArticle = new NewsletterArticleSaxReaderMapper().map(journalArticle);

			document.add(new Field("issueNumber", newsletterArticle.get_issueNumber().toString()));
			document.add(new Field("journalArticleType", JournalArticleType.NEWSLETTER_ARTICLE.getDdmStructureKey()));
		}
	}

	@Override
	public void postProcessFullQuery(BooleanQuery fullQuery, SearchContext searchContext) throws Exception {
	}

	@Override
	public void postProcessSearchQuery(BooleanQuery searchQuery, BooleanFilter booleanFilter,
			SearchContext searchContext) throws Exception {
	}

	@Override
	public void postProcessSearchQuery(BooleanQuery searchQuery, SearchContext searchContext) throws Exception {
	}

	@Override
	public void postProcessSummary(Summary summary, Document document, Locale locale, String snippet) {
	}

	private boolean isNewsletterArticle(JournalArticle journalArticle) {
		return structureKeyEqualsIgnoreCase(journalArticle, JournalArticleType.NEWSLETTER_ARTICLE.getDdmStructureKey());
	}

	//TODO: move to shared class
	private boolean isNewsletterIssue(JournalArticle journalArticle) {
		return structureKeyEqualsIgnoreCase(journalArticle, JournalArticleType.NEWSLETTER_ISSUE.getDdmStructureKey());
	}

	//TODO: move to shared class
	private boolean structureKeyEqualsIgnoreCase(JournalArticle journalArticle, String structureKey) {
		String structureKeyFromJournalArticle = journalArticle.getDDMStructureKey();
		_log.fatal(String.format("structureKey = %s", structureKey));

		return structureKeyFromJournalArticle.equalsIgnoreCase(structureKey);
	}

	private static final Log _log = LogFactoryUtil.getLog(JournalArticlePostProcessor.class);

}