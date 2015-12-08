package fpoly.vn.mob103_asm;

import java.util.ArrayList;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class StudentManageActivity extends ActionBarActivity {
	Dialog diaStu;
	EditText edName, edId, edGen, edDob, edAdd, edEma,  edCId;
	Button btSave, btClear, btaddstudent;
	String strName, strId, strGen, strDob, strAdd, strEma,
			strCId;
	
	ListView txtView;
	ManagementHandler db;
	ArrayAdapter<Student> ArrStudent;
	ArrayList<Student> ListStudent;
	Student st;
	Dialog dialog ;
	EditText etName;
	EditText etGen;
	EditText etDob;
	EditText etAdd;
	EditText etEma;
	EditText etCid;
	EditText etId;
	String trName;
	String trGen;
	String trDob;
	String trAdd;
	String trEma;
	String trCid;
	String trId;
	Button btUp;
	Button btDel;

	public void search(View v) {
		Intent in = new Intent(StudentManageActivity.this, SearchActivity.class);
		startActivity(in);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_student_manage);
		
		db = new ManagementHandler(this, "StudentManagement", null, 2);
		txtView = (ListView) findViewById(R.id.iList);
		showStudentList();

		btaddstudent = (Button) findViewById(R.id.btnAddStu);
		btaddstudent.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				diaStu = new Dialog(StudentManageActivity.this);
				diaStu.setContentView(R.layout.diastudent);
				diaStu.setTitle("Add Student");

				
				edName = (EditText) diaStu.findViewById(R.id.edtName);
				edGen = (EditText) diaStu.findViewById(R.id.edtGen);
				edDob = (EditText) diaStu.findViewById(R.id.edtDob);
				edAdd = (EditText) diaStu.findViewById(R.id.edtAdd);
				edEma = (EditText) diaStu.findViewById(R.id.edtEmail);
				edCId = (EditText) diaStu.findViewById(R.id.edtCid);

				btSave = (Button) diaStu.findViewById(R.id.btnSave);
				btClear = (Button) diaStu.findViewById(R.id.btnClear);
				showStudentList();
				btClear.setOnClickListener(new Button.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub

						
						edName.setText("");
						edGen.setText("");
						edDob.setText("");
						edAdd.setText("");
						edEma.setText("");
						edCId.setText("");

					}

				});
				
				btSave.setOnClickListener(new Button.OnClickListener() {

					@Override
					public void onClick(View v) {


						strName = edName.getText().toString();
						strGen = edGen.getText().toString();
						strDob = edDob.getText().toString();
						strAdd = edAdd.getText().toString();
						strEma = edEma.getText().toString();
						strCId = edCId.getText().toString();

						if (strName.trim().equalsIgnoreCase("")) {
							Toast.makeText(StudentManageActivity.this,
									"Enter Student ID Please!!!",
									Toast.LENGTH_LONG).show();
						} else if (strGen.trim().equalsIgnoreCase("")) {
							Toast.makeText(StudentManageActivity.this,
									"Enter Gender Please!!!", Toast.LENGTH_LONG)
									.show();

						} else if (strDob.trim().equalsIgnoreCase("")) {
							Toast.makeText(StudentManageActivity.this,
									"Enter DOB Please!!!", Toast.LENGTH_LONG)
									.show();
						} else if (strAdd.trim().equalsIgnoreCase("")) {
							Toast.makeText(StudentManageActivity.this,
									"Enter Address Please!!!",
									Toast.LENGTH_LONG).show();
						} else if (strEma.trim().equalsIgnoreCase("")) {
							Toast.makeText(StudentManageActivity.this,
									"Enter E-mail Please!!!", Toast.LENGTH_LONG)
									.show();
						 
						} else if (strCId.trim().equalsIgnoreCase("")) {
							Toast.makeText(StudentManageActivity.this,
									"Enter Class ID Please!!!",
									Toast.LENGTH_LONG).show();
						} else {
							Toast.makeText(StudentManageActivity.this,
									"Save Successful!", Toast.LENGTH_LONG).show();
							Student st = new Student();
							st.name = strName;
							st.gender = strGen;
							st.dob = strDob;
							st.address = strAdd;
							st.email = strEma;
							st.classId = strCId;
							db.addStudent(st);
							showStudentList();
							ArrStudent.notifyDataSetChanged();
							
							diaStu.dismiss();
						}

					}
				});

				diaStu.show();
			}
		});
		//xoa student
		
		txtView.setOnItemLongClickListener(new OnItemLongClickListener(){
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id){
				st = ArrStudent.getItem(position);
				
				dialog = new Dialog(StudentManageActivity.this);
				dialog.setContentView(R.layout.updatestudent);
				dialog.setTitle("Student Information");
				
				
				
				
				etName = (EditText) dialog.findViewById(R.id.etname);
				etGen = (EditText) dialog.findViewById(R.id.etgen);
				etDob = (EditText) dialog.findViewById(R.id.etdob);
				etAdd = (EditText) dialog.findViewById(R.id.etadd);
				etEma = (EditText) dialog.findViewById(R.id.etemail);
				etCid = (EditText) dialog.findViewById(R.id.etcid);
				
				trId = String.valueOf(st.id);
				trName = st.name;
				trGen = st.gender;
				trDob =st.dob;
				trAdd = st.address;
				trEma = st.email;
				trCid = st.classId;
				
				
				
				// Truyền dữ liệu lên Dialog
				
				etName.setText(st.name);
				etGen.setText(st.gender);
				etDob.setText(st.dob);
				etAdd.setText(st.address);
				etEma.setText(st.email);
				etCid.setText(st.classId);
				
				
				

				btUp = (Button) dialog.findViewById(R.id.btnUpdate);
				btDel = (Button) dialog.findViewById(R.id.btnDelete);
				btUp.setOnClickListener(new Button.OnClickListener(){

					@Override
					public void onClick(View v) {
						
						
						 trName = etName.getText().toString();
						 trGen = etGen.getText().toString();
						 trDob = etDob.getText().toString();
						 trAdd = etAdd.getText().toString();
						 trEma = etEma.getText().toString();
						 trCid = etCid.getText().toString(); 
						
						//st.name = etName.getText().toString();
						//st.gender = etGen.getText().toString();
						//st.dob = etDob.getText().toString();
						//st.address = etAdd.getText().toString();
						//st.email = etEma.getText().toString();
						//st.classId = etCid.getText().toString();
						
						st = new Student(Integer.parseInt(trId),trName,trGen,trDob,trAdd,trEma,trCid);
						db.updateStudent(st);
						showStudentList();
						//ArrStudent.notifyDataSetChanged();
						dialog.dismiss();
						
					}
					
				});
				
				btDel.setOnClickListener(new Button.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						db.deleteStudent(st);
						showStudentList();
						ArrStudent.notifyDataSetChanged();
						dialog.dismiss();
					}
				});
				dialog.show();
				return true;	
				
			}
			});
		
	}
		public void showStudentList(){
			ListStudent = db.getAllStudent();
			txtView = (ListView) findViewById(R.id.iList);
			ArrStudent = new ArrayAdapter<Student>(StudentManageActivity.this,
					android.R.layout.simple_list_item_1, ListStudent);
			txtView.setAdapter(ArrStudent);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.student_manage, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}