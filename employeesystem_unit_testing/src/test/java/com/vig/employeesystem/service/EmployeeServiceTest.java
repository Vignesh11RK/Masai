package com.vig.employeesystem.service;
import com.vig.employeesystem.entity.Employee;
import com.vig.employeesystem.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.Arrays;
import org.mockito.MockitoAnnotations;

public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository repository;

    @InjectMocks
    private EmployeeServiceImpl service;

    public EmployeeServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetEmployeeById_Found() {
        Employee employee = new Employee(1L, "John", "IT");
        when(repository.findById(1L)).thenReturn(Optional.of(employee));

        Employee result = service.getEmployeeById(1L);
        assertEquals("John", result.getName());
    }

    @Test
    public void testGetEmployeeById_NotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            service.getEmployeeById(1L);
        });
    }

    @Test
    public void testGetEmployeesByDepartment() {
        List<Employee> list = Arrays.asList(
                new Employee(1L, "John", "IT"),
                new Employee(2L, "Jane", "IT")
        );
        when(repository.findByDepartment("IT")).thenReturn(list);

        List<Employee> result = service.getEmployeesByDepartment("IT");
        assertEquals(2, result.size());
    }
}
