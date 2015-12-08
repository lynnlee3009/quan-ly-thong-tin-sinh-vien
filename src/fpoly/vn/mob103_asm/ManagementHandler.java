package fpoly.vn.mob103_asm;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class ManagementHandler extends SQLiteOpenHelper {
	// khai báo bảng student
	
	private static final String TABLE = "Student";
	private static final String KEY_ID = "id";
	private static final String KEY_NAME = "name";
	private static final String KEY_GENDER = "gender";
	private static final String KEY_DOB = "dob";
	private static final String KEY_ADDRESS = "address";
	private static final String KEY_EMAIL = "email";
	
	
	private static final String KEY_CLASSID = "classId";
	

	// khai báo mảng class
	private static final String TABLE_1 = "Class";
	private static final String KEY_ID_1 = "classId";
	private static final String KEY_CLASS_1 = "clas";
	private static final String KEY_MAJOR_1 = "major";
	private static final String KEY_SUBJECT = "subject";
	private static final String KEY_COURSE = "course";
	

	public ManagementHandler(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		//xóa bảng trước khi tạo mới
		db.execSQL("DROP TABLE IF EXISTS " + TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_1);
		
		//khai bao cú pháp SQL tạo bảng
		
		//tao bang class
		String CREATE_TABLE = "CREATE TABLE " + TABLE_1 + "("+ KEY_ID_1 + " TEXT PRIMARY KEY ," + KEY_CLASS_1 + " TEXT," + KEY_MAJOR_1 + " TEXT," + KEY_SUBJECT + " TEXT," + KEY_COURSE + " INTEGER)";
	
		db.execSQL(CREATE_TABLE);
	
		
		//tao bang student
		
		CREATE_TABLE = "CREATE TABLE " + TABLE + "("+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT," + KEY_GENDER + " TEXT," + KEY_DOB + " TEXT," + KEY_ADDRESS + " TEXT," + KEY_EMAIL + " TEXT," + KEY_CLASSID + " TEXT)";
	
		db.execSQL(CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + TABLE);
		onCreate(db);
	}
	
	//lấy tất cả ds
	
	public ArrayList<Student> getAllStudent(){
		ArrayList<Student> listStudent = new ArrayList<Student>();
		
		//khai bao lay ds
		String selectQuery = "SELECT * FROM " + TABLE;
		
		//goi phuong thuc de thao tac tren table
		SQLiteDatabase db = this.getWritableDatabase();
		
		//tao cursor de doc du lieu
		Cursor cur = db.rawQuery(selectQuery, null);
		
		//chuyen con tro ve vi tri ban dau
		if(cur.moveToFirst()){
			do{
				//khai bao biến nhận giá trị đọc từ cur
				int id = Integer.parseInt(cur.getString(0));
				String name = cur.getString(1);
				String gender = cur.getString(2);
				String dob = cur.getString(3);
				String address = cur.getString(4);
				String email = cur.getString(5);
				
				String classId = cur.getString(6);
				
				//khai bao đt student
				Student st = new Student(id,name,gender,dob,address,email,classId);
				 
				//thêm vào ds
				listStudent.add(st);
				
				
			}while(cur.moveToNext());
		}
		return listStudent;
	}
	
	//phuong thuc tim kiem
	
	public ArrayList<Student> getStudent(String find){
		ArrayList<Student> listStudent = new ArrayList<Student>();
		String selectQuery = "SELECT * FROM " + TABLE + " WHERE name like '%" + find + "%'";
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cur = db.rawQuery(selectQuery, null);
		if(cur.moveToFirst()){
			do{
				//khai bao biến nhận giá trị đọc từ cur
				int id = Integer.parseInt(cur.getString(0));
				String name = cur.getString(1);
				String gender = cur.getString(2);
				String dob = cur.getString(3);
				String address = cur.getString(4);
				String email = cur.getString(5);
				
				String classId = cur.getString(6);
				
				//khai bao đt student
				Student st = new Student(id,name,gender,dob,address,email,classId);
				 
				//thêm vào ds
				listStudent.add(st);
				
				
			}while(cur.moveToNext());
		}
		return listStudent;	
	}
 // them 1 student
	public void addStudent(Student st){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues val = new ContentValues();
		val.put(KEY_NAME, st.name);
		val.put(KEY_GENDER, st.gender);
		val.put(KEY_DOB, st.dob);
		val.put(KEY_ADDRESS, st.address);
		val.put(KEY_EMAIL, st.email);
		val.put(KEY_CLASSID, st.classId);
		
		db.insert(TABLE, null, val);
		db.close();
	}
	
