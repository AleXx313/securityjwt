package ru.mironov.securityjwt.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.mironov.securityjwt.dto.JwtAuthenticationResponse;
import ru.mironov.securityjwt.dto.SignInRequest;
import ru.mironov.securityjwt.dto.SignUpRequest;
import ru.mironov.securityjwt.model.Role;
import ru.mironov.securityjwt.model.User;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceTest {

    @Spy
    JwtService jwtService = new JwtService("vWDxrSylT77HSJM08V5YOSDxwpHXbVGApmjmR16igxYDvpefITlTwHlt8uUmm4Ij7A");
    @Spy
    BCryptPasswordEncoder passwordEncoder;
    @Mock
    UserService userService;
    @Mock
    AuthenticationManager authenticationManager;
    @InjectMocks
    AuthenticationService authenticationService;

    @Test
    public void signUp_shouldSignUpNewUserAndReturnCorrectToken() {
        SignUpRequest request = new SignUpRequest();
        request.setUsername("Tester");
        request.setEmail("test@gmail.com");
        request.setPassword("password");
        when(userService.create(any(User.class))).thenReturn(any(User.class));
        JwtAuthenticationResponse jwt = authenticationService.signUp(request);
        assertThat(jwt).isNotNull();
        String token = jwt.getToken();
        String userName = jwtService.extractUserName(token);
        assertThat(userName).isEqualTo("Tester");
    }

    @Test
    void signIn_shouldSignInUserAndReturnCorrectToken() {
        SignInRequest request = new SignInRequest();
        request.setUsername("Tester");
        request.setPassword("password");
        Authentication authentication = new TestingAuthenticationToken(request.getUsername(), request.getPassword());
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        User user = new User();
        user.setId(1L);
        user.setUsername("Tester");
        user.setEmail("test@gmail.com");
        user.setPassword("password");
        user.setRole(Role.ROLE_USER);
        when(userService.userDetailsService()).thenReturn(username -> user);
        JwtAuthenticationResponse tokenResponse = authenticationService.signIn(request);
        assertThat(tokenResponse).isNotNull();
        String token = tokenResponse.getToken();
        String userName = jwtService.extractUserName(token);
        assertThat(userName).isEqualTo("Tester");
    }
}