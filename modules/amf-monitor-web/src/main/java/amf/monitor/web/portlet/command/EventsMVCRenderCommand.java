package amf.monitor.web.portlet.command;

import amf.monitor.web.constants.AmfMonitorWebPortletKeys;

import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.training.amf.monitor.model.Event;
import com.liferay.training.amf.monitor.service.EventLocalService;
import com.liferay.training.amf.monitor.service.event.EventCreateDateComparator;

import java.util.Collections;
import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true,
property = {
		"javax.portlet.name=" + AmfMonitorWebPortletKeys.AmfMonitorWeb, "mvc.command.name=/",
		"mvc.command.name=/events/view"
},
service = MVCRenderCommand.class)
public class EventsMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		SearchContainer eventSearchContainer = createSearchContainer(renderRequest, renderResponse);

		renderRequest.setAttribute("eventSearchContainer", eventSearchContainer);

		return "/view.jsp";
	}

	//TODO: make creation of Search Container a shared method
	private SearchContainer createSearchContainer(RenderRequest request, RenderResponse response) {
		String emptyResultsMessage = "There are no events to display!";
		PortletURL iteratorURL = response.createRenderURL();
		List<String> headerNames = Collections.emptyList();

		SearchContainer<Event> eventSearchContainer = new SearchContainer<>(
			request, iteratorURL, headerNames, emptyResultsMessage);

		eventSearchContainer.setDelta(EVENT_PAGE_SIZE);

		int eventStartIndex = eventSearchContainer.getStart();
		int eventEndIndex = eventSearchContainer.getEnd();

		List<Event> events = getEvents(request, eventStartIndex, eventEndIndex);

		if (events != null)eventSearchContainer.setResults(events);

		int total = _eventLocalService.getEventsCount();
		eventSearchContainer.setTotal(total);

		return eventSearchContainer;
	}

	private List<Event> getEvents(RenderRequest request, int eventStartIndex, int eventEndIndex) {
		User currentUser = null;

		try {
			HttpServletRequest httpServletRequest = PortalUtil.getOriginalServletRequest(
				PortalUtil.getHttpServletRequest(request));

			currentUser = PortalUtil.getUser(httpServletRequest);
		} catch (PortalException e) {
			return null;
		}

		if (currentUser == null) return null;

		String currentUserName = currentUser.getScreenName();
		String eventType = ParamUtil.getString(request, "eventType", "all");

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);

		long groupId = themeDisplay.getScopeGroupId();

		EventCreateDateComparator eventCreateDateComparator = new EventCreateDateComparator(false);

		List<Event> allUserEvents = _eventLocalService.getEvents(groupId, eventStartIndex, eventEndIndex,
																eventCreateDateComparator);

		if (allUserEvents != null && allUserEvents.size() > 0) return allUserEvents;

		if (eventType == null || eventType.equalsIgnoreCase("all"))

			return _eventLocalService.getEvents(currentUserName,
												eventStartIndex, eventEndIndex, new EventCreateDateComparator(false));

		return _eventLocalService.getEvents(currentUserName,
											eventType, eventStartIndex, eventEndIndex,
											new EventCreateDateComparator(false));
	}

	private static final int EVENT_PAGE_SIZE = 20;

	private static final Log _log = LogFactoryUtil.getLog(EventsMVCRenderCommand.class);

	@Reference
	private EventLocalService _eventLocalService;

}