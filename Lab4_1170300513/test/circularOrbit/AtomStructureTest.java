package circularOrbit;

import static org.junit.Assert.*;

import org.junit.Test;

import Exceptions.ReadFileFailException;
import Exceptions.TheSameTagRException;
import centralObject.CentralObject;
import physicalObject.PhysicalObject;
import track.Track;

public class AtomStructureTest {

	
	Track t = Track.Roundcreator(1);
	
	// Testing strategy:
	// 用人工确认的结果与执行方法的结果进行对比，判断方法的正确性。
	
	PhysicalObject p1 = PhysicalObject.Q3creator();
 	@Test
	public void testAddorbit() throws TheSameTagRException{
 		CircularOrbit< CentralObject, PhysicalObject> cir = CircularOrbit.empty("Q3");
 		cir.addorbit(t);
 		assertEquals(t, cir.getorbits().get(0).gettrack());
	}

	@Test
	public void testRemoveorbit() throws TheSameTagRException{
		CircularOrbit< CentralObject, PhysicalObject> cir = CircularOrbit.empty("Q3");
 		cir.addorbit(t);
 		cir.removeorbit(t);
 		assertEquals(0, cir.getorbits().size());
	}

	@Test
	public void testDeleteorbitobj() {
		CircularOrbit< CentralObject, PhysicalObject> cir = CircularOrbit.empty("Q3");
		try {
			cir.readfile("test AtomicStructure.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
 		cir.deleteorbitobj(cir.getorbits().get(0).getthingsintrack().get(0));
 		assertEquals(1, cir.getorbits().get(0).getthingsintrack().size());
	}

	@Test
	public void testDeletecentralobj() {
		CircularOrbit< CentralObject, PhysicalObject> cir = CircularOrbit.empty("Q3");
		try {
			cir.readfile("test AtomicStructure.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAddcenterobject() {
		CircularOrbit< CentralObject, PhysicalObject> cir = CircularOrbit.empty("Q3");
		CentralObject centralObject = CentralObject.Q3creator("Na");
		cir.addcenterobject(centralObject);
		assertEquals(centralObject, cir.getcenter());
	}

	@Test
	public void testAddtoorbit() throws TheSameTagRException{
		CircularOrbit< CentralObject, PhysicalObject> cir = CircularOrbit.empty("Q3");
 		cir.addorbit(t);
 		cir.addtoorbit(p1, t);
 		assertEquals(p1, cir.getorbits().get(0).getthingsintrack().get(0));
	}

	

	@Test
	public void testTransit() {
		CircularOrbit< CentralObject, PhysicalObject> cir = CircularOrbit.empty("Q3");
		try {
			cir.readfile("test AtomicStructure.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
		cir.transit(cir.getorbits().get(2).getthingsintrack().get(0), cir.getorbits().get(0).gettrack());
		assertEquals(3, cir.getorbits().get(0).getthingsintrack().size());
	}


	@Test
	public void testGetcenter() {
		CircularOrbit< CentralObject, PhysicalObject> cir = CircularOrbit.empty("Q3");
		CentralObject centralObject = CentralObject.Q3creator("Na");
		cir.addcenterobject(centralObject);
		assertEquals(centralObject, cir.getcenter());
	}

	@Test
	public void testGetlogicdistance() {
		CircularOrbit< CentralObject, PhysicalObject> cir = CircularOrbit.empty("Q3");
		try {
			cir.readfile("test AtomicStructure.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
		PhysicalObject e1 = cir.getorbits().get(0).getthingsintrack().get(0);
		PhysicalObject e2 = cir.getorbits().get(1).getthingsintrack().get(0);
		assertEquals(-1,cir.getlogicdistance(e1, e2));
	}

	@Test
	public void testGetphysicaldistance() {
		CircularOrbit< CentralObject, PhysicalObject> cir = CircularOrbit.empty("Q3");
		try {
			cir.readfile("test AtomicStructure.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
		PhysicalObject e1 = cir.getorbits().get(0).getthingsintrack().get(0);
		PhysicalObject e2 = cir.getorbits().get(1).getthingsintrack().get(0);
		assertEquals(-1, (int)(cir.getphysicaldistance(e1, e2)));
	}

	

	@Test
	public void testGetorbits() throws TheSameTagRException{
		CircularOrbit< CentralObject, PhysicalObject> cir = CircularOrbit.empty("Q3");
 		cir.addorbit(t);
 		assertEquals(1, cir.getorbits().size());
	}

	
	@Test
	public void testWrongtxt()
	{
		CircularOrbit<CentralObject, PhysicalObject> cir = CircularOrbit.empty("Q3");
		try {
			cir.readfile("test AtomicStructureWrong.txt");
		} catch (ReadFileFailException e) {
			ReadFileFailException exp = new ReadFileFailException();
			assertEquals(exp.getClass(), e.getClass());
		}catch (Exception e) {
			System.out.println("The path is not valid, please re-enter it.");
		}
		cir = CircularOrbit.empty("Q3");
		try {
			cir.readfile("test AtomicStructureWrong2.txt");
		} catch (ReadFileFailException e) {
			ReadFileFailException exp = new ReadFileFailException();
			assertEquals(exp.getClass(), e.getClass());
		}catch (Exception e) {
			System.out.println("The path is not valid, please re-enter it.");
		}
		cir = CircularOrbit.empty("Q3");
		cir.islegal();
		try {
			cir.readfile("test AtomicStructureWrong3.txt");
		} catch (ReadFileFailException e) {
			ReadFileFailException exp = new ReadFileFailException();
			assertEquals(exp.getClass(), e.getClass());
		}catch (Exception e) {
			System.out.println("The path is not valid, please re-enter it.");
		}
	}
	
}
