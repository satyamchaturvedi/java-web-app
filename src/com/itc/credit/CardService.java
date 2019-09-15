package com.itc.credit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itc.bean.Card;

@Transactional
@Service
public class CardService {


	String USER_AGENT = "MOZILLA/5.0";
	String url = "http://192.168.22.21:8080/creditcardproject/rest/card/details";

	public boolean validateCard(Card card) {
		boolean valid = false;
		HashMap map = null;
		String delim = System.getProperty("line.separator");
		System.out.println(delim);
		url += "/" + card.getCardNo();

		try {
			URL obj = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
			// URLConnection conn = obj.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("User-Agent", USER_AGENT);
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine + delim);
			}
			in.close();
			System.out.println("res: " + response);
			String val = response.toString();
			System.out.println(val);
			System.out.println();
			map = getReturnVal(val);
			System.out.println(map);
			if (map.get("name") != null) {
				valid = true;
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return valid;
	}

	private HashMap getReturnVal(String val) {
		HashMap keyMap = new HashMap();
		JSONParser parser = new JSONParser();

		Object obj;
		try {
			obj = parser.parse(val);

			System.out.println(obj);
			JSONObject obj2 = (JSONObject) obj;
			Set keySet = obj2.keySet();
			for (Object objKey : keySet) {
				Object jObj = obj2.get(objKey);
				if (jObj instanceof JSONObject) {
					JSONObject obj3 = (JSONObject) jObj;
					Set keySetInner = obj3.keySet();
					HashMap innerMap = new HashMap();
					for (Object objKeyInner : keySetInner) {
						Object jObjInner = obj3.get(objKeyInner);
						innerMap.put(objKeyInner, jObjInner);
					}
					keyMap.put(objKey, innerMap);

				} else if (jObj instanceof JSONArray) {
					JSONArray objArr = (JSONArray) jObj;
					if (objArr.size() > 0) {
						JSONObject obj4 = (JSONObject) objArr.get(0);
						Set keySetInner = obj4.keySet();
						HashMap innerMap = new HashMap();
						for (Object objKeyInner : keySetInner) {
							Object jObjInner = obj4.get(objKeyInner);
							innerMap.put(objKeyInner, jObjInner);
						}
						keyMap.put(objKey, jObj);
					}
				} else {
					keyMap.put(objKey, jObj);
				}
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return keyMap;
	}
}
