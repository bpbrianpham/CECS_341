package q12;

import java.util.Scanner;

public class Lab6_q12 {
	public static int[] opcodeStringToArray(String opcodeBinary){
		if (opcodeBinary.length() != 6){
			String zeroes = "";
			int moreZeroes = 6 - opcodeBinary.length();
			for(int i = 0; i < moreZeroes; i++)
				zeroes += "0";
			opcodeBinary = zeroes + opcodeBinary;
		}
		String array[] = opcodeBinary.split("");
		int array2[] = new int[6];
		for(int i = 0; i < array.length; i++)
			array2[i] = Integer.parseInt(array[i]);
		return array2;
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
		int [] array = new int[6];
		for(int i = 0; i < array.length; i++)
			array[i] = switchBit(opcode[i]);
		return bitJudge(array);
	}
	
	public static boolean andGate2(int [] opcode){
		int [] array = new int[6];
		for(int i = 0; i < array.length; i++)
			array[i] = opcode[i];
		array[1] = switchBit(array[1]);
		array[2] = switchBit(array[2]);
		array[3] = switchBit(array[3]);
		return bitJudge(array);
	}
	
	public static boolean andGate3(int [] opcode){
		int [] array = new int[6];
		for(int i = 0; i < array.length; i++)
			array[i] = opcode[i];
		array[1] = switchBit(array[1]);
		array[3] = switchBit(array[3]);
		return bitJudge(array);
	}
	
	public static boolean andGate4(int [] opcode){
		int [] array = new int[6];
		for(int i = 0; i < array.length; i++)
			array[i] = opcode[i];
		for(int i = 0; i < array.length; i++)
			if(i != 3)
				array[i] = switchBit(array[i]);
		return bitJudge(array);
	}
	
	
	public static void main(String [] args){
		Scanner reader = new Scanner(System.in);
		System.out.print("Enter in a opcode command (decimal): ");
		int opcode = reader.nextInt();
		String opcodeBinary = Integer.toBinaryString(opcode);
		int opcodeArray [] = opcodeStringToArray(opcodeBinary);
		
		String result = "";
		
		//RegDst
		if(andGate1(opcodeArray)) result += "1 ";
		else result += "0 ";
		
		//AlUSrc - error
		if(andGate2(opcodeArray) || andGate3(opcodeArray)) result += "1 ";
		else result += "0 ";
		
		//MemtoReg - error
		if(andGate2(opcodeArray)) result += "1 ";
		else result += "0 ";
		
		//RegWrite - error
		if(andGate1(opcodeArray) || andGate2(opcodeArray)) result += "1 ";
		else result += "0 ";
		
		//MemRead - error
		if(andGate2(opcodeArray)) result += "1 ";
		else result += "0 ";
		
		//MemWrite - error
		if(andGate3(opcodeArray)) result += "1 ";
		else result += "0 ";
		
		//Branch - error
		if(andGate4(opcodeArray)) result += "1 ";
		else result += "0 ";
		
		//ALUOp1
		if(andGate1(opcodeArray)) result += "1 ";
		else result += "0 ";
		
		//ALUOp2 - error
		if(andGate4(opcodeArray)) result += "1 ";
		else result += "0 ";
		
		System.out.println(result);
		
		
	}
}










