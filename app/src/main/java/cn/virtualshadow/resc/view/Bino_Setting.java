package cn.virtualshadow.resc.view;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
import cn.virtualshadow.resc.MainActivity;
import cn.virtualshadow.resc.Activity.ActivityToGetPackage;
import android.util.EventLog;
import android.widget.Scroller;
import android.view.GestureDetector;

public class Bino_Setting extends View
{

	private Bino_App_Selector selector;

	private int selectIndex;

	private ActivityToGetPackage parent;

	private int modTick=0;

	private GestureDetector mGesture;

	public Bino_Setting(Context mainActivity, AttributeSet attr)
	{
		super(mainActivity, attr);
		this.parent = (ActivityToGetPackage) mainActivity;
		
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		// TODO: Implement this method
		super.onDraw(canvas);
		canvas.drawColor(Color.argb(100, 200, 200, 200));

	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		// TODO: Implement this method
		if (event.getAction() == event.ACTION_UP)
		{
			selector.intoSelectApp();
		}
		return super.onTouchEvent(event);
	}

	public void lockSelector(Bino_App_Selector bas)
	{
		this.selector = bas;
	}


	public void p_scroll()
	{
		modTick++;
		if (modTick == 7)
		{
			selectIndex = selectIndex + 1;
			if (selectIndex <= parent.appLength())
			{
				selector.selectApp(parent, selectIndex);	
			}
			else
			{
				selectIndex = 0;	
			}
			modTick = 0;
		}
	}

	public void r_scroll()
	{
		modTick++;
		if (modTick == 7)
		{
			selectIndex = selectIndex - 1;
			if (selectIndex >= 0)
			{
				selector.selectApp(parent, selectIndex);	
			}
			else
			{
				selectIndex = parent.appLength();	
			}
			modTick = 0;
		}
	}

}
