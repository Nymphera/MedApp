package med.medapp.repository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PatientRepository {
    private static long PATIENT_ID = 0L;
    private static long APPOINTMENT_ID = 0L;

    private List<PatientEntity> patients = new ArrayList<>();
    private List<AppointmentEntity> appointments;

    public List<PatientEntity> getAll() {
        return patients;
    }

    public Optional<PatientEntity> getById (long id) {
        return patients.stream().filter(pat -> pat.getId() == id).findFirst();
    }

    public void deletePatient (long id) {
        patients.removeIf(pat -> pat.getId() == id);
    }

    public void registerNewPatient (PatientEntity newPatient) {
        newPatient.setId(++PATIENT_ID);
        patients.add(newPatient);
    }

    public void updatePatient(PatientEntity patient) {
        deletePatient(patient.getId());
        patients.add(patient);
    }

    public Optional<AppointmentEntity> getAppointmentById (long id) {
        return appointments.stream().filter(visit -> visit.getId() == id).findFirst();
    }

    public List<AppointmentEntity> getByPatient (long id) {
        return getById(id).get().getAppointments();
    }

    public void regirsterAppointment (AppointmentEntity appointment) {
        appointment.setId(++APPOINTMENT_ID);
        appointments.add(appointment);
    }

    public void deleteAppointment (long id) {
        appointments.removeIf(visit -> visit.getId() == id);
    }

    public void updateAppointment (AppointmentEntity appointment) {
        deleteAppointment(appointment.getId());
        appointments.add(appointment);
    }
}
