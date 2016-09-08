package com.fxiaoke.dataUtill;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ReadjsonData {

	public static void main(String[] args) throws JSONException {
		String jsonData = read("jsapi.json");
		JSONArray picname = ReadjsonData.readjsonArray(jsonData, "pic_name");
//		JSONObject mjson = readjson(jsonData, "xpath");
//		String d = mjson.getString("选择部门").toString();
//		String p = mjson.getString("上传文件").toString();
//		String a = mjson.getString("上传图片");
		JSONObject mjson = new JSONObject(jsonData);
		String name = picname.get(0).toString();
//		int m = Integer.parseInt(mjson.getString("picUpload_num"));
		 System.out.printf( name + "\n");
//		 System.out.printf(d + "\n" + p + "\n" + a + "\n" + m + "\n");
	}

	public static String readString(String data){
		int index1 =data.indexOf("：");
//		int index2 =data.indexOf("FS log",1);
		String  resultdata =data. substring(index1+1);
		 System.out.printf("resultdata =" + resultdata );
		return  resultdata;
	}
	
	
	public static String read(String jsonfilename) {
		File classpathRoot = new File(System.getProperty("user.dir"));
		File jsonDir = new File(classpathRoot, "json");
		File json = new File(jsonDir,jsonfilename);
		BufferedReader reader = null;
		String temp = "";
		String s = "";
		try {
			reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(json), "UTF-8"));
			while ((temp = reader.readLine()) != null) {
				s = s + temp;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return s;
	}

	public static JSONObject readjsonObject(String jsonData, String word)
			throws JSONException {
		JSONObject jsonObject = new JSONObject(jsonData);
		String jsoncapa = jsonObject.getString(word).toString();
		JSONObject jsonOb = new JSONObject(jsoncapa);
		return jsonOb;
	}
	
	public static String readjsonString(String jsonData, String word)
			throws JSONException {
		JSONObject jsonObject = new JSONObject(jsonData);
		String jsoncapa = jsonObject.getString(word).toString();
		return jsoncapa;
	}


	public static JSONArray readjsonArray(String jsonData, String word) {
		JSONArray jsonArray = null;
		try {
			JSONObject jsonObject = new JSONObject(jsonData);
			jsonArray = jsonObject.getJSONArray(word);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArray;
	}
  public static List<String> getAllKeys(String data) throws JSONException{
	  List<String> listkey = new ArrayList<String>();
	  JSONObject jsonObject = new JSONObject(data);
	  Iterator keys=jsonObject.keys();
	  while(keys.hasNext()){
//		   System.out.printf(keys.next().toString());
		  listkey.add(keys.next().toString());
	  }
	  return listkey;
  }
}
