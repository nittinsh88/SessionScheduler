package com.scope.scheduler.track;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 
 * This is the scheduler class that process the tracks and create schedules
 * 
 * @author nittinsharma
 *
 */
public class TrackScheduler {
	// 9 to 12 3 Hours in Minutes
	public static final int MORNING_MINUTES = 180;
	// 1 to 5 4 hours in minutes
	public static final int EVENING_MINUTES = 240;
	// lunch 1 hour in minutes
	public static final int LUNCH_MINUTES = 60;
	// Total hours in one track
	public static final double TOTAL_TRACK_MINUTES = 420;
	// Lunch String
	static String lunchTime = "12:00 PM" + " " + "Lunch";
	// start Time for morning Session
	static LocalTime startTime = LocalTime.of(9, 0);
	// start Time for eve session
	static LocalTime eveTime = LocalTime.of(13, 0);
	// map to save remainig talks
	static Map<String, Integer> remainigtalks;
	// date formatter
	static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm a");

	/**
	 * sets the Tracks
	 * @param talks
	 * @return
	 */
	public static List<LinkedList<String>> scheduleTracks(Map<String, Integer> talks) {
		remainigtalks = new HashMap<>(talks);
		int totalSumofTalks = talks.values().stream().mapToInt(Number::intValue).sum();
		double numberOfTracks = Math.ceil(totalSumofTalks / TOTAL_TRACK_MINUTES);
		List<LinkedList<String>> tracks = new ArrayList<LinkedList<String>>((int) numberOfTracks);
		for (int i = 0; i < numberOfTracks; i++)
			tracks.add(new LinkedList<String>());
		for (LinkedList<String> track : tracks) {
			setTracks(talks, track);
			talks = new HashMap<>(remainigtalks);
		}
		return tracks;

	}

	private static void setTracks(Map<String, Integer> talks, LinkedList<String> track) {
		boolean alreadySet = false;
		LocalTime morningTime = startTime;
		LocalTime eveningTime = eveTime;
		int sum180 = MORNING_MINUTES;
		int sum240 = EVENING_MINUTES;

		for (Entry<String, Integer> entry : talks.entrySet()) {
			// setMorningTracks
			if (sum180 >= entry.getValue()) {
				sum180 = sum180 - entry.getValue();
				String sessionSlot = morningTime.format(dateTimeFormatter) + " " + entry.getKey() + "  "
						+ (entry.getValue().equals(5) ? "lightning" : entry.getValue() + "min");
				if (alreadySet) {
					int lunhIndex = track.indexOf(lunchTime);
					track.add(lunhIndex, sessionSlot);
				} else {
					track.add(sessionSlot);
				}
				remainigtalks.remove(entry.getKey());
				morningTime = morningTime.plusMinutes(entry.getValue());
				continue;

			}

			if (!alreadySet) {
				setLunch(track);
				alreadySet = true;
			}

			// setEveningTracks();
			if (sum240 >= entry.getValue()) {
				sum240 = sum240 - entry.getValue();
				String sessionSlot = eveningTime.format(dateTimeFormatter) + " " + (entry.getKey().lastIndexOf(" D")==-1?entry.getKey():entry.getKey().replaceAll(" D", "")) + "  "
						+ (entry.getValue().equals(5) ? "lightning" : entry.getValue() + "min");
				track.add(sessionSlot);
				remainigtalks.remove(entry.getKey());
				eveningTime = eveningTime.plusMinutes(entry.getValue());
				continue;
			}
			if (sum240 > 0)
				continue;
		}
		if (!alreadySet) {
			setLunch(track);
			alreadySet = true;
		}
		setColleguesEvent(track, eveningTime);
	}

	private static void setColleguesEvent(LinkedList<String> track, LocalTime eveningTime) {
		LocalTime collegueMeetTime = eveningTime;
		if (eveningTime.isBefore(LocalTime.of(16, 0)))
			collegueMeetTime = LocalTime.of(16, 0);
		String collegueEvent = collegueMeetTime.format(dateTimeFormatter) + "  Meet Your Colleagues Event";
		track.add(collegueEvent);
	}

	private static void setLunch(LinkedList<String> track) {
		track.add(lunchTime);
	}

}
