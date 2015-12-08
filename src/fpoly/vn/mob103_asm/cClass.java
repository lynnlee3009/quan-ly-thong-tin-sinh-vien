package fpoly.vn.mob103_asm;

public class cClass {
	String classId;
	String clas;
	String major;
	String subject;
	int course;

	public cClass() {
	}

	public cClass(String classId, String clas, String major, String subject,int course) {
		this.classId = classId;
		this.clas = clas;
		this.major = major;
		this.subject = subject;
		this.course = course;
	}
	@Override
	public String toString(){
		return classId+"\n"+clas+"\n"+major+"\n"+subject+"\n"+course;
	}
}
