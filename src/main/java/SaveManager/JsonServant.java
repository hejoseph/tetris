//package SaveManager;
//
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Map;
//
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//
//public class JsonServant {
//	public static void main(String[] args) {
////		Map m = HashMap();
////		writeToFile();
//	}
//
//	public static void writeToFile(Object o) {
//		JSONObject obj1 = new JSONObject();
//	  	obj1.put("name", "mkyong.com");
//	  	obj1.put("age", new Integer(100));
//
//	  	JSONArray list = new JSONArray();
//	  	list.add("msg 1");
//	  	list.add("msg 2");
//	  	list.add("msg 3");
//
//	  	obj1.put("messages", list);
//
//	  	try {
//
//	  		FileWriter file = new FileWriter("test.json");
//	  		file.write(obj1.toJSONString());
//	  		file.flush();
//	  		file.close();
//
//	  	} catch (IOException e) {
//	  		e.printStackTrace();
//	  	}
//
//	  	System.out.print(obj1);
//	}
//	
//	public static void readFromFile(){
//		JSONParser parser = new JSONParser();
//
//		try {
//
//			Object obj = parser.parse(new FileReader("c:\\test.json"));
//
//			JSONObject jsonObject = (JSONObject) obj;
//
//			String name = (String) jsonObject.get("name");
//			System.out.println(name);
//
//			long age = (Long) jsonObject.get("age");
//			System.out.println(age);
//
//			// loop array
//			JSONArray msg = (JSONArray) jsonObject.get("messages");
//			Iterator<String> iterator = msg.iterator();
//			while (iterator.hasNext()) {
//				System.out.println(iterator.next());
//			}
//
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//
//	     }
//}
//
