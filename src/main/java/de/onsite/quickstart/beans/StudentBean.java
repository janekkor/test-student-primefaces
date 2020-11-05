package de.onsite.quickstart.beans;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import de.onsite.quickstart.model.Student;
import de.onsite.quickstart.service.StudentService;


@Component
@SessionScope
public class StudentBean {

	@Autowired
	private StudentService studentService;

	@SuppressWarnings("unused")
	private List<Student> students;
	
	@PostConstruct
	public void init() {
		students = studentService.retrieveAllStudents();
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}	
}