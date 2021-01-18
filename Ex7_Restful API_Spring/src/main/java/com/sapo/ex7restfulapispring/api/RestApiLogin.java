package com.sapo.ex7restfulapispring.api;

import com.sapo.ex7restfulapispring.entities.User;
import com.sapo.ex7restfulapispring.jwt.JwtTokenProvider;
import com.sapo.ex7restfulapispring.payload.LoginRequest;
import com.sapo.ex7restfulapispring.payload.LoginResponse;
import com.sapo.ex7restfulapispring.payload.RandomStuff;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin")
public class RestApiLogin {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider tokenProvider;

    public RestApiLogin(AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/login")
    public LoginResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        // Xác thực thông tin người dùng Request lên
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        // Nếu không xảy ra exception tức là thông tin hợp lệ
        // Set thông tin authentication vào Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Trả về jwt cho người dùng.
        String jwt = tokenProvider.generateToken((User) authentication.getPrincipal());
        return new LoginResponse(jwt);
    }

    // Api /api/random yêu cầu phải xác thực mới có thể request
    @PostMapping("/random")
    public RandomStuff randomStuff(){
        return new RandomStuff("JWT Hợp lệ mới có thể thấy được message này");
    }

}
