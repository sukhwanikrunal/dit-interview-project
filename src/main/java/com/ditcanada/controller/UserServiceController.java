package com.ditcanada.controller;

import com.ditcanada.api.request.AddUserRequest;
import com.ditcanada.api.response.BaseResponse;
import com.ditcanada.api.response.DataResponse;
import com.ditcanada.api.response.JwtResponse;

import com.ditcanada.configuration.JwtTokenUtil;
import com.ditcanada.model.User;
import com.ditcanada.service.UserService;
import com.ditcanada.service.impl.JwtUserDetailsServiceimpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin
public class UserServiceController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUserDetailsServiceimpl userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @ApiOperation(value = "Greet the world")
    @GetMapping(value = "/v1/hello", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> sayHello() {
        log.info("Hello, world!! ");
        DataResponse<String> response = new DataResponse<>();
        response.setSuccess(true);
        response.setData("Hello, World!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "Create user", response = DataResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully created user"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying " + "to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to retrieve is not " + "found")})
    @PostMapping(value = "/v1/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> addUser(@RequestBody AddUserRequest request) {
        log.info("Add User");
        if (null == request.getUsername() || "".equalsIgnoreCase(request.getUsername())) {
            return new ResponseEntity<>(new BaseResponse(false, "Enter Username"), HttpStatus.BAD_REQUEST);
        }
        if (null == request.getLastname() || "".equalsIgnoreCase(request.getLastname())) {
            return new ResponseEntity<>(new BaseResponse(false, "Enter Lastname"), HttpStatus.BAD_REQUEST);
        }
        if (null == request.getPassword() || "".equalsIgnoreCase(request.getPassword())) {
            return new ResponseEntity<>(new BaseResponse(false, "Enter Password"), HttpStatus.BAD_REQUEST);
        }
        try {
            User user = userService.addUser(request.getFirstname(),
                    request.getLastname(),
                    request.getUsername(),
                    request.getPassword());

            DataResponse<User> response = new DataResponse<>();
            response.setSuccess(true);
            response.setData(user);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Exception occurred ", e);
            return new ResponseEntity<>(new BaseResponse(false, e.getMessage()), HttpStatus.FORBIDDEN);

        }
    }

    @ApiOperation(value = "Login User based on username and password", response = DataResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully logged in"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying " + "to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to retrieve is not " + "found")})
    @GetMapping(value = "/v1/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> loginUser(@RequestParam String username, @RequestParam String password) throws Exception {
        log.info("Login User");
        if ((null == username || "".equalsIgnoreCase(username))
                || (null == password) || "".equalsIgnoreCase(password)) {
            return new ResponseEntity<>(new BaseResponse(false, "Either username or password is wrong"), HttpStatus.BAD_REQUEST);
        }

        if (userService.isVerifiedUser(username, password)) {

            authenticate(username, password);

            final UserDetails userDetails = userDetailsService
                    .loadUserByUsername(username);

            final String token = jwtTokenUtil.generateToken(userDetails);
            log.info("Generated token for user : {} is {}", username,token);
            return new ResponseEntity<>(new BaseResponse(true), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new BaseResponse(false, "Not a valid user"), HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping(value = "/authenticate")
    public ResponseEntity<BaseResponse> createAuthenticationToken() throws Exception {

        DataResponse<JwtResponse> response = new DataResponse<>();
        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setAuthorized(true);

        response.setSuccess(true);
        response.setData(jwtResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

}


