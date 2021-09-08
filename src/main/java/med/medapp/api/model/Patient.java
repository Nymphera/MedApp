package med.medapp.api.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Patient {
    private long id;
    private String firstName;
    private String lastName;
    private String pesel;
    private String adress;
    private List<Appointment> appointments;

}
