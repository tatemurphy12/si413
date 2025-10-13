; ModuleID = 'preamble.c'
source_filename = "preamble.c"
target datalayout = "e-m:e-p270:32:32-p271:32:32-p272:64:64-i64:64-f80:128-n8:16:32:64-S128"
target triple = "x86_64-pc-linux-gnu"

%struct._IO_FILE = type { i32, i8*, i8*, i8*, i8*, i8*, i8*, i8*, i8*, i8*, i8*, i8*, %struct._IO_marker*, %struct._IO_FILE*, i32, i32, i64, i16, i8, [1 x i8], i8*, i64, %struct._IO_codecvt*, %struct._IO_wide_data*, %struct._IO_FILE*, i8*, i64, i32, [20 x i8] }
%struct._IO_marker = type opaque
%struct._IO_codecvt = type opaque
%struct._IO_wide_data = type opaque

@stdin = external global %struct._IO_FILE*, align 8
@.str = private unnamed_addr constant [2 x i8] c"\0A\00", align 1
@.str.1 = private unnamed_addr constant [4 x i8] c"Aye\00", align 1
@.str.2 = private unnamed_addr constant [4 x i8] c"Nay\00", align 1

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i8* @input(i8* noundef %0, i32 noundef %1) #0 {
  %3 = alloca i8*, align 8
  %4 = alloca i8*, align 8
  %5 = alloca i32, align 4
  store i8* %0, i8** %4, align 8
  store i32 %1, i32* %5, align 4
  %6 = load i8*, i8** %4, align 8
  %7 = load i32, i32* %5, align 4
  %8 = load %struct._IO_FILE*, %struct._IO_FILE** @stdin, align 8
  %9 = call i8* @fgets(i8* noundef %6, i32 noundef %7, %struct._IO_FILE* noundef %8)
  %10 = icmp eq i8* %9, null
  br i1 %10, label %11, label %12

11:                                               ; preds = %2
  store i8* null, i8** %3, align 8
  br label %18

12:                                               ; preds = %2
  %13 = load i8*, i8** %4, align 8
  %14 = load i8*, i8** %4, align 8
  %15 = call i64 @strcspn(i8* noundef %14, i8* noundef getelementptr inbounds ([2 x i8], [2 x i8]* @.str, i64 0, i64 0)) #4
  %16 = getelementptr inbounds i8, i8* %13, i64 %15
  store i8 0, i8* %16, align 1
  %17 = load i8*, i8** %4, align 8
  store i8* %17, i8** %3, align 8
  br label %18

18:                                               ; preds = %12, %11
  %19 = load i8*, i8** %3, align 8
  ret i8* %19
}

declare i8* @fgets(i8* noundef, i32 noundef, %struct._IO_FILE* noundef) #1

; Function Attrs: nounwind readonly willreturn
declare i64 @strcspn(i8* noundef, i8* noundef) #2

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i8* @reverse(i8* noundef %0) #0 {
  %2 = alloca i8*, align 8
  %3 = alloca i8*, align 8
  %4 = alloca i8*, align 8
  %5 = alloca i8*, align 8
  %6 = alloca i8, align 1
  store i8* %0, i8** %3, align 8
  %7 = load i8*, i8** %3, align 8
  %8 = icmp eq i8* %7, null
  br i1 %8, label %14, label %9

9:                                                ; preds = %1
  %10 = load i8*, i8** %3, align 8
  %11 = load i8, i8* %10, align 1
  %12 = sext i8 %11 to i32
  %13 = icmp eq i32 %12, 0
  br i1 %13, label %14, label %16

14:                                               ; preds = %9, %1
  %15 = load i8*, i8** %3, align 8
  store i8* %15, i8** %2, align 8
  br label %41

16:                                               ; preds = %9
  %17 = load i8*, i8** %3, align 8
  store i8* %17, i8** %4, align 8
  %18 = load i8*, i8** %3, align 8
  %19 = load i8*, i8** %3, align 8
  %20 = call i64 @strlen(i8* noundef %19) #4
  %21 = getelementptr inbounds i8, i8* %18, i64 %20
  %22 = getelementptr inbounds i8, i8* %21, i64 -1
  store i8* %22, i8** %5, align 8
  br label %23

