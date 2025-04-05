package Pkg_Person;

@SuppressWarnings("serial")
public class Student extends Person{
	private int rollno;
	private int std;
	private String division;
	
	public int getRollno() {
		return rollno;
	}

	public void setRollno(int rollno) {
		this.rollno = rollno;
	}

	public int getStd() {
		return std;
	}

	public void setStd(int std) {
		this.std = std;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}
    
	
	public Student(String name, String emailid, String phoneNO, String address, String dob, int rollno, int std,
			String division) {
		super(name, emailid, phoneNO, address, dob);
		this.rollno = rollno;
		this.std = std;
		this.division = division;
	}

	public Student() {
		super();
	}
	@Override
	public String toString() {
		return "Student [rollno=" + rollno + ", std=" + std + ", division=" + division + ", name=" + name + ", emailid="
				+ emailid + ", phoneNO=" + phoneNO + ", address=" + address + ", dob=" + dob + "]";
	}

	
}