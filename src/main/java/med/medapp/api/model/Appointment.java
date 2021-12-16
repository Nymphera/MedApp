package med.medapp.api.model;

import lombok.*;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Appointment {

    @NotNull (message = "Date is required")
    private LocalDate dateOfAppointment;
    private Long patientId;
    private String description;

    @AssertTrue
    public boolean isDateInFuture () {
        return dateOfAppointment.isAfter(LocalDate.now());
    }



}
