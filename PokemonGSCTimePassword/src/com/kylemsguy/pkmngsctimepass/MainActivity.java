package com.kylemsguy.pkmngsctimepass;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.os.Build;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}
	
	public void helpTname(View view){
		
	}

	public void submitAction(View view) {
		String name;
		int cash;
		int train_id;
		
		int rawPasswd;

		EditText nameField = (EditText) findViewById(R.id.trainer_name);
		EditText moneyField = (EditText) findViewById(R.id.money);
		EditText idField = (EditText) findViewById(R.id.trainer_id);
		EditText resultField = (EditText) findViewById(R.id.passwd_result);

		name = nameField.getText().toString();
		try {
			cash = Integer.parseInt(moneyField.getText().toString());
		} catch (NumberFormatException e) {
			moneyField.setError("Invalid Input");
			return;
		}
		
		try {
			train_id = Integer.parseInt(idField.getText().toString());
		} catch (NumberFormatException e) {
			idField.setError("Invalid Input");
			return;
		}

		try {
			rawPasswd = PkmnGSCTools.genTimePass(name, cash, train_id);
		} catch (Exception e) {
			nameField.setError("Invalid character in Trainer Name");
			return;
		}
		
		StringBuffer passwd = new StringBuffer();
		for(int i = 0; i < 4 - Math.log10(rawPasswd); i++){
			passwd.append("0");
		}
		
		passwd.append(Integer.toString(rawPasswd));
		
		resultField.setText(passwd.toString());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}
