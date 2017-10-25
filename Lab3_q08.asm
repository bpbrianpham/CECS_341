#Use the register $t0 for the variable `index' and $t1 for `quot'

#int divd = 0xFFFF, index = 0, quot, rem;
#while (divd > 1) {
#rem = divd % 2;
#if (rem == 0) {
#quot = divd / 2;
#divd = quot;
#}
#else {
#divd = 3 * divd + 1;
#}
#index++;
#}

addi $t0, $zero, 0	#index
lui $t2, 0x0000
ori $t2, 0xFFFF		#divd
divdChx:
ble $t2, 1, exit
rem $t3, $t2, 2
bne $t3, 0, else
div $t1, $t2, 2
add $t2, $zero, $t1
j L1
else:
mul $t4, $t2, 3
addi $t2, $t4, 1
L1:
addi $t0, $t0, 1
j divdChx
exit:
