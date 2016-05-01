package cn.virtualshadow.resc;

import android.os.Bundle;
import cn.virtualshadow.resc.Activity.BlankActivity;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends BlankActivity
{

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
	}
    
}
