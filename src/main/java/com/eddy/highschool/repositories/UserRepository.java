package com.eddy.highschool.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eddy.highschool.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

	List<User> findAll();
	
	User findByEmail(String email);
	
	User findByUsername(String username);
	
	@Query(value="SELECT User.username "
			+ "FROM User "
			+ "INNER JOIN users_roles ON user.id = users_roles.user_id "
			+ "INNER JOIN role ON users_roles.role_id = role.id "
			+ "WHERE name =\"ROLE_TEACHER\"", nativeQuery = true)
	List<String> findAllUsersTeachers();
	
	@Query(value="SELECT User.username "
			+ "FROM User "
			+ "INNER JOIN users_roles ON user.id = users_roles.user_id "
			+ "INNER JOIN role ON users_roles.role_id = role.id "
			+ "WHERE name =\"ROLE_STUDENT\"", nativeQuery = true)
	List<String> findAllUsersStudents();
}
