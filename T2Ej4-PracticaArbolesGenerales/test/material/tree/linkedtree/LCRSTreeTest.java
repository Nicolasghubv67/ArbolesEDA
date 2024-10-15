/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package material.tree.linkedtree;

import junit.framework.TestCase;
import material.tree.narytree.LCRSTree;
import material.Position;

/**
 *
 * @author A. Duarte, F. Vélez
 */
public class LCRSTreeTest extends TestCase {

	private LCRSTree<Integer> tree = new LCRSTree<>();

	public void setTree() {

		Position<Integer> p = tree.addRoot(1);
		tree.add(2, p);
		Position<Integer> p1 = tree.add(3, p);
		tree.add(4, p);

		tree.add(5, p1);
		Position<Integer> p2 = tree.add(6, p1);

		tree.add(7, p2);
		Position<Integer> p3 = tree.add(8, p2);

		tree.add(9, p3);
		tree.add(10, p3);
		tree.add(11, p3);
		tree.add(12, p3);
	}


	public void testRoot() {
		this.setTree();
		Integer a = this.tree.root().getElement();
		boolean b = (a == 1);
		assertEquals(b, true);

	}

	public void testIsEmpty() {
		assertEquals(this.tree.isEmpty(), true);
	}

	public void testIsEmpty2() {
		Position<Integer> p = this.tree.addRoot(2);
		this.tree.add(3, p);
		assertEquals(this.tree.isEmpty(), false);
	}

//	public void testParent() {
//		this.setTree();
//
//		try {
//			Position<Integer> p = this.tree.root();
//			this.tree.parent(p);
//		} catch (BoundaryViolationException e) {
//			assertTrue(true);
//		}
//	}

	public void testParent2() {
		Position<Integer> p = tree.addRoot(1);
		tree.add(2, p);
		Position<Integer> p1 = tree.add(3, p);
		tree.add(4, p);
		tree.add(5, p1);
		Position<Integer> p2 = tree.add(6, p1);
		tree.add(7, p2);
		Position<Integer> p3 = tree.add(8, p2);
		tree.add(9, p3);
		tree.add(10, p3);
		tree.add(11, p3);
		tree.add(12, p3);
		assertEquals(p2, tree.parent(p3));
	}

	public void testParent3() {
		this.setTree();

		try {
			this.tree.parent(null);
		} catch (RuntimeException e) {
			assertTrue(true);
		}
	}

	public void testPositions() {
		Position<Integer> p = this.tree.addRoot(100);
		this.tree.add(200, p);
		this.tree.add(300, p);
		String salida = "";
		for (Position<Integer> e : this.tree) {
			salida += e.getElement();
		}
		assertEquals(salida, "100200300");
	}

	public void testRemove() {
		Position<Integer> p = this.tree.addRoot(100);
		Position<Integer> q = this.tree.add(200, p);
		Position<Integer> h = this.tree.add(300, p);
		this.tree.add(400, h);
		this.tree.add(500, h);
		this.tree.remove(h);
	}

	public void testRemove2() {
		this.setTree();
		this.tree.remove(this.tree.root());
	}

	public void testRemove3() {
		Position<Integer> p = tree.addRoot(1);
		tree.add(2, p);
		Position<Integer> p1 = tree.add(3, p);
		tree.add(4, p);
		tree.add(5, p1);
		Position<Integer> p2 = tree.add(6, p1);
		tree.add(7, p2);
		Position<Integer> p3 = tree.add(8, p2);
		tree.add(9, p3);
		tree.add(10, p3);
		tree.add(11, p3);
		tree.add(12, p3);

		this.tree.remove(p2);

		StringBuilder s = new StringBuilder();
		for (Position<Integer> pos : this.tree) {
			s.append(pos.getElement());
		}
		assertEquals(s.toString(), "12345");
	}

	public void testGetUnmodifiableChildren() {
		Position<Integer> p = this.tree.addRoot(100);
		this.tree.add(200, p);
		this.tree.add(300, p);
		Iterable<? extends Position<Integer>> l = this.tree.children(p);
		try {
			l.iterator().remove();
			fail("The children collection has been modified");
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	public void testGetChildren() {
		Position<Integer> p = this.tree.addRoot(100);
		this.tree.add(200, p);
		this.tree.add(300, p);

		String salida = "";
		for (Position<Integer> e : this.tree.children(p)) {
			salida += e.getElement();
		}
		assertEquals(salida, "200300");
	}

	public void testGetChildren2() {
		Position<Integer> p = tree.addRoot(1);
		tree.add(2, p);
		Position<Integer> p1 = tree.add(3, p);
		tree.add(4, p);
		tree.add(5, p1);
		Position<Integer> p2 = tree.add(6, p1);
		tree.add(7, p2);
		Position<Integer> p3 = tree.add(8, p2);
		tree.add(9, p3);
		tree.add(10, p3);
		tree.add(11, p3);
		tree.add(12, p3);

		String salida = "";
		for (Position<Integer> e : this.tree.children(p3)) {
			salida += e.getElement();
		}
		assertEquals(salida, "9101112");
	}

	public void testIterator() {
		this.setTree();

		StringBuilder s = new StringBuilder();
		for (Position<Integer> pos : this.tree) {
			s.append(pos.getElement());
		}
		assertEquals(s.toString(), "123456789101112");
	}

	public void testIsRoot() {
		this.setTree();
		Integer a = this.tree.root().getElement();
		boolean b = (a == 1);
		assertEquals(b, true);

	}

	public void testIsRoot2() {
		try {
			this.tree.isRoot(null);
		} catch (RuntimeException e) {
			assertTrue(true);
		}
	}
	
	public void testSwapElements() {
		Position<Integer> p = tree.addRoot(1);
		tree.add(2, p);
		Position<Integer> p1 = tree.add(3, p);
		tree.add(4, p);
		tree.add(5, p1);
		Position<Integer> p2 = tree.add(6, p1);
		tree.add(7, p2);
		Position<Integer> p3 = tree.add(8, p2);
		tree.add(9, p3);
		tree.add(10, p3);
		tree.add(11, p3);
		tree.add(12, p3);


		this.tree.swapElements(p, p1);
		this.tree.swapElements(p2, p3);
		
		String salida = "";
		for (Position<Integer> e : this.tree) {
			salida += e.getElement();
		}
		assertEquals(salida, "321458769101112");
	}

	
	public void testReplace() {
		Position<Integer> p = tree.addRoot(1);
		tree.add(2, p);
		Position<Integer> p1 = tree.add(3, p);
		tree.add(4, p);
		tree.add(5, p1);
		Position<Integer> p2 = tree.add(6, p1);
		tree.add(7, p2);
		Position<Integer> p3 = tree.add(8, p2);
		tree.add(9, p3);
		tree.add(10, p3);
		tree.add(11, p3);
		tree.add(12, p3);


		this.tree.replace(p, -1);
		this.tree.replace(p1, -2);
		this.tree.replace(p2, -3);
		this.tree.replace(p3, -4);
		
		String salida = "";
		for (Position<Integer> e : this.tree) {
			salida += e.getElement();
		}
		assertEquals(salida, "-12-245-37-49101112");
	}
}