23:                                               ; preds = %27, %16
  %24 = load i8*, i8** %5, align 8
  %25 = load i8*, i8** %4, align 8
  %26 = icmp ugt i8* %24, %25
  br i1 %26, label %27, label %39

27:                                               ; preds = %23
  %28 = load i8*, i8** %4, align 8
  %29 = load i8, i8* %28, align 1
  store i8 %29, i8* %6, align 1
  %30 = load i8*, i8** %5, align 8
  %31 = load i8, i8* %30, align 1
  %32 = load i8*, i8** %4, align 8
  store i8 %31, i8* %32, align 1
  %33 = load i8, i8* %6, align 1
  %34 = load i8*, i8** %5, align 8
  store i8 %33, i8* %34, align 1
  %35 = load i8*, i8** %4, align 8
  %36 = getelementptr inbounds i8, i8* %35, i32 1
  store i8* %36, i8** %4, align 8
  %37 = load i8*, i8** %5, align 8
  %38 = getelementptr inbounds i8, i8* %37, i32 -1
  store i8* %38, i8** %5, align 8
  br label %23, !llvm.loop !6

39:                                               ; preds = %23
  %40 = load i8*, i8** %3, align 8
  store i8* %40, i8** %2, align 8
  br label %41

41:                                               ; preds = %39, %14
  %42 = load i8*, i8** %2, align 8
  ret i8* %42
}

; Function Attrs: nounwind readonly willreturn
declare i64 @strlen(i8* noundef) #2

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i8* @concatenate(i8* noundef %0, i8* noundef %1) #0 {
  %3 = alloca i8*, align 8
  %4 = alloca i8*, align 8
  %5 = alloca i8*, align 8
  %6 = alloca i64, align 8
  %7 = alloca i64, align 8
  %8 = alloca i64, align 8
  %9 = alloca i8*, align 8
  store i8* %0, i8** %4, align 8
  store i8* %1, i8** %5, align 8
  %10 = load i8*, i8** %4, align 8
  %11 = call i64 @strlen(i8* noundef %10) #4
  store i64 %11, i64* %6, align 8
  %12 = load i8*, i8** %5, align 8
  %13 = call i64 @strlen(i8* noundef %12) #4
  store i64 %13, i64* %7, align 8
  %14 = load i64, i64* %6, align 8
  %15 = load i64, i64* %7, align 8
  %16 = add i64 %14, %15
  %17 = add i64 %16, 1
  store i64 %17, i64* %8, align 8
  %18 = load i64, i64* %8, align 8
  %19 = call noalias i8* @malloc(i64 noundef %18) #5
  store i8* %19, i8** %9, align 8
  %20 = load i8*, i8** %9, align 8
  %21 = icmp eq i8* %20, null
  br i1 %21, label %22, label %23

22:                                               ; preds = %2
  store i8* null, i8** %3, align 8
  br label %31

23:                                               ; preds = %2
  %24 = load i8*, i8** %9, align 8
  %25 = load i8*, i8** %4, align 8
  %26 = call i8* @strcpy(i8* noundef %24, i8* noundef %25) #5
  %27 = load i8*, i8** %9, align 8
  %28 = load i8*, i8** %5, align 8
  %29 = call i8* @strcat(i8* noundef %27, i8* noundef %28) #5
  %30 = load i8*, i8** %9, align 8
  store i8* %30, i8** %3, align 8
  br label %31

31:                                               ; preds = %23, %22
  %32 = load i8*, i8** %3, align 8
  ret i8* %32
}

; Function Attrs: nounwind
declare noalias i8* @malloc(i64 noundef) #3

; Function Attrs: nounwind
declare i8* @strcpy(i8* noundef, i8* noundef) #3

; Function Attrs: nounwind
declare i8* @strcat(i8* noundef, i8* noundef) #3

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i32 @string_compare(i8* noundef %0, i8* noundef %1) #0 {
  %3 = alloca i8*, align 8
  %4 = alloca i8*, align 8
  store i8* %0, i8** %3, align 8
  store i8* %1, i8** %4, align 8
  %5 = load i8*, i8** %3, align 8
  %6 = load i8*, i8** %4, align 8
  %7 = call i32 @strcmp(i8* noundef %5, i8* noundef %6) #4
  ret i32 %7
}

