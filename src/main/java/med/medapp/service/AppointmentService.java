package med.medapp.service;

import lombok.AllArgsConstructor;
import med.medapp.api.model.Appointment;
import med.medapp.api.model.Patient;
import med.medapp.exception.AppointmentAlreadyDoneException;
import med.medapp.repository.AppointmentEntity;

import med.medapp.repository.AppointmentRepository;
import med.medapp.repository.PatientEntity;
import med.medapp.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AppointmentService {
    private AppointmentRepository appointmentRepository;
    private PatientRepository patientRepository;


    public List<Appointment> getByPatientId(long id) {
        return patientRepository.getById(id).getAppointments()
                .stream().map(this::mapToAppointment)
                .collect(Collectors.toList());
    }

    public Appointment getById(long id) {
        return appointmentRepository.findById(id)
                .map(this::mapToAppointment)
                .orElseThrow(() -> new IllegalStateException("Appointment does not exist"));
    }

    public void registerAppointment(Appointment appointment) {
        // TO DO: checking if patient exists
//       if ( patientService.geById(appointment.getPatientId()) != null) {
           AppointmentEntity entity = AppointmentEntity.builder()
                   .dateOfAppointment(appointment.getDateOfAppointment())
                   .description(appointment.getDescription())
                   .done(false)
                   .build();
           appointmentRepository.save(entity);
//           throw new IllegalStateException("Patient does not exist");
//       }

   }

    public void deleteAppointment(long id) {
        if (appointmentRepository.getById(id).isDone()) {
            throw new AppointmentAlreadyDoneException("TThis visit has already taken place");
        } else {
            appointmentRepository.deleteById(id);
        }
    }


    private Appointment mapToAppointment (AppointmentEntity entity) {
        return Appointment.builder()
                .dateOfAppointment(entity.getDateOfAppointment())
                .patientId(entity.getPatient().getId())
                .description(entity.getDescription())
                .build();
    }


}

