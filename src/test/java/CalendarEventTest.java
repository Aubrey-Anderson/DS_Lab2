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
	GregorianCalendar repeatUntil;
	
	MeetingCalendar cal;
	
	OneTimeEvent eventA;
	OneTimeEvent eventB;
	

	
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
		
		repeatUntil = new GregorianCalendar(2024,8,28,9,00);

		
		
	}

	@Test
	void testOneTimeEvent()
	{
		eventA = new OneTimeEvent("Meeting A", "location A", startA, endA);
		eventB = new OneTimeEvent("Meeting B", "location B", startAB, endB);
		
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
		
		eventA.scheduleEvent(cal);
		eventB.scheduleEvent(cal);
	    assertEquals(eventA.getDescription(), (cal.findMeeting(startAB)).getDescription());
		
	}
	
	@Test
	void testPriorityEvent()
	{
		PriorityEvent eventC = new PriorityEvent("Meeting C", "location C", startAB, endA);
		eventA = new OneTimeEvent("Meeting A", "location A", startA, endA);
		
		assertEquals("Meeting C", eventC.getDescription());
		assertEquals("location C", eventC.getLocation());
		assertEquals(startAB, eventC.getStartTime());
		assertEquals(endA, eventC.getEndTime());
		
		eventC.setDescription("changed C");
		assertEquals("changed C", eventC.getDescription());
		eventC.setLocation("new location");
		assertEquals("new location", eventC.getLocation());
		eventC.setStartTime(startA);
		assertEquals(startA, eventC.getStartTime());
		eventC.setEndTime(endAB);
		assertEquals(endAB, eventC.getEndTime());
		

		eventA.scheduleEvent(cal);
		eventC.scheduleEvent(cal);
		assertEquals(eventC.getDescription(), (cal.findMeeting(startA)).getDescription());
		
	}
	
	@Test 
	void testWeeklyEvent()
	{
		GregorianCalendar startE = new GregorianCalendar(2025,9,6,9,00);
		GregorianCalendar endE = new GregorianCalendar(2025,9,6,10,00);
		WeeklyEvent eventD = new WeeklyEvent("Meeting D", "Location D", startA, endA, repeatUntil);
		WeeklyEvent eventE = new WeeklyEvent("Meeting E", "Location E", startE, endE, repeatUntil);
		eventB = new OneTimeEvent("Meeting B", "location B", startE, endE);
		
		GregorianCalendar newRepeatUntil = new GregorianCalendar(2024,9,4,9,00);
		
		assertEquals("Meeting D", eventD.getDescription());
		assertEquals("Location D", eventD.getLocation());
		assertEquals(startA, eventD.getStartTime());
		assertEquals(endA, eventD.getEndTime());
		assertEquals(repeatUntil, eventD.getRepeatsUntil());
		
		eventD.setDescription("changed D");
		assertEquals("changed D", eventD.getDescription());
		eventD.setLocation("new location");
		assertEquals("new location", eventD.getLocation());
		eventD.setStartTime(startAB);
		assertEquals(startAB, eventD.getStartTime());
		eventD.setEndTime(endAB);
		assertEquals(endAB, eventD.getEndTime());
		eventE.setRepeatsUntil(newRepeatUntil);
		assertEquals(newRepeatUntil, eventE.getRepeatsUntil());
		
		eventD.scheduleEvent(cal);
		
		//GregorianCalendar secondMeeting = new GregorianCalendar(2023,9,4,9,00);
		//GregorianCalendar lastMeeting = new GregorianCalendar(2024,8,28,9,00);
		//GregorianCalendar nonexistentMeeting = new GregorianCalendar(2024,9,4,9,00);
		
		assertEquals(eventD.getDescription(), (cal.findMeeting(startAB)).getDescription());
	//	assertEquals(eventD.getDescription(), (cal.findMeeting(secondMeeting)).getDescription());
	//	assertEquals(eventD.getDescription(), (cal.findMeeting(lastMeeting)).getDescription());
	//	assertNull(cal.findMeeting(nonexistentMeeting));
		
		eventB.scheduleEvent(cal);
		eventE.scheduleEvent(cal);
		
		assertEquals(eventB.getDescription(), cal.findMeeting(startE));
	}
	


}
