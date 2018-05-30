package com.wemall.shrio.filterchaindef;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.wemall.shriourl.dao.ShrioUrlDao;
import com.wemall.shriourl.entity.ShrioUrl;

/**
 * 
 * 加载第三方角色资源配置服务类
 * 
 * @author shadow
 * 
 */
public class SimpleFilterChainDefinitionsService extends AbstractFilterChainDefinitionsService {

	@Autowired
	private ShrioUrlDao shrioUrlDao;

	@Override
	public Map<String, String> initOtherPermission() {
		// extend to load other permission
		Map<String, String> shrioUrlMap = new HashMap<String, String>();
		List<ShrioUrl> shrioUrlList = shrioUrlDao.selectAllShrioUrl();
		if (shrioUrlList != null && shrioUrlList.size() > 0) {
			for (ShrioUrl shrioUrl : shrioUrlList) {
				if (shrioUrl.getUrl() != null && !shrioUrlList.isEmpty()) {
					String def = "";
					if (shrioUrl.getPermissions() != null && !shrioUrl.getPermissions().isEmpty()) {
						def = "perms[" + shrioUrl.getPermissions() + "]";
					}
					if (shrioUrl.getRoles() != null && !shrioUrl.getRoles().isEmpty()) {
						if (!"".equals(def)) {
							def = def + ",";
						}
						def = def + "roles[" + shrioUrl.getRoles()+ "]";
					}
					if (!"".equals(def)) {
						shrioUrlMap.put(shrioUrl.getUrl(), def);
					}
				}
			}
		}
		return shrioUrlMap;
	}

}