package mockito.homework.controller;

import mockito.homework.model.Employee;
import mockito.homework.service.DepartmentService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class DepartmentControllerTest {

    @Mock
    private DepartmentService departmentService;

    @InjectMocks
    private DepartmentController departmentController;

    @Test
    public void testMaxSalaryDepartmentId() {
        Employee employee = new Employee("Vova", "Dubrovsky", 60000, 1);
        when(departmentService.maxSalaryDepartmentId(1)).thenReturn(employee);

        Employee result = departmentController.maxSalaryDepartmentId(1);
        assertEquals(employee, result);
    }

    @Test
    public void testMinSalaryDepartmentId() {
        Employee employee = new Employee("Sasha", "Lebedev", 50000, 1);
        when(departmentService.minSalaryDepartmentId(1)).thenReturn(employee);

        Employee result = departmentController.minSalaryDepartmentId(1);
        assertEquals(employee, result);
    }

    @Test
    public void testFindByDepartment() {
        List<Employee> employees = Arrays.asList(
                new Employee("Vova", "Dubrovsky", 60000, 1),
                new Employee("Sasha", "Lebedev", 50000, 1)
        );
        when(departmentService.findByDepartmentId(1)).thenReturn(employees);

        Collection<Employee> result = departmentController.findByDepartment(1);
        assertEquals(employees.size(), result.size());
        assertEquals(employees, result);
    }

    @Test
    public void testGroupByDepartment() {
        Map<Integer, List<Employee>> employeeMap = new HashMap<>();
        employeeMap.put(1, Arrays.asList(
                new Employee("Vova", "Dubrovsky", 60000, 1),
                new Employee("Sasha", "Lebedev", 50000, 1)
        ));
        employeeMap.put(2, Arrays.asList(
                new Employee("Vova", "Dubrovsky", 70000, 2),
                new Employee("Sasha", "Lebedev", 55000, 2)
        ));
        when(departmentService.groupByDepartmentId()).thenReturn(employeeMap);

        Map<Integer, List<Employee>> result = departmentController.groupByDepartment();
        assertEquals(employeeMap.size(), result.size());
        assertEquals(employeeMap, result);
    }
}