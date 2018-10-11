package amf.user.search.service.permission;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.BaseResourcePermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.ResourcePermissionChecker;

@Component(immediate = true, property = {
		"resource.name=" + UserSearchPermissionChecker.RESOURCE_NAME }, service = ResourcePermissionChecker.class)
public class UserSearchPermissionChecker extends BaseResourcePermissionChecker {

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
		boolean hasPermission = contains(permissionChecker, RESOURCE_NAME, "amfusersearchresultsweb", classPK, actionId);
		_log.fatal(String.format("UserSearchPermissionChecker.contains returned %b", hasPermission));
		return hasPermission;
	}
	
	@Override
	public Boolean checkResource(PermissionChecker permissionChecker, long classPK, String actionId) {
		return contains(permissionChecker, classPK, actionId);
	}

	public static final String VIEW_USER_SEARCH = "VIEW_USER_SEARCH";
	public static final String RESOURCE_NAME = "amf.user.search";

	private static final Log _log = LogFactoryUtil.getLog(UserSearchPermissionChecker.class);
}
