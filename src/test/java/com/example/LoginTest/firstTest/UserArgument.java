//package com.example.LoginTest.firstTest;
//
//import com.example.LoginTest.Model.Resgister;
//import com.example.LoginTest.entity.Users;
//import net.bytebuddy.description.method.MethodDescription;
//import net.bytebuddy.implementation.MethodCall;
//import org.eclipse.angus.mail.iap.Argument;
//import org.junit.jupiter.api.extension.ExtensionContext;
//import org.junit.jupiter.params.provider.Arguments;
//import org.junit.jupiter.params.provider.ArgumentsProvider;
//
//import java.util.List;
//import java.util.UUID;
//import java.util.stream.Stream;
//
//public class UserArgument  implements ArgumentsProvider {
//
//    @Override
//    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
//        return Stream.of(
//
//
//                Arguments.of(Resgister.builder().email("helloworld@gmail.com" ).username("raj").password(UUID.randomUUID().toString()).build()),
//                Arguments.of(Resgister.builder().email("cello@gmail.com" ).username("raj2").password(UUID.randomUUID().toString()).build())
//        );
//    }
//}
