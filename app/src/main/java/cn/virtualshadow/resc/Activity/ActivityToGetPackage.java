package cn.virtualshadow.resc.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import cn.virtualshadow.resc.AppDataManager.AppData;
import cn.virtualshadow.resc.util.DataStream;
import java.util.ArrayList;
import java.util.List;
import android.widget.Toast;

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
		getApps();
	} 

	private void getApps()
	{
		packageMamager = getPackageManager();
		Intent intent=new Intent(Intent.ACTION_MAIN, null);
		intent.addCategory(Intent.CATEGORY_LAUNCHER);
		appList = packageMamager.queryIntentActivities(intent, 0);
		if (appList.size() == (getFilesDir().listFiles().length / 2))
		{
			//缓存数量与app数量相等
			Toast.makeText(this, "同步完成", Toast.LENGTH_LONG).show();
		}
		else
		{
			//app数量与缓存数量不相符
			try
			{
				for (ResolveInfo resolve:appList)
				{
					AppData appData=new AppData();
					appData.appName = (String) resolve.loadLabel(packageMamager);
					appData.packageName = resolve.activityInfo.packageName;
					appData.icon = resolve.loadIcon(packageMamager);
					appData.permission = resolve.activityInfo.permission;
					appDataList.add(appData);
				}
			}
			finally
			{

				showApps();
				Toast.makeText(this, "更新完毕", Toast.LENGTH_LONG).show();
			}
		}

	}

	private void showApps()
	{
		// 重写缓存
		DataStream.push(this, appDataList).to();
	}

}
