bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; a-doubleword; b,c,d-byte; x-qword
    a dd 10
    b db 5
    c db 5
    d db 5
    x dq 1
    two db 2
    seven db 7
    
; our code starts here
segment code use32 class=code
    start:
        ; a-(7+x)/(b*b-c/d+2);
        
        ;(b*b-c/d+2)
        mov al, [b]
        mul byte [b]
        mov bl, [c]
        mov bh, 0
        sub ax, bx
        mov bl, [d]
        add bl, two
        mov bh, 0
        div bx
        mov bx, ax
        
        ; a-(7+x)
        ; I dont know if it's ok but I'm going to try to get rid of the bracket therefore a-(7+x) = a-7-x
        mov eax, [a]
        mov ecx, 0
        mov cl, [seven]
        sub eax, ecx
        mov edx 0
        sub eax,dword [x]
        sbb edx,dword [x + 4]
        
        div bx; Are we allowed to do this?
        
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
