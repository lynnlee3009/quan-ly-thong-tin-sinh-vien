package fpoly.vn.mob103_asm;

public class Student {
	int id;
	String name;
	String gender;
	String dob;
	String address;
	String email;
	String major;
	
	String classId;
	
	public Student() {
		// TODO Auto-generated constructor stub
	}
	
	 public Student(int id, String name, String gender, String dob, String address, String email, String classId) {
	        this.id = id;
	        this.name = name;
	        this.gender = gender;
	        this.dob = dob;
	        this.address = address;
	        this.email = email;
	        
	        this.classId = classId;
	    }
	 
	 @Override
	 public String toString(){
		 return id+"\n"+name+"\n"+gender+"\n"+dob+"\n"+address+"\n"+email+"\n"+classId;
		 
	 }
	

}
