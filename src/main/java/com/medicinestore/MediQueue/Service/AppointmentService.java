package com.medicinestore.MediQueue.Service;

import com.medicinestore.MediQueue.DTO.AppointmentDTO;
import com.medicinestore.MediQueue.Entity.Appointment;
import com.medicinestore.MediQueue.Repository.AppointmentRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepo appointmentRepo;
    private final ModelMapper modelMapper;
    private final SmsService smsService;
    private final EmailService emailService;

    public AppointmentDTO createAppointment(AppointmentDTO appointmentDTO){
        Optional<Appointment> exist = appointmentRepo.findByEmailAndAppointmentDate(
                appointmentDTO.getEmail(),
                appointmentDTO.getAppointmentDate()
        );

        if(exist.isPresent()){
            throw new RuntimeException("Appointment already exists for this patient on " +
                    appointmentDTO.getAppointmentDate());
        }

        Appointment appointment = modelMapper.map(appointmentDTO, Appointment.class);

        Appointment save = appointmentRepo.save(appointment);

        try {
            emailService.sendAppointmentMail(save);
        } catch (Exception e) {
            System.err.println("Email sending failed: " + e.getMessage());
        }

        try {
            smsService.sendAppointmentSms(save);
        } catch (Exception e) {
            System.err.println("SMS sending failed: " + e.getMessage());
        }

        return modelMapper.map(save, AppointmentDTO.class);
    }

    public List<AppointmentDTO> getAllAppointment(){
         List<Appointment> appointment = appointmentRepo.findAll();

         return appointment.stream()
                 .map(ap -> modelMapper.map(ap, AppointmentDTO.class))
                 .toList();
    }

    public List<AppointmentDTO> getAppointmentByDate(LocalDate appointmentDate) {
        List<Appointment> appointments = appointmentRepo.findByAppointmentDate(appointmentDate);

        return appointments.stream()
                .map(ap -> modelMapper.map(ap, AppointmentDTO.class))
                .toList();
    }

    public String deleteById(Long id){
        Appointment find = appointmentRepo.findById(id).orElseThrow(
                () -> new RuntimeException("patent is not present with given ID: "+ id)
        );

        appointmentRepo.deleteById(id);

        return "patient deleted with id "+ id;
    }
}

