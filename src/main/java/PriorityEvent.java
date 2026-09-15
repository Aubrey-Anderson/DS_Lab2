import java.util.GregorianCalendar;

import calendar.Meeting;
import calendar.MeetingCalendar;

public class PriorityEvent extends CalendarEvent
{

	public PriorityEvent(String desc, String loc, GregorianCalendar start, GregorianCalendar end)
	{
		super(desc, loc, start, end);
	}

	@Override
	public void scheduleEvent(MeetingCalendar cal)
	{
		Meeting meeting = new Meeting(getDescription(), getLocation(), getStartTime(), getEndTime());
		if(cal.doesMeetingConflict(meeting))
		{
			cal.addMeeting(meeting, true);
		}else {
			cal.addMeeting(meeting);
		}

	}

}
