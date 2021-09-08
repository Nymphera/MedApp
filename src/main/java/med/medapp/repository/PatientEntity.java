package med.medapp.repository;

import lombok.*;
import med.medapp.api.model.Appointment;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientEntity {
    @Setter
    private long id;
    private String firstName;
    private String lastName;
    private String pesel;
    private String adress;
    private List<AppointmentEntity> appointments = new ArrayList<>();

    public PatientEntity update(String newFirstName, String newLastName, String newAdress) {
        this.firstName = newFirstName;
        this.lastName = newLastName;
        this.adress = newAdress;
        return this;
    }


}
