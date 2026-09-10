import java.util.GregorianCalendar;

import calendar.MeetingCalendar;

public class MultiDayPerWeekEvent extends CalendarEvent
{

	GregorianCalendar repeatsUntil;
	int[] days;

	public MultiDayPerWeekEvent(String desc, String loc, GregorianCalendar start, GregorianCalendar end, GregorianCalendar repeat, int[] days)
	{
		super(desc, loc, start, end);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void scheduleEvent(MeetingCalendar cal)
	{
		// TODO Auto-generated method stub

	}

	public GregorianCalendar getRepeatsUntil()
	{
		return repeatsUntil;
	}

	public void setRepeatsUntil(GregorianCalendar repeatsUntil)
	{
		this.repeatsUntil = repeatsUntil;
	}

	public int[] getDays()
	{
		return days;
	}

	public void setDays(int[] days)
	{
		this.days = days;
	}

}
