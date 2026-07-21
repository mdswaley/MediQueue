package com.medicinestore.MediQueue.DTO;

import com.medicinestore.MediQueue.Options.DoctorOptions;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDTO {
    private Long id;
    private String patientName;
    private Integer age;
    private String mobileNumber;
    private String email;
    private Integer tokenNumber;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private DoctorOptions doctorOptions;
}
