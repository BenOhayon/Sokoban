package boot;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import db.PersPlayer;

public class Run
{
	public static void main(String[] args)
	{
		
		String path = "D:\\Ben\\My Documents\\Java\\External JARs";
		
		String[] strs = path.split("\\\\");
		
		for(String s : strs)
			System.out.println(s);
		
//		Configuration c = new Configuration();
//		c.configure();
//		SessionFactory sessionFactory = c.buildSessionFactory();
//		Session session = sessionFactory.openSession();
//		Transaction t = session.beginTransaction();
//		t.commit();
//		
//		session.close();
//		
//		
//		session = sessionFactory.openSession();
//		Query<PersPlayer> q = session.createQuery("from World_Records order by seconds desc");
//		List<PersPlayer> list = q.list();
//		
//		for(PersPlayer r : list)
//		{
//			System.out.println(r);
//		}
//		
//		session.close();
		

	}
}
