bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; ...
    M dd 200
    MNew dd 0
; our code starts here
segment code use32 class=code
    ;Given the doubleword M, compute the doubleword MNew as follows:
    ;the bits 0-3 a of Mnew are the same as the bits 5-8 a M.
    ;the bits 4-7 a of MNew have the value 1
    ;the bits 27-31 a of MNew have the value 0
    ;the bits 8-26 of MNew are the same as the bits 8-26 a of M
    start:
        ; ...
            mov eax, 0
            mov eax, [M]
            mov dl, 6
            mov ecx, 1111b
            shr ecx, dl
            and eax, ecx
            or [MNew], eax
            
            mov eax, [MNew]
            or eax, 111100000b
            or [MNew], eax
            
            mov eax, [MNew]
            and eax, 00000111111111111111111111111111b
            or [MNew], eax
       
            
            mov eax, [M]
            and eax, 00000111111111111111111100000000b
            or [MNew], eax
            
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
