	.text
	.file	"ctest.c"
	.globl	main                            # -- Begin function main
	.p2align	4, 0x90
	.type	main,@function
main:                                   # @main
	.cfi_startproc
# %bb.0:
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset %rbp, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register %rbp
	subq	$128, %rsp
	movq	.L__const.main.str(%rip), %rax
	movq	%rax, -12(%rbp)
	movl	.L__const.main.str+8(%rip), %eax
	movl	%eax, -4(%rbp)
	leaq	-12(%rbp), %rsi
	leaq	.L.str(%rip), %rdi
	movb	$0, %al
	callq	printf@PLT
	leaq	-12(%rbp), %rdi
	callq	reverse@PLT
	leaq	-12(%rbp), %rsi
	leaq	.L.str.1(%rip), %rdi
	movb	$0, %al
	callq	printf@PLT
	leaq	.L.str.2(%rip), %rax
	movq	%rax, -120(%rbp)
	leaq	.L.str.3(%rip), %rax
	movq	%rax, -128(%rbp)
	leaq	-112(%rbp), %rdi
	movq	-120(%rbp), %rdx
	movq	-128(%rbp), %rcx
	movl	$100, %esi
	callq	concatenate@PLT
	leaq	-112(%rbp), %rsi
	leaq	.L.str.4(%rip), %rdi
	movb	$0, %al
	callq	printf@PLT
	xorl	%eax, %eax
	addq	$128, %rsp
	popq	%rbp
	.cfi_def_cfa %rsp, 8
	retq
.Lfunc_end0:
	.size	main, .Lfunc_end0-main
	.cfi_endproc
                                        # -- End function
	.type	.L__const.main.str,@object      # @__const.main.str
	.section	.rodata.str1.1,"aMS",@progbits,1
.L__const.main.str:
	.asciz	"Hello World"
	.size	.L__const.main.str, 12

	.type	.L.str,@object                  # @.str
.L.str:
	.asciz	"Original:  \"%s\"\n"
	.size	.L.str, 17

	.type	.L.str.1,@object                # @.str.1
.L.str.1:
	.asciz	"In-place:  \"%s\"\n"
	.size	.L.str.1, 17

	.type	.L.str.2,@object                # @.str.2
.L.str.2:
	.asciz	"Wow this is "
	.size	.L.str.2, 13

	.type	.L.str.3,@object                # @.str.3
.L.str.3:
	.asciz	"really freaking hard"
	.size	.L.str.3, 21

	.type	.L.str.4,@object                # @.str.4
.L.str.4:
	.asciz	"Safe concat result: \"%s\"\n"
	.size	.L.str.4, 26

	.ident	"Ubuntu clang version 14.0.0-1ubuntu1.1"
	.section	".note.GNU-stack","",@progbits
	.addrsig
	.addrsig_sym printf
	.addrsig_sym reverse
	.addrsig_sym concatenate
