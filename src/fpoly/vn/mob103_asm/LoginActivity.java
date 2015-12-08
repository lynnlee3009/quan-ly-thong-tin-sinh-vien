package fpoly.vn.mob103_asm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends ActionBarActivity {
	String filename = "mydata";
	CheckBox chek;
		
	public void test(View v){
			
	EditText dtUser = (EditText) findViewById(R.id.edtUser);
			
	EditText dtPass = (EditText) findViewById(R.id.edtPass);
			
	String strUser = dtUser.getText().toString();
			
	String strPass = dtPass.getText().toString();
			
			
	if(strUser.trim().equalsIgnoreCase("")){
				
		Toast.makeText(this, "Username Not Null! Please Try Again!", Toast.LENGTH_LONG).show();
		
	}else if(strPass.trim().equalsIgnoreCase("")){
				
		Toast.makeText(this, "Password Not Null! Please Try Again!", Toast.LENGTH_LONG).show();
			
	}else if(!strUser.trim().equalsIgnoreCase("admin")){
				
		Toast.makeText(this, "Username Not Match! Please Try Again!", Toast.LENGTH_LONG).show();
			
	}else if(!strPass.trim().equalsIgnoreCase("admin")){
				
		Toast.makeText(this, "Password Not Match! Please Try Again!", Toast.LENGTH_LONG).show();
				
			
	}else{
				
		Toast.makeText(this, "Login Successful!", Toast.LENGTH_LONG).show();
				
		Intent in = new Intent(LoginActivity.this, HomeActivity.class);
				
		startActivity(in);
				
		finish();
			
	}
		
	}
		
	public void saveLogin(){
			
		SharedPreferences share = getSharedPreferences(filename, MODE_PRIVATE);
			
		SharedPreferences.Editor editor = share.edit();
			
		EditText dtUser = (EditText) findViewById(R.id.edtUser);
			
		EditText dtPass = (EditText) findViewById(R.id.edtPass);
			
		String strUser = dtUser.getText().toString();
		
		String strPass = dtPass.getText().toString();
			
		boolean chk = chek.isChecked();
			
	if(!chk){
				
		editor.clear();
			
	}else{
				
		editor.putString("username", strUser);
				
		editor.putString("password", strPass);
				
		editor.putBoolean("savelogin", chk);
			
	}
			
		editor.commit();
			
		
	}
		
	public void restor(){
			
		SharedPreferences sharePre = getSharedPreferences(filename, MODE_PRIVATE);
			
		EditText dtUser = (EditText) findViewById(R.id.edtUser);
			
		EditText dtPass = (EditText) findViewById(R.id.edtPass);
			
		boolean chk = sharePre.getBoolean("savelogin", false);
			
	if(chk){
				
		String user = sharePre.getString("username", "");
	    		
		String pass = sharePre.getString("password", "");
	    		
		dtUser.setText(user);
	    		
		dtPass.setText(pass);
			
	}
			
		chek.setChecked(chk);
		
	}
		
		
	public void OnPause(){
			
		super.onPause();
			
		saveLogin();
		
	}

	public void OnResume(){
			
		super.onResume();
	
		restor();
		
	}

	public void Reset(View v){
			
			
		EditText dtUser = (EditText) findViewById(R.id.edtUser);
			
		EditText dtPass = (EditText) findViewById(R.id.edtPass);
			
		String strUser = dtUser.getText().toString();
			
		String strPass = dtPass.getText().toString();
			
		dtUser.setText("");
			
		strUser ="";
			
		dtPass.setText("");
			
		strPass = "";
		
	}
	    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
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
