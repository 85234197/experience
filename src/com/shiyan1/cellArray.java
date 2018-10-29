package com.shiyan1;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.shiyan1.LifeGameService;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.shiyan1.cellState;




/**
 * 细胞阵列
 */

public class cellArray extends JPanel implements Runnable{       
	private int row;
	private int col;
	private int[][] cells;
	private final int width = 25;    
	private final int height = 25;  
	private final int DELAY_TIME = 2000; 
	private volatile boolean isChanging = false; 
	
	public cellArray(int row,int col) {    //构造函数
		this.row=row;
		this.col=col;
		this.cells=new int[row][col];
		
		
	}
	
	public void setRow(int row) {   //设置行
		this.row=row;
	}
	
	public int getRow() {          //得到行
		return row;
	}
	
	public void setCol(int col) {  //设置列
		this.col=col;
	}
	
	public int getCol() {         //得到列
		return col;
	}
	
	public void setCells(int[][] cells) {    //设置细胞数组
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++)
				this.cells[i][j]=cells[i][j];
		}
	}
	
	public int[][] getCells(){        //得到细胞数组
		return cells;
	}
	
	public boolean setCell(int x,int y,int cell) {
		//设置某个细胞的状态，数值表示生死
		if(x<0||this.row<=x||y<0||this.col<=y) {
			//如果要设置的细胞不在背景内（行或列越界）
			return false;
		}
		this.cells[x][y]=cell;
		return true;
	}
	
	public int getCell(int x,int y) {
		if(x<0||this.row<=x||y<0||this.col<=y) {
			return -1;    //不在背景内的区域值通通设置为-1
		}
		return this.cells[x][y];
	}
	
	public boolean equals(Object obj) {  /*判断上一代的图和当前代是不是
		*完全一样，如果完全一样则不需要作改动了
		*/
		if(this==obj) return true;    //直接比较对象
		if(obj==null) return false;
		if(this.getClass()!=obj.getClass()) return false;    //比较两个类是否相同
	    cellArray other=(cellArray)obj;
	    if(this.row!=other.getRow()||this.col!=other.getCol())
	    	//比较两个对象的行列数是否相同
	    	return false;
	    for(int i=0;i<this.row;++i) {
	    	for(int j=0;j<this.col;++j) {
	    		if(this.cells[i][j]!=other.getCell(i, j)) {
	    			return false;
	    		}
	    	}
	    	
	    }
	    return true;
	}
	
	
	public void run()
	{
		LifeGameService service=new LifeGameService();
		int [][]cell;
		while(true)
		{
			synchronized(this)
			{
				while(isChanging)
				{
					try 
					{
						this.wait();
					} catch (InterruptedException e) 
					{
						e.printStackTrace();
					}
				}
				
				repaint();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				cell=service.generate(this);
				
				for(int i = 0; i < this.row; i++)
				{
					for(int j = 0; j < this.col; j++)
					{
						this.cells[i][j]=cell[i][j];
					}
				}
			}
		}
	}
	
	
	 protected void paintComponent(Graphics g) {     //一个小格一个小格画
	        super.paintComponent(g);
	      //  if(now.equals(next))
	       // 	return;
	        for (int i = 0; i < row; i++) {  
	            for (int j = 0; j < col; j++) {  
	                if (cells[i][j]==1) {  
	                    g.fillRect( j * width, i * height, width, height);                     
	                } else {  
	                	 g.drawRect( j * width, i * height, width, height); 
	                }  
	            }  
	        }  
	    }  
	 
	 public static final int[][] Map = {  
			 {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			 {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			 {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			 {0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0},
			 {0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0},
			 {0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0},
			 {0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0},
			 {0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0},
			 {0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
			 {0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
			 {0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
			 {0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
			 {0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
			 {0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
			 {0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
			 {0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
			 {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			 {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			 {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			 {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		};  
	 
	 
	 
	 
	 public void SetMap() {
		 isChanging=true;
		 synchronized(this) {
		 for(int i=0;i<this.row;i++) {
			 for(int j=0;j<this.col;j++)
				 this.cells[i][j]=Map[i][j];
		 }
		 
	    isChanging=false;
	    this.notifyAll();
		 }
	 }
	
	 
	 public void RanMap() {
		 LifeGameService service=new LifeGameService();
		
		 isChanging=true;
		 synchronized(this) {
		service.randInit(this);
	    isChanging=false;
	    this.notifyAll();
		 }
		 
	 }
	 
	 public void Init() {
		 for(int i=0;i<row;i++) {
				for(int j=0;j<col;j++)
					cells[i][j]=cellState.DEAD.getValue();
			}
	 }
	 
}
