package med.medapp.repository;

import lombok.*;
import med.medapp.api.model.Patient;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "patients")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Basic (optional = false)
    private Long id;
    private String firstName;
    private String lastName;
    private String pesel;
    private String adress;
    @OneToMany (mappedBy = "patient")
    private List<AppointmentEntity> appointments = new ArrayList<>();

    public PatientEntity update(Patient patient) {
        this.firstName = patient.getFirstName();
        this.lastName = patient.getLastName();
        this.adress = patient.getAdress();
        return this;
    }


}
