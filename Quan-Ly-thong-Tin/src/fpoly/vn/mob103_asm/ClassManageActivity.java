package fpoly.vn.mob103_asm;

import java.util.ArrayList;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class ClassManageActivity extends ActionBarActivity {
	Dialog diaClass;
	EditText edId, edClass, edMa, edSub, edCo;
	String stId, stClass, stMa, stSub, stCo;
	Button bnDel, bnSae, bnAddClass;
	ListView txtList;
	ManagementHandler db;
	ArrayAdapter<cClass> ArrClass;
	ArrayList<cClass> ListClass;
	cClass cla;
	Dialog dilo;
	EditText tdId;
	EditText tdClass ;
	EditText tdMajor;
	EditText tdSub;
	EditText tdCo;
	String tId;
	String tClass;
	String tMajor;
	String tSub;
	String tCo ;
	Button bnUpdate;
	Button bnDele ;
	
	public void search(View v){
		Intent in = new Intent(ClassManageActivity.this, SearchActivity.class);
		startActivity(in);
	}
	
	
	
	@Override
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_class_manage);
		
		
		
		db = new ManagementHandler(this,"StudentManagement", null, 2);
		txtList = (ListView) findViewById(R.id.List);
		showClassList();
		
		bnAddClass = (Button) findViewById(R.id.btnAddCla);
		
		bnAddClass.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v){
				diaClass = new Dialog(ClassManageActivity.this);
				diaClass.setContentView(R.layout.diaclass);
				diaClass.setTitle("Add Class");
				
				edId = (EditText) diaClass.findViewById(R.id.edtClasId);
				edClass = (EditText) diaClass.findViewById(R.id.edtClass);
				edMa = (EditText) diaClass.findViewById(R.id.edtMa);
				edSub = (EditText) diaClass.findViewById(R.id.edtSub);
				edCo = (EditText) diaClass.findViewById(R.id.edtCo);
				
				bnSae = (Button) diaClass.findViewById(R.id.btnSae);
				bnDel = (Button) diaClass.findViewById(R.id.btnDel);
				showClassList();
				
				bnDel.setOnClickListener(new Button.OnClickListener(){

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						edId.setText("");
						edClass.setText("");
						edMa.setText("");
						edSub.setText("");
						edCo.setText("");
					}
					
				});
				
				bnSae.setOnClickListener(new Button.OnClickListener(){

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						
						stId = edId.getText().toString();
						stClass = edClass.getText().toString();
						stMa = edMa.getText().toString();
						stSub = edSub.getText().toString();
						stCo = edCo.getText().toString();
						
						if(stId.trim().equalsIgnoreCase("")){
							Toast.makeText(ClassManageActivity.this, "Enter Class ID ! Please !", Toast.LENGTH_LONG).show();
							
						}else if(stClass.trim().equalsIgnoreCase("")){
							Toast.makeText(ClassManageActivity.this, "Enter Class ! Please !", Toast.LENGTH_LONG).show();
						}else if(stMa.trim().equalsIgnoreCase("")){
							Toast.makeText(ClassManageActivity.this, "Enter Major ! Please !", Toast.LENGTH_LONG).show();
						}else if(stSub.trim().equalsIgnoreCase("")){
							Toast.makeText(ClassManageActivity.this, "Enter Subject ! Please !", Toast.LENGTH_LONG).show();
						}else if(stCo.trim().equalsIgnoreCase("")){
							Toast.makeText(ClassManageActivity.this, "Enter Course ! Please !", Toast.LENGTH_LONG).show();
						}else{
							Toast.makeText(ClassManageActivity.this, "Save Successful!", Toast.LENGTH_LONG).show();
							
						    cla = new cClass(stId,stClass,stMa,stSub,Integer.parseInt(stCo));
							//cla.classId = stId;
							//cla.clas = stClass;
							//cla.major = stMa;
							//cla.subject = stSub;
							//cla.course = Integer.parseInt(stCo);
							db.addClass(cla);
							showClassList();
							ArrClass.notifyDataSetChanged();
							diaClass.dismiss();
						}
					}
					
				});
				diaClass.show();
			}
		});
		
		txtList.setOnItemLongClickListener(new OnItemLongClickListener(){

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				cla = ArrClass.getItem(position);
				dilo = new Dialog(ClassManageActivity.this);
				dilo.setContentView(R.layout.select_class);
				dilo.setTitle("Class Information");
				
				
				 tdId = (EditText) dilo.findViewById(R.id.eClasId);
				 tdClass = (EditText) dilo.findViewById(R.id.eClass);
				 tdMajor = (EditText) dilo.findViewById(R.id.eMa);
				 tdSub = (EditText) dilo.findViewById(R.id.eSub);
				 tdCo = (EditText) dilo.findViewById(R.id.eCo);
				
				 tId = cla.classId;
				 tClass = cla.clas;
				 tMajor = cla.major;
				 tSub = cla.subject;
				 tCo = String.valueOf(cla.course);
				
				tdId.setText(tId);
				tdClass.setText(tClass);
				tdMajor.setText(tMajor);
				tdSub.setText(tSub);
				tdCo.setText(tCo);
				
				bnUpdate = (Button) dilo.findViewById(R.id.btnUD);
				bnDele = (Button) dilo.findViewById(R.id.btnDL);
				bnUpdate.setOnClickListener(new Button.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						
						tId = tdId.getText().toString();
						tClass = tdClass.getText().toString();
						tMajor = tdMajor.getText().toString();
						tSub  = tdSub.getText().toString();
						tCo = tdCo.getText().toString();
						
						cla = new cClass(tId,tClass,tMajor,tSub,Integer.parseInt(tCo));
						db.updateClass(cla);
						showClassList();
						dilo.dismiss();
					}
				});
				bnDele.setOnClickListener(new Button.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						db.deleteClass(cla);
						showClassList();
						ArrClass.notifyDataSetChanged();
						dilo.dismiss();
						
					}
				});
				dilo.show();
				return true;
			}
			
		});
		
	}
	
public void showClassList(){
		
		ListClass = db.getAllClass();
		ArrClass = new ArrayAdapter<cClass>(ClassManageActivity.this, android.R.layout.simple_list_item_1, ListClass );
		txtList.setAdapter(ArrClass);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.class_manage, menu);
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