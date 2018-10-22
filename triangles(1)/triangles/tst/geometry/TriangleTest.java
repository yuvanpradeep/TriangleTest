/**
 * "Dark Triangles" demonstrates black box testing for educational purposes.
 * Copyright (C) 2010-2013 Daniel Speicher and University of Bonn
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package geometry;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import geometry.basic.Point;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TriangleTest {

	/*
	 * The following methods make sure that the test is run for all Triangle
	 * classes we are interested in. You don't need to understand the mechanics, 
	 * just make sure that you never create a triangle directly, but use one of 
	 * the methods createTriangle(...).
	 */
	private Class<Triangle> classUnderTest;

	@SuppressWarnings("unchecked")
	public TriangleTest(Object classUnderTest) {
		this.classUnderTest = (Class<Triangle>) classUnderTest;
	}

	@Parameters
	public static Collection<Object[]> triangleClassesUnderTest() {
		Object[][] classes = { 
				{ Triangle00.class },
				{ Triangle01.class },
				{ Triangle02.class },
				{ Triangle03.class }, 
				{ Triangle04.class },
				{ Triangle05.class },
				{ Triangle06.class },
				{ Triangle07.class },
				{ Triangle08.class },
				{ Triangle09.class },
				{ Triangle10.class },
				{ Triangle11.class },	
				{ Triangle12.class },
				{ Triangle13.class },
				{ Triangle14.class },
				{ Triangle15.class } ,
				{ Triangle16.class }
		};
		return Arrays.asList(classes);
	}

	private Triangle createTriangle(Point a, Point b, Point c) throws Exception {
		Constructor<Triangle> constructor = classUnderTest.getConstructor(
				Point.class, Point.class, Point.class);
		return constructor.newInstance(a, b, c);
	}

	private Triangle createTriangle(double ax, double ay, double bx, double by,
			double cx, double cy) throws Exception {
		Point a = new Point(ax, ay);
		Point b = new Point(bx, by);
		Point c = new Point(cx, cy);
		return createTriangle(a, b, c);
	}

	/*
	 * Create some triangles for the test.
	 * 
	 * Remember to always use createTriangle(...) to create a triangle. Never
	 * call the constructor directly. This allows us to run the test for all the
	 * classes one after another.
	 */
	private Triangle triangle;
	private Triangle equalToTriangle;
	private Triangle veryLargeTriangle;
	private Triangle verySmallTriangle;
	private Triangle srceTriangle;
	private Triangle containTriangle;
	
	private Triangle Striangle;
	private Triangle SymmetricTriangle;
	
	private Triangle onetriangle;
	private Triangle two_outer;
	
	private Triangle sourceX;
	private Triangle in_but_only_onepoint_change;
	private Triangle partially_out;
	
	private Triangle completely_outer;
	private Triangle triangle1;
	
	private Triangle trianglecase3;
	private Triangle full_outer_triangleC3;

	@Before
	public void setUpBefore() throws Exception {
	
//		triangle = createTriangle(a, b, c);
//		equalToTriangle = createTriangle(a1,b1,c1);
		veryLargeTriangle = createTriangle(-10000.0, -10000.0, +10000.0,
				-10000.0, 0.0, +20000.0);
		verySmallTriangle = createTriangle(-0.00001, -0.00001, +0.00001,
				-0.00001, 0.0, +0.00002);
		
		
		// One coordinates completly lies outside case 5
		Point aa = new Point(3,2);
		Point bb = new Point(21,42);
		Point cc = new Point(21,6);
		Point aa1 = new Point(42,21);
		Point bb1 = new Point(15,24);
		Point cc1 = new Point(15,21);
		srceTriangle =createTriangle(aa,bb,cc);
		containTriangle =createTriangle(aa1,bb1,cc1);
		
		//check for symmetric case
		Point x = new Point(10, 20);
		Point y = new Point(25, 20);
		Point z = new Point(15, 10);
		
		Point x1 = new Point(-10, -20);
		Point y1 = new Point(-25, -20);
		Point z1 = new Point(-15, -10);
		
		Striangle = createTriangle(x,y,z);
		SymmetricTriangle = createTriangle(x1,y1,z1);
		
		// When one point is same in both triangle 
		
		Point k = new Point(8,25);
		Point l = new Point(0,0);
		Point m = new Point(10,4);
		
		Point k1 = new Point(0,0);
		Point l1 = new Point(5,30);
		Point m1 = new Point(35,0);
		
		onetriangle = createTriangle(k,l,m);
		two_outer = createTriangle(k1,l1,m1);
		
		// When one triangle is above or below in one point 
		//case 1
		Point q = new Point(35,-30);
		Point w = new Point(63,-30);
		Point r = new Point(48,-43);
		
		Point q1 = new Point(35,-30);
		Point w1 = new Point(47,-34);
		Point r1 = new Point(48,-43);
		
		Point q2 = new Point(35,-30);
		Point w2= new Point(48,-48);
		Point r2 = new Point(48,-43);
		
		sourceX = createTriangle(q,w,r);
		in_but_only_onepoint_change = createTriangle(q1,w1,r1);
		partially_out = createTriangle(q2,w2,r2);
		
		// case for getting zero to fail
	    Point a4 = new Point(-1,-1);
		Point b4 = new Point(1, 2);
		Point c4 = new Point(1,-0.3);
		completely_outer = createTriangle(a4, b4, c4);
        triangle1 = createTriangle(0,0, 0,1, 1,0);
        
        // case for getting six to fail case3
        Point t = new Point(10,30);
		Point h = new Point(50,30);
		Point u = new Point(30,10);
		
		Point t1 = new Point(-10,50);
		Point h1 = new Point(70,50);
		Point u1 = new Point(30,20);
		
		trianglecase3 = createTriangle(t,h,u);
		full_outer_triangleC3 = createTriangle(t1,h1,u1);
		
	}
		// case for getting six to fail case6
		@Test
		public void ifTargetTriangleTwopointsareOutside() throws Exception {
			assertFalse("... it should be inside the second one", trianglecase3.contains(full_outer_triangleC3));
		}
	 //case for getting zero to fail
		@Test
		public void ifTargetTriangleisCompletelyOutside() throws Exception{
			assertFalse("... completely outside", completely_outer.contains(triangle1));
		}
	//case 1
		@Test
		public void ifTargetTraingleisPartiallyinside() throws Exception {
		assertTrue("... ", sourceX.contains(in_but_only_onepoint_change));
		}
		
		@Test
		public void ifTraingleisPartiallyoutside() throws Exception {
		assertFalse("... partially outside", sourceX.contains(partially_out));	
		}
		
	// One coordinates completly lies outside case 5
	@Test
	public void onepointOfSmallTriangleOutsideBiggerTriangle() throws Exception {
		assertFalse("... source triangle will not contain containedTriangle if one cordinate of container Triangle lies outside.",
				srceTriangle.contains(containTriangle));
	}

