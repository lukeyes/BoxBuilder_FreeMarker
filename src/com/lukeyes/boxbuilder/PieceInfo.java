package com.lukeyes.boxbuilder;

public class PieceInfo {

	
	public static PieceInfo of(
			Vector4 x,
			Vector4 y,
			float dX,
			float dY,
			int a,
			int b,
			int c,
			int d,
			boolean slots) {
		
		PieceInfo peice = new PieceInfo();
		peice.x = x;
		peice.y = y;
		peice.dX = dX;
		peice.dY = dY;
		peice.a = a;
		peice.b = b;
		peice.c = c;
		peice.d = d;
		peice.slots = slots;
		
		return peice;
	}

	private PieceInfo() {
		// TODO Auto-generated constructor stub
	}
	
	public Vector4 x;
	public Vector4 y;
	public float dX;
	public float dY;
	public int a;
	public int b;
	public int c;
	public int d;
	public boolean slots;
	

}
