package myapp.model;

public class Contact {

	String empID,empName,mobileNo,officeNo,homeNo,email;
	
	public Contact()
	{
		
	}

	public Contact(String empID, String empName, String mobileNo, String officeNo, String homeNo, String email) {
		super();
		this.empID = empID;
		this.empName = empName;
		this.mobileNo = mobileNo;
		this.officeNo = officeNo;
		this.homeNo = homeNo;
		this.email = email;
	}

	public String getEmpID() {
		return empID;
	}

	public void setEmpID(String empID) {
		this.empID = empID;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getOfficeNo() {
		return officeNo;
	}

	public void setOfficeNo(String officeNo) {
		this.officeNo = officeNo;
	}

	public String getHomeNo() {
		return homeNo;
	}

	public void setHomeNo(String homeNo) {
		this.homeNo = homeNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
