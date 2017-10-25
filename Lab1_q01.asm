#File name: q1.asm
#/*
#Use the register $t0 for the variable `sum'
#*/
#int sum = 0;
#for (int i = 1; i < 10; i++) {
#sum += i;
#}
.globl main

main:

addi $t1, $zero, 0 #sum is $t1
addi $t2, $zero, 1 #i is $t2
LOOP:
bge $t2, 10, EXIT
add $t1, $t1, $t2
addi $t2, $t2, 1
j LOOP
EXIT:
jr $ra