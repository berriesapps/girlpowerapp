/**
 * This class is based on AnimatedView class provided 
 * in the bouncing ball example provided here:
 * http://www.techrepublic.com/blog/software-engineer/bouncing-a-ball-on-androids-canvas/
 * I have modified the original code to bounce provided text instead of a ball image
 * I have also added code to change the text color each time the text bounces
 * on the sides of the screen
 */
package com.shecodes.girlpower.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

public class AnimatedTextView extends View {
	private static final int FRAME_RATE = 25;
	private static final int[] COLOR_LIST = { Color.RED, Color.YELLOW, Color.MAGENTA,
			Color.BLUE, Color.WHITE, Color.LTGRAY, Color.CYAN, Color.YELLOW };
	private static final int REGULAR_TEXT_SIZE = 85;

	private int x = -1;
	private int y = -1;
	private int xVelocity = 8;
	private int yVelocity = 8;
	private Handler mHandler;
	private String mText = "";
	private Paint mPaint;
	private Rect mTextBounds;
	private int mColorIndex = 0;

	public AnimatedTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mPaint = new Paint();
		mPaint.setTypeface(Typeface.DEFAULT_BOLD);// your preference here
		mPaint.setTextSize(REGULAR_TEXT_SIZE);// have this the same as your text size
		mPaint.setColor(Color.MAGENTA);
		mTextBounds = new Rect();
		mHandler = new Handler();
	}

	public void setText(String textToBounce) {
		mText = textToBounce;
		mPaint.getTextBounds(mText, 0, mText.length(), mTextBounds);
	}

	private Runnable r = new Runnable() {
		@Override
		public void run() {
			invalidate();
		}
	};

	protected void onDraw(Canvas c) {

		if (x < 0 && y < 0) {
			x = 0;
			y = this.getHeight() / 2;
		} else {
			x += xVelocity;
			y += yVelocity;
			if ((x > Math.abs(this.getWidth() - mTextBounds.width()))
					|| (x < 0)) {
				changeColor();
				xVelocity = xVelocity * -1;
			}
			if ((y > this.getHeight() - mTextBounds.height()) || (y < 0)) {
				changeColor();
				yVelocity = yVelocity * -1;
			}
		}
		c.drawText(mText, x, y, mPaint);
		mHandler.postDelayed(r, FRAME_RATE);
	}

	private void changeColor() {
		mColorIndex++;
		mColorIndex = mColorIndex % COLOR_LIST.length;
		mPaint.setColor(COLOR_LIST[mColorIndex]);
	}
}
