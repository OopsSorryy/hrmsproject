package hrms.hrms.api.controllers;

import hrms.hrms.core.dataAccess.abstracts.UserDao;
import hrms.hrms.core.entities.AuthRequest;
import hrms.hrms.core.entities.AuthResponse;
import hrms.hrms.core.entities.User;
import hrms.hrms.core.entities.dtos.UserDto;
import hrms.hrms.core.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;




@RestController
@RequestMapping("/auth")
public class AuthController {

    AuthenticationManager authenticationManager;
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }




    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid UserDto request){
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
            User user = (User) authentication.getPrincipal();

            String accessToken = jwtTokenUtil.generateAccessToken(user);
            AuthResponse response = new AuthResponse(user.getEmail(), accessToken);

            return ResponseEntity.ok(response);

        } catch (BadCredentialsException ex) {
            return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        }

    }
}
