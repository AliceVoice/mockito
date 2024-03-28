package mockito.homework.service;

import mockito.homework.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class DepartmentServiceTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private DepartmentService departmentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testMaxSalaryDepartmentId() {
        List<Employee> employees = Arrays.asList(
                new Employee("Vova", "Dubrovsky", 60000, 1),
                new Employee("Sasha", "Lebedev", 70000, 1),
                new Employee("Max", "Kozlov", 55000, 2)
        );
        when(employeeService.getEmployees()).thenReturn(employees);

        Employee result = departmentService.maxSalaryDepartmentId(1);
        assertEquals(70000, result.getSalary());
    }

    @Test
    public void testMinSalaryDepartmentId() {
        List<Employee> employees = Arrays.asList(
                new Employee("Vova", "Dubrovsky", 60000, 1),
                new Employee("Sasha", "Lebedev", 70000, 1),
                new Employee("Max", "Kozlov", 55000, 2)
        );
        when(employeeService.getEmployees()).thenReturn(employees);

        Employee result = departmentService.minSalaryDepartmentId(2);
        assertEquals(55000, result.getSalary());
    }

    @Test
    public void testFindByDepartmentId() {
        List<Employee> employees = Arrays.asList(
                new Employee("Vova", "Dubrovsky", 60000, 1),
                new Employee("Sasha", "Lebedev", 70000, 1),
                new Employee("Max", "Kozlov", 55000, 2)
        );
        when(employeeService.getEmployees()).thenReturn(employees);

        Collection<Employee> result = departmentService.findByDepartmentId(1);
        assertEquals(2, result.size());
        assertEquals(employees.get(0), result.stream().findFirst().get());
        assertEquals(employees.get(1), result.stream().skip(1).findFirst().get());
    }

    @Test
    public void testGroupByDepartmentId() {
        List<Employee> employees = Arrays.asList(
                new Employee("Vova", "Dubrovsky", 60000, 1),
                new Employee("Sasha", "Lebedev", 70000, 1),
                new Employee("Max", "Kozlov", 55000, 2)
        );
        when(employeeService.getEmployees()).thenReturn(employees);

        Map<Integer, List<Employee>> result = departmentService.groupByDepartmentId();
        assertEquals(2, result.size());
        assertEquals(2, result.get(1).size());
        assertEquals(1, result.get(2).size());
    }
}