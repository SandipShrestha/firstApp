package myapp.dao;

import java.util.List;
import myapp.model.Contact;

public interface ContactDAO {
	public List<Contact> displayAllContacts();
	public int insert(Contact contact);
	public Contact getContact(String id);
	public String updateContact(String id,String empName, String mobileNo,String officeNo,String homeNo,String email);
	public String deleteContact(String id);
	
}

