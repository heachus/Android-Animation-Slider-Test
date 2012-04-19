package de.monkey.animationslider;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ViewFlipper;

public class HomeActivity extends Activity {
	private ViewFlipper vf;  
    private float oldTouchValue;

    @Override
	public boolean onTouchEvent(MotionEvent touchevent) {
		switch (touchevent.getAction())
        {
            case MotionEvent.ACTION_DOWN:
            {
                oldTouchValue = touchevent.getX();
                break;
            }
            case MotionEvent.ACTION_UP:
            {
                float currentX = touchevent.getX();
                Log.v("werte", "old: " + oldTouchValue + " -> new: " + currentX);
                if (oldTouchValue < currentX && (currentX - oldTouchValue) > 150.0)
                {
                    vf.setInAnimation(AnimationHelper.inFromLeftAnimation());
                    vf.setOutAnimation(AnimationHelper.outToRightAnimation());
                    vf.showNext();
                }
                if (oldTouchValue > currentX && (oldTouchValue - currentX) > 150.0)
                {
                    vf.setInAnimation(AnimationHelper.inFromRightAnimation());
                    vf.setOutAnimation(AnimationHelper.outToLeftAnimation());
                    vf.showPrevious();
                }
            break;
            }
        }
        return false;
	}
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.homeactivity);
        
        vf = (ViewFlipper) findViewById(R.id.layoutswitcher);
	}
}