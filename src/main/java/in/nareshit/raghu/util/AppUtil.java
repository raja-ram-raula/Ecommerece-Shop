package in.nareshit.raghu.util;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class AppUtil {
	
	public static Map<Integer,String> convertListToMap(List<Object[]> list) {
		return list.stream()
				.collect(
						Collectors.toMap(
								ob-> Integer.valueOf(ob[0].toString()),
								ob -> ob[1].toString())
						);	
	}
	
	public static Map<Long,String> convertListToMapLong(List<Object[]> list) {
		return list.stream()
				.collect(
						Collectors.toMap(
								ob-> Long.valueOf(ob[0].toString()),
								ob -> ob[1].toString())
						);	
	}
	
	public static String genPwd() {
		return UUID.randomUUID()
				.toString()
				.replace("-", "")
				.substring(0,8);
	}

}
