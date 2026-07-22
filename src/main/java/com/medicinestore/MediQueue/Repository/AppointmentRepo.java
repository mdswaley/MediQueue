package com.medicinestore.MediQueue.Repository;

import com.medicinestore.MediQueue.Entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Long> {
    Optional<Appointment> findByEmailAndAppointmentDate(String email, LocalDate appointmentDate);

    List<Appointment> findByAppointmentDate(LocalDate appointmentDate);
}
