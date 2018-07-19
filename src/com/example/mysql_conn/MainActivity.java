package com.example.mysql_conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mysql.jdbc.Driver;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		new Thread() {
			public void run() {
				addUser();
			};
		}.start();
	}

	/*
	 * 获取服务器的连接对象
	 */
	public Connection conn() {
		try {
			Log.d("htxeian", "5555");
//			Class.forName("com.mysql.fabric.jdbc.FabricMySQLDriver");
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			Log.d("htxeian", "00000");
			Class cls = loader.loadClass("com.mysql.jdbc.Driver");
			cls.newInstance();
			Log.d("htxeian", "00000");
//			Driver d = new Driver();
			String url = "jdbc:mysql://127.0.0.1:3306/htxeian";
//			String url = "url=jdbc:mysql://localhost:3306/htxeian?useUnicode=true&characterEncoding=utf8";
			Log.d("htxeian", "jiazai");
			Connection conn = DriverManager.getConnection(url, "root", "123456");
			return conn;
		} catch (Exception e) {
			Log.d("htxeian", "01111");
			Log.d("htxeian", e.toString());
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * 注册时候进行添加用户 返回0表示正常 返回1表示用户名存在
	 */
	public int addUser() {
		// 判断用户名是否存在
		Connection conn = conn();
		PreparedStatement psta = null;
		try {
			String sql = "insert into user(username,account,password,status) values(?,?,?,?);";
			psta = conn.prepareStatement(sql);

			psta.setString(1, "zp");
			psta.setString(2, "sfagh");
			psta.setString(3, "astd");
			psta.setInt(4, 0);
			boolean is = psta.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				psta.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

}