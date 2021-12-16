package med.medapp.repository;

import lombok.*;
import med.medapp.api.model.Doctor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table (name = "appointments")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class AppointmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateOfAppointment;
    @ManyToOne
    private PatientEntity patient;
    private long doctorId;
    private String description;
    private boolean done;

    // TO DO: implementation id service and repository
    public AppointmentEntity reschedule (LocalDate newDate) {
        this.dateOfAppointment = newDate;
        return this;
    }

    // TO DO: implementation id service and repository
    public AppointmentEntity complete () {
        this.done = true;
        return this;
    }
}
