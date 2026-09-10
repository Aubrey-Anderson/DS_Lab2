import java.util.GregorianCalendar;
import calendar.MeetingCalendar;

public class OneTimeEvent extends CalendarEvent
{

	public OneTimeEvent(String desc, String loc, GregorianCalendar start, GregorianCalendar end)
	{
		super(desc, loc, start, end);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void scheduleEvent(MeetingCalendar cal)
	{
		// TODO Auto-generated method stub

	}

}
