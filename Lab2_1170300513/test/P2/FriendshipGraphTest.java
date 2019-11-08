package P2;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import P1.*;

public class FriendshipGraphTest {
	 // Testing strategy:
	 //构造一个解集answer，与实际方法得到的结果进行对比。
	@Test
	public void testAddVertex() {
		Person rachel = new Person("Rachel");
		Person ross = new Person("Ross");
		Person ben = new Person("Ben");
		Person kramer = new Person("Kramer");
		Person geoge = new Person("Geoge");
		Person kawhi = new Person("Kawhi");
		Person paul = new Person("Paul");
		Person james = new Person("James");
		FriendshipGraph graph = new FriendshipGraph(); //测试集。
		Set<String> answers = new HashSet<>();//答案集。
		graph.addVertex(rachel); //测试集调用FriendshipGraph类中的函数.
		graph.addVertex(ross);
		graph.addVertex(ben);
		graph.addVertex(kramer);
		graph.addVertex(geoge);
		graph.addVertex(kawhi);
		graph.addVertex(paul);
		graph.addVertex(james);
		answers.add(rachel.name);
		answers.add(ross.name);
		answers.add(ben.name);
		answers.add(kramer.name);
		answers.add(geoge.name);
		answers.add(kawhi.name);
		answers.add(paul.name);
		answers.add(james.name);
		assertEquals(answers, graph.g.vertices());

	}

	@Test
	public void testAddEdge() {
		FriendshipGraph graph = new FriendshipGraph();
		FriendshipGraph answers = new FriendshipGraph();
		Person rachel = new Person("Rachel");
		Person ross = new Person("Ross");
		graph.addVertex(ross);
		graph.addVertex(rachel);
		graph.addEdge(rachel, ross);
		String answer = "Vertices: \n" + 
				"Rachel\n" + 
				"Ross\n" + 
				"Edges: \n" + 
				"(Rachel,Ross) : 1\n";
		System.out.println(graph.g);
		assertEquals(answer, graph.g.toString());
	}

	@Test
	public void testGetDistance() {
		FriendshipGraph graph = new FriendshipGraph();
		Person rachel = new Person("Rachel");
		Person ross = new Person("Ross");
		Person ben = new Person("Ben");
		Person kramer = new Person("Kramer");
		Person geoge = new Person("Geoge");
		graph.addVertex(rachel);
		graph.addVertex(ross);
		graph.addVertex(ben);
		graph.addVertex(kramer);
		graph.addVertex(geoge);
		graph.addEdge(kramer, geoge); 
		graph.addEdge(ben, kramer);
		graph.addEdge(kramer, ben);
		graph.addEdge(rachel, ben);
		graph.addEdge(ben, rachel);
		graph.addEdge(ben, ross);
		graph.addEdge(ross, ben);
		assertEquals(3, graph.getDistance(rachel, geoge));
		assertEquals(1, graph.getDistance(rachel, ben));
		assertEquals(2, graph.getDistance(rachel, kramer));
		assertEquals(2, graph.getDistance(ross, kramer));
		assertEquals(1, graph.getDistance(kramer, geoge));
	}

}
