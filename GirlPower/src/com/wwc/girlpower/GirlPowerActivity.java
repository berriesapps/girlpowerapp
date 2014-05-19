package com.wwc.girlpower;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

/**
 * This activity will let the user enter an input string and click on a share button 
 * in order to share the GirlPower message with any android application that supports 
 * sending text.
 * Make sure you run the application on your device and use the Share button
 * to send me an email at berriesapps@gmail.com with your GirlPower message!
 * 
 * Homework:
 * =================
 * 1. add "Show" button. When clicked, the girl power message will be displayed 
 * on screen using an android "Toast"  
 * 2. override all the activity lifecycle methods and add debug logs  
 *    Run the app, play with it and look at the logs
 * 3. add "Save" button. Each time the button is clicked, the input string will 
 * be added to an ArrayList which will be defined as a member variable of the activity  
 * 4. add "Display All" button. When clicked all saved input strings will be displayed in 
 * a new activity that you will create named PowerListDisplayActivity.
 *
 * Have Fun!
 * Berry :)
 */
public class GirlPowerActivity extends Activity {
	private static final String TAG = Activity.class.getSimpleName();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d(TAG, "onCreate lifecycle method called");
		super.onCreate(savedInstanceState);

		// the layout for this activity is defined 
		// in res/layout/girl_power.xml
		setContentView(R.layout.girl_power);
	}

	
	@Override
	protected void onDestroy() {
		Log.d(TAG, "onDestroy lifecycle method called");
		super.onDestroy();
	}	
	
	public void shareText(View v) {
		String textToShare = buildGirlPowerString();

		// create an implicit intent for sending text 
		Intent shareIntent = new Intent();
		shareIntent.setAction(Intent.ACTION_SEND);
		shareIntent.putExtra(Intent.EXTRA_TEXT, textToShare);
		shareIntent.setType("text/plain");
		
		// create a chooser intent, so the user will be able to
		// choose the application to use for sharing
		Intent chooserIntent = Intent.createChooser(shareIntent, getText(R.string.share));
		
		// start activity with the chooser intent
		startActivity(chooserIntent);
	}
		
	private String buildGirlPowerString() {
		// retrieve the EditText view defined in the activity layout
		EditText editTextView = (EditText) findViewById(R.id.edittext_id);

		// retrieve the userInput from the view
		String userInput = editTextView.getText().toString();
		
		// retrieve the title & poweredBy strings defined in res/values/strings.xml
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
