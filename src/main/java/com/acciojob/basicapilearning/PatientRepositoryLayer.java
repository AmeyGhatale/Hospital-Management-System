package com.acciojob.basicapilearning;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.*;

@Repository
public class PatientRepositoryLayer {

    Map<Integer, Patient> patientDb = new HashMap<>();

    public String addPatientToDb(Patient patient){
        int id = patient.getPatientId();
        if(patientDb.containsKey(id))
            return "Patient has already been added" ;

        patientDb.put(id, patient);
        return "Patient has been added to DB";
    }

    public Patient getPatientFromDb(int patientId){
        return patientDb.get(patientId);
    }

    public List<Patient> getAllPatients(){
        List<Patient> patientList = new ArrayList<>();

        for(Patient p : patientDb.values()){
            patientList.add(p);
        }

        return patientList;
    }

    public Map<Integer, Patient> getPatientDb(){
        return patientDb;
    }


}
