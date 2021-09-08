package med.medapp.service;

import med.medapp.api.model.Appointment;
import med.medapp.exception.AppointmentAlreadyDoneException;
import med.medapp.repository.AppointmentEntity;
import med.medapp.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService {
    private PatientRepository patientRepository;

    public List<Appointment> getByPatientId(long id) {
        return patientRepository.getByPatient(id)
                .stream().map(this::mapToAppointment)
                .collect(Collectors.toList());
    }

    public Appointment getById(long id) {
        return patientRepository.getAppointmentById(id)
                .map(this::mapToAppointment)
                .orElseThrow(() -> new IllegalStateException("Appointment does not exist"));
    }

    public void registerAppointment(Appointment appointment) {
        AppointmentEntity entity = AppointmentEntity.builder()
                .dateOfAppointment(appointment.getDateOfAppointment())
                .doctorId(appointment.getDoctorId())
                .patientId(appointment.getPatientId())
                .description(appointment.getDescription())
                .done(false)
                .build();
        patientRepository.regirsterAppointment(entity);

//        patientRepository.getById(appointment.getPatientId()).ifPresent(pat -> pat.getAppointments().add(entity));

    }

    public void deleteAppointment(long id) {
        if (patientRepository.getAppointmentById(id).get().isDone()) {
            throw new AppointmentAlreadyDoneException("TThis visit has already taken place");
        } else {
            patientRepository.deleteAppointment(id);
        }
    }


    private Appointment mapToAppointment(AppointmentEntity entity) {
        return Appointment.builder()
                .dateOfAppointment(entity.getDateOfAppointment())
                .patientId(entity.getPatientId())
                .doctorId(entity.getDoctorId())
                .description(entity.getDescription())
                .build();
    }
}
