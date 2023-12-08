package com.example.eduwise.controller;

import com.example.eduwise.exceptions.NotFoundException;
import com.example.eduwise.model.dto.RoleDto;
import com.example.eduwise.response.BaseResponse;
import com.example.eduwise.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/role")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public BaseResponse<RoleDto> creatRole(@Valid @RequestBody RoleDto roleDto){
        try{
            return BaseResponse.ok(roleService.creatRole(roleDto));
        }catch (Exception ex){
            return BaseResponse.fail();
        }
    }
    @GetMapping
    public List<RoleDto> getAllRole(){
        return roleService.getAllRole();
    }
    @GetMapping("/{id}")
    public BaseResponse<RoleDto> getByIdRole(@Valid @PathVariable Integer id){
        try {
            return BaseResponse.success(roleService.getByIdRole(id));
        }catch (Exception ex){
            return BaseResponse.fail();
        }
    }
    @PutMapping("/{id}")
    public void updateRole(@Valid @RequestBody RoleDto roleDto,@PathVariable Integer id) throws NotFoundException {
        roleService.updateRole(roleDto,id);
    }
    @DeleteMapping("/{id}")
    public void  deleteRole(@Valid @PathVariable Integer id){
        roleService.deleteRole(id);
    }
}
