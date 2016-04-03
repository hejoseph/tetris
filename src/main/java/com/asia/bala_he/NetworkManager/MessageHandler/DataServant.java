package com.asia.bala_he.NetworkManager.MessageHandler;

import java.util.HashMap;
import java.util.Map;


public class DataServant {
	public static Map parseIntoHashMap(String s){
		Map map = new HashMap();
		if(s!=null){
			String[] tab = s.split("&");
			if(tab.length>0){
				for(String val : tab){
					String[] kv = val.split("=");
					if(kv.length>1){
						map.put(kv[0], kv[1]);
					}
				}
			}
		}
		return map;
	}
	
	public static void main(String[] args) {
		Map m = parseIntoHashMap("a=b&c=d");
		System.out.println(m.get("a"));
	}
}
