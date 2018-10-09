package amf.user.search.results.web.portlet;

import java.io.IOException;

import javax.portlet.Event;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.ProcessEvent;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import amf.user.search.results.web.constants.AmfUserSearchResultsWebPortletKeys;

/**
 * @author liferay
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + AmfUserSearchResultsWebPortletKeys.AmfUserSearchResultsWeb,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user",
		"javax.portlet.supported-processing-event=ipc.usersearchbyzip;http://my.event.tld/ns"
	},
	service = Portlet.class
)
public class AmfUserSearchResultsWebPortlet extends MVCPortlet {
	
	@ProcessEvent(qname="{http://my.event.tld/ns}ipc.usersearchbyzip")
	public void arrivalDestination(EventRequest request, EventResponse response) {
		
		Event event = request.getEvent();
		String zipCode = (String)event.getValue();
		response.setRenderParameter("zipCode", zipCode);
	}

	private static final Log _log = LogFactoryUtil.getLog(AmfUserSearchResultsWebPortlet.class);
}