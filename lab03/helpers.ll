; ModuleID = 'helpers.c'
source_filename = "helpers.c"
target datalayout = "e-m:e-p270:32:32-p271:32:32-p272:64:64-i64:64-f80:128-n8:16:32:64-S128"
target triple = "x86_64-pc-linux-gnu"

%struct._IO_FILE = type { i32, i8*, i8*, i8*, i8*, i8*, i8*, i8*, i8*, i8*, i8*, i8*, %struct._IO_marker*, %struct._IO_FILE*, i32, i32, i64, i16, i8, [1 x i8], i8*, i64, %struct._IO_codecvt*, %struct._IO_wide_data*, %struct._IO_FILE*, i8*, i64, i32, [20 x i8] }
%struct._IO_marker = type opaque
%struct._IO_codecvt = type opaque
%struct._IO_wide_data = type opaque

@stdin = external global %struct._IO_FILE*, align 8
@.str = private unnamed_addr constant [2 x i8] c"\0A\00", align 1

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

declare i8* @fgets(i8* noundef, i32 noundef, %struct._IO_FILE* noundef) #1   ;put in your compiler code

; Function Attrs: nounwind readonly willreturn
declare i64 @strcspn(i8* noundef, i8* noundef) #2

; i8* @reverse(i8* %input)
define i8* @reverse(i8* noundef %input) {
entry:
  ; compute length of input
  %len = call i64 @strlen(i8* noundef %input)

  ; allocate output buffer: len + 1 (for '\0')
  %size = add i64 %len, 1
  %out = call i8* @malloc(i64 %size)

  ; initialize loop vars
  %i = alloca i64, align 8
  store i64 0, i64* %i
  br label %loop

loop:                                             ; preds = %loop, %entry
  %iv = load i64, i64* %i
  %cmp = icmp slt i64 %iv, %len
  br i1 %cmp, label %body, label %done

body:                                             ; preds = %loop
  ; source index = len - 1 - i
  %sub = sub i64 %len, 1
  %srcIndex = sub i64 %sub, %iv
  %srcPtr = getelementptr inbounds i8, i8* %input, i64 %srcIndex
  %ch = load i8, i8* %srcPtr

  ; dest index = i
  %dstPtr = getelementptr inbounds i8, i8* %out, i64 %iv
  store i8 %ch, i8* %dstPtr

  ; increment i
  %inc = add i64 %iv, 1
  store i64 %inc, i64* %i
  br label %loop

done:                                             ; preds = %loop
  ; null terminate
  %nullPtr = getelementptr inbounds i8, i8* %out, i64 %len
  store i8 0, i8* %nullPtr

  ret i8* %out
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
