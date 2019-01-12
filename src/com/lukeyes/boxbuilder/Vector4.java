package com.lukeyes.boxbuilder;

public class Vector4 {

	public static Vector4 of(
			int s,
			int x,
			int y,
			int z) {
		
		Vector4 vec = new Vector4();
		vec.s = s;
		vec.x = x;
		vec.y = y;
		vec.z = z;
		
		return vec;
	}
	
	public Vector4() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public int s;	
	public int x;
	public int y;
	public int z;

}
