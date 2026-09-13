import static org.junit.jupiter.api.Assertions.*;
import java.util.GregorianCalendar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import calendar.Meeting;
import calendar.MeetingCalendar;

class CalendarEventTest
{
    MeetingCalendar cal;

    GregorianCalendar startA;
    GregorianCalendar endA;

    GregorianCalendar startB;
    GregorianCalendar endB;

    GregorianCalendar startC;
    GregorianCalendar endC;

    GregorianCalendar repeatUntil;

    GregorianCalendar monday;
    GregorianCalendar tuesday;
    GregorianCalendar wednesday;
    GregorianCalendar thursday;
    GregorianCalendar friday;

    @BeforeEach
    void setUp() throws Exception
    {
        cal = new MeetingCalendar();

        startA = new GregorianCalendar(2023, 8, 28, 8, 30);
        endA = new GregorianCalendar(2023, 8, 28, 9, 30);

        startB = new GregorianCalendar(2023, 8, 28, 9, 30);
        endB = new GregorianCalendar(2023, 8, 28, 10, 30);

        startC = new GregorianCalendar(2023, 8, 28, 10, 30);
        endC = new GregorianCalendar(2023, 8, 28, 11, 30);
        
        monday = new GregorianCalendar(2023, 9, 2, 8, 30);
        tuesday = new GregorianCalendar(2023, 9, 3, 8, 30);
        wednesday = new GregorianCalendar(2023, 9, 4, 8, 30);
        thursday = new GregorianCalendar(2023, 8, 28, 8, 30);
        friday = new GregorianCalendar(2023, 8, 29, 8, 30);

    }

    @Test
    void testOneTimeEvent()
    {
    	GregorianCalendar emptyTime = new GregorianCalendar(2023, 8, 28, 10, 00);
        OneTimeEvent eventA = new OneTimeEvent("Meeting A", "Location A", startA, endA);
        OneTimeEvent duplicate = new OneTimeEvent("Meeting Dupe", "Location A", startA, endA);

        assertEquals("Meeting A", eventA.getDescription());
        assertEquals("Location A", eventA.getLocation());
        assertEquals(startA, eventA.getStartTime());
        assertEquals(endA, eventA.getEndTime());


        assertNull(cal.findMeeting(startA));
        eventA.scheduleEvent(cal);
        duplicate.scheduleEvent(cal);
        Meeting meeting = cal.findMeeting(startA);
        assertNotNull(meeting);
        assertEquals("Meeting A", meeting.getDescription());

        assertNull(cal.findMeeting(emptyTime));
        
    }

    @Test
    void testPriorityEvent()
    {
    	GregorianCalendar endingB = new GregorianCalendar(2023, 8, 28, 12, 30);
        OneTimeEvent eventA = new OneTimeEvent("Meeting A", "Location A", startA, endA);
        OneTimeEvent eventB = new OneTimeEvent("Meeting B", "Location B", startB, endingB);
        PriorityEvent eventC = new PriorityEvent("Meeting C", "Location C", startB, endC);

        eventA.scheduleEvent(cal);
        assertNotNull(cal.findMeeting(startA));
        assertEquals("Meeting A", cal.findMeeting(startA).getDescription());
        
        eventB.scheduleEvent(cal);
        assertNotNull(cal.findMeeting(startB));
        assertEquals("Meeting B", cal.findMeeting(startB).getDescription());

        eventC.scheduleEvent(cal);
        assertNotNull(cal.findMeeting(startB));
        assertEquals("Meeting C", cal.findMeeting(startB).getDescription());

        assertNotNull(cal.findMeeting(startA));
        assertNull(cal.findMeeting(endingB));
        
    }
	
	
	@Test 
	void testWeeklyEvent()
	{
		GregorianCalendar weeklyStart = new GregorianCalendar(2023, 8, 28, 8, 30);
	    GregorianCalendar weeklyEnd = new GregorianCalendar(2023, 8, 28, 9, 30);
	    GregorianCalendar weeklyRepeatUntil = new GregorianCalendar(2024, 8, 28, 9, 30);
	    
	    WeeklyEvent eventW = new WeeklyEvent("Meeting W", "Location W", weeklyStart, weeklyEnd, weeklyRepeatUntil);
	    assertEquals("Meeting W", eventW.getDescription());
	    assertEquals("Location W", eventW.getLocation());
	    assertEquals(weeklyStart, eventW.getStartTime());
	    assertEquals(weeklyEnd, eventW.getEndTime());
   //   assertEquals(weeklyRepeatUntil, eventW.getRepeatsUntil());
	    
	    GregorianCalendar week2 = new GregorianCalendar(2023, 9, 5, 8, 30);
	    GregorianCalendar lastWeek = new GregorianCalendar(2024, 8, 26, 8, 30);
	    GregorianCalendar invalidWeek = new GregorianCalendar(2024, 9, 3, 8, 30);
		
		eventW.scheduleEvent(cal);
		assertNotNull(cal.findMeeting(weeklyStart));
		assertEquals("Meeting W", cal.findMeeting(weeklyStart).getDescription());
		
		assertNotNull(cal.findMeeting(week2));
		assertEquals("Meeting W", cal.findMeeting(week2).getDescription());
		
		assertNotNull(cal.findMeeting(lastWeek));
		assertEquals("Meeting W", cal.findMeeting(lastWeek).getDescription());
		
		assertNull(cal.findMeeting(invalidWeek));
		
	}
	@Test
	void testWeeklyEventDisplacement()
	{
		GregorianCalendar weeklyRepeatUntil = new GregorianCalendar(2024, 8, 28, 9, 30);
		OneTimeEvent eventA = new OneTimeEvent("Meeting A", "Location A", startA, endA);
		WeeklyEvent overlap = new WeeklyEvent("Meeting Dupe", "Location Duplicate", startA, endA, weeklyRepeatUntil);
		
		eventA.scheduleEvent(cal);
		assertNotNull(cal.findMeeting(startA));
		overlap.scheduleEvent(cal);
        assertEquals("Meeting A", cal.findMeeting(startA).getDescription());
        
        GregorianCalendar dupeWeek2 = new GregorianCalendar(2023, 9, 5, 8, 30);
        assertNotNull(cal.findMeeting(dupeWeek2));
        assertEquals("Meeting Dupe", cal.findMeeting(dupeWeek2).getDescription());
        
	}
	
