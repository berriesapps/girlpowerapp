package com.shecodes.girlpower.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.shecodes.girlpower.R;
import com.shecodes.girlpower.views.AnimatedTextView;

public class BounceActivity extends Activity {

	public static final String BOUNCY_TEXT_KEY = "BOUNCY";
			
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bounce_activity);
        setTitle(R.string.title);
        AnimatedTextView animatedView = (AnimatedTextView)findViewById(R.id.anim_view);
        Intent intent = getIntent();
        String textToBounce = intent.getStringExtra(BOUNCY_TEXT_KEY);
        animatedView.setText(textToBounce);
    }
}
