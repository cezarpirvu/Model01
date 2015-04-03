package ro.pub.cs.systems.pdsd.model01;

import java.io.ObjectOutputStream.PutField;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter.ViewBinder;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Model01SecondaryActivity extends Activity {

	//vector pentru butoane
	final protected int buttons[] = {
		R.id.buttonA,
		R.id.buttonB,
		R.id.buttonC,
		R.id.buttonD,
		R.id.buttonE,
		R.id.buttonF,
		R.id.buttonG,
		R.id.buttonH,
		R.id.buttonI
	};
	
	//clasa listeneri
	protected class MyListener implements View.OnClickListener {
		@Override
		public void onClick(View view) {
			
			EditText concatenate = (EditText)findViewById(R.id.concat);
			
			if (view instanceof Button) {
				
				//cancel
				if (view.getId() == R.id.cancel) {
					setResult(RESULT_CANCELED, new Intent());
					finish();
				}
				
				//butoane
				for (int i=0; i<buttons.length; i++) {
					if (view.getId() == buttons[i]) {
						String value = ((Button) view).getText().toString();
						concatenate.setText(concatenate.getText().toString() + value);
					}
				}
				
				//ok
				if (view.getId() == R.id.ok) {
					Intent intent = new Intent("ro.pub.cs.systems.pdsd.model01.Model01ThirdActivity");
					String value = concatenate.getText().toString();
					intent.putExtra("cuvant", value);
					startActivityForResult(intent, 2016);
				}
			}
			
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_model01_secondary);
		
		//listeneri
		MyListener ml = new MyListener();
		//pt butoane
		for (int i=0; i<buttons.length; i++) {
			Button button = (Button)findViewById(buttons[i]);
			button.setOnClickListener(ml);
		}
		//pt cancel
		Button cancel = (Button)findViewById(R.id.cancel);
		cancel.setOnClickListener(ml);
		//pt ok
		Button ok = (Button)findViewById(R.id.ok);
		ok.setOnClickListener(ml);
		
		EditText concatenate = (EditText)findViewById(R.id.concat);
		//sectiune pentru salvare
		if (savedInstanceState != null) {
			String value = savedInstanceState.getString("concatenare");
			if (value != null) {
				concatenate.setText(value);
			} else {
				concatenate.setText("");
			}
		} else {
			concatenate.setText("");
		}
	}
	
	//salvare context
	@Override
	protected void onSaveInstanceState(Bundle saveInstanceState) {
		super.onSaveInstanceState(saveInstanceState);
		
		EditText concatenare = (EditText)findViewById(R.id.concat);
		saveInstanceState.putString("concatenare", concatenare.getText().toString());
	};
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.model01_secondary, menu);
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
