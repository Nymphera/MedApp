package med.medapp.api.model;

import lombok.*;
import org.hibernate.validator.constraints.pl.PESEL;
import org.springframework.lang.NonNull;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Patient {

    private Long id;
    @NotNull (message = "Name is required")
    private String firstName;
    @NotNull (message = "Surname is required")
    private String lastName;
    @PESEL (message = "PESEL should have proper format")
    private String pesel;
    @NotNull (message = "Adress is required")
    private String adress;
    private List<Appointment> appointments;

}
