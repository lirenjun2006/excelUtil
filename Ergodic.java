package com.fxiaoke.dataUtill;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Ergodic {
	public static void main(String[] args) throws JSONException {
		List<List> strs = new ArrayList<List>();
		String data = ReadjsonData.read("testwe1.json");
		addList(strs, data, "max");
		addList(strs, data, "selectedUsers");
		addList(strs, data, "filterUsers");
		addList(strs, data, "radio");
		addList(strs, data, "title");
		addList(strs, data, "excludeSelf");
		addList(strs, data, "firstSelf");
		addList(strs, data, "isPrivate");
//		List<String> result = descart(strs);
//		for (int i = 0; i < result.size(); i++) {
//			ReportUtil.log(result.get(i));
//		}

	}

	public static void addList(List<List> alist, String data, String parameter)
			throws JSONException {
		JSONArray arrydata = ReadjsonData.readjsonArray(data, parameter);
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < arrydata.length(); i++) {
			list.add(arrydata.get(i).toString());
		}
		alist.add(list);
	}

	public static List<String>  descart(List<List> strs) throws JSONException {
		int total = 1;
		for (int i = 0; i < strs.size(); i++) {
			total *= strs.get(i).size();
		}
		String[] mysesult = new String[total];
		int now = 1;
		// 每个元素每次循环打印个数
		int itemLoopNum = 1;
		// 每个元素循环的总次数
		int loopPerItem = 1;
		for (int i = 0; i < strs.size(); i++) {
			List temp = strs.get(i);
			now = now * temp.size();
			// 目标数组的索引值
			int index = 0;
			int currentSize = temp.size();
			itemLoopNum = total / now;
			loopPerItem = total / (itemLoopNum * currentSize);
			int myindex = 0;
			for (int j = 0; j < temp.size(); j++) {

				// 每个元素循环的总次数
				for (int k = 0; k < loopPerItem; k++) {
					if (myindex == temp.size())
						myindex = 0;
					// 每个元素每次循环打印个数
					for (int m = 0; m < itemLoopNum; m++) {
						mysesult[index] = (mysesult[index] == null ? ""
								: mysesult[index] + ",")
								+ ((String) temp.get(myindex));
						index++;
					}
					myindex++;

				}
			}
		}
		return Arrays.asList(mysesult);
	}

}
