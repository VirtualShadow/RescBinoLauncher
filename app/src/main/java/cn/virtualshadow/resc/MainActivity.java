package cn.virtualshadow.resc;

import android.os.Bundle;
import cn.virtualshadow.resc.Activity.BlankActivity;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.Toast;
import cn.virtualshadow.resc.Activity.ActivityToGetPackage;
import cn.virtualshadow.resc.view.Bino_App_Selector;
import android.view.MotionEvent;
import cn.virtualshadow.resc.view.Bino_Setting;

public class MainActivity extends ActivityToGetPackage
{

	private Bino_App_Selector bas;

	private Bino_Setting bs;

	@Override
	protected void start(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.start(savedInstanceState);
		final View view=findViewById(R.layout.main);
		getWindowManager().addView(view,
		getActivitysFloatWindowLayoutParams());
		view.findViewById(R.id.torch)
		.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					// TODO: Implement this method
					getWindowManager().removeView(view);
					Toast.makeText(getApplicationContext(),"移除",Toast.LENGTH_LONG).show();
				}
			});
		bs=(Bino_Setting)view.findViewById(R.id.mainBino_Setting);
		bas=(Bino_App_Selector)view.findViewById(R.id.mainBino_App_Selector);
		bas.bindActivity(this);
		bs.lockSelector(bas);
		
		
	}
	

}
