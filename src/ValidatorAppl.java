import telran.validation.Validator;

import java.time.LocalDate;
import java.util.List;

public class ValidatorAppl {
    public static void main(String[] args) {
        Address addr = new Address("B7", "Yeelim", 3);
        Address addr2 = new Address("", null, 0);
        Employee empl = new Employee(10001, "vova@gmail.com", "vova", LocalDate.now().minusDays(1), LocalDate.now().plusDays(1), addr);
        Employee empl2 = new Employee(9999911, "asdf", "", LocalDate.now(), LocalDate.now(), addr2);
        Validator validator = new Validator();
        List<String> errors = validator.validate(empl2);
        errors.forEach(e->System.out.println(e));
    }
}
