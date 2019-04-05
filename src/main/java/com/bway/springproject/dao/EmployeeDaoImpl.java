package com.bway.springproject.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bway.springproject.model.Employee;

@Repository
public class EmployeeDaoImpl implements EmployeeDao{
	@Resource
	private SessionFactory sessionFactory;
	
	
	@Override
	@Transactional
	public void addEmployee(Employee e) {
		Session sess = sessionFactory.getCurrentSession();
		sess.save(e);
		
		
	}

	@Override
	@Transactional
	public void deleteEmployee(int id) {
		Session sess = sessionFactory.getCurrentSession();
		Employee e = (Employee) sess.get(Employee.class, id);
		sess.delete(e);
		
	}

	@Override
	@Transactional
	public void updateEmployee(Employee e) {

		Session sess = sessionFactory.getCurrentSession();
		sess.update(e);
		
	}

	@Override
	@Transactional
	public Employee getById(int id) {
		Session sess = sessionFactory.getCurrentSession();
		Employee e = (Employee) sess.get(Employee.class, id);
	
	
		return e;
	}

	@Override
	@Transactional
	public List<Employee> getAll() {

		Session sess = sessionFactory.getCurrentSession();
		Criteria crt = sess.createCriteria(Employee.class);
		
		return crt.list();
	}

}
