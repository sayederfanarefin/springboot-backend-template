package com.sweetitech.template.repository;

import com.sweetitech.template.domain.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {

    Role findById(long id);

}
