package com.scope.scheduler;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.scope.scheduler.reader.InputReader;
import com.scope.scheduler.track.TalkMapper;
import com.scope.scheduler.track.TrackScheduler;

/**
 * @author nittinsharma
 * 
 * This is the Main Class to Run the app
 *
 */
public class Main {
	
	
	public static void main(String[] args) {		
		List<String> rawSessions=InputReader.read();
		Map<String,Integer> talks=TalkMapper.mapRawInputToTalks(rawSessions);
		List<LinkedList<String>> resultTracks=TrackScheduler.scheduleTracks(talks);
		int i=1;
		for(LinkedList<String> tracks:resultTracks){
			System.out.println("Track :"+i);
			for(String track:tracks){
		    System.out.println(track);}
			i++;
		}
	}

}
