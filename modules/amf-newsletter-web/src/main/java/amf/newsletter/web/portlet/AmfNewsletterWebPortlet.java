package amf.newsletter.web.portlet;

import amf.newsletter.web.constants.AmfNewsletterWebPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

/**
 * @author liferay
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample", "com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=AMF Newsletters", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + AmfNewsletterWebPortletKeys.AmfNewsletterWeb,
		"javax.portlet.resource-bundle=content.Language", "javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class AmfNewsletterWebPortlet extends MVCPortlet {
}