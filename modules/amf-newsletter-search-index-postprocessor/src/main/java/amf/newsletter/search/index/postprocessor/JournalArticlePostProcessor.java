package amf.newsletter.search.index.postprocessor;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.IndexerPostProcessor;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.search.filter.BooleanFilter;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;

@Component(
	immediate = true,
	property = "indexer.class.name=com.liferay.journal.model.JournalArticle",
	service = IndexerPostProcessor.class
)
public class JournalArticlePostProcessor implements IndexerPostProcessor {

	@Override
	public void postProcessContextBooleanFilter(
			BooleanFilter booleanFilter, SearchContext searchContext)
		throws Exception {

		if (_log.isInfoEnabled()) {
			_log.info("postProcessContextBooleanFilter");
		}
	}

	@Override
	public void postProcessContextQuery(
			BooleanQuery contextQuery, SearchContext searchContext)
		throws Exception {

		if (_log.isInfoEnabled()) {
			_log.info("postProcessContextQuery");
		}
	}

	@Override
	public void postProcessDocument(Document document, Object obj)
		throws Exception {
	}

	@Override
	public void postProcessFullQuery(
			BooleanQuery fullQuery, SearchContext searchContext)
		throws Exception {

		if (_log.isInfoEnabled()) {
			_log.info("postProcessFullQuery");
		}
	}

	@Override
	public void postProcessSearchQuery(BooleanQuery searchQuery, BooleanFilter booleanFilter,
			SearchContext searchContext) throws Exception {
	}

	@Override
	public void postProcessSearchQuery(
			BooleanQuery searchQuery, SearchContext searchContext)
		throws Exception {

		if (_log.isInfoEnabled()) {
			_log.info("postProcessSearchQuery");
		}
	}

	@Override
	public void postProcessSummary(
		Summary summary, Document document, Locale locale, String snippet) {

		if (_log.isInfoEnabled()) {
			_log.info("postProcessSummary");
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		JournalArticlePostProcessor.class);

}