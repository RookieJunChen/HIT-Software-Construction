package debug;

import static org.junit.Assert.*;

import org.junit.Test;

public class TopVotedCandidateTest {

	@Test
	public void testTopVotedCandidate() {
		int[] persons = {0,1,1,0,0,1,0};
		int[] times = {0,5,10,15,20,25,30};
		TopVotedCandidate t = new TopVotedCandidate(persons, times);
		assertEquals(t.q(3), 0);
		assertEquals(t.q(12), 1);
		assertEquals(t.q(25), 1);
		assertEquals(t.q(15), 0);
		assertEquals(t.q(24), 0);
		assertEquals(t.q(8), 1);
	}

}
