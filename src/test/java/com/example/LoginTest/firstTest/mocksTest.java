//package com.example.LoginTest.firstTest;
//
//import com.example.LoginTest.controller.LoginController;
//import com.example.LoginTest.entity.Users;
//import com.example.LoginTest.repo.UserRepo;
//import com.example.LoginTest.serviceImpl.LoginServiceImpl;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.*;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import static org.mockito.Mockito.*;
////
////@SpringBootTest
//public class mocksTest {
////    @Autowired
////    private LoginServiceImpl loginService;
//
//
//// use for inectmock without full context reload
//    @InjectMocks
//    private LoginServiceImpl loginService;
////  use for relaod full bean with context
////  @MockBean
////   private UserRepo userRepo;
//
// //use when we donot reload full context and use injectmock on service
//    @Mock
//    private UserRepo userRepo;
//
//    // use to inject mockinject userrepo in service
//    @BeforeEach
//    @Disabled
//    void injection(){
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    @Disabled
//    public void getLoginTestMock() {
//        when(userRepo.findByEmail("raj")).thenReturn(Users.builder().name("hello raj").password("dfasdfsdf").build());
//        UserDetails raj = loginService.loadUserByUsername("raj");
//        Assertions.assertNotNull(raj);
//    }
//}
