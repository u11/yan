package com.example.contact;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.model.User;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

public class UserAdapter extends BaseAdapter {
	// 自定义图片Adapter以内部类形式存在于MainActivity中，方便访问MainActivity中的各个变量，特别是imgs数组
	private Context context;// 用于接收传递过来的Context对象
	private LayoutInflater layoutinflater;
	private String inflater = Context.LAYOUT_INFLATER_SERVICE; // 这个必须得有
	private List<User> list;
	private List<HashMap<String, Object>> newslist = new ArrayList<HashMap<String, Object>>();
	private ProgressDialog ProgressDialog1; // 加载对话框
	private ImageLoader imageLoader = ImageLoader.getInstance();
	private DisplayImageOptions options; // imageloade配置
	// private ImageLoader mImageLoader;
	private boolean mBusy = false;

	public void Clearlist() {
		try {
			newslist.clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 构造函数加载上下文
	public UserAdapter(Context context, List<User> list) {
		super();
		this.context = context;
		layoutinflater = (LayoutInflater) context.getSystemService(inflater); // 这个必须得写
		this.list = list;
		options = new DisplayImageOptions.Builder()
				.showStubImage(R.drawable.img_loading)
				.showImageForEmptyUri(R.drawable.img_loading)
				.showImageOnFail(R.drawable.img_loading).cacheInMemory(true)
				.cacheOnDisc(true).displayer(new RoundedBitmapDisplayer(10))
				.build();
		// mImageLoader = new ImageLoader();
	}

	@Override
	public int getCount() {
		return newslist.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public void addlist(List<User> addlist) {
		try {
			for (User model : addlist) {
				HashMap<String, Object> item = new HashMap<String, Object>();
				item.put("id", model.getUId());
				item.put("name", model.getUName());
				item.put("email", model.getUMail());
				item.put("lname", model.ulname);
				item.put("add", model.uadd);
				item.put("tel", model.getUTel());
				item.put("dw", model.udw);
				item.put("lx", model.ulx);
				newslist.add(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		try {
			if (convertView == null) {
				convertView = layoutinflater.from(context).inflate(
						R.layout.item_task, null);//
				viewHolder = new ViewHolder();

				viewHolder.txtType = (TextView) convertView
						.findViewById(R.id.txtType);// 课程名称
				viewHolder.txtEmail = (TextView) convertView
						.findViewById(R.id.txtEmail);// 课程名称
				viewHolder.txtName = (TextView) convertView
						.findViewById(R.id.txtName);// 课程名称
				viewHolder.txtTel = (TextView) convertView
						.findViewById(R.id.txtTel);// 课程名称

				convertView.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) convertView.getTag();
			}
			try {
				if (!mBusy) {
					viewHolder = (ViewHolder) convertView.getTag();
					if(newslist.get(position).get("lx").equals("1")){
						 viewHolder.txtType.setText("注册用户");
						 viewHolder.txtTel.setVisibility(View.GONE);
					}
					if(newslist.get(position).get("lx").equals("0")){
						 viewHolder.txtType.setText("联系人");
						 viewHolder.txtTel.setText(newslist.get(position).get("tel").toString());
							
					}
					viewHolder.txtEmail.setText(newslist.get(position).get("email").toString());
					viewHolder.txtName.setText(newslist.get(position).get("name").toString()+"   "+newslist.get(position).get("lname").toString());
					   

				} else {

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// return linearlayout;
		return convertView;
	}

	static class ViewHolder {
		TextView txtType;// 商品名称
		TextView txtName;// 商品描述
		TextView txtEmail;// 商品类别
		TextView txtTel;// 商品类别
		ImageView imgOrderProduct;
	}
}
