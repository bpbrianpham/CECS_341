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
fp1: .double 0.0
fp2: .double 0.0
fp3: .double 1.0
fp4: .double 4.0
.text

l.d $f0, fp1		#pi
l.d $f2, fp2		#fraction
l.d $f4, fp3		#1
l.d $f6, fp4		#4

addi $t1, $0, 0		#i

loop:
bge $t1, 1000, exit
mul $t2, $t1, 2
addi $t2, $t2, 1	#fraction
mtc1 $t2, $f2
cvt.d.w $f2, $f2
rem $t3, $t1, 2
bne $t3, 0, odd
div.d $f8, $f4, $f2
add.d $f0, $f0, $f8
addi $t1, $t1, 1
j loop
odd:
div.d $f8, $f4, $f2
sub.d $f0, $f0, $f8
addi $t1, $t1, 1
j loop
exit:
mul.d $f0, $f0, $f6
