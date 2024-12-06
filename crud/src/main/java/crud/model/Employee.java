package crud.model;

import jakarta.persistence.*;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

/**
 * Entidad que representa un empleado y crea una tabla en la base de datos.
 */
@Entity
@Table

public class Employee {
    /**
     * Crea una columna en la tabla para el ID del empleado y la establece como clave primaria que se autoincrementa.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Crea una columna en la tabla para el nombre del empleado y la establece como no nula.
     */
    @Column(unique = true)
    @NotNull
    private String name;
    /**
     * Crea una columna en la tabla para el email del empleado.
     */
    @Email
    private String email;
    private String address;
    private Long phoneNumber;

    /**
     * Constructor vacío,  necesario para JPA.
     */
    public Employee(){

    }
    /**
     * Constructor con todos los campos.
     * @param id ID del empleado.
     * @param name Nombre del empleado.
     * @param email Email del empleado.
     * @param address Dirección del empleado.
     * @param phoneNumber Número de teléfono del empleado.
     */
    public Employee(Long id, String name, String email, String address, Long phoneNumber) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
    /**
     * Constructor sin el campo ID.
     * @param name Nombre del empleado.
     * @param email Email del empleado.
     * @param address Dirección del empleado.
     * @param phoneNumber Número de teléfono del empleado.
     */
    public Employee(String name, String email, String address, Long phoneNumber) {
        this.email = email;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
    // Getters y setters
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}