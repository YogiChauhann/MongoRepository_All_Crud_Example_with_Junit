package com.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Repos.StudentRepos;
import com.entities.Student;

@Service
public class StudentService {
	
	@Autowired
	StudentRepos studentRepos;
	
	public List<Student> getAllStudents() {
		return studentRepos.findAll();
	}

}
