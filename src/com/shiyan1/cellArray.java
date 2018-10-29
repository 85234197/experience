package com.shiyan1;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.shiyan1.LifeGameService;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.shiyan1.cellState;




/**
 * ϸ������
 */

public class cellArray extends JPanel implements Runnable{       
	private int row;
	private int col;
	private int[][] cells;
	private final int width = 25;    
	private final int height = 25;  
	private final int DELAY_TIME = 2000; 
	private volatile boolean isChanging = false; 
	
	public cellArray(int row,int col) {    //���캯��
		this.row=row;
		this.col=col;
		this.cells=new int[row][col];
		
		
	}
	
	public void setRow(int row) {   //������
		this.row=row;
	}
	
	public int getRow() {          //�õ���
		return row;
	}
	
	public void setCol(int col) {  //������
		this.col=col;
	}
	
	public int getCol() {         //�õ���
		return col;
	}
	
	public void setCells(int[][] cells) {    //����ϸ������
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++)
				this.cells[i][j]=cells[i][j];
		}
	}
	
	public int[][] getCells(){        //�õ�ϸ������
		return cells;
	}
	
	public boolean setCell(int x,int y,int cell) {
		//����ĳ��ϸ����״̬����ֵ��ʾ����
		if(x<0||this.row<=x||y<0||this.col<=y) {
			//���Ҫ���õ�ϸ�����ڱ����ڣ��л���Խ�磩
			return false;
		}
		this.cells[x][y]=cell;
		return true;
	}
	
	public int getCell(int x,int y) {
		if(x<0||this.row<=x||y<0||this.col<=y) {
			return -1;    //���ڱ����ڵ�����ֵͨͨ����Ϊ-1
		}
		return this.cells[x][y];
	}
	
	public boolean equals(Object obj) {  /*�ж���һ����ͼ�͵�ǰ���ǲ���
		*��ȫһ���������ȫһ������Ҫ���Ķ���
		*/
		if(this==obj) return true;    //ֱ�ӱȽ϶���
		if(obj==null) return false;
		if(this.getClass()!=obj.getClass()) return false;    //�Ƚ��������Ƿ���ͬ
	    cellArray other=(cellArray)obj;
	    if(this.row!=other.getRow()||this.col!=other.getCol())
	    	//�Ƚ�����������������Ƿ���ͬ
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
	
	
	 protected void paintComponent(Graphics g) {     //һ��С��һ��С��
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
