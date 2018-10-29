package com.shiyan1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.shiyan1.cellArray;
import com.shiyan1.LifeGameService;

public class Dialogue extends JFrame{
	private final cellArray array;
	private final LifeGameService service=new LifeGameService();   //
	public Dialogue(int row,int col) {
		array=new cellArray(row,col);
		new Thread(array).start();
		add(array);
	}

public static void main(String[] args) {
	Dialogue dialogue=new Dialogue(20,43);
	
    JMenuBar menu = new JMenuBar();
    dialogue.setJMenuBar(menu);
    
    JMenu options = new JMenu("OPTION");
    menu.add(options);
    
    JMenuItem random = options.add("���ģʽ");
    random.addActionListener(dialogue.new RandomActionListener());
    
    
    JMenuItem fixed = options.add("�̶�ģʽ��������");
    fixed.addActionListener(dialogue.new FixedActionListener());
    
   
    JMenuItem square = options.add("�˳�");
    square.addActionListener(dialogue.new ExidActionListener());        
    
	
	
	dialogue.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	dialogue.setSize(1290, 700);
	dialogue.setTitle("Life Game");
	dialogue.setLocationRelativeTo(null);   //�����ڷ�������Ļ����
	dialogue.setVisible(true);
	dialogue.setResizable(false);   //ʹ���ڿɸı��С
	
	
	
}





class FixedActionListener implements ActionListener
{
	public void actionPerformed(ActionEvent e) 
	{
		array.SetMap();
	}
}

class RandomActionListener implements ActionListener
{
	public void actionPerformed(ActionEvent e) 
	{
		array.RanMap();
	}
}

class ExidActionListener implements ActionListener
{
	public void actionPerformed(ActionEvent e) 
	{
		dispose();
		System.exit(0);
	}
}
}

