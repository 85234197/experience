package com.shiyan1;

//Ï¸°û×´Ì¬
public enum cellState{
	DEAD(0),
	LIVE(1);
		private int value;
		
		cellState(int value){
			this.value=value;
		}
		
		public int getValue() {
			return value;
		}
		
	}

