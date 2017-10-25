#Use the register $f0 for the variable ‘pi’.
#Expected $f0: 0x40490fe5

#float pi = 0, fraction;
#for (int i = 0; i < 1000000; i++) {
#fraction = 2 * i + 1;
#if (i % 2 == 0) {
#pi += 1 / fraction;
#}
#else {
#pi -= 1 / fraction;
#}
#}
#pi *= 4;

.data
fp1: .float 0
fp2: .float 0
fp3: .float 1
fp4: .float 4
.text

l.s $f0, fp1		#pi
l.s $f2, fp2		#fraction
l.s $f3, fp3		#1
l.s $f5, fp4		#4

addi $t1, $0, 0		#i

loop:
bge $t1, 1000, exit
mul $t2, $t1, 2
addi $t2, $t2, 1	#fraction
mtc1 $t2, $f2
cvt.s.w $f2, $f2
rem $t3, $t1, 2
bne $t3, 0, odd
div.s $f4, $f3, $f2
add.s $f1, $f1, $f4
addi $t1, $t1, 1
j loop
odd:
div.s $f4, $f3, $f2
sub.s $f1, $f1, $f4
addi $t1, $t1, 1
j loop
exit:
mul.s $f0, $f1, $f5
