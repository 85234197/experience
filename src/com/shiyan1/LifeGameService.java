package com.shiyan1;

import java.awt.Graphics;
import java.util.Random;
import com.shiyan1.cellArray;
import com.shiyan1.cellState;

public class LifeGameService {
	private int[] direct= {-1,0,1};
	//表示细胞移动的方向
	
	public int countNeighbor(cellArray now,int x,int y) {    //数出周围存活细胞的个数
		int count=0;
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				if(cellState.LIVE.getValue()==now.getCell(x+this.direct[i],y+this.direct[j])) {
					++count;
				}
			}
		}	
		if(cellState.LIVE.getValue()==now.getCell(x, y)) {
		     --count;
	    }
 	    return count;
	 }
	
	 public int[][] generate(cellArray now) {
	        if (null == now) {
	            return null;
	        }
	        int liveCount;
	        cellArray next = new cellArray(now.getRow(), now.getCol());
	        for (int i = 0; i < next.getRow(); ++i) {
	            for (int j = 0; j < next.getCol(); ++j) {
	                liveCount = this.countNeighbor(now, i, j);
	                if (cellState.LIVE.getValue() == now.getCell(i, j) && (liveCount < 2 || liveCount > 3)) { //人口过少,人口过多
	                    next.setCell(i, j, cellState.DEAD.getValue());
	                } else if (cellState.LIVE.getValue() == now.getCell(i, j) && (2 <= liveCount && liveCount <= 3)) { //正常
	                    next.setCell(i, j, cellState.LIVE.getValue());
	                } else if (cellState.DEAD.getValue() == now.getCell(i, j) && (3 == liveCount)) { //繁殖
	                    next.setCell(i, j, cellState.LIVE.getValue());
	                }
	            }
	        }
	        return next.getCells();
	    }
	  
	  public cellArray randInit(cellArray cellularArray) {
	        if (null == cellularArray) return null;
	        Random r = new Random();
	        int value;
	        for (int i = 0; i < cellularArray.getRow(); ++i) {
	            for (int j = 0; j < cellularArray.getCol(); ++j) {
	                value = r.nextInt(2);
	                cellularArray.setCell(i, j, value);
	            }
	        }
	        return cellularArray;
	    }
	  

	  public cellArray emptyInit(cellArray cellularArray) {
	        if (null == cellularArray) return null;
	        for (int i = 0; i < cellularArray.getRow(); ++i) {
	            for (int j = 0; j < cellularArray.getCol(); ++j) {
	                cellularArray.setCell(i, j, cellState.DEAD.getValue());
	            }
	        }
	        return cellularArray;
	    }
	  
	 
	  
}
