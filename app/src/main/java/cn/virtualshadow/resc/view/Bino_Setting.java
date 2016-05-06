package cn.virtualshadow.resc.view;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import cn.virtualshadow.resc.Activity.ActivityToGetPackage;
import android.graphics.drawable.Drawable;
import cn.virtualshadow.resc.R;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Gallery.LayoutParams;

public class Bino_Setting extends SeekBar implements OnSeekBarChangeListener
{

	
	private Bino_App_Selector selector;

	private int selectIndex;

	private ActivityToGetPackage parent;

	private PopupWindow appNameTag;

	private TextView nameTag;

	public Bino_Setting(Context mainActivity, AttributeSet attr)
	{
		super(mainActivity, attr);
		this.parent = (ActivityToGetPackage) mainActivity;
		
		setMax(parent.appLength());
		setOnSeekBarChangeListener(this);
	}



	



	public void lockSelector(Bino_App_Selector bas)
	{
		this.selector = bas;
	}


	public void scroll()
	{
		if (selectIndex <= parent.appLength())
		{
			selector.selectApp(parent, selectIndex);	
		}
		}
	@Override
	public void onProgressChanged(SeekBar p1, int p2, boolean p3)
	{
		// TODO: Implement this method
		if(p2!=0){
			selectIndex=p2;
			scroll();
		}
		if(appNameTag!=null){
			nameTag.setText(selector.getSelectApp().appName);
		}
		else
		{
		appNameTag=new PopupWindow();
		appNameTag.setWidth(LayoutParams.WRAP_CONTENT);
		appNameTag.setHeight(LayoutParams.WRAP_CONTENT);
		nameTag=new TextView(parent);
		nameTag.setTextSize(26);
		nameTag.setText(selector.getSelectApp().appName);
		appNameTag.setContentView(nameTag);
		appNameTag.showAsDropDown(this);
		}
		
		
	}

	@Override
	public void onStartTrackingTouch(SeekBar p1)
	{
		// TODO: Implement this method
		
	}

	@Override
	public void onStopTrackingTouch(SeekBar p1)
	{
		// TODO: Implement this method
		selector.intoSelectApp();
		appNameTag.dismiss();
		appNameTag=null;
	}




	

}
