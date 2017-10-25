#Use the register $t0 for the variable `sum'

#int sum = 0;
#for (int i = 1; i < 10; i++) {
#for (int j = 1; j < 10; j++) {
#sum += i * j;
#}
#}

.globl main
main:

addi $t0, $zero, 0	#$t0 is 'sum'
addi $t1, $zero, 1	#$t1 is 'i'
LOOPA:
bge $t1, 10, EXIT
addi $t2, $zero, 1	#$t2 is 'j'
LOOPB:
bge $t2, 10, ENDB
mul $t4, $t1, $t2
add $t0, $t0, $t4
addi $t2, $t2, 1
j LOOPB
ENDB:
addi $t1, $t1, 1
j LOOPA
EXIT:
jr $ra