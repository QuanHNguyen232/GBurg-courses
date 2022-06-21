.text
main:

	#read an integer
	la $a0, prompt
	jal PromptForInt
	#result in $v0
	move $s0, $v0
	
	#logical operators
	sra $s1, $s0, 3
	
	#print the integer
	move $a0, $s0
	jal PrintInteger
	# print new line
	jal PrintNewLine
	
	#print the integer
	move $a0, $s1
	jal PrintInteger
	jal PrintNewLine
	

	#halt the program by calling exit
	jal Exit
.data
prompt: .asciiz "Enter an integer: "


.include "utils.asm"


