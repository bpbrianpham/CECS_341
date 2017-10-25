package q11;

import java.util.Scanner;

public class Lab5_q11 {
	static final String [] hexDigits = {"0", "1", "2", "3", "4", "5", "6", "7", "8" , "9",
			"A", "B", "C", "D", "E", "F"};
	
	static final String [] binaryDigits = {"0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000", "1001", 
			"1010", "1011", "1100", "1101", "1110", "1111", };
	
	public static String hex2Binary(String hex){
		String binary = "";
		for (int j = 0; j < hex.length(); j++){
			for(int i = 0; i < hexDigits.length; i++){
				if(hex.substring(j, j + 1).equalsIgnoreCase(hexDigits[i])){
					binary += binaryDigits[i]; 
				}
			}
		}
		return binary;
	}
	
	public static String binary2Hex(String binary){
		String hex = "0x";
		for(int j = 0; j < binary.length(); j = j + 4){
			for(int i = 0; i < binaryDigits.length; i++){
				if(binary.substring(j, j + 4).equalsIgnoreCase(binaryDigits[i])){
					hex += hexDigits[i];
				}
			}
		}
		return hex;
	}
	
	public static int binary2Decimal(String binary){
		double decimal = 0;
	    for(int i = 0; i < binary.length(); i++){
	        if(binary.charAt(i)== '1'){
	        	decimal += Math.pow(2, binary.length()-1-i);
	        }

	    }
	    return (int) decimal;
	}
	
	public static String[] opCode(String binary){
		String[] type = {"", ""};
		String [] rTypeBitCommand = {"100000", "100001", "100100", "001101", "011010", 
				"011011", "001001", "001000", "010000", "010010", "010001", "010011", "011000", "011001", 
				"100111", "100101", "000000", "000100", "101010", "101011", "000011",
				"000111", "000010", "000110", "100010", "100011", "001100", "100110"};
		String [] rTypeCommand = {"add", "addu", "and", "break", "div", "divu", "jalr",
				"jr", "mfhi", "mflo", "mthi", "mtlo", "mult", "multu", 
				"nor", "or", "sll", "sllv", "slt", "sltu", "sra", 
				"srav", "srl", "srlv", "sub", "subu", "syscall", "xor"};
		
		String[] iTypeBitCommand = {"001000", "001001", "001100", "000100", "000001", "000111", 
				"000110", "000001", "000101", "100000", "100100", "100001", 
				"100101", "001111", "100011", "110001", "001101", "101000", 
				"001010", "001011", "101001", "101011", "111001", "001110"};
		String[] iTypeCommand = {"addi", "addiu", "andi", "beq", "bgez", "bgtz", 
				"blez", "bltz", "bne", "lb", "lbu", "lh", 
				"lhu", "lui", "lw", "lwc1", "ori", "sb", 
				"slti", "sltiu", "sh", "sw", "swc1", "xori"};
		
		for(int i = 0; i < rTypeBitCommand.length; i++){
			if(binary.substring(26, binary.length()).equals(rTypeBitCommand[i])){
				type[0] = "R";
				type[1] = rTypeCommand[i];
			}
		}
		for(int i = 0; i < iTypeBitCommand.length; i++){
			if(binary.substring(0, 6).equals(iTypeBitCommand[i])){
				type[0] = "I";
				type[1] = iTypeCommand[i];
			}
		}
		
		if(binary.substring(0, 5).equals("00001")){
			type[0] = "J";
			type[1] = "J";
		}
		return type;
	}
	
