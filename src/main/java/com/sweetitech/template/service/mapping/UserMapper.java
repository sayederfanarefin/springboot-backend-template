package com.sweetitech.template.service.mapping;

import com.sweetitech.template.common.exception.EntityNotFoundException;
import com.sweetitech.template.common.util.PasswordUtil;
import com.sweetitech.template.domain.Role;
import com.sweetitech.template.domain.User;
import com.sweetitech.template.domain.dto.UserDTO;
import com.sweetitech.template.service.RoleService;
import com.sweetitech.template.service.implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.sweetitech.template.common.util.PasswordUtil.EncType.BCRYPT_ENCODER;

@Service
public class UserMapper {

    @Autowired
    private RoleService roleService;

    /**
     * Maps DTO to Entity
     *
     * @param dto
     * @return entity
     */
    public User map(UserDTO dto) throws Exception {

        Role role = roleService.findRoleById(dto.getRoleId());
        if (role == null) {
            throw new EntityNotFoundException("Role does not exist");
        }
        User entity = new User();
        entity.setName(dto.getName());
        entity.setDesignation(dto.getDesignation());
        entity.setUsername(dto.getUsername());
        entity.setPassword(PasswordUtil.encryptPassword(dto.getPassword(), BCRYPT_ENCODER, null));
        entity.setPhone(dto.getPhone());
        entity.setRole(role);

        return entity;
    }

}