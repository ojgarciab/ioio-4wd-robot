package com.linaresdigital.android.ioio4wdrobotcontrol;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class JoystickView extends View {
	/* Draw styles by order */
	private Paint mPaintBaseOuter;
	private Paint mPaintBaseInner;
	private Paint mPaintStick;
	private Paint mPaintHandle;
	/* Size, center, position, ect */
	private int mSize, mCenterX, mCenterY, mPosX, mPosY;
	public float X, Y;

	public JoystickView(Context context) {
		super(context);
		initJoystickView();
	}

	private void initJoystickView() {
		setFocusable(true);
		mPaintBaseOuter = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaintBaseOuter.setColor(Color.GRAY);
		mPaintBaseOuter.setStrokeWidth(1);
		mPaintBaseOuter.setStyle(Paint.Style.FILL_AND_STROKE);
		mPaintBaseInner = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaintBaseInner.setColor(Color.rgb(0x40, 0x40, 0x20));
		mPaintBaseInner.setStrokeWidth(1);
		mPaintBaseInner.setStyle(Paint.Style.FILL_AND_STROKE);
		mPaintStick = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaintStick.setColor(Color.rgb(0x30, 0x30, 0x40));
		mPaintStick.setStrokeWidth(15);
		mPaintStick.setStyle(Paint.Style.FILL_AND_STROKE);
		mPaintHandle = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaintHandle.setColor(Color.DKGRAY);
		mPaintHandle.setStrokeWidth(1);
		mPaintHandle.setStyle(Paint.Style.FILL_AND_STROKE);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.save();
		canvas.drawCircle(mCenterX, mCenterY, mSize * 0.4f, mPaintBaseOuter);
		canvas.drawCircle(mCenterX, mCenterY, mSize >> 3, mPaintBaseInner);
		canvas.drawLine(mCenterX, mCenterY, mCenterX + mPosX, mCenterY + mPosY, mPaintStick);
		canvas.drawCircle(mCenterX + mPosX, mCenterY + mPosY, mSize / 5, mPaintHandle);
		canvas.restore();
	}	

}
