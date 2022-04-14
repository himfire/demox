package com.example.wrfx.controller.user;


import com.example.wrfx.persistance.dto.user.SignUpDTO;
import com.example.wrfx.persistance.dto.user.UserDTO;
import com.example.wrfx.services.user.UserServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    Logger _log = LoggerFactory.getLogger(UserController.class);
    private final UserServices userService;

    @Autowired
    public UserController(UserServices userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity getAllUsers(@RequestParam int limit, @RequestParam int offset){
        return new ResponseEntity(userService.getAllUsers(PageRequest.of(offset,limit, Sort.Direction.DESC,"id")),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id){
        return new ResponseEntity(userService.getUserById(id),HttpStatus.OK);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity getByUsername(@PathVariable String username){
        return new ResponseEntity(userService.getUserByUsername(username),HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity getByEmail(@PathVariable String email){
        return new ResponseEntity(userService.getUserByEmail(email),HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity signUp(@Valid @RequestBody SignUpDTO dto){
        return new ResponseEntity(userService.signUp(dto),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateUser(@RequestBody UserDTO dto, @PathVariable Long id){
        return new ResponseEntity(userService.updateUser(dto,id),HttpStatus.OK);
    }
}
