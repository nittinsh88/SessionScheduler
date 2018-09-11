package com.test.scheduler;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.test.scheduler.reader.InputReader;
import com.test.scheduler.track.TalkMapper;
import com.test.scheduler.track.TrackScheduler;

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
