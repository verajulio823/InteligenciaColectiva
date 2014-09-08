package com.unsa.PhotoSharing.persistence.Dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class SessionFactoryUtil 
{
	private static SessionFactory sessionFactory;



	private static SessionFactory initSessionFactory()
	{
		// Annotation and XML
		//sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
		// XML only
		Configuration configuration = new Configuration();
	    configuration.configure();
	    ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
	    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		
		return sessionFactory;

	}
	public static SessionFactory getInstance() 
	{
		if (sessionFactory != null)
			return sessionFactory;
		else return initSessionFactory();
	}

	public Session openSession() 
	{
		return sessionFactory.openSession();
	}

	public Session getCurrentSession() 
	{
		return sessionFactory.getCurrentSession();
	}

	public static void close()
	{
		if (sessionFactory != null)
		{
			sessionFactory.close();
			sessionFactory = null;
		}
	}
}
