package cn.virtualshadow.resc.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.widget.Toast;
import cn.virtualshadow.resc.AppDataManager.AppData;
import cn.virtualshadow.resc.util.DataStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ActivityToGetPackage extends BlankActivity
{
	private List<ResolveInfo> appList;

	private PackageManager packageMamager;

	private ArrayList<AppData> appDataList=new ArrayList<AppData>();
	@Override
	protected void start(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		
		super.start(savedInstanceState);
		if(!new File(getFilesDir().getPath()+"/appdata dir").exists()){
		new File(getFilesDir().getPath()+"/appdata dir").mkdir();
		}
		getApps();
		
	} 

	private void getApps()
	{
		packageMamager = getPackageManager();
		Intent intent=new Intent(Intent.ACTION_MAIN, null);
		intent.addCategory(Intent.CATEGORY_LAUNCHER);
		appList = packageMamager.queryIntentActivities(intent, 0);
		if (appList.size() == appLength())
		{
			//缓存数量与app数量相等
			Toast.makeText(this, "同步完成", Toast.LENGTH_LONG).show();
		}
		else if(appList.size()<appLength()){
			int dataLength=DataStream.push(this,appDataList).getFileDir("appdata dir").listFiles().length/2;
			File[] allDataFile=DataStream.push(this,appDataList).getFileDir("appdata dir").listFiles();
			for (int i=0;i < dataLength;i++)
			{
				allDataFile[i].delete();
			}
			reWriteAppDataFile();
		}
		else
		{
			//app数量与缓存数量不相符
			reWriteAppDataFile();
		}

	}

	private void reWriteAppDataFile(){
		try
		{
			for (ResolveInfo resolve:appList)
			{
				AppData appData=new AppData();
				appData.appName =resolve.loadLabel(packageMamager).toString();
				appData.packageName = resolve.activityInfo.packageName;
				appData.icon = resolve.loadIcon(packageMamager);
				appDataList.add(appData);
			}
		}
		finally
		{
			showApps();
			Toast.makeText(this, "更新完毕", Toast.LENGTH_LONG).show();
		}
	}
	
	private void showApps()
	{
		// 重写缓存
		DataStream.push(this, appDataList).to("appdata dir");
	}

	public int appLength(){
		return DataStream.push(this).getFileDir("appdata dir").listFiles().length/2;
	}

	public void startSelectActivity(String packageName){
		Intent intent=packageMamager.getLaunchIntentForPackage(packageName);
		ActivityToGetPackage.this.startActivity(intent);
	}

	@Override
	public FileOutputStream openFileOutput(String name, int mode) throws FileNotFoundException
	{
		// TODO: Implement this method
		FileOutputStream result=new FileOutputStream(getFilesDir().getPath() + "/" + name);
		
		return result;
	}
		
		
}
