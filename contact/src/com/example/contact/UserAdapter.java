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
	// �Զ���ͼƬAdapter���ڲ�����ʽ������MainActivity�У��������MainActivity�еĸ����������ر���imgs����
	private Context context;// ���ڽ��մ��ݹ�����Context����
	private LayoutInflater layoutinflater;
	private String inflater = Context.LAYOUT_INFLATER_SERVICE; // ����������
	private List<User> list;
	private List<HashMap<String, Object>> newslist = new ArrayList<HashMap<String, Object>>();
	private ProgressDialog ProgressDialog1; // ���ضԻ���
	private ImageLoader imageLoader = ImageLoader.getInstance();
	private DisplayImageOptions options; // imageloade����
	// private ImageLoader mImageLoader;
	private boolean mBusy = false;

	public void Clearlist() {
		try {
			newslist.clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ���캯������������
	public UserAdapter(Context context, List<User> list) {
		super();
		this.context = context;
		layoutinflater = (LayoutInflater) context.getSystemService(inflater); // ��������д
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
						.findViewById(R.id.txtType);// �γ�����
				viewHolder.txtEmail = (TextView) convertView
						.findViewById(R.id.txtEmail);// �γ�����
				viewHolder.txtName = (TextView) convertView
						.findViewById(R.id.txtName);// �γ�����
				viewHolder.txtTel = (TextView) convertView
						.findViewById(R.id.txtTel);// �γ�����

				convertView.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) convertView.getTag();
			}
			try {
				if (!mBusy) {
					viewHolder = (ViewHolder) convertView.getTag();
					if(newslist.get(position).get("lx").equals("1")){
						 viewHolder.txtType.setText("ע���û�");
						 viewHolder.txtTel.setVisibility(View.GONE);
					}
					if(newslist.get(position).get("lx").equals("0")){
						 viewHolder.txtType.setText("��ϵ��");
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
		TextView txtType;// ��Ʒ����
		TextView txtName;// ��Ʒ����
		TextView txtEmail;// ��Ʒ���
		TextView txtTel;// ��Ʒ���
		ImageView imgOrderProduct;
	}
}
