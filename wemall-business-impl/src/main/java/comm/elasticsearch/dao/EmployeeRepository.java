package comm.elasticsearch.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.elasticsearch.entity.Employee;

/**
 * @author linzhiqiang
 */
public interface EmployeeRepository extends ElasticsearchRepository<Employee,String>{
 
    /**
     * 查询雇员信息
     * @param id
     * @return	
     */
    Employee queryEmployeeById(String id);
}
