package track;

import static org.junit.Assert.*;

import org.junit.Test;

public class OvalTrackTest {

	Track t1 = Track.Ovalcreator(4, 2);
	Track t2 = Track.Ovalcreator(4, 2);
	
	// Testing strategy:
	// 用人工确认的结果与执行方法的结果进行对比，判断方法的正确性。
	
	@Test
	public void testEqualsTrack() {
		assertEquals(true, t1.equals(t2));
	}

	@Test
	public void testGetridus() {
		assertEquals(3, t1.getridus().intValue());
	}

	@Test
	public void testGeta() {
		assertEquals(4, t1.geta().intValue());
	}

	@Test
	public void testGetb() {
		assertEquals(2, t1.getb().intValue());
	}

}
