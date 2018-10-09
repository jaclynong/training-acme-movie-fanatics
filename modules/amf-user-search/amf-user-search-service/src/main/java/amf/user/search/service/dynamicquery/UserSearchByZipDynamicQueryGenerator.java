package amf.user.search.service.dynamicquery;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Order;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Address;
import com.liferay.portal.kernel.model.User;

public class UserSearchByZipDynamicQueryGenerator {

	public DynamicQuery generateForUserSearchByZip(String zipCode) {
		
	    try {
	    	DynamicQuery addressQuery = DynamicQueryFactoryUtil.forClass(Address.class)
	    			.add(RestrictionsFactoryUtil.eq("primary", true))
	                .add(RestrictionsFactoryUtil.eq("zip", zipCode))
	                .setProjection(ProjectionFactoryUtil.property("userId"));
	    	
	    	Order order = OrderFactoryUtil.asc("firstName");
	    	
	    	DynamicQuery userQuery = DynamicQueryFactoryUtil.forClass(User.class)
	                .add(PropertyFactoryUtil.forName("userId").in(addressQuery))
	                .addOrder(order);
	    	
	    	return userQuery;
	    }
	    catch (Exception e) {
	    	_log.fatal(String.format("UserSearchByZipDynamicQueryGenerator.generateForUserSearchByZip Exception: %s", e.getMessage()));
	    }

	    return null;
	}

	private static final Log _log = LogFactoryUtil.getLog(UserSearchByZipDynamicQueryGenerator.class);
}
