package amf.newsletter.web.portlet;

import amf.newsletter.web.constants.AmfNewsletterWebPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

@Component(
		immediate = true,
		property = {
			"com.liferay.portlet.display-category=category.sample", "com.liferay.portlet.instanceable=false",
			"javax.portlet.display-name=AMF Newsletter Issue", "javax.portlet.init-param.template-path=/",
			"javax.portlet.init-param.view-template=/newsletter-issue/newsletter_issue.jsp",
			"javax.portlet.name=" + AmfNewsletterWebPortletKeys.AmfNewsletterIssueWeb,
			"javax.portlet.resource-bundle=content.Language", "javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
	)
public class AmfNewsletterIssuePortlet extends MVCPortlet {
}