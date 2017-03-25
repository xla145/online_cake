package site.xulian.cake.utils;

import java.util.ArrayList;
import java.util.List;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> list =new ArrayList<String>();
		list.add("123我们爱");
		list.add("我们的爱");
		list.add("123我们的爱");
		list.add("3345我们的爱");
		 System.out.println(list.size());
        for(int i = list.size()-1; i>=0; i--){
        	char [] name = list.get(i).toCharArray();
        	//System.out.println("name  "+name[0]);
        	if(Character.isDigit(name[0])){
        		//System.out.println("list  "+list.get(i) +"i  "+i);
        	    list.remove(i);
        	    //i--;
        	}
        }
        for(String l:list){
        	System.out.println(l);
        }
	}

}