; Function Attrs: nounwind readonly willreturn
declare i32 @strcmp(i8* noundef, i8* noundef) #2

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i32 @string_contains(i8* noundef %0, i8* noundef %1) #0 {
  %3 = alloca i32, align 4
  %4 = alloca i8*, align 8
  %5 = alloca i8*, align 8
  store i8* %0, i8** %4, align 8
  store i8* %1, i8** %5, align 8
  %6 = load i8*, i8** %4, align 8
  %7 = load i8*, i8** %5, align 8
  %8 = call i8* @strstr(i8* noundef %6, i8* noundef %7) #4
  %9 = icmp ne i8* %8, null
  br i1 %9, label %10, label %11

10:                                               ; preds = %2
  store i32 1, i32* %3, align 4
  br label %12

11:                                               ; preds = %2
  store i32 0, i32* %3, align 4
  br label %12

12:                                               ; preds = %11, %10
  %13 = load i32, i32* %3, align 4
  ret i32 %13
}

; Function Attrs: nounwind readonly willreturn
declare i8* @strstr(i8* noundef, i8* noundef) #2

; Function Attrs: noinline nounwind optnone uwtable
define dso_local void @print_aye_or_nay(i8* noundef %0) #0 {
  %2 = alloca i8*, align 8
  %3 = alloca i8, align 1
  store i8* %0, i8** %2, align 8
  %4 = load i8*, i8** %2, align 8
  %5 = load i8, i8* %4, align 1
  %6 = trunc i8 %5 to i1
  %7 = zext i1 %6 to i8
  store i8 %7, i8* %3, align 1
  %8 = load i8, i8* %3, align 1
  %9 = trunc i8 %8 to i1
  br i1 %9, label %10, label %12

10:                                               ; preds = %1
  %11 = call i32 @puts(i8* noundef getelementptr inbounds ([4 x i8], [4 x i8]* @.str.1, i64 0, i64 0))
  br label %14

12:                                               ; preds = %1
  %13 = call i32 @puts(i8* noundef getelementptr inbounds ([4 x i8], [4 x i8]* @.str.2, i64 0, i64 0))
  br label %14

14:                                               ; preds = %12, %10
  ret void
}

declare i32 @puts(i8* noundef) #1

attributes #0 = { noinline nounwind optnone uwtable "frame-pointer"="all" "min-legal-vector-width"="0" "no-trapping-math"="true" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "tune-cpu"="generic" }
attributes #1 = { "frame-pointer"="all" "no-trapping-math"="true" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "tune-cpu"="generic" }
attributes #2 = { nounwind readonly willreturn "frame-pointer"="all" "no-trapping-math"="true" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "tune-cpu"="generic" }
attributes #3 = { nounwind "frame-pointer"="all" "no-trapping-math"="true" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "tune-cpu"="generic" }
attributes #4 = { nounwind readonly willreturn }
attributes #5 = { nounwind }

!llvm.module.flags = !{!0, !1, !2, !3, !4}
!llvm.ident = !{!5}