    public static void main(String args[]){
    	Scanner reader = new Scanner(System.in);
    	String hex = reader.nextLine();
    	String binary = hex2Binary(hex);    	
    	String[] opCodeType = opCode(binary);
    	String mipsLine = "";
    	
    	if(opCodeType[0].equalsIgnoreCase("R")){
    		String function = opCodeType[1];
    		String rs = binary.substring(6, 11);
    		String rt = binary.substring(11, 16);
    		String rd = binary.substring(16, 21);
    		String shamt = binary.substring(21, 26);
    		
    		if (function.equalsIgnoreCase("break") ||
    				function.equalsIgnoreCase("syscall"))
    			mipsLine = function;
    		else if(function.equalsIgnoreCase("div") ||
    				function.equalsIgnoreCase("divu") ||
    				function.equalsIgnoreCase("mult") ||
    				function.equalsIgnoreCase("multu"))
    			mipsLine = function + " $" + binary2Decimal(rs) + 
    				" $" + binary2Decimal(rt);
    		else if(function.equalsIgnoreCase("jalr"))
    			mipsLine = function + " $" + binary2Decimal(rd) +
    				" $" + binary2Decimal(rs);
    		else if(function.equalsIgnoreCase("jr") ||
    				function.equalsIgnoreCase("mthi") ||
    				function.equalsIgnoreCase("mtlo"))
    			mipsLine = function + " $" + binary2Decimal(rs);
    		else if(function.equalsIgnoreCase("mfhi") ||
    				function.equalsIgnoreCase("mflo"))
    			mipsLine = function + " $" + binary2Decimal(rd);
    		else if(function.equalsIgnoreCase("sll") ||
    				function.equalsIgnoreCase("sra") ||
    				function.equalsIgnoreCase("srl"))
    			mipsLine = function + " $" + binary2Decimal(rd) +
    				" $" + binary2Decimal(rt) +
    				" $" + binary2Decimal(shamt);
    		else if(function.equalsIgnoreCase("sllv") ||
    				function.equalsIgnoreCase("srav") ||
    				function.equalsIgnoreCase("srlv"))
    			mipsLine = function + " $" + binary2Decimal(rd) +
    				" $" + binary2Decimal(rt) +
    				" $" + binary2Decimal(rs);
    		else{
    			mipsLine = function + " $" + binary2Decimal(rd) +
        				" $" + binary2Decimal(rs) +
        				" $" + binary2Decimal(rt);
    		}
    		
    		
    	}else if(opCodeType[0].equalsIgnoreCase("I")){
    		String function = opCodeType[1];
    		String rs = binary.substring(6, 11);
    		String rt = binary.substring(11, 16);
    		String imdte = binary.substring(16, binary.length());
    		
    		if(function.equalsIgnoreCase("bgez") ||
    				function.equalsIgnoreCase("bgtz") ||
    				function.equalsIgnoreCase("blez") ||
    				function.equalsIgnoreCase("bltz"))
    			mipsLine = function + " $" + binary2Decimal(rs) +
    				" " + binary2Hex(imdte);
    		else if(function.equalsIgnoreCase("beq") ||
    				function.equalsIgnoreCase("bne"))
    			mipsLine = function + " $" + binary2Decimal(rs) +
    				" $" + binary2Decimal(rt) + " " +
    				binary2Hex(imdte);
    		else if(function.equalsIgnoreCase("addi") ||
    				function.equalsIgnoreCase("addiu") ||
    				function.equalsIgnoreCase("andi") ||
    				function.equalsIgnoreCase("ori") ||
    				function.equalsIgnoreCase("slti") ||
    				function.equalsIgnoreCase("sltiu") ||
    				function.equalsIgnoreCase("xori"))
    			mipsLine = function + " $" + binary2Decimal(rt) +
    				" $" + binary2Decimal(rs) + " " +
    				binary2Hex(imdte);
    		else if(function.equalsIgnoreCase("lui"))
    			mipsLine = function + " $" + binary2Decimal(rt) +
    				" " + binary2Hex(imdte);
    		else{
    			mipsLine = function + " $" + binary2Decimal(rt) +
    					" " + binary2Hex(imdte) + "($" + binary2Decimal(rs) + ")";
    		}
    		
    		
    	}else{
    		String function = "j";
    		String addressCode = binary.substring(6, binary.length());
    		String adjustedAddressCode = addressCode + "00";
    		mipsLine = function + " " + binary2Hex(adjustedAddressCode);
    	}
    	System.out.println(mipsLine);
    }
}








