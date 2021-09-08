package med.medapp.service;

import lombok.AllArgsConstructor;
import med.medapp.api.model.Appointment;
import med.medapp.api.model.Patient;
import med.medapp.api.model.UpdatePatient;
import med.medapp.repository.AppointmentEntity;
import med.medapp.repository.PatientEntity;
import med.medapp.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PatientService {
    private PatientRepository patientRepository;

    public void registerNewPatient(Patient patient) {
        PatientEntity entity = PatientEntity.builder()
                .firstName(patient.getFirstName())
                .lastName(patient.getLastName())
                .adress(patient.getAdress())
                .pesel(patient.getPesel())
                .appointments(new ArrayList<>())
                .build();
        patientRepository.registerNewPatient(entity);
    }

    public void updatePatient (UpdatePatient patient) {
        patientRepository.getById(patient.getId())
                .map(pat -> pat.update(patient.getFirstName(), patient.getLastName(), patient.getAdress()))
                .ifPresent(pat -> patientRepository.registerNewPatient(pat));
    }

    public void deletePatient (long id) {
        patientRepository.deletePatient(id);
    }

    public Patient geById (long id) {
        return patientRepository.getById(id)
                .map(this::mapToPatient)
                .orElseThrow(() -> new IllegalStateException("Patient does not exist"));
    }

    public List<Patient> getAll () {
        return patientRepository.getAll()
                .stream().map(this::mapToPatient)
                .collect(Collectors.toList());
    }



    private Patient mapToPatient (PatientEntity entity) {
        return Patient.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .pesel(entity.getPesel())
                .adress(entity.getAdress())
                .appointments(entity.getAppointments().stream()
                .map(visit -> new Appointment(visit.getDateOfAppointment(), visit.getPatientId(), visit.getDoctorId(), visit.getDescription()))
                .collect(Collectors.toList()))
                .build();
    }


}
