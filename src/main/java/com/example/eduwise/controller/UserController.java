package com.example.eduwise.controller;

import com.example.eduwise.model.dto.UserDto;
import com.example.eduwise.response.BaseResponse;
import com.example.eduwise.service.UserService;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @PermitAll
    public BaseResponse<UserDto> creatUser(@Valid @RequestBody UserDto userDto) {
        try {
            return BaseResponse.ok(userService.creatUser(userDto));
        } catch (Exception ex) {
            return BaseResponse.fail();
        }
    }

    @GetMapping
    @PreAuthorize("hasAuthority('USER')")
    public List<UserDto> getAllUser() {
        return userService.getAllUser();
    }

    @GetMapping("/{id}")
    public BaseResponse<UserDto> getByIdUser(@Valid @PathVariable Integer id) {
        try {
            return BaseResponse.success(userService.getByIdUser(id));
        } catch (Exception ex) {
            return BaseResponse.fail();
        }
    }

    @PutMapping("/{id}")
    public void updateByIdUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer id) {
        userService.updateByIdUser(userDto, id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@Valid @PathVariable Integer id) {
        userService.deleteUser(id);
    }
}
