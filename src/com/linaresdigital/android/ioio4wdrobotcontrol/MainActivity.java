package com.linaresdigital.android.ioio4wdrobotcontrol;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {
	private JoystickView[] joySticks;
	private static TextView tvStatus;
	public boolean landscape = false, portrait = false;
	public static Handler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		handler = new Handler();
		setContentView(R.layout.activity_main);
		tvStatus = (TextView) findViewById(R.id.txtStatus);
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

	public static void changeStatus(final int estado) {
		handler.post(new Runnable() {
			@Override
			public void run() {
				tvStatus.setText(estado);
			}
		});
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
	/*
	 * Intent i = new Intent(this, PreferencesActivity.class);
	 * startActivity(i);
	 */
}
