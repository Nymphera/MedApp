package med.medapp.service;

import lombok.AllArgsConstructor;
import med.medapp.api.model.Appointment;
import med.medapp.api.model.Patient;
import med.medapp.api.model.UpdatePatient;

import med.medapp.repository.PatientEntity;
import med.medapp.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PatientService {
    private PatientRepository patientRepository;

    public void registerNewPatient(Patient patient) {
        PatientEntity entity = mapToEntity(patient);
        patientRepository.save(entity);
    }

    public void updatePatient(Patient patient) {
        patientRepository.findById(patient.getId())
                .ifPresent(pat -> pat.update(patient));
    }

    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }

    public Patient geById(long id) {
        return patientRepository.findById(id)
                .map(this::mapToPatient)
                .orElseThrow(() -> new IllegalStateException("Patient does not exist"));
    }

    public List<Patient> getAll() {
        return patientRepository.findAll()
                .stream().map(this::mapToPatient)
                .collect(Collectors.toList());
    }


    private Patient mapToPatient(PatientEntity entity) {
        return Patient.builder()
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .pesel(entity.getPesel())
                .adress(entity.getAdress())
                .appointments(entity.getAppointments().stream()
                        .map(visit -> new Appointment(visit.getDateOfAppointment(),
                                entity.getId(), visit.getDescription()))
                        .collect(Collectors.toList()))
                .build();
    }

    private PatientEntity mapToEntity(Patient patient) {
        return PatientEntity.builder()
                .firstName(patient.getFirstName())
                .lastName(patient.getLastName())
                .adress(patient.getAdress())
                .pesel(patient.getPesel())
                .appointments(new ArrayList<>())
                .build();
    }


}