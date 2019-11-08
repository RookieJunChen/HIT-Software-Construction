package P4.twitter;
import java.time.Instant;
import java.util.*;

import static org.junit.Assert.*;

import org.junit.Test;

public class MySocialNetworkTest {
	private static final Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
    private static final Instant d2 = Instant.parse("2016-02-17T11:00:00Z");
    private static final Instant d3 = Instant.parse("2016-02-17T10:00:00Z");
    private static final Tweet tweet1 = new Tweet(1, "alyssa", "And that concludes my to¡­ #CSCW2014 proceedings @geoge", d1);
    private static final Tweet tweet2 = new Tweet(2, "bbitdiddle", "And that concludes my to¡­ #cscw2014 proceedings @alyssa", d2);
    private static final Tweet tweet3 = new Tweet(1, "geoge", "And that concludes my to¡­ #CSCW2014 proceedings @alyssa", d3);
	@Test
	public void testGuessFollowsGraph() {
		List<Tweet> tweets = new ArrayList<>();
		tweets.add(tweet1);
		tweets.add(tweet2);
		tweets.add(tweet3);
		String a1 = "alyssa";
		String a2 = "bbitdiddle";
		String a3 = "geoge";
		SocialNetwork temp = new SocialNetwork();
		Map<String, Set<String>> socialnetwork  =  temp.myguessFollowsGraph(tweets);

		Map<String, Set<String>> answers = new TreeMap<>();
		Set<String> alyssaS = new HashSet<>();
		alyssaS.add(a2);
		alyssaS.add(a3);
		Set<String> bbitdiddles = new HashSet<>();
		bbitdiddles.add(a1);
		bbitdiddles.add(a3);
		Set<String> geoges = new HashSet<>();
		geoges.add(a1);
		geoges.add(a2);
		answers.put(a1, alyssaS);
		answers.put(a2, bbitdiddles);
		answers.put(a3, geoges);
		assertEquals(answers, socialnetwork);
		
	}

}
