package com.android.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ImageService {

	public static byte[] getImageByte(String path) throws IOException {
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET"); // �������󷽷�ΪGET
		conn.setReadTimeout(5 * 1000); // ���������ʱʱ��Ϊ5��
		InputStream inputStream = conn.getInputStream(); // ͨ�����������ͼƬ����
		StreamTool str = new StreamTool();
		byte[] data = null;
		try {
			data = str.readinstream(inputStream);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // ���ͼƬ�Ķ���������
		return data;
	}

	/**
	 * ��ȡͼƬ
	 * 
	 * @param path
	 *            ͼƬ·��
	 * @return
	 */
	public static Bitmap getImageBitmap(String path) throws Exception {
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(5000);
		conn.setRequestMethod("GET");
		if (conn.getResponseCode() == 200) {
			InputStream inStream = conn.getInputStream();
			Bitmap bitmap = BitmapFactory.decodeStream(inStream);
			return bitmap;
		}
		return null;
	}
}
