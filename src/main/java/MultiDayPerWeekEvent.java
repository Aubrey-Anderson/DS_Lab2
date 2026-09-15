import java.util.GregorianCalendar;

import calendar.MeetingCalendar;

public class MultiDayPerWeekEvent extends CalendarEvent
{

	private GregorianCalendar repeatUntil;
	private int[] days;

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

	public GregorianCalendar getRepeatUntil()
	{
		return repeatUntil;
	}

	public void setRepeatUntil(GregorianCalendar repeatsUntil)
	{
		this.repeatUntil = repeatsUntil;
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
