package com.medicinestore.MediQueue.Controller;

import com.medicinestore.MediQueue.DTO.AppointmentDTO;
import com.medicinestore.MediQueue.Service.AppointmentService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/appointment")
public class AppointmentController {
    private final AppointmentService appointmentService;


    @PostMapping("/create")
    public ResponseEntity<AppointmentDTO> createAppointment(@RequestBody AppointmentDTO appointmentDTO){
        return ResponseEntity.ok(appointmentService.createAppointment(appointmentDTO));
    }

    @GetMapping("/getAllAppointment")
    public ResponseEntity<List<AppointmentDTO>> getAllAppointment(){
        return ResponseEntity.ok(appointmentService.getAllAppointment());
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<AppointmentDTO>> getAppointmentByDate(@PathVariable LocalDate date) {
        return ResponseEntity.ok(appointmentService.getAppointmentByDate(date));
    }
}
