#File name: q2.asm
#/*
#Use the register $t0 for the variable `sum'
#*/
#int sum = 0, i = 10;
#while (--i > 0) {
#sum += i;
#}

.globl main

main:

addi $t0, $zero, 0	#sum is $t0
addi $t1, $zero, 10	#i is $t1

LOOP:
addi $t1, $t1, -1
ble $t1, 0, EXIT
add $t0, $t0, $t1
j LOOP 
EXIT:

jr $ra