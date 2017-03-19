package com.android.app;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.contact.api.ApiHttpClient;
import com.android.utils.StringUtils;
import com.example.model.User;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.PersistentCookieStore;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

import java.io.File;
import java.util.Properties;
import java.util.UUID;

public class App extends Application {
	private static App mAppApplication;

	@Override
	public void onCreate() {
		super.onCreate();
		mAppApplication = this;
		AsyncHttpClient client = new AsyncHttpClient();
		PersistentCookieStore myCookieStore = new PersistentCookieStore(this);
		client.setCookieStore(myCookieStore);
		ApiHttpClient.setHttpClient(client);
		// String token = getToken();
		// if (!StringUtils.isEmpty(token)) {
		// ApiHttpClient.setCookie("t=" + token + ";");
		// } else {
		// ApiHttpClient.setCookie("");
		// }
		// ApiHttpClient.setCookie();
		initImageLoader(getApplicationContext());

	}

	/** ��ȡApplication */
	public static App getApp() {
		return mAppApplication;
	}

	@Override
	public void onTerminate() {

		super.onTerminate();
		// ����ݻٵ�ʱ������������
	}

	// ����������Ϣ ��������һ�����������û���Ϣ������
	public void setProperties(Properties ps) {
		AppConfig.getAppConfig(this).set(ps);
	}

	// ��ȡ������Ϣ
	public Properties getProperties() {
		return AppConfig.getAppConfig(this).get();
	}

	public void setProperty(String key, String value) {
		AppConfig.getAppConfig(this).set(key, value);
	}

	public String getProperty(String key) {
		String res = AppConfig.getAppConfig(this).get(key);
		return res;
	}

	public void removeProperty(String... key) {
		AppConfig.getAppConfig(this).remove(key);
	}

	public String getAppId() {
		String uniqueID = getProperty(AppConfig.CONF_APP_UNIQUEID);
		if (StringUtils.isEmpty(uniqueID)) {
			uniqueID = UUID.randomUUID().toString();
			setProperty(AppConfig.CONF_APP_UNIQUEID, uniqueID);
		}
		return uniqueID;
	}

	/**
	 * ��ȡApp��װ����Ϣ
	 * 
	 * @return
	 */
	public PackageInfo getPackageInfo() {
		PackageInfo info = null;
		try {
			info = getPackageManager().getPackageInfo(getPackageName(), 0);
		} catch (NameNotFoundException e) {
			e.printStackTrace(System.err);
		}
		if (info == null)
			info = new PackageInfo();
		return info;
	}

	// ��ȡ�豸ID
	public String getDeviceID() {
		TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		String DEVICE_ID = tm.getDeviceId();
		return DEVICE_ID;
	}

	public void updateHttpClientHeader() {
		String token = getToken();
		if (StringUtils.isEmpty(token)) {
			ApiHttpClient.setCookie("");
		} else {
			ApiHttpClient.setCookie("t=" + token + ";");
		}

	}

	// ��ȡ�ֻ������豸����Ϣ
	public String getDeviceInfo() {
		return android.os.Build.MANUFACTURER + android.os.Build.MODEL + ","
				+ android.os.Build.VERSION.SDK + ","
				+ android.os.Build.VERSION.RELEASE;
	}

	// ע����¼ ���token ��������û���Ϣ ���ҷ���㲥֪ͨ
	public void LogOut() {
		removeProperty("user.uid", "user.utel", "user.uname", "user.utoken",
				"user.ubirth");
		updateHttpClientHeader();
	}

	public boolean islogin() {

		String uid = getProperty("user.uid");
		if (!StringUtils.isEmpty(uid)) {
			return true;
		}
		return false;
	}

	public User getLoginUser() {
		User user = new User();
		user.setUId(getProperty("user.uid"));
		user.setUTel(getProperty("user.utel"));
		user.setUName(getProperty("user.uname"));
		user.setUToken(getProperty("user.utoken"));
		user.setUBirth(getProperty("user.ubirth"));
		return user;
	}

	public String getToken() {
		return getProperty("user.utoken");
	}

	// ���õ�¼��Ϣ
	public void updateLoginInfo(final User user) {
		setProperties(new Properties() {
			{
				setProperty("user.uid", String.valueOf(user.getUId()));
				setProperty("user.utel", String.valueOf(user.getUTel()));
				setProperty("user.uname", String.valueOf(user.getUName()));
				setProperty("user.utoken", String.valueOf(user.getUToken()));
				setProperty("user.ubirth", String.valueOf(user.getUBirth()));
			}
		});
		updateHttpClientHeader();

	}

	//
	public void setHostIP(String ip) {
		setProperty("sys.host", String.valueOf(ip));
	}

	// ������Ʒ�����json����
	public void setMainLBJson(String json) {
		setProperty("sys.splb", String.valueOf(json));
	}

	// ��ȡ��Ʒ���JSON
	public String getMainLBJson() {
		String LBJson = getProperty("sys.splb");

		return LBJson;
	}

	// ��ȡhostip��ַ
	public String getHostIP() {
		String HostIp = getProperty("sys.host");
		if (StringUtils.isEmpty(HostIp)) {
			HostIp = "10.0.2.2";
		}
		return HostIp;
	}

	public String getImageHost() {
		return "http://" + getHostIP() + "/farm";
	}

	// ��ȡ��ǰ��¼��Ϣ
	public void getLoginInfo() {

	}

	/** ��ʼ��ImageLoader */
	public static void initImageLoader(Context context) {
		String filePath = Environment.getExternalStorageDirectory()
				+ "/Android/data/" + context.getPackageName() + "/cache/";

		// File cacheDir = StorageUtils.getOwnCacheDirectory(context,
		// filePath);// ��ȡ�������Ŀ¼��ַ
		// Log.d("cacheDir", cacheDir.getPath());
		// ��������ImageLoader(���е�ѡ��ǿ�ѡ��,ֻʹ����Щ������붨��)����������趨��APPLACATION���棬����Ϊȫ�ֵ����ò���
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				context)
				// .memoryCacheExtraOptions(480, 800) // max width, max
				// height���������ÿ�������ļ�����󳤿�
				// .discCacheExtraOptions(480, 800, CompressFormat.JPEG,
				// 75, null) // Can slow ImageLoader, use it carefully
				// (Better don't use it)���û������ϸ��Ϣ����ò�Ҫ�������
				.threadPoolSize(3)
				// �̳߳��ڼ��ص�����
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.memoryCache(new WeakMemoryCache())
				// .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024
				// * 1024)) // You can pass your own memory cache
				// implementation�����ͨ���Լ����ڴ滺��ʵ��
				.memoryCacheSize(2 * 1024 * 1024)
				// /.discCacheSize(50 * 1024 * 1024)
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				// �������ʱ���URI������MD5
				// ����
				// .discCacheFileNameGenerator(new
				// HashCodeFileNameGenerator())//�������ʱ���URI������HASHCODE����
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				// .discCacheFileCount(100) //�����File����
				// .discCache(new UnlimitedDiscCache(cacheDir))// �Զ��建��·��
				.defaultDisplayImageOptions(DisplayImageOptions.createSimple())
				.imageDownloader(
						new BaseImageDownloader(context, 5 * 1000, 30 * 1000)) // connectTimeout
																				// (5
																				// s),
				// readTimeout(30)// ��ʱʱ��
				.writeDebugLogs() // Remove for release app
				.build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);// ȫ�ֳ�ʼ��������
	}

	private static String lastToast = "";
	private static long lastToastTime;

}
