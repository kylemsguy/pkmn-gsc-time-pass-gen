package com.kylemsguy.pkmngsctimepass;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.AlertDialog;
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
					.add(R.id.container, new MainFragment()).commit();
		}
	}

	public void showNotifDialog(String contents) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(contents);
		builder.setPositiveButton("OK", null);
		builder.setCancelable(true);
		AlertDialog dialog = builder.create();
		dialog.show();
	}

	public void helpTname(View view) {
		String text = "Special Characters:\n" + "To type Pk, enter +.\n"
				+ "To type Mn, enter =\n" + "\n"
				+ "Example: IAmAPkMn => IAmA+=";

		showNotifDialog(text);
	}

	public void submitAction(View view) {
		String name;
		int cash = 0;
		int train_id = 0;
		boolean error = false;

		int rawPasswd;

		EditText nameField = (EditText) findViewById(R.id.trainer_name);
		EditText moneyField = (EditText) findViewById(R.id.money);
		EditText idField = (EditText) findViewById(R.id.trainer_id);
		EditText resultField = (EditText) findViewById(R.id.passwd_result);

		name = nameField.getText().toString();

		if (name.length() == 0) {
			nameField.setError("Invalid Input");
		}

		try {
			cash = Integer.parseInt(moneyField.getText().toString());
		} catch (NumberFormatException e) {
			moneyField.setError("Invalid Input");
			error = true;
		}

		try {
			train_id = Integer.parseInt(idField.getText().toString());
		} catch (NumberFormatException e) {
			idField.setError("Invalid Input");
			error = true;
		}

		if (error) {
			return;
		}

		try {
			rawPasswd = PkmnGSCTools.genTimePass(name, cash, train_id);
		} catch (Exception e) {
			nameField.setError("Invalid character in Trainer Name");
			return;
		}

		StringBuffer passwd = new StringBuffer();
		for (int i = 0; i < 4 - Math.log10(rawPasswd); i++) {
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
		if (id == R.id.help) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
