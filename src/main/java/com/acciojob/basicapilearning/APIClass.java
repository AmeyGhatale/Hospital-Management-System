package com.acciojob.basicapilearning;

import com.sun.jdi.request.StepRequest;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class APIClass {

    Map<Integer, User> userDb = new HashMap<>();

    @GetMapping("/addUser")
    public String addUser(@RequestParam("userId") int userId,
                          @RequestParam("name") String name,
                          @RequestParam("age") int age)
    {
        User userInfo = new User(userId, name, age);
        if(!userDb.containsKey(userId))
            userDb.put(userId, userInfo);
        else
            return "User is present already";

        return "User has been added";
    }

    @PostMapping("/addUserViaReqeuestBody")
    public String addUserViaRequestBody(@RequestBody User user){
        int key = user.getUserId();
        userDb.put(key, user);

        return "User has been added by PostMapping";
    }

    @GetMapping("/getUser")
    public User getUserInfo( @RequestParam("userId") int userId){
        User user = userDb.get(userId);
        return user;
    }

    @GetMapping("/getWeatherInfo")
    public String weatherInfo(){
        return "Today's weather feels like cloudy.   \nThe temperature is 27 degree";
    }

    @GetMapping("/multiply")
    public String mul(@RequestParam("no1") int no1, @RequestParam("no2") int no2){
        int prod = no1 * no2;
        return "Product of 2 numbers are : "+prod;
    }

}
