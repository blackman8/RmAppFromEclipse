package com.bmb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class StringConstants {

	public static String adb_path = "/Users/zhenhuakong/dev/android-sdks/platform-tools/adb";// 本机的adb文件完整路径
	public static String packageName = "com.sina.weibo";// 需要卸载的本机应用包名
	private static final String propertiesFileName = "rm.ini";

	private StringConstants() {
	}

	private static String getEclipsePath(){
	    File file = new File("");
	    File fir_parent = new File(file.getAbsolutePath());
	    File sec_parent = new File(fir_parent.getAbsolutePath());
	    File thi_parent = new File(sec_parent.getAbsolutePath());
	    File fou_parent = new File(thi_parent.getAbsolutePath());
	    System.out.println(fou_parent.getAbsolutePath());
	    
	    return fou_parent.getAbsolutePath();
	}
	
	public static boolean initProperties() {
		String eclipsePath = getEclipsePath();
		if(null == eclipsePath ||  eclipsePath.length()  < 1){
			return false;
		}
		File file = new File(eclipsePath + File.separator + propertiesFileName);
		if (file.exists()) {
			BufferedReader bufferedReader = null;
			InputStreamReader read = null;
			FileInputStream stream = null;
			try {
				stream = new FileInputStream(file);
				read = new InputStreamReader(stream, "UTF-8");// 考虑到编码格式
				bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					if(null != lineTxt && lineTxt.length() > 0){
						if(lineTxt.startsWith("packagename:")){
							packageName = lineTxt.substring(12);
						}else if(lineTxt.startsWith("adbpath:")){
							adb_path = lineTxt.substring(8);
						}
					}
					System.out.println(lineTxt);
				}
				return true;
			} catch (Exception e) {
				System.out.println("读取文件内容出错");
				e.printStackTrace();
			} finally {
				if (null != bufferedReader) {
					try {
						bufferedReader.close();
						bufferedReader = null;
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (null != read) {
					try {
						read.close();
						read = null;
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (null != stream) {
					try {
						stream.close();
						stream = null;
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

		}else{
			System.out.println("找不到指定的文件");
		}
		return false;
	}

}
