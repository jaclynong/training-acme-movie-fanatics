package com.liferay.training.amf.monitor.service.permission;

import com.liferay.portal.kernel.security.permission.BaseResourcePermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.ResourcePermissionChecker;

import org.osgi.service.component.annotations.Component;

@Component(
		immediate = true, property = "resource.name=" + EventPermissionChecker.RESOURCE_NAME,
		service = ResourcePermissionChecker.class
	)
public class EventPermissionChecker extends BaseResourcePermissionChecker {

	public static final String RESOURCE_NAME = "com.liferay.training.amf.monitor";

	public static final String VIEW_EVENTS_FOR_ALL_USERS = "VIEW_EVENTS_FOR_ALL_USERS";

	public static boolean contains(PermissionChecker permissionChecker, long classPK, String actionId) {
		return contains(permissionChecker, RESOURCE_NAME, "amfmonitorweb", classPK, actionId);
	}

	@Override
	public Boolean checkResource(PermissionChecker permissionChecker, long classPK, String actionId) {
		return contains(permissionChecker, classPK, actionId);
	}

	private static final String TOP_LEVEL_RESOURCE = "com.liferay.training.amf.monitor";

}