!0 = !{i32 1, !"wchar_size", i32 4}
!1 = !{i32 7, !"PIC Level", i32 2}
!2 = !{i32 7, !"PIE Level", i32 2}
!3 = !{i32 7, !"uwtable", i32 1}
!4 = !{i32 7, !"frame-pointer", i32 2}
!5 = !{!"Ubuntu clang version 14.0.0-1ubuntu1.1"}
!6 = distinct !{!6, !7}
!7 = !{!"llvm.loop.mustprogress"}
define i32 @main() {
%strPtr0 = getelementptr inbounds [10 x i8], [10 x i8]* @lit0, i64 0, i64 0
%strPtr1 = getelementptr inbounds [38 x i8], [38 x i8]* @lit1, i64 0, i64 0
call i32 @puts(i8* noundef %strPtr1)
%str2 = alloca [256 x i8], align 16
%strPtr2 = getelementptr inbounds [256 x i8], [256 x i8]* %str2, i64 0, i64 0
call i8* @input(i8* noundef %strPtr2, i32 noundef 256)
%strPtr3 = call i8* @reverse(i8* noundef %strPtr2)
%comRes0 = call i32 @string_compare(i8* noundef %strPtr3, i8* noundef %strPtr0)
%boolPtr1 = alloca i1, align 1
%boolPtr1LD = icmp eq i32 %comRes0, 0
store i1 %boolPtr1LD, ptr %boolPtr1, align 1
call void @print_aye_or_nay(ptr %boolPtr1)
%strPtr4 = getelementptr inbounds [31 x i8], [31 x i8]* @lit2, i64 0, i64 0
call i32 @puts(i8* noundef %strPtr4)
%str5 = alloca [256 x i8], align 16
%strPtr5 = getelementptr inbounds [256 x i8], [256 x i8]* %str5, i64 0, i64 0
call i8* @input(i8* noundef %strPtr5, i32 noundef 256)
%strPtr6 = getelementptr inbounds [5 x i8], [5 x i8]* @lit3, i64 0, i64 0
%conRes2 = call i32 @string_contains(i8* noundef %strPtr5, i8* noundef %strPtr6)
%boolPtr2  = alloca i1, align 1
%boolPtr2LD = icmp ne i32 %conRes2, 0
store i1 %boolPtr2LD, ptr %boolPtr2, align 1
%strPtr7 = getelementptr inbounds [12 x i8], [12 x i8]* @lit4, i64 0, i64 0
%comRes3 = call i32 @string_compare(i8* noundef %strPtr7, i8* noundef %strPtr5)
%boolPtr4 = alloca i1, align 1
%boolPtr4LD = icmp slt i32 %comRes3, 0
store i1 %boolPtr4LD, ptr %boolPtr4, align 1
%boolPtr25LD= load i1, ptr %boolPtr2, align 1
%boolPtr45LD= load i1, ptr %boolPtr4, align 1
%boolPtr5= alloca i1, align 1
%boolPtr5LD = and i1 %boolPtr25LD, %boolPtr45LD
store i1 %boolPtr5LD, ptr %boolPtr5, align 1
call void @print_aye_or_nay(ptr %boolPtr5)
%strPtr8 = getelementptr inbounds [25 x i8], [25 x i8]* @lit5, i64 0, i64 0
call i32 @puts(i8* noundef %strPtr8)
%str9 = alloca [256 x i8], align 16
%strPtr9 = getelementptr inbounds [256 x i8], [256 x i8]* %str9, i64 0, i64 0
call i8* @input(i8* noundef %strPtr9, i32 noundef 256)
%strPtr10 = getelementptr inbounds [24 x i8], [24 x i8]* @lit6, i64 0, i64 0
call i32 @puts(i8* noundef %strPtr10)
%str11 = alloca [256 x i8], align 16
%strPtr11 = getelementptr inbounds [256 x i8], [256 x i8]* %str11, i64 0, i64 0
call i8* @input(i8* noundef %strPtr11, i32 noundef 256)
%strPtr12 = call i8* @concatenate(i8* noundef %strPtr9, i8* noundef %strPtr11)
call i32 @puts(i8* noundef %strPtr12)
%strPtr13 = getelementptr inbounds [2 x i8], [2 x i8]* @lit7, i64 0, i64 0
%conRes6 = call i32 @string_contains(i8* noundef %strPtr12, i8* noundef %strPtr13)
%boolPtr6  = alloca i1, align 1
%boolPtr6LD = icmp ne i32 %conRes6, 0
store i1 %boolPtr6LD, ptr %boolPtr6, align 1
call void @print_aye_or_nay(ptr %boolPtr6)
%strPtr14 = getelementptr inbounds [20 x i8], [20 x i8]* @lit8, i64 0, i64 0
call i32 @puts(i8* noundef %strPtr14)
  ret i32 0
}

@lit0= constant [10 x i8] c"backwards\00"
@lit1= constant [38 x i8] c"Enter the password or walk the plank!\00"
@lit2= constant [31 x i8] c"Where is the treasure located?\00"
@lit3= constant [5 x i8] c"tree\00"
@lit4= constant [12 x i8] c"Port Royale\00"
@lit5= constant [25 x i8] c"What is your first name?\00"
@lit6= constant [24 x i8] c"What is your last name?\00"
@lit7= constant [2 x i8] c"X\00"
@lit8= constant [20 x i8] c"~~~~~~~~~~~~~~~~~~~\00"

