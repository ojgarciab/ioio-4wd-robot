package com.linaresdigital.android.ioio4wdrobotcontrol;

import ioio.lib.api.DigitalOutput;
import ioio.lib.api.PwmOutput;
import ioio.lib.api.exception.ConnectionLostException;
import ioio.lib.util.BaseIOIOLooper;

public class IoioLoop extends BaseIOIOLooper {
	private PwmOutput[] pwmMotors;
	private DigitalOutput activityLed;
	private int blinkCount = 0;

	@Override
	protected void setup() throws ConnectionLostException {
		pwmMotors = new PwmOutput[4];
		activityLed = ioio_.openDigitalOutput(0, true);
		pwmMotors[0] = ioio_.openPwmOutput(10, 500);
		pwmMotors[1] = ioio_.openPwmOutput(11, 500);
		pwmMotors[2] = ioio_.openPwmOutput(13, 500);
		pwmMotors[3] = ioio_.openPwmOutput(12, 500);
	}

	@Override
	public void disconnected() {
		pwmMotors[0].close();
		pwmMotors[1].close();
		pwmMotors[2].close();
		pwmMotors[3].close();
		activityLed.close();
	}

	@Override
	public void loop() throws ConnectionLostException {
		blinkCount = ++blinkCount % 10;
		// Turn off the LED 2 cycles and turn it on 8 cycles
		activityLed.write(blinkCount > 2);
	}
}
