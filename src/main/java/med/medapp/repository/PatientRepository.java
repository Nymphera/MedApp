package med.medapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;



public interface PatientRepository extends JpaRepository<PatientEntity, Long > {


}
