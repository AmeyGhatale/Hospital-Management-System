package com.acciojob.basicapilearning;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PatientControllerLayer {
    PatientServiceLayer servObj = new PatientServiceLayer();

    @PostMapping("/addPatient")
    public String addPatient(@RequestBody Patient patient)
    {
        String result = servObj.addPatientToDb(patient);
        return result;
    }

    @GetMapping("/getPatient")
    public Patient getPatient(@RequestParam("patientId") int id)
    {
        return servObj.getPatient(id);
    }

    @GetMapping("/getAllPatientsList")
    public void getAllPatients(){
            List<Patient> patientList = new ArrayList<>();
            patientList = servObj.getAllPatients();

            for(Patient p : patientList)
                System.out.println(p.toString());
    }

    @GetMapping("/getByAge")
    public Patient getByAge(@RequestParam("age") int age)
    {
        Patient patient = servObj.getByAge(age);
        return patient;
    }

    @PutMapping("/updatePatientInfo")
    public String updatePatientInfo(@RequestParam("patientId") Integer id, @RequestParam("name") String name,
                                @RequestParam("age") Integer age, @RequestParam("disease") String disease)
    {
        return servObj.updatePatient(id, name, age, disease);
    }

    @PatchMapping("/updatePatientUsingPatch")
    public String updatePatient(@RequestParam("patientId") Integer id, @RequestParam(value = "name", required = false) String name,
                                @RequestParam(value = "age", required = false) Integer age,
                                @RequestParam(value = "disease", required = false) String disease)
    {
        String response;
        if(name!=null)
            response = servObj.updatePatientName(id, name);
        else if(disease!=null)
            response = servObj.updatePatientDisease(id, disease);
        else
            response = servObj.updatePatientAge(id, age);

        return response;
    }

    @DeleteMapping("/deletePatient")
    public String deletePatient(@RequestParam("patientId") int id)
    {
        return servObj.deletePatient(id);
    }

}
