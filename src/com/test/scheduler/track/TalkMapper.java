package com.test.scheduler.track;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TalkMapper {
	
	
	public static Map<String,Integer>  mapRawInputToTalks(List<String> rawSessions){
		Map<String,Integer> talksMap=new HashMap<>();
		for(String rawSession: rawSessions ){
			 int intMinutes;
			 int totalMinutes = 0;
		String title = rawSession.substring(0, rawSession.lastIndexOf(" "));
        String minutesString = rawSession.substring(rawSession.lastIndexOf(" ") + 1).replaceAll("[0-9]","");
        String minutes = rawSession.replaceAll("\\D+", "");
        if("lightning".equals(minutesString))
        {
            intMinutes = 5;

            totalMinutes = totalMinutes + intMinutes;
        }else
        {
            intMinutes = Integer.parseInt(minutes);
            totalMinutes = totalMinutes + intMinutes;
        }
        talksMap.put(title, totalMinutes);
        
		
		}
		return talksMap;
		
	}

}
