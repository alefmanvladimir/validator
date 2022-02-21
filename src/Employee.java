import telran.validation.constraints.*;

import java.time.LocalDate;

public class Employee {
    private static final int MAX_VALUE = 999999;
    @Max(value=MAX_VALUE, message="id of Employee can not be greater than " + MAX_VALUE)
    @Min(100000)
    int id;
    @Email
    String email;
    @NotEmpty
    String name;
    @Past
    LocalDate birthDate;
    @Future
    LocalDate jobFinishedDate;
    @Valid
    Address address;
    public Employee(int id, String email, String name, LocalDate birthDate, LocalDate jobFinishedDate, Address address) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.birthDate = birthDate;
        this.jobFinishedDate = jobFinishedDate;
        this.address = address;
    }

}
