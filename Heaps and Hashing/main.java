/************************************
 * Collaborators: Sami Junior Kahil *
 ************************************/

import java.io.*;
import java.util.*;


public class main {     

     
    public static void main(String[] args) {
    //TODO:build the hash table and insert keys using the insertKeyArray function.
    	int[] arrX = {70, 54, 19, 58, 46, 14, 67, 80, 3, 93, 47, 50, 74, 72, 85, 95, 86, 91, 81, 90};
    	int[] arrY = {79, 13, 45, 64, 32, 95, 67, 27, 78, 18, 41, 69, 15, 29, 72, 57, 81, 50, 60, 14};

    	
    	Chaining chainX = new Chaining(10, 0, 1023);
    	System.out.println(chainX.insertKeyArray(arrX)); 		//number of collisions 17

    	System.out.println("--------------------------------------------------------");
    	
    	Chaining chainY = new Chaining (10, 0, 590);
    	System.out.println(chainY.insertKeyArray(arrY)); 		//number of collisions 2
    	
    	System.out.println("--------------------------------------------------------");
    	
    	Open_Addressing openX = new Open_Addressing(10, 0, 1023);
    	System.out.println(openX.insertKeyArray(arrX));			//number of collisions 179
    	
    	System.out.println("--------------------------------------------------------");
    	
    	Open_Addressing openY = new Open_Addressing(10, 0, 590);
    	System.out.println(openY.insertKeyArray(arrY));			//number of collisions 24
    	
    	
    }
}