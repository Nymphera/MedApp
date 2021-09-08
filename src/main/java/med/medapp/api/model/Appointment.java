package med.medapp.api.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Appointment {
    private LocalDate dateOfAppointment;
    private long patientId;
    private long doctorId;
    private String description;


}
