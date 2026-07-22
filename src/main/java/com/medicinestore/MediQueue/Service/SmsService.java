package com.medicinestore.MediQueue.Service;

import com.medicinestore.MediQueue.Entity.Appointment;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SmsService {

    private final RestClient restClient;

    @Value("${msg91.auth-key}")
    private String authKey;

    @Value("${msg91.template-id}")
    private String templateId;

    public void sendAppointmentSms(Appointment appointment) {

        Map<String, Object> requestBody = new HashMap<>();

        requestBody.put("template_id", templateId);
        requestBody.put("mobiles", "91" + appointment.getMobileNumber());

        Map<String, String> variables = new HashMap<>();
        variables.put("var1", appointment.getPatientName());
        variables.put("var2", appointment.getDoctorOptions().name());
        variables.put("var3", appointment.getAppointmentDate().toString());
        variables.put("var4", appointment.getAppointmentTime().toString());
        variables.put("var5", String.valueOf(appointment.getTokenNumber()));

        requestBody.put("var", variables);

        try {
            String response = restClient.post()
                    .uri("https://control.msg91.com/api/v5/flow/")
                    .header("authkey", authKey)
                    .body(requestBody)
                    .retrieve()
                    .body(String.class);

            System.out.println("SMS Sent: " + response);

        } catch (Exception e) {
            System.err.println("SMS Sending Failed: " + e.getMessage());
        }
    }
}
