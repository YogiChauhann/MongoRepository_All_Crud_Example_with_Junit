package com.Repos;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.entities.Student;


@Repository
public interface StudentRepos extends MongoRepository<Student, String> {

}
