package com.bway.springproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bway.springproject.dao.EmployeeDao;
import com.bway.springproject.model.Employee;

@RestController
@RequestMapping("/api/employee")
public class EmployeeRestApi {
	
	@Autowired
	private EmployeeDao edao;
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ResponseEntity<List<Employee>> getAllEmp(){
		
		ResponseEntity<List<Employee>> emplist=  new ResponseEntity<List<Employee>>(edao.getAll(), HttpStatus.OK);
		
		return emplist;
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<Employee> getOneEmp(@PathVariable("id") int id){
		
		ResponseEntity<Employee> emp = new ResponseEntity<Employee> (edao.getById(id), HttpStatus.OK);
		return emp;
	}
	
	@RequestMapping(value="/currency",method=RequestMethod.GET)
	public String getCurrency(){
		
		RestTemplate tmp = new RestTemplate();
		return tmp.getForObject("http://data.fixer.io/api/latest?access_key=b50342fe4f6ecb1c204744ce20066666&format=1", String.class);
		
	}
}
