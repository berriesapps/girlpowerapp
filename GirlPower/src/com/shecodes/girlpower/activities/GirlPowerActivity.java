package com.shecodes.girlpower.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.shecodes.girlpower.R;

/**
 * This activity will display UI that asks the user to enter an input string and
 * click on a share The user can then click one of the following buttons Show:
 * will display the text in a "Toast" message Share: will display a dialogue
 * with possible activities that support sharing text Bounce: will pass on the
 * text to an activity that bounces the text on screen
 */
public class GirlPowerActivity extends Activity {

	private static final String TAG = Activity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d(TAG, "onCreate lifecycle method called");
		super.onCreate(savedInstanceState);

		// the layout for this activity is defined
		// in res/layout/girl_power.xml
		setContentView(R.layout.girl_power_activity);
	}

	public void shareText(View v) {
		// retrieve the user input from the EditText view
		EditText editTextView = (EditText) findViewById(R.id.edittext_id);

		String userInput = editTextView.getText().toString();

		if (isValidString(userInput)) {
			String textToShare = buildGirlPowerString(userInput);

			// create an implicit intent for sending text
			Intent shareIntent = new Intent();
			shareIntent.setAction(Intent.ACTION_SEND);
			shareIntent.putExtra(Intent.EXTRA_TEXT, textToShare);
			shareIntent.putExtra(Intent.EXTRA_SUBJECT,
					getText(R.string.app_name));
			shareIntent.setType("text/plain");

			// create a chooser intent, so the user will be able to
			// choose the application to use for sharing
			Intent chooserIntent = Intent.createChooser(shareIntent,
					getText(R.string.share));

			// start activity with the chooser intent
			startActivity(chooserIntent);
		}
	}

	public void bounceText(View v) {
		
		// retrieve the user input from the EditText view
		EditText editTextView = (EditText) findViewById(R.id.edittext_id);
		String userInput = editTextView.getText().toString();

		if (isValidString(userInput)) {
			// create an explicit intent for opening the Bouncy activity
			// adding the user input as extra info to be stored in the intent
			Intent bouncyIntent = new Intent(this, BounceActivity.class);
			bouncyIntent.putExtra(BounceActivity.BOUNCY_TEXT_KEY, userInput);

			// start the BounceActivity
			startActivity(bouncyIntent);
		}
	}

	private boolean isValidString(String userInput) {
		return (userInput != null && userInput.trim().length() > 0);
	}

	private String buildGirlPowerString(String userInput) {
		// retrieve the title & poweredBy strings defined in
		// res/values/strings.xml
		String title = getString(R.string.title);
		String poweredBy = getString(R.string.poweredBy);

		// build a string out of the title, userInput and poweredBy
		// strings separated by new line character
		StringBuilder text = new StringBuilder();
		text.append(title);
		text.append("\n");
		text.append(userInput);
		text.append("\n");
		text.append(poweredBy);
		return text.toString();
	}
}
