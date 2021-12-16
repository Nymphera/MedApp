package med.medapp.api;

import lombok.AllArgsConstructor;
import med.medapp.api.model.Errors;
import med.medapp.api.model.Patient;
import med.medapp.api.model.UpdatePatient;
import med.medapp.service.PatientService;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

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
    public ResponseEntity registerNewPatient ( @Valid @RequestBody Patient newPatient, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errorMessages = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            Errors errors = Errors.builder().errors(errorMessages).build();
            return ResponseEntity.status(400).body(errors);
        } else {
            patientService.registerNewPatient(newPatient);
            return ResponseEntity.status(201).build();
        }
    }

    @PutMapping
    public void updatePatient (@RequestBody Patient patient) {
        patientService.updatePatient(patient);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus (value = HttpStatus.NO_CONTENT  )
    public void deletePatient (@PathVariable long id) {
        patientService.deletePatient(id);
    }
}