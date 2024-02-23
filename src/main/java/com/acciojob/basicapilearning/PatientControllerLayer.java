package com.acciojob.basicapilearning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PatientControllerLayer {
    @Autowired
    private PatientServiceLayer patientServObj;

    @PostMapping("/addPatient")
    public ResponseEntity<String> addPatient(@RequestBody Patient patient)
    {
        String result = patientServObj.addPatientToDb(patient);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("/getPatient/{patientId}/")
    public ResponseEntity<Patient> getPatient(@PathVariable("patientId") int id)
    {
        Patient patient =  patientServObj.getPatient(id);
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @GetMapping("/getAllPatientsList")
    public void getAllPatients(){
            List<Patient> patientList = new ArrayList<>();
            patientList = patientServObj.getAllPatients();

            for(Patient p : patientList)
                System.out.println(p.toString());
    }

    @GetMapping("/getByAge")
    public ResponseEntity<Patient> getByAge(@RequestParam("age") int age)
    {
        Patient patient = patientServObj.getByAge(age);
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @PutMapping("/updatePatientInfo")
    public ResponseEntity<String> updatePatientInfo(@RequestParam("patientId") Integer id, @RequestParam("name") String name,
                                @RequestParam("age") Integer age, @RequestParam("disease") String disease)
    {
        String result =  patientServObj.updatePatient(id, name, age, disease);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PatchMapping("/updatePatientUsingPatch")
    public ResponseEntity<String> updatePatient(@RequestParam("patientId") Integer id, @RequestParam(value = "name", required = false) String name,
                                @RequestParam(value = "age", required = false) Integer age,
                                @RequestParam(value = "disease", required = false) String disease)
    {
        String response;
        if(name!=null)
            response = patientServObj.updatePatientName(id, name);
        else if(disease!=null)
            response = patientServObj.updatePatientDisease(id, disease);
        else
            response = patientServObj.updatePatientAge(id, age);

        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/deletePatient")
    public ResponseEntity<String > deletePatient(@RequestParam("patientId") int id)
    {
        String response =  patientServObj.deletePatient(id);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

}
