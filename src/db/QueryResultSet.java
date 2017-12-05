package db;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class QueryResultSet
{
	private SessionFactory sf;
	private Session session;
	private Query<PersPlayer> query;
	private Transaction trans;
	private List<PersPlayer> resultSet;
	
	public QueryResultSet()
	{
		resultSet = new ArrayList<>();
	}
	
	@SuppressWarnings("unchecked")
	public void getEntriesBySteps(int steps)
	{
		try
		{
			sf = new Configuration().configure().buildSessionFactory();
			session = sf.openSession();
			query = session.createQuery("from World_Records where steps = :steps");
			query.setParameter("steps", steps);
			resultSet = query.list();
		}
		catch(HibernateException he)
		{
			he.printStackTrace();
		}
		finally
		{
			session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void getEntriesBySeconds(int seconds)
	{
		try
		{
			sf = new Configuration().configure().buildSessionFactory();
			session = sf.openSession();
			query = session.createQuery("from World_Records where seconds = :sec");
			query.setParameter("sec", seconds);
			resultSet = query.list();
		}
		catch(HibernateException he)
		{
			he.printStackTrace();
		}
		finally
		{
			session.close();
		}
	}
	
	public void addNewEntry(PersPlayer player)
	{
		try
		{
			sf = new Configuration().configure().buildSessionFactory();
			session = sf.openSession();
			trans = session.beginTransaction();
			session.save(player);
			trans.commit();

			Alert message = new Alert(AlertType.INFORMATION);
			message.setHeaderText(null);
			message.setTitle("World Record Database Log");
			message.setContentText("Entry was added successfully!");
			message.showAndWait();
		}
		catch (HibernateException e)
		{
			if (trans != null)
				trans.rollback();
			e.printStackTrace();
		}
		finally
		{
			session.close();
		} 
	}
	
	@SuppressWarnings("unchecked")
	public void getAllEntries()
	{
		try
		{
			sf = new Configuration().configure().buildSessionFactory();
			session = sf.openSession();
			query = session.createQuery("from World_Records");
			resultSet = query.list();
		}
		catch(HibernateException he)
		{
			he.printStackTrace();
		}
		finally
		{
			session.close();
		}
	}
	
	public void updateSteps(String username, int newStepsValue)
	{
		try
		{
			session = sf.openSession();
			trans = session.beginTransaction();
			
			PersPlayer p = session.get(PersPlayer.class, username);
			p.setSteps(newStepsValue);
			session.update(p);
			trans.commit();
		}
		catch (HibernateException e)
		{
			trans.rollback();
			e.printStackTrace();
		}
		finally
		{
			session.close();
		}
	}
	
	public void updateSeconds(String username, int newSecondsValue)
	{
		try
		{
			session = sf.openSession();
			trans = session.beginTransaction();
			
			PersPlayer p = session.get(PersPlayer.class, username);
			p.setSeconds(newSecondsValue);
			session.update(p);
			trans.commit();
		}
		catch (HibernateException e)
		{
			trans.rollback();
			e.printStackTrace();
		}
		finally
		{
			session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void getEntriesByLevel(String level)
	{
		try
		{
			sf = new Configuration().configure().buildSessionFactory();
			session = sf.openSession();
			query = session.createQuery("from World_Records where levelName = :level");
			query.setParameter("level", level);
			resultSet = query.list();
		}
		catch (HibernateException e)
		{
			e.printStackTrace();
		}
		finally
		{
			session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void getEntriesByUsername(String username)
	{
		try
		{
			sf = new Configuration().configure().buildSessionFactory();
			session = sf.openSession();
			query = session.createQuery("from World_Records where username = :username");
			query.setParameter("username", username);
			resultSet = query.list();
		}
		catch (HibernateException e)
		{
			e.printStackTrace();
		}
		finally
		{
			session.close();
		}
	}
	
	

	public List<PersPlayer> getResultSet()
	{
		return resultSet;
	}

	public void setResultSet(List<PersPlayer> resultSet)
	{
		this.resultSet = resultSet;
	}
	
	
}
