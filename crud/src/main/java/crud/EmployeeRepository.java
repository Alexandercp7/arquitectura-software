package crud;

import crud.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

    /**
     * Extiende la interfaz JpaRepository para añadir un método personalizado que busca un empleado por su nombre.
     */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findEmployeeByName(String name);
}
