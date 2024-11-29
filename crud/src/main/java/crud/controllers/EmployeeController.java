package crud.controllers;

import crud.model.Employee;
import crud.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@CrossOrigin(origins = "http://127.0.0.1:5501")
@RestController
@RequestMapping(path="api/v1/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getEmployees(){
        return this.employeeService.getEmployees();
    }

    @PostMapping
    public ResponseEntity<Object> addEmployee(@RequestBody Employee employee){
        return this.employeeService.newEmployee(employee);
    }

    @PutMapping
    public ResponseEntity<Object> updateEmployee(@RequestBody Employee employee){
        return this.employeeService.updateEmployee(employee);
    }
    @DeleteMapping(path ="{employeeId}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable("employeeId") Long id){
        return this.employeeService.deleteEmployee(id);
    }
}
