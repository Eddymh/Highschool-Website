package com.eddy.highschool.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eddy.highschool.models.Course;

@Repository
public interface CourseRepository extends CrudRepository<Course,Long> {

}
