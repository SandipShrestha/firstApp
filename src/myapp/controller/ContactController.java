package myapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import myapp.dao.ContactDAOImpl;
import myapp.model.Contact;
import myapp.util.Status;
@Controller
public class ContactController {
	@Autowired
	ContactDAOImpl cdaoimpl;
	public ContactController()
	{
		System.out.println("Contact Controller Called");
	}
	@RequestMapping(value="/editRecord",method=RequestMethod.POST)
	public ModelAndView editContactRecord(HttpServletRequest request) {
		
		String id=request.getParameter("empID");
		String empName =request.getParameter("empName");
		String mobileNo =request.getParameter("mobileNo");
		String officeNo =request.getParameter("officeNo");
		String homeNo=request.getParameter("homeNo");
		String email=request.getParameter("email");
		
		String s=cdaoimpl.updateContact(id, empName,mobileNo,officeNo,homeNo,email);
		if(s.equals("ok"))
		{
			return new ModelAndView("status","data",new Status("Record Edited Successfully"));
		}
		else
		{
			return new ModelAndView("status","data",new Status("Error While Editing Record"));
		}
	
	}
	@RequestMapping(value="/displayAddRecordPage",method=RequestMethod.GET)
	public ModelAndView mainpage()
	{
		return new ModelAndView("addContact","objContact", new Contact());
	}
	@RequestMapping(value="/addVals",method=RequestMethod.POST)
	public ModelAndView addRecord(@ModelAttribute("objContact")Contact contact)
	{
		
		int c=cdaoimpl.insert(contact);
		if(c>=1)
		{
			return new ModelAndView("status","data",new Status("Record Inserted Successfully"));
		}
		else
		{
			return new ModelAndView("status","data",new Status("Error While Inserting Record"));
		}
		
	}
	@RequestMapping(value="/editForm",method=RequestMethod.GET)
	public ModelAndView editContact(HttpServletRequest request) {
		//int id=Integer.parseInt();
		Contact contact=cdaoimpl.getContact(request.getParameter("id"));
		
			return new ModelAndView("editForm","data",contact);
	}
	
	
	
	
	@RequestMapping(value="/deleteForm",method=RequestMethod.GET)
	public ModelAndView deleteContact(HttpServletRequest request) {
		
		//int id=Integer.parseInt(request.getParameter("id"));
		String s=cdaoimpl.deleteContact(request.getParameter("id"));
		if(s.equals("ok"))
		{
			return new ModelAndView("status","data",new Status("Record Deleted Successfully"));
		}
		else
		{
			return new ModelAndView("status","data",new Status("Error While Deleting Record"));
		}
	
	}
	////////////////////////////////
	
	/////////////////////////////////////
	
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public ModelAndView displayMainPage(ModelAndView model)
	{
		//model.addObject("contactList", cdaoimpl.displayAllContacts());
	//	model.setViewName("main");
		//return model;
		List<Contact> contacts=cdaoimpl.displayAllContacts();
		System.out.println("Size of Contact="+contacts.size());
		for(int i=0;i<contacts.size();i++)
		{
			System.out.println(contacts.get(i).getEmpID());
		}
		return new ModelAndView("main","contactList", contacts);
		//return "main";
	}
}