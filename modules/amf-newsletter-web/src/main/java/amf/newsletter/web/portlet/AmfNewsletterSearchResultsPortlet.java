package amf.newsletter.web.portlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import amf.newsletter.web.constants.AmfNewsletterWebPortletKeys;

@Component(
		immediate = true,
		property = {
			"com.liferay.portlet.display-category=category.sample",
			"com.liferay.portlet.instanceable=false",
			"javax.portlet.init-param.template-path=/",
			"javax.portlet.init-param.view-template=/search/search-newsletters.jsp",
			"javax.portlet.name=" + AmfNewsletterWebPortletKeys.AmfNewsletterSearchResultsWeb,
			"javax.portlet.display-name=AMF Newsletter Search Results",
			"javax.portlet.resource-bundle=content.Language",
			"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
	)
public class AmfNewsletterSearchResultsPortlet extends MVCPortlet {

}
