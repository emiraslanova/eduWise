package com.example.eduwise.service;

import com.example.eduwise.exceptions.NotFoundException;
import com.example.eduwise.mapper.RoleMapper;
import com.example.eduwise.model.dto.RoleDto;
import com.example.eduwise.model.entity.Role;
import com.example.eduwise.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleService(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    public RoleDto creatRole(RoleDto roleDto) throws NotFoundException {
        try {
            Role role = roleMapper.toRole(roleDto);
            Role savedRole = roleRepository.save(role);
            return roleMapper.toRoleDto(savedRole);
        } catch (Exception ex) {
            throw new NotFoundException("Role not created " + ex.getMessage());
        }

    }

    public List<RoleDto> getAllRole() {
        List<Role> roles = roleRepository.findAll();
        return roleMapper.toRoleDtoList(roles);
    }

    public RoleDto getByIdRole(Integer id) throws NotFoundException {
        try {
            Role role = roleRepository.findById(id).orElse(null);
            return roleMapper.toRoleDto(role);
        } catch (Exception e) {
            throw new NotFoundException("Role not found id" + e.getMessage());
        }
    }

    public void updateRole(RoleDto roleDto, Integer id) throws NotFoundException {
        Optional<Role> optionalRole = roleRepository.findById(id);
        if (optionalRole.isPresent()) {
            Role oldRole = optionalRole.get();
            Role newRole = roleMapper.toRole(roleDto);
            updateRoleFields(oldRole, newRole);
            roleRepository.save(oldRole);
        }
        throw new NotFoundException("Role not update");

    }

    private void updateRoleFields(Role oldRole, Role newRole) {
        oldRole.setName(newRole.getName());
    }

    public void deleteRole(Integer id) {

    }
}
