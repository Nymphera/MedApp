package med.medapp.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {
    private long id;
    private String firstName;
    private String lastName;
    private List<Patient> patients;


    
}
