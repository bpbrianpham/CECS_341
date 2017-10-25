#Use the register $a0 and $a1 for the variable `n' and `acc', respectively
#A return value should be stored in the register $v0

#int sum(int n, int acc) {
#if (n > 0) {
#return sum(n-1, acc + n);
#}
#else {
#return acc;
#}
#}

addi $a0, $0, 10
addi $a1, $0, 0

fact:
addi $sp, $sp, -12		#adjust stack for 2 items
sw $ra, 8($sp)			#save return address
sw $a0, 4($sp)			#save argument
sw $a1, 0($sp)			#acc

slti $t0, $a0, 0		# if n < 0
beq $t0, $zero, decrement	#while n > 0 --> decrement
add $v0, $zero, $a1
addi $sp, $sp, 12
j exit

decrement:			#return sum(n-1, acc+n)
add $a1, $a1, $a0
addi $a0,$a0,-1
jal fact
lw $a1, 0($sp)
lw $a0, 4($sp)
lw $ra, 8($sp)
addi $sp,$sp, 12
add $v0, $a1, $v0
jr $ra

exit: