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

	/** 获取Application */
	public static App getApp() {
		return mAppApplication;
	}

	@Override
	public void onTerminate() {

		super.onTerminate();
		// 整体摧毁的时候调用这个方法
	}

	// 设置属性信息 批量设置一组属性例如用户信息等内容
	public void setProperties(Properties ps) {
		AppConfig.getAppConfig(this).set(ps);
	}

	// 获取属性信息
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
	 * 获取App安装包信息
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

	// 获取设备ID
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

	// 获取手机厂商设备的信息
	public String getDeviceInfo() {
		return android.os.Build.MANUFACTURER + android.os.Build.MODEL + ","
				+ android.os.Build.VERSION.SDK + ","
				+ android.os.Build.VERSION.RELEASE;
	}

	// 注销登录 清除token 清除缓存用户信息 并且发起广播通知
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

	// 设置登录信息
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

	// 设置商品分类的json数据
	public void setMainLBJson(String json) {
		setProperty("sys.splb", String.valueOf(json));
	}

	// 获取商品类别JSON
	public String getMainLBJson() {
		String LBJson = getProperty("sys.splb");

		return LBJson;
	}

	// 获取hostip地址
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

	// 获取当前登录信息
	public void getLoginInfo() {

	}

	/** 初始化ImageLoader */
	public static void initImageLoader(Context context) {
		String filePath = Environment.getExternalStorageDirectory()
				+ "/Android/data/" + context.getPackageName() + "/cache/";

		// File cacheDir = StorageUtils.getOwnCacheDirectory(context,
		// filePath);// 获取到缓存的目录地址
		// Log.d("cacheDir", cacheDir.getPath());
		// 创建配置ImageLoader(所有的选项都是可选的,只使用那些你真的想定制)，这个可以设定在APPLACATION里面，设置为全局的配置参数
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				context)
				// .memoryCacheExtraOptions(480, 800) // max width, max
				// height，即保存的每个缓存文件的最大长宽
				// .discCacheExtraOptions(480, 800, CompressFormat.JPEG,
				// 75, null) // Can slow ImageLoader, use it carefully
				// (Better don't use it)设置缓存的详细信息，最好不要设置这个
				.threadPoolSize(3)
				// 线程池内加载的数量
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.memoryCache(new WeakMemoryCache())
				// .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024
				// * 1024)) // You can pass your own memory cache
				// implementation你可以通过自己的内存缓存实现
				.memoryCacheSize(2 * 1024 * 1024)
				// /.discCacheSize(50 * 1024 * 1024)
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				// 将保存的时候的URI名称用MD5
				// 加密
				// .discCacheFileNameGenerator(new
				// HashCodeFileNameGenerator())//将保存的时候的URI名称用HASHCODE加密
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				// .discCacheFileCount(100) //缓存的File数量
				// .discCache(new UnlimitedDiscCache(cacheDir))// 自定义缓存路径
				.defaultDisplayImageOptions(DisplayImageOptions.createSimple())
				.imageDownloader(
						new BaseImageDownloader(context, 5 * 1000, 30 * 1000)) // connectTimeout
																				// (5
																				// s),
				// readTimeout(30)// 超时时间
				.writeDebugLogs() // Remove for release app
				.build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);// 全局初始化此配置
	}

	private static String lastToast = "";
	private static long lastToastTime;

}
