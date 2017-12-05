package db;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class Run
{
	private static SessionFactory factory;

	public static void main(String[] args)
	{
		Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
		Configuration configuration = new Configuration();
		configuration.configure();
		factory = configuration.buildSessionFactory();
		// Add a few employee records in the database
		int empID1 = addEmployee("Zara", "Ali", 1000);
		int empID2 = addEmployee("Daisy", "Das", 5000);
		int empID3 = addEmployee("John", "Paul", 10000);
		int empID4 = addEmployee("Jane", "Paul", 8000);
		System.out.println("Employees list:");
		printEmployees();
		System.out.println("Employees whose name start with J:");
		printEmployeesWhoseNameStartsWith("J");
		System.out.println("Updating salary for employee 1");
		updateSalary(empID1, 2333.33);
		System.out.println("Deleting employee 3");
		deleteEmployee(empID3);
		System.out.println("Employees list:");
		printEmployees();
	}

	private static int addEmployee(String fName, String lName, double salary)
	{
		Employee emp = new Employee(fName, lName, salary);
		Transaction tx = null;
		int empID = 0;
		Session session = factory.openSession();
		try
		{
			tx = session.beginTransaction();
			empID = (Integer) session.save(emp);
			tx.commit();
		}
		catch (HibernateException e)
		{
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		}
		finally
		{
			session.close();
		}
		
		return empID;
	}

	// Method to print all employees
	private static void printEmployees() {
		Session session = factory.openSession();
		try {
			Query<Employee> query = session.createQuery("from Employees");
			List<Employee> list = query.list();
			for (Employee e: list) {
				System.out.println(e);
			}
		}
		catch (HibernateException e) {
			e.printStackTrace();
		}
		finally {
			session.close();
		}
	}
	// Method to print all employees whose names start with specified prefix

	private static void printEmployeesWhoseNameStartsWith(String prefix)
	{
		Session session = factory.openSession();
		Query query = session.createQuery("from Employees E where E.first_name LIKE :prefix");
		query.setParameter("prefix", prefix + "%");
		List<Employee> list = query.list();
		for (Employee e: list) {
			System.out.println(e);
		}
		session.close();
	}

	// Method to update a salary for an employee
	private static void updateSalary(int empId, double salary)
	{
		Session session = factory.openSession();
		Transaction tx = null;
		try
		{
			tx = session.beginTransaction();
			Employee emp = session.get(Employee.class, empId);
			emp.setSalary(salary);
			session.update(emp);
			tx.commit();
		}
		
		catch (HibernateException e)
		{
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		}
		finally
		{
			session.close();
		}
	}

	// Method to delete an employee
	private static void deleteEmployee(int empId)
	{
		Session session = factory.openSession();
		Transaction tx = null;
		try
		{
			tx = session.beginTransaction();
			Employee emp = session.get(Employee.class, empId);
			session.delete(emp);
			tx.commit();
		}
		catch (HibernateException e)
		{
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		}
		finally
		{
			session.close();
		}
	}
}
