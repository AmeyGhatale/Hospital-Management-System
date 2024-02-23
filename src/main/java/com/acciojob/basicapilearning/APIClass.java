package com.acciojob.basicapilearning;

import com.sun.jdi.request.StepRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class APIClass {

    Map<Integer, User> userDb = new HashMap<>();

    @GetMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestParam("userId") int userId,
                                  @RequestParam("name") String name,
                                  @RequestParam("age") int age)
    {
        User userInfo = new User(userId, name, age);
        if(!userDb.containsKey(userId))
            userDb.put(userId, userInfo);
        else
            return new ResponseEntity<>("User is present already", HttpStatus.ALREADY_REPORTED);

        return new ResponseEntity<>("User has been added", HttpStatus.CREATED);
    }

    @PostMapping("/addUserViaReqeuestBody")
    public ResponseEntity<String> addUserViaRequestBody(@RequestBody User user){
        int key = user.getUserId();
        userDb.put(key, user);

        return new ResponseEntity<>("User has been added by PostMapping", HttpStatus.CREATED);
    }

    @GetMapping("/getUser")
    public ResponseEntity<User> getUserInfo( @RequestParam("userId") int userId){
        User user = userDb.get(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

//    @GetMapping("/getWeatherInfo")
//    public ResponseEntity<String> weatherInfo(){
//        return "Today's weather feels like cloudy.   \nThe temperature is 27 degree";
//    }

    @GetMapping("/multiply")
    public String mul(@RequestParam("no1") int no1, @RequestParam("no2") int no2){
        int prod = no1 * no2;
        return "Product of 2 numbers are : "+prod;
    }

}
