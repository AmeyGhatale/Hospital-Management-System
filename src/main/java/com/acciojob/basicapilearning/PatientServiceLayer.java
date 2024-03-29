package com.acciojob.basicapilearning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PatientServiceLayer {
    @Autowired
    private PatientRepositoryLayer patientRepoObj;

    public String addPatientToDb(Patient patient){
        return patientRepoObj.addPatientToDb(patient);
    }

    public Patient getPatient(int id){
        return patientRepoObj.getPatientFromDb(id);
    }

    public List<Patient> getAllPatients(){
        List<Patient> patientList = new ArrayList<>();

        patientList = patientRepoObj.getAllPatients();

        return patientList;
    }

    public Patient getByAge(int age){
        List<Patient> patientList = new ArrayList<>();
        patientList = patientRepoObj.getAllPatients();

        for(Patient p : patientList){
            if(p.getAge() == age)
                return p;
        }
        return null;
    }

    public String updatePatient(int id, String name, int age, String disease){
        Patient patient = getPatient(id);
        patient.setName(name);
        patient.setAge(age);
        patient.setDisease(disease);
        patientRepoObj.addPatientToDb(patient);

        return "Patient info has been updated";
    }

    public String updatePatientName(int id, String name){
        Patient patient = getPatient(id);
        patient.setName(name);
        return "Patient's name has been updated";
    }
    public String updatePatientAge(int id, int age){
        Patient patient = getPatient(id);
        patient.setAge(age);
        return "Patient's age has been updated";
    }
    public String updatePatientDisease(int id, String disease){
        Patient patient = getPatient(id);
        patient.setDisease(disease);
        return "Patient's disease name has been updated";
    }

    public String deletePatient(int id){
        Map<Integer, Patient> patient = patientRepoObj.getPatientDb();
        patient.remove(id);
        return "Patient has been removed";
    }


}
