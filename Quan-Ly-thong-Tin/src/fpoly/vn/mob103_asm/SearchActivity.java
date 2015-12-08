package fpoly.vn.mob103_asm;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class SearchActivity extends ActionBarActivity {
	Button bnSearch;
	EditText edSearch;
	String stSearch;
	ListView lv;
	List<Student> listStudent;
	ArrayAdapter<Student> stuAdap;
	//List<cClass> listClass;
	//ArrayAdapter<cClass> classAda;
	ManagementHandler db;
	
	public void SeInforStudent(){
		edSearch = (EditText) findViewById(R.id.edtSearch);
		stSearch = edSearch.getText().toString();
		lv = (ListView) findViewById(R.id.txtListView);
		
		listStudent = new ArrayList<Student>();
		listStudent = db.getStudent(stSearch);
		stuAdap = new ArrayAdapter<Student>(this, android.R.layout.simple_list_item_1, listStudent);
		lv.setAdapter(stuAdap);
		
	}
	//public void SeInforClass(){
		//edSearch = (EditText) findViewById(R.id.edtSearch);
		//stSearch = edSearch.getText().toString();
		//listClass = db.getAllClass();

		//lv = (ListView) findViewById(R.id.txtListView);
		//listClass = new ArrayList<cClass>();
		//listClass = db.getClass(stSearch);
		//classAda = new ArrayAdapter<cClass>(this, android.R.layout.simple_list_item_1, listClass);
		//lv.setAdapter(classAda);
//	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		
		 db = new ManagementHandler(this,"StudentManagement", null, 2);
		//ArrayList<Student> getStudent  = new ArrayList<Student>();
		//ArrayList<cClass> getClass = new ArrayList<cClass>();
		//EditText edSearch = (EditText) findViewById(R.id.edtSearch);
		//ListView lv = (ListView) findViewById(R.id.txtListView);
		bnSearch = (Button) findViewById(R.id.btnSearch);
		bnSearch.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SeInforStudent();
				//SeInforClass();
			}
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
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
