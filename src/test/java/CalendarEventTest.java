import static org.junit.jupiter.api.Assertions.*;
import java.util.GregorianCalendar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import calendar.Meeting;
import calendar.MeetingCalendar;

class CalendarEventTest
{
	Meeting A;
	Meeting B;
	Meeting C;
	

	Meeting AB;
	Meeting BC;
	
	GregorianCalendar startA;
	GregorianCalendar endA;
	GregorianCalendar startAB;
	GregorianCalendar endAB;
	GregorianCalendar endB;
	GregorianCalendar endC;
	
	MeetingCalendar cal;
	
	OneTimeEvent eventA;
	
	@BeforeEach
	void setUp() throws Exception
	{
		
		cal = new MeetingCalendar();
		startA = new GregorianCalendar(2023,8,28,8,30);
		endA = new GregorianCalendar(2023,8,28,9,30);
		endB = new GregorianCalendar(2023,8,28,10,30);
		endC = new GregorianCalendar(2023,8,28,11,30);
		
		startAB = new GregorianCalendar(2023,8,28,9,00);
		endAB = new GregorianCalendar(2023,8,28,10,00);

		eventA = new OneTimeEvent("Meeting A", "location A", startA, endA);
		
	}

	@Test
	void testOneTimeEvent()
	{
		
		assertEquals("Meeting A", eventA.getDescription());
		assertEquals("location A", eventA.getLocation());
		assertEquals(startA, eventA.getStartTime());
		assertEquals(endA, eventA.getEndTime());
		
		eventA.setDescription("changed A");
		assertEquals("changed A", eventA.getDescription());
		eventA.setLocation("new location");
		assertEquals("new location", eventA.getLocation());
		eventA.setStartTime(startAB);
		assertEquals(startAB, eventA.getStartTime());
		eventA.setEndTime(endAB);
		assertEquals(endAB, eventA.getEndTime());
		
	}
	
	@Test
	void testScheduleEvent() 
	{
		eventA.scheduleEvent(cal);
	}

}
