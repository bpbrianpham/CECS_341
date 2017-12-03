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
			int indexOfFirstOne = 0;	//used for 2's Complement

			for (int k = 0; k < 32; k++) bits[k] = '0';	//32 bit array
			
			int ind = 0;
			while (num > 0) {
				if (num % 2 == 0) bits[ind] = '0';
				else {
					bits[ind] = '1';
					if (indexOfFirstOne == 0) indexOfFirstOne = ind; //only changes when first instance of '1'
				}

				ind++;

				num /= 2;
			}

			if (negative) {	//Only runs for negative numbers
				int negInd = indexOfFirstOne + 1; 
				while (negInd < 32) {
					bits[negInd] = (bits[negInd] == '0') ? '1' : '0';
					negInd++;
				}
			}

			string bitRepresentation(bits);

			bitRepresentation = bitRepresentation.substr(0, 32);
			cout << "matrix[" << i << "]" << "[" << j << "]" << "(0x" << memAddress << ") = " << matrix[i][j] << " =>   " << bitRepresentation <<endl;
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
		int indexOfFirstOne = 0;	//used for 2's Complement

		for (int i = 0; i < 32; i++) bits[i] = '0';	//32 bit array

		int ind = 0;
		while (num > 0) {
			if (num % 2 == 0) bits[ind] = '0';
			else {
				bits[ind] = '1';
				if (indexOfFirstOne == 0) indexOfFirstOne = ind; //only changes when first instance of '1'
			}

			ind++;

			num /= 2;
		}

		if (negative) {	//Only runs for negative numbers
			int negInd = indexOfFirstOne + 1;
			while (negInd < 32) {
				bits[negInd] = (bits[negInd] == '0') ? '1' : '0';
				negInd++;
			}
		}

		string bitRepresentation(bits);

		bitRepresentation = bitRepresentation.substr(0, 32);
		cout << "matrix[" << j << "]" << "(0x" << memAddress << ") = " << matrix[j] << " =>   " << bitRepresentation << endl;
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