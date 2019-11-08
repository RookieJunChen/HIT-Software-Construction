package circularOrbit;

import static org.junit.Assert.*;

import org.junit.Test;

import centralObject.CentralObject;
import physicalObject.PhysicalObject;

public class StellarSystemTest {

	// Testing strategy:
	// 用人工确认的结果与执行方法的结果进行对比，判断方法的正确性。

	
	@Test
	public void testRemoveorbit() {
		CircularOrbit< CentralObject, PhysicalObject> cir = CircularOrbit.empty("Q2");
		try {
			cir.readfile("test StellarSystem.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(8, cir.getorbits().size());
		cir.removeorbit(cir.getorbits().get(0).gettrack());
		assertEquals(7, cir.getorbits().size());
	}

	


	@Test
	public void testGetcenter() {
		CircularOrbit< CentralObject, PhysicalObject> cir = CircularOrbit.empty("Q2");
		try {
			cir.readfile("test StellarSystem.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
		String test = "Sun";
		assertEquals(test, cir.getcenter().getname());
	}


	@Test
	public void testGetphysicaldistance() {
		CircularOrbit< CentralObject, PhysicalObject> cir = CircularOrbit.empty("Q2");
		try {
			cir.readfile("test StellarSystem.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
		PhysicalObject p1 = cir.getorbits().get(0).getthingsintrack().get(0);
		PhysicalObject p2 = cir.getorbits().get(1).getthingsintrack().get(0);
		double answer = 1377773.2517283596;
		assertEquals(0, (int)(cir.getphysicaldistance(p1, p2) - answer));
	}

	

	
	@Test
	public void testGetvpositions() {
		CircularOrbit< CentralObject, PhysicalObject> cir = CircularOrbit.empty("Q2");
		try {
			cir.readfile("test StellarSystem.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
		cir.getvpositions(0, "Y");
		cir.getvpositions(100, "Y");
	}

	@Test
	public void testGetpositions()
	{
		CircularOrbit< CentralObject, PhysicalObject> cir = CircularOrbit.empty("Q2");
		try {
			cir.readfile("test StellarSystem.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
		PhysicalObject p = cir.getorbits().get(0).getthingsintrack().get(0);
		cir.getpositions(p);
		double answer1 = 149000.0;
		double answer2 = 0.0;
		assertEquals(0, (int)(cir.getpositions(p).getridus().doubleValue() - answer1));
		assertEquals(0, (int)(cir.getpositions(p).getangle().doubleValue() - answer2));
	}

	

}
