package comm.elasticsearch.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

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
    
    Employee findByLastName(String lastName);
    
    Employee queryEmployeeByLastName(String lastName);
    List<Employee> findByLastNameAndFirstName(String lastName, String firstName);
    List<Employee> findByLastNameOrFirstName(String lastName, String firstName);
    List<Employee> findByLastNameOrFirstNameLike(String lastName, String firstName);
    Page<Employee> findByLastNameOrFirstNameNot(String lastName, String firstName, Pageable pageable);
}
