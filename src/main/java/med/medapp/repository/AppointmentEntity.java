package med.medapp.repository;

import lombok.*;
import med.medapp.api.model.Doctor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class AppointmentEntity {
    @Setter
    private long id;
    private LocalDate dateOfAppointment;
    private long patientId;
    private long doctorId;
    private String description;
    private boolean done;

    public AppointmentEntity reschedule (LocalDate newDate) {
        this.dateOfAppointment = newDate;
        return this;
    }

    public AppointmentEntity complete () {
        this.done = true;
        return this;
    }
}
