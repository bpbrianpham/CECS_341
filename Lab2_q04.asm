#The base address of A[] is 0x10010000
#Use the register $t0 for the variable `max'

#int A[5] = { 1, 2, 3, 4, 5 };
#int max = 0;
#for (int i = 0; i < 5; i++) {
#if (A[i] > max) {max = A[i];}
#}




.data
.word 1
.word 2
.word 3
.word 4
.word 5
.text

.globl main
main:

lui $s1, 0x1001
ori $s1, 0x0000		#$s1 is address of A
addi $t1, $zero, 0 	#$t1 is 'max'
addi $t2, $zero, 0	#$t2 is 'i'
LOOP:
bge $t2, 5, EXIT
lw $t0, 0($s1)
ble $t0, $t1, SKIP
addi $t1, $t0, 0
sw $t1, 0($s1)
SKIP:
addi $s1, $s1, 4
addi $t2, $t2, 1
j LOOP
EXIT:
jr $ra
