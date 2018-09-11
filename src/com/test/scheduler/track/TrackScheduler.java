package com.test.scheduler.track;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**Each session contains multiple talks.

 * @author nitsharm7
 *
 */
public class TrackScheduler {
	//9 to 12 
		public static final int MORNING_MINUTES= 180;
		//1 to 5
		public static final int EVENING_MINUTES= 240;
		//lunch
		public static final int LUNCH_MINUTES= 60;
		//Total hours in one track
		public static final double TOTAL_TRACK_MINUTES=  420;
		//Lunch String
		static String lunchTime = "12:00 PM" + " " + "Lunch";
		
		static LocalTime startTime=LocalTime.of(9, 0);
		static LocalTime eveTime=LocalTime.of(13, 0);
		static Map<String,Integer> remainigtalks;
		static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
	
	
	 public   static List<LinkedList<String>>  scheduleTracks(Map<String,Integer> talks){
		 remainigtalks=new HashMap<>(talks);
		 int totalSumofTalks = talks.values().stream().mapToInt(Number::intValue).sum();
		 double numberOfTracks=Math.ceil(totalSumofTalks/TOTAL_TRACK_MINUTES);
		 List<LinkedList<String>> tracks=new ArrayList<LinkedList<String>>((int) numberOfTracks);
		 for (int i=0;i<numberOfTracks;i++)
		 tracks.add(new LinkedList<String>());
		 for(LinkedList<String> track:tracks){			 
		 setTracks(talks,track);
		 talks=new HashMap<>(remainigtalks);
		 }  
		   
		return tracks;
		
	}


		private static void setTracks(Map<String, Integer> talks,LinkedList<String> track) {
			  boolean alreadySet=false;
			LocalTime sessionTime=startTime;
			LocalTime morningTime=startTime;
			LocalTime eveningTime=eveTime;
			int  sum180=MORNING_MINUTES;
			 int  sum240=EVENING_MINUTES;			 
		for(Entry<String, Integer> entry : talks.entrySet()){
		 //setMorningTracks
			  if (sum180 >= entry.getValue()) {	        	
	        	sum180 = sum180 - entry.getValue();
	        	String sessionSlot=morningTime.format(dateTimeFormatter)+" "+entry.getKey()+"  "+(entry.getValue().equals(5)?"lightning":entry.getValue()+"min");
	        	if(alreadySet){
	        		int lunhIndex=track.indexOf(lunchTime);
	        			        			 track.add(lunhIndex, sessionSlot);
	        		}else{
	        	track.add(sessionSlot);}
	        	remainigtalks.remove(entry.getKey());
	        	morningTime=morningTime.plusMinutes(entry.getValue());
	        	continue;

	        }
       
		if(!alreadySet){
		 setLunch(track,sessionTime);
		alreadySet=true; 
		}
		
		//setEveningTracks();
		 if (sum240 >= entry.getValue()) {
             sum240 = sum240 - entry.getValue();
             String sessionSlot=eveningTime.format(dateTimeFormatter)+" "+entry.getKey()+"  "+(entry.getValue().equals(5)?"lightning":entry.getValue()+"min");
             track.add(sessionSlot);
             remainigtalks.remove(entry.getKey());
             eveningTime=eveningTime.plusMinutes(entry.getValue());
              continue;
     
         }         
		if (sum240 > 0)
             continue;
     }
		 
		 
		 setColleguesEvent(track,eveningTime);
		 }
	


	private static void setColleguesEvent(LinkedList<String> track, LocalTime eveningTime) {
		LocalTime collegueMeetTime = eveningTime;
		if(eveningTime.isBefore(LocalTime.of(16, 0)))
			collegueMeetTime=LocalTime.of(16, 0);			
		String collegueEvent=collegueMeetTime.format(dateTimeFormatter)+"  Meet Your Colleagues Event";
		track.add(collegueEvent);
		
	}


	private static void setEveningTracks() {
		// TODO Auto-generated method stub
		
	}


	private static void setLunch(LinkedList<String> track, LocalTime sessionTime) {
		sessionTime.plusMinutes(60);
		track.add(lunchTime);
		
	}


	private static void setMorningTracks(Entry<String, Integer> entry, LinkedList<String> track) {
		int  sum180=MORNING_MINUTES;
        if (sum180 >= entry.getValue()) {
        	sum180 = sum180 - entry.getValue();
        	String sessionSlot=startTime+" "+entry.getKey()+" "+" "+entry.getValue()+"min";
        	track.add(sessionSlot);
        	startTime=startTime.plusMinutes(entry.getValue());
//            sessionTime = sdf.format(cal.getTime()) + " " + trackTalks.get(TalkIndex).getTitle() + " " + trackTalks.get(TalkIndex).getMinutes() + "min";
//            trackTalks.get(TalkIndex).setTitle(sessionTime);
//            cal.add(Calendar.MINUTE, trackTalks.get(TalkIndex).getMinutes());
//            SessionTitle = "Track" + " " + (trackCountIndex + 1);
//            trackTalks.get(TalkIndex).setTrackTitle(SessionTitle);
        }
//        if (sum180 < entry.getValue())
//            break;
//
//        if (sum180 > 0)
//            continue;
//
//        if (sum180 <= 0)
//            break;
    }
		
	

}
