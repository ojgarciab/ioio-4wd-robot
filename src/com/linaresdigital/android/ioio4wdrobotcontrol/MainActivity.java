package com.linaresdigital.android.ioio4wdrobotcontrol;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {
	public JoystickView[] joySticks;
	public boolean landscape = false, portrait = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		joySticks[0] = (JoystickView) findViewById(R.id.joystickViewT);
		joySticks[1] = (JoystickView) findViewById(R.id.joystickViewL);
		joySticks[2] = (JoystickView) findViewById(R.id.joystickViewR);
		if (joySticks[0] != null) {
			landscape = true;
		}
		if ((joySticks[1] != null) && (joySticks[2] != null)) {
			portrait = false;
		}
	}
}
