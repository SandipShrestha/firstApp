package myapp.dao;



import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.dao.DataAccessException;
import myapp.model.Contact;
import myapp.util.DBConnector;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;
public class ContactDAOImpl implements ContactDAO {
	Transaction tx = null;
	Session session;
	List<Contact> list;
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int insert(Contact contact) {
		try
		{
			session = DBConnector.openSession();
			tx=session.beginTransaction();
			session.save(contact);
			session.getTransaction().commit();
			return 1;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
		finally
		{
			session.close();
		}
		// TODO Auto-generated method stub
		/*String name=contact.getName();
		String email=contact.getEmail();
		String address=contact.getAddress();
		String telephone=contact.getTelephone();
		//System.out.println("Name="+name+"\nEmail="+email+"\nAddresss="+address+"\nTelephone="+telephone);
		int rows = jdbcTemplate.update("insert into contact(name,email,address,telephone) values(?,?,?,?)",  name,email, address,telephone);
		return rows;*/
	}

	@Override
	public List<Contact> displayAllContacts() {
		
		//For Hibernate
		
		try
		{
			session = DBConnector.openSession();
			tx=session.beginTransaction();
			list=session.createQuery("from Contact order by empID asc").list();
			System.out.println("List Size="+list.size());
			tx.commit();
			
		}
		catch(Exception e)
		{
			tx.rollback();
			e.printStackTrace();
		}
		finally
		{
			session.close();
		}
		
		return list;
		
		
		// For connectivity using jdbcTemplate
		/*String query="select * from contact";
		List<Map<String,Object>> rows=jdbcTemplate.queryForList(query);
		
		List<Contact> htmlrows=new ArrayList<Contact>();
		for(Map<String,Object> r:rows)
		{
			Contact contact=new Contact();
			contact.setId(Integer.parseInt(String.valueOf(r.get("contact_id"))));
			contact.setName(String.valueOf(r.get("name")));
			contact.setEmail(String.valueOf(r.get("email")));
            contact.setAddress(String.valueOf(r.get("address")));
            contact.setTelephone(String.valueOf(r.get("telephone")));
            htmlrows.add(contact);
	
		}
		return htmlrows;*/
	}

	@Override
	public String deleteContact(String id) 
	{
		try{
			session = DBConnector.openSession();
			tx=session.beginTransaction();
		
		Query query=session.createQuery("from Contact where empID = :id");
		query.setString("id", id)	;
		Contact c=(Contact)query.uniqueResult();
		session.delete(c);
		session.getTransaction().commit();
		return "ok";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "nok";
		}
		finally
		{
			session.close();
		}
		
		//int s=3;
		// TODO Auto-generated method stub
		/*int s=jdbcTemplate.update("Delete from Contact where contact_id="+id);
		if(s>=1)
		{
		return "ok";
		}
		else
		{
			return "nok";
		}*/

	}

	@Override
	public Contact getContact(String id) {
		
		
		//for hibernate use below lines of code:
		
		try
		{
			session = DBConnector.openSession();
			tx=session.beginTransaction();
		
		Query query=session.createQuery("from Contact where empID = :id");
		query.setString("id", id)	;
		//query.setInteger("id",id);
			Contact contact=(Contact)query.uniqueResult();
			return contact;
			
		/*	Query query=session.createQuery("from Contact where empID = :id");
			query.setInteger("id",id);
			Contact contact=(Contact)query.uniqueResult();*/
			
			//Contact contact=session.get(Contact.class, id);
			//return contact;
			//Person p=session.load(Person.class, new Integer(id));
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		finally
		{
			session.close();
		}
		
		
		// for jdbctemplate use below lines of code:
			/*String query="select * from contact where contact_id="+id;
			List<Map<String,Object>> rows=jdbcTemplate.queryForList(query);
			Map<String,Object> r =rows.get(0);
			Contact contact=new Contact();
			contact.setId(Integer.parseInt(String.valueOf(r.get("contact_id"))));
			contact.setName(String.valueOf(r.get("name")));
			contact.setEmail(String.valueOf(r.get("email")));
            contact.setAddress(String.valueOf(r.get("address")));
            contact.setTelephone(String.valueOf(r.get("telephone")));*/
           // return contact;
		
	}
	
	
	
@Transactional
	@Override
	public String updateContact(String id,String empName, String mobileNo,String officeNo,String homeNo,String email)
	{
	
    
	// Using Hibernate
	Contact contact=new Contact(id,empName,mobileNo,officeNo,homeNo,email);	
	try
	{
		session = DBConnector.openSession();
		tx=session.beginTransaction();
		session.update(contact);
		session.getTransaction().commit();
		return "ok";
	}
	catch(Exception e)
	{
		e.printStackTrace();
		return "nok";
		
	}
	finally
	{
		session.close();
	}
	//using jdbc
	/*int s = jdbcTemplate.update("update contact set  name=?,email=?,address=?,telephone=? where contact_id=?",  name,email, address,telephone,id);
		if(s>=1)
		{
		return "ok";
		}
		else
		{
			return "nok";
		}*/
	}

/*
@Override
public void saveMultipleRows(List<Contact> lists) {
	String query="insert into contact(name,email,address,telephone) values(?,?,?,?)";
	List<Object[]> rows=new ArrayList<Object[]>();
	for(Contact contact: lists)
	{
		rows.add(new Object[]{contact.getName(),contact.getEmail(),contact.getAddress(),contact.getTelephone()});
	}
    jdbcTemplate.batchUpdate(query, rows) ;   
}*/
	
}

