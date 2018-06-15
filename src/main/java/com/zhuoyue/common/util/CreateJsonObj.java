package com.zhuoyue.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.transaction.interceptor.TransactionInterceptor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CreateJsonObj {

	public static void main(String[] args) throws JsonProcessingException {
		
		
		
		//0,4.7,0
		
		//0,0,90
		
		//0.1,20,0.1
		
		
		List<Object> list = new ArrayList<Object>();
		
		Random random = new Random();
		for(int i =1;i<=2000;i++){
			TestObject object = new TestObject();
			object.setName("Cylinder_"+i);
			object.setStyle("Cylinder");
			object.setDir("0,0,90");
			//object.setPos(getRandomDoubleString()+","+getRandomDoubleString()+","+getRandomDoubleString());
			object.setPos("0,4.7,"+ (0-3*i));
			object.setScale("0.1,20,0.1");
			
			 
			list.add(object);
		}
		
		/*		  {
				"Style":"Cube",
		        "name": "Cube",
		        "pos": "0,2.43,20",
		        "dir": "0,0,0",
				"Scale":"40,5,0.1"
		      },*/
		
		
		for(int i =1;i<=2000;i++){
			TestObject object = new TestObject();
			object.setName("Cube_"+i);
			object.setStyle("Cube");
			object.setDir("0,0,0");
			object.setPos("0,2.43,"+(20-i*4));
			object.setScale("40,5,0.1");
			 
			list.add(object);
			 
		}
		ObjectMapper mapper = new ObjectMapper();  
		
		
		String jsonlist = mapper.writeValueAsString(list);  
        System.out.println(jsonlist); 
		
	}
	
	public static String getRandomDoubleString(){
		
		Random random = new Random();
	    Double dob = (double) (random.nextInt(1000)+random.nextFloat());
	    return String.format("%.1f", dob);
	}

}


class TestJosn{
	
	private Integer Num ; 
	 
 	 
}

class TestObject {
	
	private String style;
	
	private String name ; 
	
	private String pos ; 
	
	private String dir ; 
	
	private String scale ;
	
	 

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	 

 
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPos() {
		return pos;
	}

	public void setPos(String pos) {
		this.pos = pos;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}
 
	
	
}