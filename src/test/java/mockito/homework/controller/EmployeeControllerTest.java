package mockito.homework.controller;

import mockito.homework.model.Employee;
import mockito.homework.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @Test
    public void testAllEmployees() {
        Collection<Employee> employees = Arrays.asList(
                new Employee("Vova", "Dubrovsky", 60000, 1),
                new Employee("Sasha", "Lebedev", 50000, 2)
        );
        when(employeeService.getEmployees()).thenReturn(employees);

        Collection<Employee> result = employeeController.allEmployees();
        assertEquals(employees.size(), result.size());
        assertEquals(employees, result);
    }

    @Test
    public void testAddEmployee() {
        Employee employee = new Employee("Vova", "Dubrovsky", 60000, 1);
        when(employeeService.createEmployee("Vova", "Dubrovsky", 60000, 1)).thenReturn(employee);

        Employee result = employeeController.add("Vova", "Dubrovsky", 60000, 1);
        assertEquals(employee, result);
    }

    @Test
    public void testRemoveEmployee() {
        Employee employee = new Employee("Vova", "Dubrovsky", 60000, 1);
        when(employeeService.removeEmployee("Vova", "Dubrovsky")).thenReturn(employee);

        Employee result = employeeController.remove("Vova", "Dubrovsky");
        assertEquals(employee, result);
    }

    @Test
    public void testFindEmployee() {
        Employee employee = new Employee("Vova", "Dubrovsky", 60000, 1);
        when(employeeService.findEmployee("Vova", "Dubrovsky")).thenReturn(employee);

        Employee result = employeeController.find("Vova", "Dubrovsky");
        assertEquals(employee, result);
    }

    @Test
    public void testMessageNotFound() {
        ResponseEntity<String> result = employeeController.messageNotFound();
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertEquals("Сотрудник не найден.", result.getBody());
    }

    @Test
    public void testMessageIllegalArgument() {
        ResponseEntity<String> result = employeeController.messageIllegalArgument();
        assertEquals(HttpStatus.NON_AUTHORITATIVE_INFORMATION, result.getStatusCode());
        assertEquals("Неверный ввод данных. Не указаны имя или фамилия.", result.getBody());
    }
}