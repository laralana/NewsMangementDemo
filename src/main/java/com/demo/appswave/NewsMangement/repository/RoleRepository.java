package com.demo.appswave.NewsMangement.repository;

import com.demo.appswave.NewsMangement.entities.Role;
import com.demo.appswave.NewsMangement.enumeration.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(ERole name);
    boolean existsByName(ERole name);


}
