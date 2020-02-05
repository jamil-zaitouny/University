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
    a db 5
    b dw 255
    c dd 7
    d dq 8
; our code starts here
segment code use32 class=code
    start:
        ; ((a + b) + (a + c) + (b + c)) - d
        mov bx, [b]; bx = 5
        mov ch, 0;
        mov cl, [a]
        add bx, cx; bl = 5+6 :=> bx = 11
        
        mov al, [a]
        mov ah, 0; ax = 6
        mov dx, 0; dx:ax = 6
        add ax, word [c]; ax = 6 + 7 = 13
        add dx, word [c+2];dx = dx + 0 :=> dx:ax = 13
        mov ecx, 0; ecx = 0
        push dx
        push ax
        pop ecx; ecx = 13
        add cx, bx; adds low part of cx to bx :=> 
        
        mov ax, [b]
        mov dx, 0
        add ax, [c]
        add dx, [c+2]
        mov ebx, 0
        push dx
        push ax
        pop eax
        
        add eax, ecx
        mov edx, 0
        
        sub eax, dword[d]
        sbb edx, dword[d+4]
    
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
