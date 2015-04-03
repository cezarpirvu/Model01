package ro.pub.cs.systems.pdsd.model01;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Model01ThirdActivity extends Activity {

	//clasa pt listener
	protected class MyListener implements View.OnClickListener {
		@Override
		public void onClick(View view) {
			final EditText cuvant = (EditText)findViewById(R.id.cuvant);
			Button show = (Button)findViewById(R.id.show_hide);
			if (view instanceof Button) {
				//se apasa show/hide
				if (view.getId() == R.id.show_hide) {
					String value = show.getText().toString();
					if (value.equals("Show")) {
						TextView text = (TextView)findViewById(R.id.answer);
						int valoare = cuvant.length();
						String contor = Integer.toString(valoare);
						String back = text.getText().toString();
						String total = back + String.valueOf(contor);
						text.setText(total);
						text.setVisibility(View.VISIBLE);
						show.setText("Hide");
					}
					if (value.equals("Hide")) {
						TextView text = (TextView)findViewById(R.id.answer);
						text.setVisibility(View.INVISIBLE);
						String back = text.getText().toString();
						text.setText(back.substring(0, text.length()-1));
						show.setText("Show");
					}
				}
				//se apasa back
				if (view.getId() == R.id.back) {
					setResult(RESULT_CANCELED, new Intent());
					finish();
				}
				//se apasa finish
				if (view.getId() == R.id.finish) {
					//setResult(RESULT_FIRST_USER, new Intent());
					//finish();
					Intent intent = new Intent(Model01ThirdActivity.this, Model01MainActivity.class);
				    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);   
				    startActivity(intent);
				}
			}
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_model01_third);
		
		 final EditText cuvant = (EditText)findViewById(R.id.cuvant);
		
		//primesc cuvantul
		Intent intent = getIntent();
		if (intent != null) {
			String value = intent.getStringExtra("cuvant");
			cuvant.setText(cuvant.getText().toString().replace("", value));
		}
		
		//show/hide
		MyListener ml = new MyListener();
		Button showhide = (Button)findViewById(R.id.show_hide);
		showhide.setOnClickListener(ml);
		Button back = (Button)findViewById(R.id.back);
		back.setOnClickListener(ml);
		Button finish = (Button)findViewById(R.id.finish);
		finish.setOnClickListener(ml);
		Button check = (Button)findViewById(R.id.verificare);
		check.setOnClickListener(ml);
		
		//adaugam watcher sa vedem daca s-a introdus rezultatul corect
		EditText raspuns = (EditText)findViewById(R.id.raspuns);
		raspuns.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				String comp1 = s.toString();
				int lungime = cuvant.length();
				String comp2 = Integer.toString(lungime);
				Button verificare = (Button)findViewById(R.id.verificare);
				if (comp1.equals(comp2)) {
					verificare.setBackgroundColor(Color.GREEN);
				} else {
					verificare.setBackgroundColor(Color.RED);
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
		
		//contextul salvat
		/*
		if (savedInstanceState != null) {
			String value = savedInstanceState.getString("cuvant_salvat");
			if (value != null) {
				cuvant.setText(value);
			} else {
				cuvant.setText("");
			}
		} else {
			cuvant.setText("");
		}
		*/
		
		
	}

	//salvare context
	@Override
	protected void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);
		
		EditText cuvant = (EditText)findViewById(R.id.cuvant);
		savedInstanceState.putString("cuvant_salvat", cuvant.getText().toString());
	};
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.model01_third, menu);
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
