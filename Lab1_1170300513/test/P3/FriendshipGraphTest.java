package P3;

import static org.junit.Assert.*;

import org.junit.Test;

public class FriendshipGraphTest {

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
		FriendshipGraph answer = new FriendshipGraph(); //答案集。
		answer.Vertex.add(rachel);//答案集直接添加答案。
		answer.Vertex.add(ross);
		answer.Vertex.add(ben);
		answer.Vertex.add(kramer);
		answer.Vertex.add(geoge);
		answer.Vertex.add(kawhi);
		answer.Vertex.add(paul);
		answer.Vertex.add(james);
		graph.addVertex(rachel); //测试集调用FriendshipGraph类中的函数.
		graph.addVertex(ross);
		graph.addVertex(ben);
		graph.addVertex(kramer);
		graph.addVertex(geoge);
		graph.addVertex(kawhi);
		graph.addVertex(paul);
		graph.addVertex(james);
		assertEquals(answer.Vertex, graph.Vertex );
	}

	@Test
	public void testAddEdge() {
		FriendshipGraph graph = new FriendshipGraph();
		FriendshipGraph answer = new FriendshipGraph();
		Person temp;
		Person rachel = new Person("Rachel");
		Person ross = new Person("Ross");
		Person ben = new Person("Ben");
		Person kramer = new Person("Kramer");
		graph.addVertex(rachel);
		graph.addVertex(ross);
		graph.addVertex(ben);
		graph.addVertex(kramer);
		graph.addEdge(rachel, ross);
		graph.addEdge(ross, rachel);
		graph.addEdge(ross, ben);
		graph.addEdge(ben, ross);
		answer.Vertex.add(rachel);
		answer.Vertex.add(ross);
		answer.Vertex.add(ben);
		answer.Vertex.add(kramer);
		temp = answer.Vertex.get(0);
		temp.Edge.add(ross);
		answer.Vertex.set(0, temp);
		temp = answer.Vertex.get(1);
		temp.Edge.add(rachel);
		answer.Vertex.set(1, temp);
		temp = answer.Vertex.get(1);
		temp.Edge.add(ben);
		answer.Vertex.set(1, temp);
		temp = answer.Vertex.get(2);
		temp.Edge.add(ross);
		answer.Vertex.set(2, temp);
		assertEquals(answer.Vertex, graph.Vertex );
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
