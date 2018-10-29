package com.shiyan1;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class cellArrayTest {
    private static cellArray cellarray = new cellArray(10, 10);
    
    public static final int[][]cells = {
    		{0,0,0,0,0,0,0,0,1,1},
			{0,0,1,1,0,0,1,1,1,1},
			{0,0,1,1,0,0,0,0,1,1},
			{0,0,0,0,0,0,0,0,0,0},
			{1,1,1,1,1,1,1,1,1,1},
			{1,0,1,0,1,0,1,0,1,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{1,0,1,0,1,0,1,0,1,0},
			{0,1,0,1,0,1,0,1,0,1},
    };
    
    public static final int[][]cells_next = {
    		{0,0,0,0,0,0,0,0,0,1},
			{0,0,1,1,0,0,0,0,0,0},
			{0,0,1,1,0,0,0,0,0,1},
			{0,0,0,0,0,1,1,0,0,0},
			{1,0,1,0,1,0,1,0,1,1},
			{1,0,1,0,1,0,1,0,1,1},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,1,1,1,1,1,1,1,1,0},
			{0,1,1,1,1,1,1,1,1,0},
    };
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetRow() {
		cellarray.setRow(20);
		assertEquals(20, cellarray.getRow());
	}

	@Test
	public void testGetCol() {
		cellarray.setCol(20);
		assertEquals(20, cellarray.getCol());
	}

	@Test
	public void testGetCells() {
		cellarray.setCells(cells);
		assertEquals(cells, cellarray.getCells());
	}

	@Test
	public void testGetCell() {
		cellarray.setCell(8, 2, 1);
		assertEquals(1,cellarray.getCell(8, 2));
	}

	@Test
	public void testEqualsObject() {
		cellarray.setCells(cells);
		assertEquals(false, cellarray.equals(cells_next));
	}

}