//	@Test
//	public void ifATriangleIsComparedWithItself() throws Exception {
//		assertTrue("... it should contain itself.", triangle.contains(triangle));
//	}

//	@Test
//	public void ifTrianglesAreEqualToEachOther() throws Exception {
//		assertTrue("... they should contain each other.",
//				triangle.contains(equalToTriangle));
//		assertTrue("The triangles should actually be equal.",
//				triangle.equals(equalToTriangle));
//		assertFalse("But not the same.", triangle == equalToTriangle);
//	}
	@Test
	public void ifOneTriangleIsVeryLargeAndTheOtherVerySmall() throws Exception {
		assertTrue("... the large one should contain the small one.",
				veryLargeTriangle.contains(verySmallTriangle));
		assertFalse("... the small one should not contain the large one.",
				verySmallTriangle.contains(veryLargeTriangle));
	}

//	@Test
//	public void ifATriangleIsJustACornerOfAnotherTriangle() throws Exception {
//		Point x = new Point(Math.E, Math.E); // Some strange values for this
//											 // simple case.
//		Point y = new Point(Math.PI, Math.E);
//		Point z = new Point(Math.PI, Math.PI);
//		Triangle fullTriangle = createTriangle(x, y, z);
//		Triangle pointX = createTriangle(x, x, x);
//		Triangle pointY = createTriangle(y, y, y);
//		Triangle pointZ = createTriangle(z, z, z);
//		assertTrue("... it is part of the triangle",
//				fullTriangle.contains(pointX));
//		assertTrue("... it is part of the triangle",
//				fullTriangle.contains(pointY));
//		assertTrue("... it is part of the triangle",
//				fullTriangle.contains(pointZ));
//	}
//

	
}