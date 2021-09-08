package med.medapp.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import med.medapp.api.model.Appointment;
import med.medapp.api.model.Patient;
import med.medapp.service.AppointmentService;
import med.medapp.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointment")
@AllArgsConstructor
public class AppointmentEndpoint {
    private AppointmentService appointmentService;


    @GetMapping
    public List<Appointment> getByPatientId (long id) {
        return  appointmentService.getByPatientId(id);
    }

    @GetMapping ("/{id}")
    public Appointment getById (@PathVariable long id) {
        return appointmentService.getById(id);
    }

    @PostMapping
    @ResponseStatus (HttpStatus.CREATED)
    public void registerAppointment (@RequestBody Appointment newAppointment) {
        appointmentService.registerAppointment(newAppointment);
    }

    @DeleteMapping
    @ResponseStatus (HttpStatus.NO_CONTENT)
    public void deleteAppointment (long id) {
        appointmentService.deleteAppointment(id);
    }

}
