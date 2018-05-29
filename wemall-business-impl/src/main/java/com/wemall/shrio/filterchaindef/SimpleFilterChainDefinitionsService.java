package com.wemall.shrio.filterchaindef;

import java.util.HashMap;
import java.util.Map;

/**  
 *   
 * 加载第三方角色资源配置服务类  
 *   
 * @author shadow  
 *   
 */  
public class SimpleFilterChainDefinitionsService extends AbstractFilterChainDefinitionsService {  
  
    @Override  
    public Map<String, String> initOtherPermission() {  
        // extend to load other permission  
        return new HashMap<String, String>();  
    }  
  
}  