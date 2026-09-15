import java.util.Calendar;
import java.util.GregorianCalendar;

import calendar.Meeting;
import calendar.MeetingCalendar;

public class WeeklyEvent extends CalendarEvent
{

	private GregorianCalendar repeatUntil;

	public WeeklyEvent(String desc, String loc, GregorianCalendar start, GregorianCalendar end, GregorianCalendar repeat)
	{
		super(desc, loc, start, end);
		this.repeatUntil = repeat;
	}

	public GregorianCalendar getRepeatUntil()
	{
		return repeatUntil;
	}

	public void setRepeatsUntil(GregorianCalendar repeatsUntil)
	{
		this.repeatUntil = repeatsUntil;
	}

	@Override
	public void scheduleEvent(MeetingCalendar cal)
	{
		GregorianCalendar weekEndTime = (GregorianCalendar)getEndTime().clone();
		GregorianCalendar i = (GregorianCalendar)getStartTime().clone();
		while(i.before(getRepeatUntil()))
		{
			Meeting meeting = new Meeting(getDescription(), getLocation(), i, weekEndTime);
			if(!cal.doesMeetingConflict(meeting))
			{
				cal.addMeeting(meeting);
			}
			i.add(Calendar.DAY_OF_MONTH, 7);
			weekEndTime.add(Calendar.DAY_OF_MONTH, 7);
		}

	}

}
