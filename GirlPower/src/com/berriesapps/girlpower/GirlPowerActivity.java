package com.berriesapps.girlpower;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class GirlPowerActivity extends Activity implements View.OnClickListener {

	private static final String NEW_LINE = "\n";
	private EditText mEditText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.girl_power);
		mEditText = (EditText) findViewById(R.id.edittext_id);
		Button showButton = (Button) findViewById(R.id.show_button_id);
		Button shareButton = (Button) findViewById(R.id.share_button_id);
		showButton.setOnClickListener(this);
		shareButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		String title = getResources().getString(R.string.title);
		StringBuilder text = new StringBuilder();
		text.append(title).append(NEW_LINE);
		text.append(mEditText.getText()).append(NEW_LINE);
		text.append(getResources().getString(R.string.poweredBy));

		int viewId = v.getId();
		if (viewId == R.id.show_button_id) {
			Toast.makeText(this, text, Toast.LENGTH_LONG).show();
		} else if (viewId == R.id.share_button_id) {
			shareText(text);
		}
	}

	private void shareText(CharSequence text) {
		Intent shareIntent = new Intent();
		shareIntent.setAction(Intent.ACTION_SEND);
		shareIntent.putExtra(Intent.EXTRA_TEXT, text);
		shareIntent.setType("text/plain");
		CharSequence chooserTitle = getResources().getText(R.string.share);
		Intent chooserIntent = Intent.createChooser(shareIntent, chooserTitle);
		startActivity(chooserIntent);
	}   

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.girl_power, menu);
        return true;
    }
    
}
