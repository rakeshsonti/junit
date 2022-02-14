/**
 * 
 */
package com.unit.testing.junit.controller;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unit.testing.junit.model.Student;

/**
 * @author Rambhajan Sonti 13-Feb-2022 12:01:07 pm
 */
@RestController
@RequestMapping("unit-testing")
public class MainController {
	@Autowired
	JdbcTemplate jdbcTemplate;

	@GetMapping("/welcome")
	public String welcomePage() {

		return "Welcome to junit project";
	}

	@PostMapping("/setStudents")
	public int[][] setStudents(@RequestBody List<Student> req) {
		System.out.println(req);
		int res[][] = batchInsert(req);
		return res;
	}

	public int[][] batchInsert(List<Student> students) {
		String query = "INSERT INTO students(name,subject) VALUES(?,?)";

		int[][] updateCounts = jdbcTemplate.batchUpdate(query, students, 50,
				new ParameterizedPreparedStatementSetter<Student>() {
					public void setValues(PreparedStatement ps, Student argument) throws SQLException {
						ps.setString(1, argument.getName());
						ps.setString(2, argument.getSubject());
					}
				});
		return updateCounts;

	}

	@PostMapping("/setStudent")
	public Integer setStudent(@RequestBody Student req) {
		System.out.println(req);
		String query = "INSERT INTO students(rollNumber,name,subject) VALUES(?,?,?)";
		Object[] params = new Object[] { req.getRollNumber(), req.getName(), req.getSubject() };
		int count = jdbcTemplate.update(query, params);
		return count;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/getAllStudent")
	public List<Student> getAllStudent() {
		List<Student> result = new ArrayList<Student>();
		String query = "SELECT * FROM students";
		result = jdbcTemplate.query(query, new BeanPropertyRowMapper(Student.class));
		return result;
	}

	@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
	@GetMapping("/getOneStudent")
	public Student getOneStudent(@PathParam("roll_number") Integer rollNumber) {
		String query = "SELECT * FROM students WHERE rollNumber=?";
		Object params[] = new Object[] { rollNumber };
		Student result = (Student) jdbcTemplate.queryForObject(query, params, new BeanPropertyRowMapper(Student.class));
		return result;
	}

	@PutMapping("updateStudent/{roll_number}/{subject}/{name}")
	public int updateStudent(@PathVariable("roll_number") Integer roll_number, @PathVariable("subject") String subject,
			@PathVariable("name") String name){
		String updateQuery = "UPDATE students SET name=?,subject=? WHERE rollNumber=?";
		Object[] params = new Object[] { subject, name, roll_number };
		int updateCount = jdbcTemplate.update(updateQuery, params);
		return updateCount;
	}

	@DeleteMapping("deleteStudent/{roll_number}")
	public int deleteStudent(@PathVariable("roll_number") Integer roll_number) {
		String updateQuery = "delete from students WHERE rollNumber=?";
		Object[] params = new Object[] { roll_number };
		int updateCount = jdbcTemplate.update(updateQuery, params);
		return updateCount;
	}

}
