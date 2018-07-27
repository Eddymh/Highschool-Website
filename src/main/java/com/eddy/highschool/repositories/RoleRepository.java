package com.eddy.highschool.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eddy.highschool.models.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role,Long>{

	//JPA Query Language(JPA)
	List<Role> findAll();
	List<Role> findByName(String name);
}
