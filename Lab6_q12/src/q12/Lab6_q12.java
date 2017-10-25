package q12;

import java.util.Scanner;

public class Lab6_q12 {
	public static int[] opcodeStringToArray(String opcodeBinary){
		int array[] = new int[6];
		for(int i = 0; i < opcodeBinary.length(); i++){
			array[i] = Integer.parseInt(opcodeBinary.substring(i, i + 1));
		}
		return array;
	}
	
	public static int switchBit(int bit){
		if(bit == 1) bit = 0;
		else bit = 1;

		return bit;
	}
	
	public static boolean bitJudge(int [] opcode){
		for(int i = 1; i < opcode.length; i++){
			if(opcode[i - 1] != opcode[i]) return false;
		}			
		return true;
	}
	
	public static boolean andGate1(int [] opcode){
		for(int i : opcode)
			opcode[i] = switchBit(opcode[i]);		
		return bitJudge(opcode);
	}
	
	public static boolean andGate2(int [] opcode){
		opcode[2] = switchBit(opcode[2]);
		opcode[3] = switchBit(opcode[3]);
		opcode[4] = switchBit(opcode[4]);
		return bitJudge(opcode);
	}
	
	public static boolean andGate3(int [] opcode){
		for(int i : opcode)
			opcode[i] = switchBit(opcode[i]);		
		return bitJudge(opcode);
	}
	
	public static boolean andGate4(int [] opcode){
		for(int i : opcode)
			opcode[i] = switchBit(opcode[i]);		
		return bitJudge(opcode);
	}
	
	
	public static void main(String [] args){
		Scanner reader = new Scanner(System.in);
		System.out.print("Enter in a opcode command (decimal): ");
		int opcode = reader.nextInt();
		String opcodeBinary = Integer.toBinaryString(opcode);
		int [] opcodeArray = opcodeStringToArray(opcodeBinary);
		
		String result = "";
		//RegDst
		if(andGate1(opcodeArray)) result += "1 ";
		else result += "0 ";
		
		//MemtoReg
		if(andGate2(opcodeArray)) result += "1 ";
		else result += "0 ";
		
		System.out.println(result);
	}
}










