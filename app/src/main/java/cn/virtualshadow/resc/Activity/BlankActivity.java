package cn.virtualshadow.resc.Activity;
import android.app.Activity;
import android.app.WallpaperManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.content.pm.PermissionInfo;
import android.graphics.drawable.BitmapDrawable;
import android.view.WindowManager.LayoutParams;
import android.graphics.PixelXorXfermode;
import android.graphics.PixelFormat;
import android.view.View;
import android.view.LayoutInflater;
import android.view.Gravity;

public class BlankActivity extends Activity
{

	private Drawable background;

	private int wallpaper_x;

	private int wallpaper_y;
	
	public WindowManager windowManage;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: get bundle
		start(savedInstanceState);
	}
	
	protected void start(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		windowManage=(WindowManager) getSystemService(WINDOW_SERVICE);
		background=WallpaperManager.getInstance(this).getDrawable();
		BitmapDrawable sourceWallpaper=(BitmapDrawable) background;
		wallpaper_x=sourceWallpaper.getBitmap().getWidth();
		wallpaper_y=sourceWallpaper.getBitmap().getHeight();
		getWindow().setBackgroundDrawable(background);
		
	}

	@Override
	public WindowManager getWindowManager()
	{
		// TODO: Implement this method
		return windowManage;
	}
	
	public WindowManager.LayoutParams getActivitysFloatWindowLayoutParams(){
		WindowManager.LayoutParams thiz=new WindowManager.LayoutParams();
		thiz.type=thiz.TYPE_TOAST;
		thiz.format=PixelFormat.TRANSPARENT;
		thiz.width=WindowManager.LayoutParams.WRAP_CONTENT;
		thiz.height=WindowManager.LayoutParams.WRAP_CONTENT;
		thiz.flags=thiz.FLAG_NOT_FOCUSABLE;
		thiz.gravity=Gravity.TOP|Gravity.LEFT;
		return thiz;
	}

	@Override
	public View findViewById(int id)
	{
		// TODO: Implement this method
		return LayoutInflater.from(this).inflate(id,null);
	}
	
	}
