package crud.services;

import crud.EmployeeRepository;
import crud.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private HashMap<String, Object> datos = new HashMap<>();
    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }
    public List<Employee> getEmployees(){
        return this.employeeRepository.findAll();
    }

    public ResponseEntity<Object> newEmployee(Employee employee) {
        Optional<Employee> res = this.employeeRepository.findEmployeeByName(employee.getName());
        if(res.isPresent()){
            datos.put("error", true);
            datos.put("message", "Ya existe un empleado con ese nombre");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        employeeRepository.save(employee);
        datos.put("data", employee);
        datos.put("message", "Se ha guardado con exito");
        return new ResponseEntity<>(
                datos,
                HttpStatus.CREATED
        );

    }


    public ResponseEntity<Object> updateEmployee(Employee employee) {
        Optional<Employee> res = this.employeeRepository.findEmployeeByName(employee.getName());

        if(res.isPresent() && employee.getId()==null){
            datos.put("error", true);
            datos.put("message", "Ya existe un empleado con ese nombre");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        employeeRepository.save(employee);
        datos.put("data", employee);
        datos.put("message", "Se ha actualizado con exito");
        return new ResponseEntity<>(
                datos,
                HttpStatus.CREATED
        );

    }

    public ResponseEntity<Object> deleteEmployee(Long id) {
        if(this.employeeRepository.existsById(id)){
            this.employeeRepository.deleteById(id);
            datos.put("message", "Se ha eliminado con exito");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.ACCEPTED
            );
        }else{
            datos.put("error", true);
            datos.put("message", "No existe un empleado con ese id");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }

    }
}
