import java.util.GregorianCalendar;
import calendar.MeetingCalendar;

abstract public class CalendarEvent
{
	String Description;
	String location;
	GregorianCalendar startTime;
	GregorianCalendar endTime;

	public CalendarEvent(String desc, String loc, GregorianCalendar start, GregorianCalendar end)
	{

	}

	public abstract void scheduleEvent(MeetingCalendar cal);

	public String getDescription()
	{
		return Description;
	}

	public void setDescription(String description)
	{
		Description = description;
	}

	public String getLocation()
	{
		return location;
	}

	public void setLocation(String location)
	{
		this.location = location;
	}

	public GregorianCalendar getStartTime()
	{
		return startTime;
	}

	public void setStartTime(GregorianCalendar startTime)
	{
		this.startTime = startTime;
	}

	public GregorianCalendar getEndTime()
	{
		return endTime;
	}

	public void setEndTime(GregorianCalendar endTime)
	{
		this.endTime = endTime;
	}
}
