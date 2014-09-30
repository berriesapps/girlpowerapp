package com.shecodes.girlpower.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.shecodes.girlpower.R;
import com.shecodes.girlpower.views.AnimatedTextView;

/**
 * In order to bounce some text 
 * Create an Intent with BounceActivity.class
 * and add to it an extra parameter with
 * key = BOUNCY_TEXT_KEY
 * value = the text to bounce
 * @author berry
 */
public class BounceActivity extends Activity {

	public static final String BOUNCY_TEXT_KEY = "BOUNCY";
			
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set the content view and title
        setContentView(R.layout.bounce_activity);
        setTitle(R.string.title);
        
        // retrieve the AnimatedTextView
        AnimatedTextView animatedView = (AnimatedTextView)findViewById(R.id.anim_view);
        
        // retrieve the text to bounce from the extra params of the 
        // intent that started this activity
        Intent intent = getIntent();
        String textToBounce = intent.getStringExtra(BOUNCY_TEXT_KEY);
        
        // set the animated view text
        animatedView.setText(textToBounce);
    }
}
