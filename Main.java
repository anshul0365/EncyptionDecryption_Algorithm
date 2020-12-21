/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Encryption_Algorithm;
import java.util.*;

/**
 *
 * @author Anshul Gupta
 */
class EncryptDecrypt{
    private String encryptValue = "";
    private String caseChangeVal = "";
    private String reversedString = "";
    private String spaceAsterikReplace = "";
    private String evenASCIIString = "";
    private String finalEncryptedString = "";
    private StringBuilder sb = new StringBuilder();
    
    public String Ciphering(String value){
        this.encryptValue = value;
        char[] x = this.encryptValue.toCharArray();
        ArrayList<String> newStringList = new ArrayList<>();
//        Checking character for Lower and Upper Case
        for(char letter: x){
            int asciiVal = (int) letter;
            if(asciiVal>=65 && asciiVal<=90){
                String temp = Character.toString(letter);
                newStringList.add(temp.toLowerCase());
            }
            else if(asciiVal>=97 && asciiVal<=122){
                String temp = Character.toString(letter);
                newStringList.add(temp.toUpperCase());
            }
            else{
                String temp = Character.toString(letter);
                newStringList.add(temp);
            }
        }
//        Appending list element into a String variable
        newStringList.forEach(str -> {
            this.caseChangeVal = this.caseChangeVal.concat(str);
        });
//        Reversing the String
        this.sb.append(this.caseChangeVal);
        this.reversedString = this.sb.reverse().toString();
        
//        Replaced Space with Asterik
        this.spaceAsterikReplace = this.reversedString.replace(" ", "*");
        
//        Even Position Character to ASCII Value
        char[] temp = this.spaceAsterikReplace.toCharArray();
        for(int i=0; i<temp.length; i++){
            if(i%2==0){
                this.evenASCIIString = this.evenASCIIString.concat(Character.toString(temp[i]));
            }
            else{
                this.evenASCIIString = this.evenASCIIString.concat(Integer.toString((int) temp[i]));
            }
        }
        
//        Append an Integer 3
        this.finalEncryptedString = this.evenASCIIString.concat(Integer.toString(3));
        
        return this.encryptValue+"\n"+this.caseChangeVal+"\n"+this.reversedString+"\n"+this.spaceAsterikReplace+"\n"+this.evenASCIIString+"\n"+this.finalEncryptedString;
    }
    
    
    public String Deciphering(){
        String remInteger = this.finalEncryptedString.substring(0, this.finalEncryptedString.length()-1);
        char[] temp = remInteger.toCharArray();
        String asciiToCharString = "";
        String tempVal = "";
        String asterikToSpaceString = "";
        String reversedString = "";
        String oppositeCaseString = "";
//        Converting ASCII To Char
        for(char x: temp){
            int asciiVal = (int) x;
            if(asciiVal>=48 && asciiVal<=57){
                tempVal = tempVal.concat(Character.toString(x));
            }
            else{
                if(tempVal.length()>0){
                    char asciiToChar = (char)Integer.parseInt(tempVal);
                    asciiToCharString = asciiToCharString.concat(Character.toString(asciiToChar));
                    tempVal="";
                }
                asciiToCharString = asciiToCharString.concat(Character.toString(x));
            }
        }
        if(tempVal.length()>0){
            char asciiToChar = (char)Integer.parseInt(tempVal);
            asciiToCharString = asciiToCharString.concat(Character.toString(asciiToChar));
            tempVal="";
        }
        
//        Replacing * to Space
        asterikToSpaceString = asciiToCharString.replace("*", " ");
        
//        Reversing String
        this.sb.setLength(0); //Clearing StringBuilder Buffer
        this.sb.append(asterikToSpaceString);
        reversedString = sb.reverse().toString();
        
//        Converting LowerToUpperCase and UpperToLowerCase
        temp = reversedString.toCharArray();
        for(char x : temp){
            if(Character.isLowerCase(x)){
                oppositeCaseString = oppositeCaseString.concat(Character.toString(x).toUpperCase());
            }
            else if(Character.isUpperCase(x)){
                oppositeCaseString = oppositeCaseString.concat(Character.toString(x).toLowerCase());
            }
            else{
                oppositeCaseString = oppositeCaseString.concat(Character.toString(x));
            }
        }
        
        return remInteger+"\n"+asciiToCharString+"\n"+asterikToSpaceString+"\n"+reversedString+"\n"+oppositeCaseString;
    }
}

public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        EncryptDecrypt ob = new EncryptDecrypt();
        System.out.println("Encryption");
        System.out.println(ob.Ciphering("Welcome to hackerrank"));
        System.out.println("\nDecryption");
        System.out.println(ob.Deciphering());
    }
}
