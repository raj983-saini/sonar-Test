//package com.example.LoginTest.firstTest;
//
//import com.example.LoginTest.Model.Resgister;
//import com.example.LoginTest.entity.Users;
//import com.example.LoginTest.entity.WeatherPOJO;
//import com.example.LoginTest.repo.UserRepo;
//import com.example.LoginTest.service.UserService;
//import com.example.LoginTest.serviceImpl.LoginServiceImpl;
//import com.example.LoginTest.serviceImpl.WeatherApi;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.ArgumentsSource;
//import org.junit.jupiter.params.provider.CsvSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest // use for reload ful context to load bean because without it autoweird not use userrepo = null
//public class First {
//    @Autowired
//    private WeatherApi weatherApi;
//    @Autowired
//    private UserRepo userRepo;
//
//    @Autowired
//    private UserService userService;
//@Disabled
//    @Test //use for methods test
//    public void getWeatherTest(){
//
//        Users byEmail = userRepo.findByEmail("ttgy2216@gmail.com");
//        WeatherPOJO mumbai = weatherApi.getWeather("mumbai");
//
//        assertNotNull(byEmail);
//        assertNotNull(mumbai);
//    }
//    @Disabled
//    @ParameterizedTest
//    @CsvSource({
//            "1,2,4",
//            "3,4,7",
//            "4,6,10"
//    })
//    public  void  getprmaterTest(int a, int b , int predirt){
//        assertEquals(predirt , a+b);
//    }
//    @ParameterizedTest
//    @Disabled
//    @ArgumentsSource(UserArgument.class)
// public  void userstest(Resgister resgister){
//     assertNotNull( userService.createUser(resgister) , "fail msg" )  ;
// }
//
//}
