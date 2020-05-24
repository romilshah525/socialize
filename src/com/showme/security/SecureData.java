package com.showme.security;

import org.springframework.stereotype.Component;

@Component
public class SecureData {

    private String encryptString(String str) { 
        int l = str.length(); 
        int k = 0, row, column; 
        
        double sq ;
        
        do{
        	sq = Math.sqrt(l);
        		
        	if((sq-Math.floor(sq)!=0))
        		l++;
        }while((sq-Math.floor(sq)!=0));
        
        row = (int)sq;
        column = (int)sq; 
  
        char s[][] = new char[row][column]; 
       
        for (int i = 0; i < row; i++)  
        { 
            for (int j = 0; j < column; j++)  
            { 
                if(k < str.length()) 
                    s[i][j] = str.charAt(k); 
                k++; 
            } 
        } 
  
        String encrptedString = "";
        for (int i = 0; i < column; i++)  
            for (int j = 0; j < column; j++) 
                encrptedString+= s[j][i];
        
        return encrptedString;
    } 
    
    private String decryptString(String str) 
    { 
    	 int l = str.length(); 
         int k = 0, row, column; 
         
         double sq ;
         
         do{
         	sq = Math.sqrt(l);
         	if((sq-Math.floor(sq)!=0))
         		l++;
         }while((sq-Math.floor(sq)!=0));
         
         row = (int)sq;
         column = (int)sq; 
  
        char s[][] = new char[row][column]; 
          
        for (int i = 0; i < row; i++)  
        { 
            for (int j = 0; j < column; j++)  
            { 
                if(k < str.length()) 
                    s[i][j] = str.charAt(k); 
                k++; 
            } 
        } 
  
        String decrptedString = "";
        for (int i = 0; i < row; i++)  
            for (int j = 0; j < column; j++) 
            	decrptedString+=s[j][i];
          
        return decrptedString;
    } 
    
    public String encryptTheMessage(String msg) {
		return encryptString(msg);
	}
	
	public String decryptTheMessage(String msg) {
		return decryptString(msg);
	}
}
