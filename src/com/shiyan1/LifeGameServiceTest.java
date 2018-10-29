package com.shiyan1;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import com.shiyan1.cellArray;
import com.shiyan1.LifeGameService;


public class LifeGameServiceTest {
	static LifeGameService service=new LifeGameService();
	static cellArray array=new cellArray(10,10);
    private static int[][] result;
	public static final int[][] Map = {
			{0,0,0,0,0,0,0,0,1,1},
			{0,0,1,1,0,0,1,1,1,1},
			{0,0,1,1,0,0,0,0,1,1},
			{0,0,0,0,0,0,0,0,0,0},
			{1,1,1,1,1,1,1,1,1,1},
			{1,0,1,0,1,0,1,0,1,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{1,0,1,0,1,0,1,0,1,0},
			{0,1,0,1,0,1,0,1,0,1},	};
	public static final int[][] MapNext = {
			{0,0,0,0,0,0,0,0,0,1},
			{0,0,1,1,0,0,0,0,0,0},
			{0,0,1,1,0,0,0,0,0,1},
			{0,0,0,0,0,1,1,0,0,0},
			{1,0,1,0,1,0,1,0,1,1},
			{1,0,1,0,1,0,1,0,1,1},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,1,1,1,1,1,1,1,1,0},
			{0,1,1,1,1,1,1,1,1,0},	};
	public static final int[][] MapEmpty = {
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},	};
	@Before
	public void setUp() throws Exception {
		array.setCells(Map);
	}

    @Test
	public void testcountNeighbor(){
    	int num = service.countNeighbor(array, 1, 8);
    	System.out.println(num);
    	assertEquals(6, num);
	}
	
	
	@Test
	public void testGenerate() {
		assertEquals(MapNext,service.generate(array));
	}

	@Ignore
	public void testRandInit() {
		fail("Not yet implemented");
	}

	@Test
	public void testEmptyInit() {
		assertEquals(MapEmpty,service.emptyInit(array).getCells());
	}

}