	@Test
	void testMultiDayPerWeekEvent()
	{
        GregorianCalendar multiRepeatUntil = new GregorianCalendar(2024, 8, 28, 9, 30);

        int[] days = {GregorianCalendar.MONDAY, GregorianCalendar.WEDNESDAY, GregorianCalendar.FRIDAY};

        MultiDayPerWeekEvent eventM = new MultiDayPerWeekEvent("Meeting M", "Location M", startA, endA, multiRepeatUntil, days);

        assertEquals("Meeting M", eventM.getDescription());
        assertEquals("Location M", eventM.getLocation());
        assertEquals(startA, eventM.getStartTime());
        assertEquals(endA, eventM.getEndTime());
  //    assertEquals(multiRepeatUntil, eventM.getRepeatsUntil());

        eventM.scheduleEvent(cal);

        
        assertNotNull(cal.findMeeting(monday));
        assertEquals("Meeting M", cal.findMeeting(monday).getDescription());

        assertNotNull(cal.findMeeting(wednesday));
        assertEquals("Meeting M", cal.findMeeting(wednesday).getDescription());

        assertNotNull(cal.findMeeting(friday));
        assertEquals("Meeting M",cal.findMeeting(friday).getDescription());

        assertNull(cal.findMeeting(tuesday));
        assertNull(cal.findMeeting(thursday));
        
        GregorianCalendar lastMeeting = new GregorianCalendar(2024, 8, 27, 8, 30);
        assertNotNull(cal.findMeeting(lastMeeting));
        assertEquals("Meeting M", cal.findMeeting(lastMeeting).getDescription());
        
        GregorianCalendar tooFarMonday = new GregorianCalendar(2024, 8, 30, 8, 30);
        assertNull(cal.findMeeting(tooFarMonday));
        	
	}
	
	@Test
	void tesMultiDayPerWeekEventDisplacement()
	{
		GregorianCalendar multiRepeatUntil = new GregorianCalendar(2024, 8, 28, 9, 30);
        int[] days = {GregorianCalendar.MONDAY, GregorianCalendar.WEDNESDAY, GregorianCalendar.FRIDAY};
        GregorianCalendar beginA = new GregorianCalendar(2023, 8, 29, 8, 30);
        GregorianCalendar endingA = new GregorianCalendar(2023, 8, 29, 9, 30);
		
		OneTimeEvent eventA = new OneTimeEvent("Meeting A", "Location A", beginA, endingA);
		MultiDayPerWeekEvent overlap = new MultiDayPerWeekEvent("Meeting M", "Location M", startA, endA, multiRepeatUntil, days);
		
		eventA.scheduleEvent(cal);
		assertNotNull(cal.findMeeting(beginA));
		overlap.scheduleEvent(cal);
        assertEquals("Meeting A", cal.findMeeting(friday).getDescription());
        
        GregorianCalendar secondFriday = new GregorianCalendar(2023, 9, 6, 8, 30);
        assertNotNull(cal.findMeeting(secondFriday));
        assertEquals("Meeting M", cal.findMeeting(secondFriday).getDescription());
        
	}

}
