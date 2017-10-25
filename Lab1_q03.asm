#File name: q3.asm
#/*
#The base address of A[] is 0x10010000
#*/
#int A[5] = { 5, 4, 3, 2, 1 };
#for (int i = 0; i < 5; i++) {
#A[i] = A[i] * 2;
#}

.data
.word 5
.word 4
.word 3
.word 2
.word 1
.text
.globl main

main:

lui $t0, 0x1001 
ori $t0, 0x0000 	#$t0 is address for A

addi $t1, $zero, 0	#$t1 is i
LOOP:
bge $t1, 5, EXIT
lw $t2, 0($t0)
add $t2, $t2, $t2
sw $t2, 0($t0)
add $t0, $t0, 4
addi $t1, $t1, 1
j LOOP
EXIT:
jr $ra