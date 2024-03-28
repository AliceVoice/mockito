package mockito.homework.controller;

import mockito.homework.model.Employee;
import mockito.homework.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {
    private final DepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @GetMapping(path = "/{departmentId}/salary/max")
    public Employee maxSalaryDepartmentId(@PathVariable int departmentId) {
        return service.maxSalaryDepartmentId(departmentId);
    }

    @GetMapping(path = "/{departmentId}/salary/min")
    public Employee minSalaryDepartmentId(@PathVariable int departmentId) {
        return service.minSalaryDepartmentId(departmentId);
    }

    @GetMapping(path = "/{departmentId}/employees")
    public Collection<Employee> findByDepartment(@PathVariable int departmentId) {
        return service.findByDepartmentId(departmentId);
    }

    @GetMapping(path = "/employees")
    public Map<Integer, List<Employee>> groupByDepartment() {
        return service.groupByDepartmentId();
    }
}