package amf.user.search.web.portlet;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.xml.namespace.QName;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ParamUtil;

import amf.user.search.service.validator.ZipCodeValidator;
import amf.user.search.web.constants.AmfUserSearchWebPortletKeys;

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
		"javax.portlet.name=" + AmfUserSearchWebPortletKeys.AmfUserSearchWeb,
		"javax.portlet.display-name=AMF User Search",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user",
		"javax.portlet.supported-publishing-event=ipc.usersearchbyzip;http://my.event.tld/ns"
	},
	service = Portlet.class
)
public class AmfUserSearchWebPortlet extends MVCPortlet {
	
	@Override
	public void processAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException, PortletException {
		
		String zipCode = ParamUtil.getString(actionRequest, "zipCode");
		
		if (!ZipCodeValidator.isZipCodeValid(zipCode)) 
			return;
		
		QName qName = new QName("http://my.event.tld/ns", "ipc.usersearchbyzip");
		actionResponse.setEvent(qName, zipCode);
		
		actionResponse.setRenderParameter("zipCode", zipCode);
	}
	
	private static final Log _log = LogFactoryUtil.getLog(AmfUserSearchWebPortlet.class);
}