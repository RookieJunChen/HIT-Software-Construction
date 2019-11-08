package debug;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.Test;

public class RemoveCommentsTest {

	@Test
	public void testRemoveComments() {
		String[] source1 = {
		"/*Test program */",
		"int main()",
		"{", 
		" // variable declaration", 
		"int a, b, c;",
		"/* This is a test",
			   "multiline"  ,
			   "comment for" ,
			   "testing*/" ,
			"a = b + c;",
			   "}"
		};
		RemoveComments f = new RemoveComments();
		List<String> answer = f.removeComments(source1);
		String line1 = "int main()";
		String line2 = "{";
		String line3 = " ";
		String line4 = "int a, b, c;";
		String line5 = "a = b + c;";
		String line6 =  "}";
		assertEquals(6, answer.size());
		assertEquals(answer.get(0), line1);
		assertEquals(answer.get(1), line2);
		assertEquals(answer.get(2), line3);
		assertEquals(answer.get(3), line4);
		assertEquals(answer.get(4), line5);
		assertEquals(answer.get(5), line6);
	}

}
