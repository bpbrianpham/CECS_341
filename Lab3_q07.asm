#Assume the size of ‘long’ is 64 bits
#Use the register $t0 for the variable ‘index’

#long index = 0, mul = 1, bound = 0xFFFFFFFF;
#while (mul <= bound) {
#mul *= ++index;
#}


addi $t0, $zero, 0	#index
addi $t1, $zero, 1	#mul
addi $t2, $zero, 0

LOOP:
bne $t2, $zero, Exit
addi $t0, $t0, 1
mult $t0, $t1
mfhi $t2
mflo $t1
j LOOP

Exit:


