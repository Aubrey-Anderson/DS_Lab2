package calendar;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class MeetingCalendar
{
	ArrayList<Meeting> meetings = new ArrayList<>();

	/**
	 * Finds all overlapping meetings
	 * 
	 * @param meeting
	 * @return and ArrayList of the meetings
	 */
	private ArrayList<Meeting> findConflicts(Meeting meeting)
	{

		ArrayList<Meeting> conflicts = new ArrayList<>();

		for (Meeting m : meetings)
		{
			if (m.overlaps(meeting))
			{
				conflicts.add(m);
			}
		}
		return conflicts;
	}

	/**
	 * 
	 * @param meeting
	 * @return true if there is a conflict between on of the meetings scheduled and
	 *         the meeting parameter
	 */
	public boolean doesMeetingConflict(Meeting meeting)
	{
		return findConflicts(meeting).size() > 0;

	}

	/**
	 * Adds a meeting to the MeetingCalendar. If there is a scheduling conflict,
	 * this method will use the value of force to determine what to do. If force is
	 * false, the scheduling will fail. If it is True, any conflicting meetings will
	 * be removed before this one is scheduled.
	 * 
	 * @param meeting
	 * @param force   describes what to do if there is a scheduling conflict.
	 * @return true if the meeting was added.
	 */
	public boolean addMeeting(Meeting meeting, boolean force)
	{

		ArrayList<Meeting> conflicts = findConflicts(meeting);
		boolean noConflict = conflicts.size() == 0;

		if (force)
		{
			for (Meeting m : conflicts)
			{
				meetings.remove(m);
			}
		}

		if (noConflict || force)
		{
			try
			{
				meetings.add((Meeting) meeting.clone());
			} catch (CloneNotSupportedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}

		return false;
	}

	/**
	 * Adds the meeting if it does not conflict with an existing meeting.
	 * 
	 * @param meeting
	 * @return
	 */
	public boolean addMeeting(Meeting meeting)
	{
		return addMeeting(meeting, false);
	}

	/**
	 * Returns the meeting that overlaps with the time variable. If there is more
	 * than one meeting (i.e. time is the start of one meeting and the ending time
	 * of another meeting), This function will return meeting that is just starting.
	 * 
	 * @param time
	 * @return a meeting if it finds one, and null otherwise.
	 */
	public Meeting findMeeting(GregorianCalendar time)
	{
		Meeting best = null;
		for (Meeting m : meetings)
		{
			if (m.overlaps(time))
			{
				if (m.getStartTime().equals(time))
				{// could be two options at boundaries, choose the meeting that is starting
					return m;
				}
				best = m;
			}
		}
		return best;
	}

	/**
	 * Removes the meeting m. If m is not present this method will do nothing.
	 * 
	 * @param m
	 */
	public void removeMeeting(Meeting m)
	{
		meetings.remove(m);
	}

}
