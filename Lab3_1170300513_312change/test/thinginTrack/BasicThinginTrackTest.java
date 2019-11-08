package thinginTrack;

import static org.junit.Assert.*;

import org.junit.Test;

import physicalObject.PhysicalObject;
import track.Track;

public class BasicThinginTrackTest {

	PhysicalObject p1 = PhysicalObject.Q3creator();
	PhysicalObject p2 = PhysicalObject.Q3creator();
	Track t = Track.Roundcreator(3);
	
	// Testing strategy:
	// 用人工确认的结果与执行方法的结果进行对比，判断方法的正确性。
	
	@Test
	public void testGettrack() {
		BasicThinginTrack<PhysicalObject> thinginTrack = new BasicThinginTrack<>(t);
		assertEquals(t,thinginTrack.gettrack() );
	}

	@Test
	public void testAddobject() {
		BasicThinginTrack<PhysicalObject> thinginTrack = new BasicThinginTrack<>(t);
		thinginTrack.addobject(p2);
		assertEquals(p2, thinginTrack.getthingsintrack().get(0));
	}

	@Test
	public void testGetthingsintrack() {
		BasicThinginTrack<PhysicalObject> thinginTrack = new BasicThinginTrack<>(t);
		thinginTrack.addobject(p1);
		assertEquals(p1, thinginTrack.getthingsintrack().get(0));
	}

	@Test
	public void testDeleteobject() {
		BasicThinginTrack<PhysicalObject> thinginTrack = new BasicThinginTrack<>(t);
		thinginTrack.addobject(p2);
		thinginTrack.addobject(p1);
		thinginTrack.deleteobject(p1);
		assertEquals(p2, thinginTrack.getthingsintrack().get(0));
		
	}

}
