package com.eddy.highschool.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eddy.highschool.models.CourseStudent;

@Repository
public interface CourseStudentRepository extends CrudRepository <CourseStudent,Long> {

}
