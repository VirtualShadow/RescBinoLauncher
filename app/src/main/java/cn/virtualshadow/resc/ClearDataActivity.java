package cn.virtualshadow.resc;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import cn.virtualshadow.resc.Activity.BlankActivity;
import android.view.LayoutInflater;
import java.io.File;

public class ClearDataActivity extends BlankActivity
{

	private Button clearData;

	private View cv;

	private Button clearCache;

	@Override
	protected void start(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.start(savedInstanceState);
		cv = LayoutInflater.from(this).inflate(R.layout.setting, null);
		setContentView(cv);

		clearData = (Button)cv.findViewById(R.id.cleardata);
		clearData.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					// TODO: Implement this method

					int dataLength=getFilesDir().listFiles().length;
					File[] allDataFile=getFilesDir().listFiles();
					for (int i=0;i < dataLength;i++)
					{
						allDataFile[i].delete();
					}
					Toast.makeText(getApplicationContext(), "清除完成,清除元素: " + dataLength, Toast.LENGTH_LONG).show();

				}
			});
		clearCache = (Button)cv.findViewById(R.id.clearcache);
		clearCache.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					// TODO: Implement this method
					File[] cacheFile=getCacheDir().listFiles();
					int cacheCount=getCacheDir().listFiles().length;
					for (int i=0;i < cacheCount;i++)
					{
						cacheFile[i].delete();
					}
					Toast.makeText(getApplicationContext(), "清除缓存完毕,清除元素: " + cacheCount, Toast.LENGTH_LONG).show();
				}
			});

	}

}
