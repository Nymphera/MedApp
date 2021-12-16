package med.medapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository <AppointmentEntity, Long> {
}
