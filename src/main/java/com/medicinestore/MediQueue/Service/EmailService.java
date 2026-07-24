package com.medicinestore.MediQueue.Service;

import com.medicinestore.MediQueue.Entity.Appointment;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendAppointmentMail(Appointment appointment) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(appointment.getEmail());
        message.setSubject("Appointment Confirmation");

        message.setText(
                """
                Dear %s,

                Your appointment has been successfully booked.

                Doctor: %s
                Date: %s
                Time: %s
                Token Number: %d
                
                Please arrive at least 10 minutes before your scheduled appointment.
                
                Thank you.
                """
                        .formatted(
                                appointment.getPatientName(),
                                appointment.getDoctorOptions(),
                                appointment.getAppointmentDate(),
                                appointment.getAppointmentTime(),
                                appointment.getTokenNumber()
                        )
        );

        mailSender.send(message);
    }
}
