package com.wemall.shrio.filterchaindef;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
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
	private final static Logger log = Logger.getLogger(SimpleFilterChainDefinitionsService.class);
	@Autowired
	private ShrioUrlDao shrioUrlDao;

	@Override
	public Map<String, String> initOtherPermission() {
		// extend to load other permission
		Map<String, String> shrioUrlMap = new HashMap<String, String>();
		List<ShrioUrl> shrioUrlList = shrioUrlDao.selectAllShrioUrl();
		if (shrioUrlList != null && shrioUrlList.size() > 0) {
			for (ShrioUrl shrioUrl : shrioUrlList) {
				if (shrioUrl.getUrl() == null || shrioUrl.getUrl().isEmpty()) {
					log.debug("sys_shrio_url表中id为" + shrioUrl.getId() + "的url字段为空，不进行过滤");
					continue;
				}
				if (shrioUrl.getFiltername() == null || shrioUrl.getFiltername().isEmpty()) {
					log.debug("sys_shrio_url表中id为" + shrioUrl.getId() + "的filtername字段为空，不进行过滤");
					continue;
				}
				if (shrioUrl.getDef() == null || shrioUrl.getDef().isEmpty()) {
					log.debug("sys_shrio_url表中id为" + shrioUrl.getId() + "的def字段为空，不进行过滤");
					continue;
				}
				shrioUrlMap.put(shrioUrl.getUrl(), shrioUrl.getFiltername() + "[" + shrioUrl.getDef() + "]");
			}
		}
		return shrioUrlMap;
	}

}