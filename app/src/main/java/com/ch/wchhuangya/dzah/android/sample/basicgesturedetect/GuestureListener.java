package com.ch.wchhuangya.dzah.android.sample.basicgesturedetect;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.ch.wchhuangya.dzah.android.util.logger.Log;

/**
 * Created by wchya on 2015-10-13.
 */
public class GuestureListener extends GestureDetector.SimpleOnGestureListener {

    public static final String TAG = "GestureListener";

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        // Up motion completing a single tap occurred.
        Log.i(TAG, "Single Tap Up" + getTouchType(e));
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        // Touch has been long enough to indicate a long press.
        // Does not indicate motion is complete yet (no up event necessarily)
        Log.i(TAG, "Long Press" + getTouchType(e));
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                            float distanceY) {
        // User attempted to scroll
        Log.i(TAG, "Scroll" + getTouchType(e1));
        return false;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                           float velocityY) {
        // Fling event occurred.  Notification of this one happens after an "up" event.
        Log.i(TAG, "Fling" + getTouchType(e1));
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        // User performed a down event, and hasn't moved yet.
        Log.i(TAG, "Show Press" + getTouchType(e));
    }

    @Override
    public boolean onDown(MotionEvent e) {
        // "Down" event - User touched the screen.
        Log.i(TAG, "Down" + getTouchType(e));
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        // User tapped the screen twice.
        Log.i(TAG, "Double tap" + getTouchType(e));
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        // Since double-tap is actually several events which are considered one aggregate
        // gesture, there's a separate callback for an individual event within the doubletap
        // occurring.  This occurs for down, up, and move.
        Log.i(TAG, "Event within double tap" + getTouchType(e));
        return false;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        // A confirmed single-tap event has occurred.  Only called when the detector has
        // determined that the first tap stands alone, and is not part of a double tap.
        Log.i(TAG, "Single tap confirmed" + getTouchType(e));
        return false;
    }


    /**
     * Returns a human-readable string describing the type of touch that triggered a MotionEvent.
     */

    private static String getTouchType(MotionEvent e){

        String touchTypeDescription = " ";
        int touchType = e.getToolType(0);

        switch (touchType) {
            case MotionEvent.TOOL_TYPE_FINGER:
                touchTypeDescription += "(finger)";
                break;
            case MotionEvent.TOOL_TYPE_STYLUS:
                touchTypeDescription += "(stylus, ";
                //Get some additional information about the stylus touch
                float stylusPressure = e.getPressure();
                touchTypeDescription += "pressure: " + stylusPressure;

                if(Build.VERSION.SDK_INT >= 21) {
                    touchTypeDescription += ", buttons pressed: " + getButtonsPressed(e);
                }

                touchTypeDescription += ")";
                break;
            case MotionEvent.TOOL_TYPE_ERASER:
                touchTypeDescription += "(eraser)";
                break;
            case MotionEvent.TOOL_TYPE_MOUSE:
                touchTypeDescription += "(mouse)";
                break;
            default:
                touchTypeDescription += "(unknown tool)";
                break;
        }

        return touchTypeDescription;
    }

    /**
     * Returns a human-readable string listing all the stylus buttons that were pressed when the
     * input MotionEvent occurred.
     */
    @TargetApi(21)
    private static String getButtonsPressed(MotionEvent e){
        String buttons = "";

        if(e.isButtonPressed(MotionEvent.BUTTON_PRIMARY)){
            buttons += " primary";
        }

        if(e.isButtonPressed(MotionEvent.BUTTON_SECONDARY)){
            buttons += " secondary";
        }

        if(e.isButtonPressed(MotionEvent.BUTTON_TERTIARY)){
            buttons += " tertiary";
        }

        if(e.isButtonPressed(MotionEvent.BUTTON_BACK)){
            buttons += " back";
        }

        if(e.isButtonPressed(MotionEvent.BUTTON_FORWARD)){
            buttons += " forward";
        }

        if (buttons.equals("")){
            buttons = "none";
        }

        return buttons;
    }
}
