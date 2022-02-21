import telran.validation.constraints.Min;
import telran.validation.constraints.NotEmpty;
import telran.validation.constraints.NotNull;

public class Address {
    @NotEmpty
    String city;
    @NotNull
    String street;
    @Min(1)
    int aprt;
    public Address(String city, String street, int aprt) {
        this.city = city;
        this.street = street;
        this.aprt = aprt;
    }
}
