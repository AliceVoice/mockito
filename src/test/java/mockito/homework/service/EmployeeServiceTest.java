package mockito.homework.service;

import mockito.homework.exception.EmployeeAlreadyAddedException;
import mockito.homework.exception.EmployeeNotFoundException;
import mockito.homework.exception.EmployeeStorageIsFullException;
import mockito.homework.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class EmployeeServiceTest {

    @Mock
    private Map<String, Employee> employees;

    @InjectMocks
    private EmployeeService employeeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateEmployee() {
        String firstName = "Vova";
        String lastName = "Dubrovsky";
        int salary = 60000;
        int department = 1;

        when(employees.size()).thenReturn(5);
        when(employees.containsKey(firstName + lastName)).thenReturn(false);

        Employee result = employeeService.createEmployee(firstName, lastName, salary, department);
        assertEquals(firstName, result.getFirstName());
        assertEquals(lastName, result.getLastName());
        assertEquals(salary, result.getSalary());
        assertEquals(department, result.getDepartmentId());
    }

    @Test
    public void testRemoveEmployeeThrowsWhenNotFound() {
        String firstName = "Vova";
        String lastName = "Dubrovsky";

        when(employees.remove(firstName + lastName)).thenReturn(null);

        assertThrows(EmployeeNotFoundException.class, () -> {
            employeeService.removeEmployee(firstName, lastName);
        });
    }

    @Test
    public void testFindEmployeeThrowsWhenNotFound() {
        String firstName = "Vova";
        String lastName = "Dubrovsky";

        when(employees.get(firstName + lastName)).thenReturn(null);

        assertThrows(EmployeeNotFoundException.class, () -> {
            employeeService.findEmployee(firstName, lastName);
        });
    }
}