package cn.virtualshadow.resc.view;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;
import cn.virtualshadow.resc.Activity.ActivityToGetPackage;
import cn.virtualshadow.resc.AppDataManager.AppData;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File;

public class Bino_App_Selector extends View
{

	private AppData selectApp_now;

	private Context parent;

	private ActivityToGetPackage atgp;
	public Bino_App_Selector(Context c, AttributeSet attr)
	{
		super(c, attr);
		this.parent=c;
	}
	public Bino_App_Selector(Context c)
	{
		super(c);
		this.parent=c;

	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		// TODO: Implement this method
		if (selectApp_now != null)
		{
			Paint packageNameTextStyle=new Paint();
			packageNameTextStyle.setTextSize(30);
			packageNameTextStyle.setColor(Color.rgb(0, 200, 100));
			canvas.drawBitmap(((BitmapDrawable)selectApp_now.icon).getBitmap(), null, new Rect(40, 40, getWidth() - 40, getHeight() - 40), new Paint());
			canvas.drawText(selectApp_now.appName, 0, 40, packageNameTextStyle);
			canvas.drawText(selectApp_now.packageName,0,getHeight(),packageNameTextStyle);
		}
		canvas.drawColor(Color.argb(100,200,200,200));
	}

	public void selectApp(Activity activity, int index)
	{
		AppData app=new AppData();
		try
		{

			FileInputStream data=activity.openFileInput(new File(activity.getFilesDir().getPath()+"/appdata dir").listFiles()[(index * 2 - 2)].getPath());
			FileInputStream icon = activity.openFileInput(new File(activity.getFilesDir().getPath()+"/appdata dir").listFiles()[(index * 2 - 1)].getPath());	
			char[] buffer=new char[data.available()];
			new InputStreamReader(data, "UTF-8").read(buffer);
			StringBuffer message=new StringBuffer(new String(buffer));
			app.appName = message.substring(0, message.indexOf("\n")).toString();
			app.packageName = message.substring(message.indexOf("\n")+1,message.length()).toString();
			byte[] drawableBuffer=new byte[icon.available()];
			icon.read(drawableBuffer);	

			Bitmap bitmap=BitmapFactory.decodeByteArray(drawableBuffer, 0, drawableBuffer.length).copy(Bitmap.Config.ARGB_8888, false);
			BitmapDrawable bd=new BitmapDrawable(bitmap);
			app.icon = bd;

		}
		catch (FileNotFoundException e)
		{Toast.makeText(activity, e.toString(), Toast.LENGTH_LONG).show();}
		catch (IOException e)
		{Toast.makeText(activity, e.toString(), Toast.LENGTH_LONG).show();}
		this.selectApp_now = app;
		invalidate();
	}
	
	public void bindActivity(ActivityToGetPackage atgp){
		this.atgp=atgp;
	}
	
	public void intoSelectApp(){
		if(selectApp_now!=null&&selectApp_now.packageName!=null){
			String sb=selectApp_now.packageName;
			String regEx="[`~!@#$%^&*()+=|{}':;',\\[\\]<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？ ]";  
			Pattern   p   =   Pattern.compile(regEx);     
			Matcher   m   =   p.matcher(sb); 
			atgp.startSelectActivity(m.replaceAll("").trim());
		}
	}

	public AppData getSelectApp(){
		return selectApp_now;
	}
}