// xoa student
	
	public void deleteStudent(Student st){
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE, KEY_ID + " = ?", new String[]{String.valueOf(st.id)} );
	
	}
	
	// nhap 1 student
	
	public int updateStudent(Student st){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues val = new ContentValues();
		val.put(KEY_NAME, st.name);
		val.put(KEY_GENDER, st.gender);
		val.put(KEY_DOB, st.dob);
		val.put(KEY_ADDRESS, st.address);
		val.put(KEY_EMAIL, st.email);
		val.put(KEY_CLASSID, st.classId);
		return db.update(TABLE, val, KEY_ID + " = ?", new String[]{String.valueOf(st.id)} );
	}
	
	// lay ds class
	
	public ArrayList<cClass> getAllClass(){
		ArrayList<cClass> listClass = new ArrayList<cClass>();
		String selectQuery = "SELECT * FROM " + TABLE_1;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cur = db.rawQuery(selectQuery, null);
		if(cur.moveToFirst()){
			do{
				String classId = cur.getString(0);
				String clas = cur.getString(1);
				String major = cur.getString(2);
				String subject = cur.getString(3);
				int course = Integer.parseInt(cur.getString(4));
				cClass cla = new cClass(classId,clas,major,subject,course);
				listClass.add(cla);
			}while(cur.moveToNext());
		}
		return listClass;
	}
	
	// tim class
	public ArrayList<cClass> getClass(String findCla){
		ArrayList<cClass> listClass = new ArrayList<cClass>();
		String selectQuery = "SELECT * FROM " + TABLE_1 + " WHERE classId = '" + findCla + "'";
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cur = db.rawQuery(selectQuery, null);
		if(cur.moveToFirst()){
			do{
				String classId = cur.getString(0);
				String clas = cur.getString(1);
				String major = cur.getString(2);
				String subject = cur.getString(3);
				int course = Integer.parseInt(cur.getString(4));
				cClass cla = new cClass(classId,clas,major,subject,course);
				listClass.add(cla);
			}while(cur.moveToNext());
		}
		return listClass;
	}
	//them lop
	public void addClass(cClass cla){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues val = new ContentValues();
		val.put(KEY_ID_1, cla.classId);
		val.put(KEY_CLASS_1, cla.clas);
		val.put(KEY_MAJOR_1, cla.major);
		val.put(KEY_SUBJECT, cla.subject);
		val.put(KEY_COURSE, cla.course);
		db.insert(TABLE_1, null, val);
		db.close();
	}
	//xoa class
	public void deleteClass(cClass cla){
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_1, KEY_ID_1 + " = ?", new String[]{String.valueOf(cla.classId)});
	}
	
	//cap nhat class
	public int updateClass(cClass cla){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues val = new ContentValues();
		val.put(KEY_ID_1, cla.classId);
		val.put(KEY_CLASS_1, cla.clas);
		val.put(KEY_MAJOR_1, cla.major);
		val.put(KEY_SUBJECT, cla.subject);
		val.put(KEY_COURSE, cla.course);
		return db.update(TABLE_1, val, KEY_ID_1 + " =?", new String[]{String.valueOf(cla.classId)});
	}
}
