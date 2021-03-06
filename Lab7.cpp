/*
Fall 2017
CECS 341
Lab7
Due: 12/4/2017 11:59PM

Write down your codes only between
//Your code from here

//Your code to here

1. (20 points) Complete the 'printMemory' function in C++.
The program should output the actual bit pattern of the memory spaces.
The output format specification;
matrix[0][1](0x61fef8) = -1 => 11111111 | 11111111 | 11111111 | 11111111
0x61fef8: Address of matrix[0][1]
-1: integer value of matrix[0][1]
11111111 | 11111111 | 11111111 | 11111111 : bit pattern of matrix[0][1]

2. (5 points) Is your system using 'big-endian' or 'little-endian'?
Little Endian
3. (Extra 5 points) Complete the 'printMemory1D' function which outputs the same result of printMemory.

An expected output example in x86 architecture.
------------------------------------------------------------------------
2D array content
0	-1	2
3	-4	5
6	-7	8
2D array information
matrix[0][0](0x61fef4) = 0 => 00000000 | 00000000 | 00000000 | 00000000
matrix[0][1](0x61fef8) = -1 => 11111111 | 11111111 | 11111111 | 11111111
matrix[0][2](0x61fefc) = 2 => 00000010 | 00000000 | 00000000 | 00000000
matrix[1][0](0x61ff00) = 3 => 00000011 | 00000000 | 00000000 | 00000000
matrix[1][1](0x61ff04) = -4 => 11111100 | 11111111 | 11111111 | 11111111
matrix[1][2](0x61ff08) = 5 => 00000101 | 00000000 | 00000000 | 00000000
matrix[2][0](0x61ff0c) = 6 => 00000110 | 00000000 | 00000000 | 00000000
matrix[2][1](0x61ff10) = -7 => 11111001 | 11111111 | 11111111 | 11111111
matrix[2][2](0x61ff14) = 8 => 00001000 | 00000000 | 00000000 | 00000000
------------------------------------------------------------------------
*/
#include <iostream>
#include <stdlib.h>
using namespace std;
#define rows 3
#define cols 3
#include <bitset>

void printMemory(int matrix[][cols]) {
	//Your code from here
	for (int i = 0; i < rows; i++) {
		for (int j = 0; j < cols; j++) {
			int* memAddress = &matrix[i][j];
			int num = matrix[i][j];
			char bits[32];
			bool negative = (num < 0);	//used for 2's Complement
			num = abs(num);	//change to positive for easier binary conversion from int
			
			for (int k = 0; k < 32; k++) bits[k] = '0';	//32 bit array
			if(negative) num -= 1;

			for(int s = 1; s <= 4; s++){
				for(int t = 8; t >= 1; t--){
					if(num % 2 == 1) 
						bits[(t*s) - 1] = '1';
					num /= 2;
				}
			}

			if(negative){
				for(int s = 0; s < 32; s++)
					bits[s] = (bits[s] == '0') ? '1':'0';
			}

			string bitRep(bits);

			bitRep = bitRep.substr(0, 32);
			for (int l = bitRep.length() - 8; l > 0; l = l - 8){
				bitRep = bitRep.insert(l, " | ");
			}
			cout << "matrix[" << i << "]" << "[" << j << "]" << "(0x" << memAddress << ") = " 
					<< matrix[i][j] << " =>   " << bitRep <<endl;
		}
	}
	//Your code to here
}

void printMemory1D(int matrix[]) {
	//Your code from here
	for (int j = 0; j < 9; j++) {
		int* memAddress = &matrix[j];
		int num = matrix[j];
		char bits[32];
		bool negative = (num < 0);	//used for 2's Complement
		num = abs(num);	//change to positive for easier binary conversion from int

		for (int k = 0; k < 32; k++) bits[k] = '0';	//32 bit array
		if(negative) num -= 1;

		for(int s = 1; s <= 4; s++){
			for(int t = 8; t >= 1; t--){
				if(num % 2 == 1) 
					bits[(t*s) - 1] = '1';
				num /= 2;
			}
		}

		if(negative){
			for(int s = 0; s < 32; s++)
				bits[s] = (bits[s] == '0') ? '1':'0';
		}

		string bitRep(bits);

		bitRep = bitRep.substr(0, 32);
		for (int l = bitRep.length() - 8; l > 0; l = l - 8){
				bitRep = bitRep.insert(l, " | ");
			}
		cout << "matrix[" << j << "]" << "(0x" << memAddress << ") = " 
				<< matrix[j] << " =>   " << bitRep << endl;
	}
	//Your code to here
}

int main() {
	int matrix[rows][cols];
	cout << "2D array content\n";
	for (int i = 0; i < rows; i++) {
		for (int j = 0; j < cols; j++) {
			matrix[i][j] = (i * cols + j) * (j % 2 == 0 ? 1 : -1);
			cout << matrix[i][j] << "\t";
		}
		cout << endl;
	}
	cout << "2D array information\n";
	printMemory(matrix);
	cout << "2D array information by 1D version\n";
	printMemory1D(matrix[0]);
	system("pause");
}