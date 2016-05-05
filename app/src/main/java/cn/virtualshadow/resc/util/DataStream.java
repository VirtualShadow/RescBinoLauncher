package cn.virtualshadow.resc.util;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.Toast;
import cn.virtualshadow.resc.AppDataManager.AppData;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;

public class DataStream
{

	private List<AppData> appList;

	private Activity parent;
	public static DataStream push(Activity activity,List<AppData> apps){
		print(activity,"元素:"+apps.size());
		return new DataStream(activity,apps);
	}
	public static DataStream push(Activity activity){
		return new DataStream(activity);
	}
	

	private static void print(Context c,String size)
	{
		// TODO: Implement this method
		Toast.makeText(c,size,Toast.LENGTH_LONG).show();
	};
	private DataStream(Activity activity,List<AppData> apps){
		this.appList=apps;
		this.parent=activity;
	}
	private DataStream(Activity activity){
		this.parent=activity;
	}
	private void output(String dir){
		try
		{
			for(AppData ad:appList){
				FileOutputStream outputStream=parent.openFileOutput(dir+ad.appName, parent.MODE_PRIVATE);
				FileOutputStream iconOutput=parent.openFileOutput(dir+ad.appName+"-icon",parent.MODE_PRIVATE);
				
				String allMessage=ad.appName+"\n"+ad.packageName;
				byte[] buffer=allMessage.getBytes();
				
				try
				{
					iconOutput.write(icon2byte(ad.icon));
					outputStream.write(buffer);
				}
				catch (IOException e)
				{print(parent,e.toString());}
				
			}
		}
		catch (FileNotFoundException e)
		{}
	}
	
	private byte[] icon2byte(Drawable icon){
		BitmapDrawable bd=(BitmapDrawable) icon;
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		bd.getBitmap().compress(Bitmap.CompressFormat.PNG, 100, baos);
		return baos.toByteArray();
	}
	
	public void to(String path){
		output(path+"/");
	}
	public File getFileDir(String path){
		File file=new File(parent.getFilesDir().getPath()+"/"+path);
		return file;
	}
}
