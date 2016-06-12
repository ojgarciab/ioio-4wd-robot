# IOIO 4WD Robot for Android
This application controls the movement of a four-wheeled robot using a mobile with android operating system and an IOIO board.

## Hardware 
* Android smartphone or tablet.
* IOIO board.
* 4WD robot.

## Where to buy parts

### Android smartphone or tablet compatibility
You can use your own device (smartphone or tablet) but may be some compatibility issues.

Check device compatibility on the link below:

https://github.com/ytai/ioio/wiki/Supported-Devices

I recommend using bluetooth dongle to allow maximum device compatibily and, of course, improved distance range (without using long USB cables) and a completely wireless operation:

https://github.com/ytai/ioio/wiki/Supported-Bluetooth-Dongles

### IOIO board
You can buy IOIO here:

* Sparkfun (OTG only, older one is retired):
 * https://www.sparkfun.com/products/13613 ($39.95)
* China (Standar and OTG):
 * http://www.dx.com/s/ioio (from $23.99).
 * http://es.aliexpress.com/wholesale?SearchText=IOIO&SortType=price_asc&needQuery=n&groupsort=1 (from $20.60).

### 4WD Robot
There is lots of four-wheeled out there.

Mine is this:

http://www.dx.com/p/152992

I tried some H-bridge drivers:
* First attempt: http://www.dx.com/p/215795
* Current attempt: http://www.dx.com/p/408436
* Another driver: http://www.dx.com/p/430474

Technical specifications:
* HG7881:
 * Input voltage: 2.5-12v
 * Maximum output current: 800 mA
 * PWM control: 2 control lines for each channel, one for PWM (speed) and other for direction.
* TB6612FNG:
 * Input voltage: 2.7-5.5v for VCC (logic), 15v max for VM (motor voltage).
 * Maximum output current: 1200 mA (3200 mA single pulse peak)
 * PWM control: 2 control lines for each channel for direction and another one for PWM (speed).
 * Notes:
    * This driver DOES NOT have flyback diodes because uses MOSFETs instead transistors, but I recommend to add them.
    * Because it is CMOS based technology, it is necessary to handle it ensuring that the environment is protected against electrostatic discharge.
* L298N:
 * Input voltage: 7-35v
 * Maximum output current: 2000 mA
 * PWM control: 2 control lines for each channel for direction and another one for PWM (speed).
