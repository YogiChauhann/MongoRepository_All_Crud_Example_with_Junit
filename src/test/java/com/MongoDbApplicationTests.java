package com;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.Repos.StudentRepos;
import com.entities.Department;
import com.entities.Student;
import com.entities.Subject;

@SpringBootTest
class MongoDbApplicationTests {

	@Autowired
	StudentRepos studentRepos;
	
	@Test
	public void testGetStudent() {
		List<Student> students = studentRepos.findAll();
		students.forEach(p -> System.out.println(">>>>>>>>>>>>>>>>>>"+
		           p.getName()+p.getEmail()+p.getDepartment()+p.getSubjects()
		           +">>>>"+p.getPercentage()));
	}
	
	@Test
	public void testGetStudentById() {
		System.out.println(studentRepos.findById("60fcc8d674be3a1512cfce36").get());
	}
	
	@Test
	public void testUpdateStudentById() {
		Student student = studentRepos.findById("60fcc8d674be3a1512cfce36").get();
		student.setName("steve_yogi");
		studentRepos.save(student);
		System.out.println(student);
	}
	
	@Test
	public void testDeleteStudentById() {
		studentRepos.deleteById("60ff4bbcadafe0073af4ee8b");
		System.out.println(">>>>>>>>" +"delete success");
	}
	
	@Test
	public void testSaveStudent() {
		Student student = new Student();
		student.setName("chacha");
		student.setEmail("dee@gmail.com");
		
		Department department = new Department();
		department.setDepartment_name("Compuetr Science");
		department.setLocation("delhi");
		student.setDepartment(department);
		
		List<Subject> list = new ArrayList<Subject>();
		
	    Subject subject = new Subject();
	    subject.setSubjectName("java");
	    subject.setMarksObtained(70);
	    Subject subject2 = new Subject();
	    subject2.setSubjectName("jpa");
	    subject2.setMarksObtained(80);
	    
	    list.add(subject);
	    list.add(subject2);
		
		student.setSubjects(list);
		
		studentRepos.save(student);
	}
	
	@Test
	public void testFindByName() {
		List<Student> student = studentRepos.findByName("deepak");
		System.out.println(">>>>>>>>>>>>>>>"+student);
		student.forEach(p -> System.out.println(p.getName()));
	}
	
	@Test
	public void testFindByNameAndEmail() {
		List<Student> student = studentRepos.findByNameAndEmail("deepak", "deepak@gmail.com");
		System.out.println(">>>>>>>>>>>>>>>"+student);
		//student.forEach(p -> System.out.println(p.getName()));
	}
	
	@Test
	public void testFindByNameOrEmail() {
		List<Student> student = studentRepos.findByNameOrEmail("ychauhan", "test@mail.com");
		System.out.println(">>>>>>>>>>>>>>>"+student);
		//student.forEach(p -> System.out.println(p.getName()));
	}
	
	@Test
	public void testGetStudentPaging() {
		Pageable pageable = PageRequest.of(0, 3);
		Page<Student> students = studentRepos.findAll(pageable);
		students.forEach(p -> System.out.println(">>>>>>>>>>>>>>>>>>"+p.getName()+p.getEmail()+p.getDepartment()+p.getSubjects()));
	}
	
	@Test
	public void testGetStudentSorting() {
		Sort sort = Sort.by(Sort.Direction.DESC, "name");
		List<Student> students = studentRepos.findAll(sort);
		students.forEach(p -> System.out.println(">>>>>>>>>>>>>>>>>>"+p.getName()+p.getEmail()+p.getDepartment()+p.getSubjects()));
	}
	
	@Test
	public void findAllPagingAndSorting() {
		Pageable pageable = PageRequest.of(1, 5, Direction.DESC, "name");
		studentRepos.findAll(pageable).forEach(p -> System.out.println(p.getName()));

	}
	
	@Test
	public void testFindByDepartmentName() {
		List<Student> student = studentRepos.findByDepartmentDepartmentName("Compuetr Science");
		student.forEach(p -> System.out.println(p.getName()));
	}
	
	@Test
	public void bySubjectName() {
		List<Student> students = studentRepos.findBySubjectsSubjectName("java");
		students.forEach(p -> System.out.println(p.getName()));
	}
	
	@Test
	public void testEmailWithLikeQuery() {
		List<Student> students = studentRepos.findByEmailIsLike("test@");
				students.forEach(p -> System.out.println(p.getName()));
		
	}
	
	@Test
	public void testFindByNameStartWithLike() {
		List<Student> list = studentRepos.findByNameStartsWith("y");
		list.forEach(p -> System.out.println(p.getName()));
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
