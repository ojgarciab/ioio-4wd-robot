package com.linaresdigital.android.ioio4wdrobotcontrol;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class JoystickView extends View {
	/* Draw styles by order */
	private Paint paintBaseOuter;
	private Paint paintBaseInner;
	private Paint paintStick;
	private Paint paintHandle;

	public JoystickView(Context context) {
		super(context);
		initJoystickView();
	}

	private void initJoystickView() {
		setFocusable(true);
		paintBaseOuter = new Paint(Paint.ANTI_ALIAS_FLAG);
		paintBaseOuter.setColor(Color.GRAY);
		paintBaseOuter.setStrokeWidth(1);
		paintBaseOuter.setStyle(Paint.Style.FILL_AND_STROKE);
		paintBaseInner = new Paint(Paint.ANTI_ALIAS_FLAG);
		paintBaseInner.setColor(Color.rgb(0x40, 0x40, 0x20));
		paintBaseInner.setStrokeWidth(1);
		paintBaseInner.setStyle(Paint.Style.FILL_AND_STROKE);
		paintStick = new Paint(Paint.ANTI_ALIAS_FLAG);
		paintStick.setColor(Color.rgb(0x30, 0x30, 0x40));
		paintStick.setStrokeWidth(15);
		paintStick.setStyle(Paint.Style.FILL_AND_STROKE);
		paintHandle = new Paint(Paint.ANTI_ALIAS_FLAG);
		paintHandle.setColor(Color.DKGRAY);
		paintHandle.setStrokeWidth(1);
		paintHandle.setStyle(Paint.Style.FILL_AND_STROKE);
	}

}
