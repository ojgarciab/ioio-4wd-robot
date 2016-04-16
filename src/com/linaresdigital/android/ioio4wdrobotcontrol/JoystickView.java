package com.linaresdigital.android.ioio4wdrobotcontrol;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class JoystickView extends View {
	/* Movement lock */
	public final static int LOCK_HORIZONTALLY = 1;
	public final static int LOCK_VERTICALLY = 2;
	/* Draw styles by order */
	private Paint mPaintBaseOuter;
	private Paint mPaintBaseInner;
	private Paint mPaintStick;
	private Paint mPaintHandle;
	/* Size, center, position, ect */
	private int mSize, mCenterX, mCenterY, mPosX, mPosY, mRadioMax;
	private int mTouchIndex = -1, mMovementLock = LOCK_HORIZONTALLY | LOCK_VERTICALLY;
	public float X, Y;

	public JoystickView(Context context) {
		super(context);
		initJoystickView();
	}

	public JoystickView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initJoystickView();
	}

	public JoystickView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
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

	public boolean onTouchEvent(MotionEvent ev) {
		final int action = ev.getAction();
		switch (action & MotionEvent.ACTION_MASK) {
		case MotionEvent.ACTION_DOWN:
			/* First pointer down: get pointer ID and use it */
			mTouchIndex = ev.getPointerId(0);
			return updateJoystick(ev);
		case MotionEvent.ACTION_MOVE:
			/* Ignore older pointer ID, use only last one */
			if (mTouchIndex == ev.getPointerId(0)) {
				return updateJoystick(ev);
			}
			break;
		case MotionEvent.ACTION_POINTER_DOWN:
			/* If there is a new pointer, get and use it */
			if (mTouchIndex >= 0) {
				mTouchIndex = ev.getPointerId((action & MotionEvent.ACTION_POINTER_INDEX_MASK) >> MotionEvent.ACTION_POINTER_INDEX_SHIFT);
				return updateJoystick(ev);
			}
			break;
		case MotionEvent.ACTION_CANCEL:
		case MotionEvent.ACTION_UP:
			/* End of movement, reset joystick position to center */
			resetJoystick();
			break;
		case MotionEvent.ACTION_POINTER_UP:
			/* If touch ID is not last (active) one, ignore it */
			if (mTouchIndex == ((action & MotionEvent.ACTION_POINTER_INDEX_MASK) >> MotionEvent.ACTION_POINTER_INDEX_SHIFT)) {
				resetJoystick();
			}
			performClick();
			break;
		}
		return false;
	}

	private boolean updateJoystick(MotionEvent ev) {
		if (mTouchIndex < 0) {
			return false;
		}
		try {
			int pointerIndex = ev.findPointerIndex(mTouchIndex);
			float x = ev.getX(pointerIndex);
			float y = ev.getY(pointerIndex);
			if ((mMovementLock & LOCK_HORIZONTALLY) == 0) {
				mPosX = (int) (x - mCenterX);
			} else {
				mPosX = 0;
			}
			if ((mMovementLock & LOCK_VERTICALLY) == 0) {
				mPosY = (int) (y - mCenterY);
			} else {
				mPosY = 0;
			}
			double radius = Math.sqrt(mPosX * mPosX + mPosY * mPosY);
			if (radius > mRadioMax) {
				mPosX /= radius / mRadioMax;
				mPosY /= radius / mRadioMax;
			}
			X = (float)mPosX / (float)mRadioMax;
			Y = -(float)mPosY / (float)mRadioMax;
			invalidate();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	private void resetJoystick() {
		/* Center joystick, remove touch ID and force redraw */
		mPosX = mPosY = 0;
		X = Y = 0.0f;
		mTouchIndex = -1;
		invalidate();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int size = Math.min(widthMeasureSpec, heightMeasureSpec);
		setMeasuredDimension(size, size);
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		mSize = Math.min(getMeasuredWidth(), getMeasuredHeight());
		mRadioMax = Math.round(mSize * 0.29f);
		mCenterX = getMeasuredWidth() / 2;
		mCenterY = getMeasuredHeight() / 2;
		mPosX = mPosY = 0;
		mPaintStick.setStrokeWidth(mSize * 0.15f);
	}

    @Override
    public boolean performClick() {
        super.performClick();
        return true;
    }

	public void setMovementLock(int value) {
		mMovementLock = value;
	}

	public int getMovementLock() {
		return mMovementLock;
	}
}
