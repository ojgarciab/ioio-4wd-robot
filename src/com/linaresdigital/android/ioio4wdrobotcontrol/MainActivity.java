package com.linaresdigital.android.ioio4wdrobotcontrol;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {
	public JoystickView[] joySticks;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		joySticks[0] = (JoystickView) findViewById(R.id.joystickViewT);
		joySticks[1] = (JoystickView) findViewById(R.id.joystickViewL);
		joySticks[2] = (JoystickView) findViewById(R.id.joystickViewR);
	}
}
