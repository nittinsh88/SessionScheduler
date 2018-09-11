package test.com.scope.scheduler.track;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.scope.scheduler.track.TrackScheduler;

public class TestScheduler {

	@Test
	public void testScheduleTracks() {
		Map<String, Integer> testInput=new HashMap<>();
		testInput.put("java session", 40);
		testInput.put("spring session",20);
		List<LinkedList<String>> resultTracks=TrackScheduler.scheduleTracks(testInput);
		LinkedList<String>  outputTest=new LinkedList<String>();
		
		outputTest.add("09:00 AM spring session  20min");
		outputTest.add("09:20 AM java session  40min");
		outputTest.add("12:00 PM Lunch");
		outputTest.add("04:00 PM  Meet Your Colleagues Event");
		assertThat(resultTracks.get(0), is(outputTest));

		
		for(LinkedList<String> tracks:resultTracks){
			for(String track:tracks){
		    System.out.println(track);}
			
		}
	}

}
