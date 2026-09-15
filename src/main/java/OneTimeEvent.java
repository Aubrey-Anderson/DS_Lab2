import java.util.GregorianCalendar;
import calendar.Meeting;
import calendar.MeetingCalendar;

public class OneTimeEvent extends CalendarEvent
{

	public OneTimeEvent(String desc, String loc, GregorianCalendar start, GregorianCalendar end)
	{
		super(desc, loc, start, end);
	}

	@Override
	public void scheduleEvent(MeetingCalendar cal)
	{
		Meeting meeting = new Meeting(getDescription(), getLocation(), getStartTime(), getEndTime());
		if(!cal.doesMeetingConflict(meeting))
		{
			cal.addMeeting(meeting);
		}

	}

}
