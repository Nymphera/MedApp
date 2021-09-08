package med.medapp.api;

import lombok.AllArgsConstructor;
import med.medapp.api.model.Patient;
import med.medapp.api.model.UpdatePatient;
import med.medapp.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/patient")
@AllArgsConstructor
public class PatientEndpoint {

    private PatientService patientService;

    @GetMapping
    public List<Patient> getAl() {
        return patientService.getAll();
    }

    @GetMapping("/{id}")
    public Patient getById(@PathVariable long id) {
        return patientService.geById(id);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public void registerNewPatient (@RequestBody Patient newPatient) {
        patientService.registerNewPatient(newPatient);
    }

    @PutMapping
    public void updatePatient (@RequestBody UpdatePatient patient) {
        patientService.updatePatient(patient);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus (value = HttpStatus.NO_CONTENT  )
    public void deletePatient (@PathVariable long id) {
        patientService.deletePatient(id);
    }
}
