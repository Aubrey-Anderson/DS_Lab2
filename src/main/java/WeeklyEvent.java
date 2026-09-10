import java.util.GregorianCalendar;
import calendar.MeetingCalendar;

public class WeeklyEvent extends CalendarEvent
{

	GregorianCalendar repeatsUntil;

	public WeeklyEvent(String desc, String loc, GregorianCalendar start, GregorianCalendar end, GregorianCalendar repeat)
	{
		super(desc, loc, start, end);
		// TODO Auto-generated constructor stub
	}

	public GregorianCalendar getRepeatsUntil()
	{
		return repeatsUntil;
	}

	public void setRepeatsUntil(GregorianCalendar repeatsUntil)
	{
		this.repeatsUntil = repeatsUntil;
	}

	@Override
	public void scheduleEvent(MeetingCalendar cal)
	{
		// TODO Auto-generated method stub

	}

}
