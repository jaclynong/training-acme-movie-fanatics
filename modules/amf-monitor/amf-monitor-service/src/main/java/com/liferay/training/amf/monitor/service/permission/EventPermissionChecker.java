package com.liferay.training.amf.monitor.service.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.BaseResourcePermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.ResourcePermissionChecker;

import org.osgi.service.component.annotations.Component;

@Component(
		immediate = true, property = "resource.name=" + EventPermissionChecker.RESOURCE_NAME,
		service = ResourcePermissionChecker.class
	)
public class EventPermissionChecker extends BaseResourcePermissionChecker {

	public static void check(PermissionChecker permissionChecker, long groupId, String actionId)
		throws PortalException {

		if (!contains(permissionChecker, groupId, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker.getUserId(), RESOURCE_NAME, groupId, actionId);
		}
	}
	
	/**
	 * Use this check top level resource permissions, returns false if user doesn't have permissions.
	 */
	public static boolean contains(PermissionChecker permissionChecker, long classPK, String actionId) {
		boolean hasPermission = contains(permissionChecker, RESOURCE_NAME, "amfmonitorweb", classPK, actionId);
		_log.fatal(String.format("EventPermissionChecker.contains returned %b", hasPermission));
		return hasPermission;
	}
	
	@Override
	public Boolean checkResource(PermissionChecker permissionChecker, long classPK, String actionId) {
		return contains(permissionChecker, classPK, actionId);
	}

	public static final String VIEW_EVENTS_FOR_ALL_USERS = "VIEW_EVENTS_FOR_ALL_USERS";
	public static final String RESOURCE_NAME = "com.liferay.training.amf.monitor";

	private static final Log _log = LogFactoryUtil.getLog(EventPermissionChecker.class);

}