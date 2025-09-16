	.text
	.file	"helpers.c"
	.globl	input                           # -- Begin function input
	.p2align	4, 0x90
	.type	input,@function
input:                                  # @input
	.cfi_startproc
# %bb.0:
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset %rbp, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register %rbp
	subq	$32, %rsp
	movq	%rdi, -16(%rbp)
	movl	%esi, -20(%rbp)
	movq	-16(%rbp), %rdi
	movl	-20(%rbp), %esi
	movq	stdin@GOTPCREL(%rip), %rax
	movq	(%rax), %rdx
	callq	fgets@PLT
	cmpq	$0, %rax
	jne	.LBB0_2
# %bb.1:
	movq	$0, -8(%rbp)
	jmp	.LBB0_3
.LBB0_2:
	movq	-16(%rbp), %rax
	movq	%rax, -32(%rbp)                 # 8-byte Spill
	movq	-16(%rbp), %rdi
	leaq	.L.str(%rip), %rsi
	callq	strcspn@PLT
	movq	%rax, %rcx
	movq	-32(%rbp), %rax                 # 8-byte Reload
	movb	$0, (%rax,%rcx)
	movq	-16(%rbp), %rax
	movq	%rax, -8(%rbp)
.LBB0_3:
	movq	-8(%rbp), %rax
	addq	$32, %rsp
	popq	%rbp
	.cfi_def_cfa %rsp, 8
	retq
.Lfunc_end0:
	.size	input, .Lfunc_end0-input
	.cfi_endproc
                                        # -- End function
	.globl	reverse                         # -- Begin function reverse
	.p2align	4, 0x90
	.type	reverse,@function
reverse:                                # @reverse
	.cfi_startproc
# %bb.0:
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset %rbp, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register %rbp
	subq	$48, %rsp
	movq	%rdi, -16(%rbp)
	cmpq	$0, -16(%rbp)
	jne	.LBB1_2
# %bb.1:
	movq	$0, -8(%rbp)
	jmp	.LBB1_6
.LBB1_2:
	movq	-16(%rbp), %rax
	movq	%rax, -24(%rbp)
	movq	-16(%rbp), %rax
	movq	%rax, -48(%rbp)                 # 8-byte Spill
	movq	-16(%rbp), %rdi
	callq	strlen@PLT
	movq	%rax, %rcx
	movq	-48(%rbp), %rax                 # 8-byte Reload
	addq	%rcx, %rax
	addq	$-1, %rax
	movq	%rax, -32(%rbp)
.LBB1_3:                                # =>This Inner Loop Header: Depth=1
	movq	-32(%rbp), %rax
	cmpq	-24(%rbp), %rax
	jbe	.LBB1_5
# %bb.4:                                #   in Loop: Header=BB1_3 Depth=1
	movq	-24(%rbp), %rax
	movb	(%rax), %al
	movb	%al, -33(%rbp)
	movq	-32(%rbp), %rax
	movb	(%rax), %cl
	movq	-24(%rbp), %rax
	movb	%cl, (%rax)
	movb	-33(%rbp), %cl
	movq	-32(%rbp), %rax
	movb	%cl, (%rax)
	movq	-24(%rbp), %rax
	addq	$1, %rax
	movq	%rax, -24(%rbp)
	movq	-32(%rbp), %rax
	addq	$-1, %rax
	movq	%rax, -32(%rbp)
	jmp	.LBB1_3
.LBB1_5:
	movq	-16(%rbp), %rax
	movq	%rax, -8(%rbp)
.LBB1_6:
	movq	-8(%rbp), %rax
	addq	$48, %rsp
	popq	%rbp
	.cfi_def_cfa %rsp, 8
	retq
.Lfunc_end1:
	.size	reverse, .Lfunc_end1-reverse
	.cfi_endproc
                                        # -- End function
	.globl	concatenate                     # -- Begin function concatenate
	.p2align	4, 0x90
	.type	concatenate,@function
concatenate:                            # @concatenate
	.cfi_startproc
# %bb.0:
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset %rbp, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register %rbp
	subq	$32, %rsp
	movq	%rdi, -8(%rbp)
	movq	%rsi, -16(%rbp)
	movq	%rdx, -24(%rbp)
	movq	%rcx, -32(%rbp)
	movq	-8(%rbp), %rdi
	movq	-16(%rbp), %rsi
	movq	-24(%rbp), %rcx
	movq	-32(%rbp), %r8
	leaq	.L.str.1(%rip), %rdx
	movb	$0, %al
	callq	snprintf@PLT
	movq	-8(%rbp), %rax
	addq	$32, %rsp
	popq	%rbp
	.cfi_def_cfa %rsp, 8
	retq
.Lfunc_end2:
	.size	concatenate, .Lfunc_end2-concatenate
	.cfi_endproc
                                        # -- End function
	.type	.L.str,@object                  # @.str
	.section	.rodata.str1.1,"aMS",@progbits,1
.L.str:
	.asciz	"\n"
	.size	.L.str, 2

	.type	.L.str.1,@object                # @.str.1
.L.str.1:
	.asciz	"%s%s"
	.size	.L.str.1, 5

	.ident	"Ubuntu clang version 14.0.0-1ubuntu1.1"
	.section	".note.GNU-stack","",@progbits
	.addrsig
	.addrsig_sym fgets
	.addrsig_sym strcspn
	.addrsig_sym strlen
	.addrsig_sym snprintf
	.addrsig_sym stdin
