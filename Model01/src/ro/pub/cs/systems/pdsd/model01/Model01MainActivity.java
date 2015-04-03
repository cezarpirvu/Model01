package ro.pub.cs.systems.pdsd.model01;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.support.v4.widget.SimpleCursorAdapter.ViewBinder;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Model01MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_model01_main);
		
		TextView introducere = (TextView)findViewById(R.id.introducere);
		String value = ("Introduceti numele si parola");
		introducere.setText(value);
		introducere.setTextColor(Color.parseColor("#ffff0000"));
		
		Button submit = (Button)findViewById(R.id.submit);
		
		MyListener ml = new MyListener();
		submit.setOnClickListener(ml);
		
		//portiune salvare context
		EditText password = (EditText)findViewById(R.id.password);
		EditText username = (EditText)findViewById(R.id.username);
		if (savedInstanceState != null) {
			String username_value = savedInstanceState.getString("username");
			if (username_value != null) {
				username.setText(username_value);
			} else {
				username.setText("");
			}
			String password_value = savedInstanceState.getString("password");
			if (password_value != null) {
				password.setText(password_value);
			} else {
				password.setText("");
			}
		} else {
			username.setText("");
			password.setText("");
		}
	}
	
	//clasa pentru listeneri
	protected class MyListener implements View.OnClickListener {
		@Override
		public void onClick(View view) {
			
			if (view instanceof Button) {
				
				if (view.getId() == R.id.submit) {
					
					Button submit = (Button)findViewById(R.id.submit);
					
					EditText username = (EditText)findViewById(R.id.username);
					EditText password = (EditText)findViewById(R.id.password);
					
					String comp1 = username.getText().toString();
					String comp2 = password.getText().toString();
					
					if (comp1 != null && comp2 != null) {
						if (comp1.equals(comp2) && !comp1.equals("") && !comp2.equals("")) {
							//schimbare culoare
							submit.setBackgroundColor(Color.GREEN);
							//intentia
							Intent intent = new Intent("ro.pub.cs.systems.pdsd.model01.Model01SecondaryActivity");
							startActivityForResult(intent, 2015);
						} else {
							submit.setBackgroundColor(Color.RED);
						}
					}
				}
			}	
		}
	}
	
	//clasa pentru salvare context
	@Override
	protected void onSaveInstanceState(Bundle saveInstanceState)  {
		super.onSaveInstanceState(saveInstanceState);
		
		CheckBox checkbox = (CheckBox)findViewById(R.id.checkbox);
		EditText username = (EditText)findViewById(R.id.username);
		EditText password = (EditText)findViewById(R.id.password);
		
		if (checkbox.isChecked()) {
			saveInstanceState.putString("username", username.getText().toString());
			saveInstanceState.putString("password", password.getText().toString());
			saveInstanceState.putBoolean("checkbox", true);
		}
	}
	
	//returneaza result code
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		
		//se reseteaza username si parola
		CheckBox checkBox = (CheckBox)findViewById(R.id.checkbox);
		if (!checkBox.isChecked()) {
			if (resultCode == 0){
				EditText username = (EditText)findViewById(R.id.username);
				EditText password = (EditText)findViewById(R.id.password);
				Button submit = (Button)findViewById(R.id.submit);
				submit.setBackgroundColor(Color.GRAY);
				username.setText("");
				password.setText("");
				//checkBox.setChecked(false);
			}
		} else {
			if (resultCode == 0){
				Button submit = (Button)findViewById(R.id.submit);
				submit.setBackgroundColor(Color.GRAY);
			}
		}
			
		Toast.makeText(this, "The activity returned with result "+resultCode, Toast.LENGTH_LONG).show();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.model01_main, menu);
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
