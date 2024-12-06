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
/**
 * Servicio encargado de  gestionar las operaciones (CRUD) de los empleados.
 */
@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private HashMap<String, Object> datos = new HashMap<>();

    /**
     * Constructor para inyectar el repositorio de empleados.
     * @param employeeRepository Repositorio de empleados.
     */
    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * Obtiene la lista de todos los empleados.
     * @return Lista de empleados.
     */
    public List<Employee> getEmployees() {
        return this.employeeRepository.findAll();
    }


    /**
     * Añade un nuevo empleado.
     * @param employee Objeto empleado a añadir.
     * @return Respuesta HTTP con el resultado de la operación.
     */
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

    /**
     * Actualiza un empleado existente.
     * @param employee Objeto empleado con los datos actualizados.
     * @return Respuesta HTTP con el resultado de la operación.
     */
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
    /**
     * Elimina un empleado por su ID.
     * @param id ID del empleado a eliminar.
     * @return Respuesta HTTP con el resultado de la operación.
     */
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
