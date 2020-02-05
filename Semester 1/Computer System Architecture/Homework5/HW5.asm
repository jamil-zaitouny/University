bits 32
globAL start
extern exit
import exit msvcrt.dll

segment data use32 class=data
  A db 2,1,-3,0
  lengthOfA equ $-A
  B db 4, 5, 7, 6, 2, 1
  lengthOfB equ $-B
  R times (lengthOfA+lengthOfB) db 0
segment code use32 class=code
;Two byte strings A and B are given. Obtain the string R by concatenating the elements of B in reverse order and the elements of A in reverse order. 
;A 2,1,-3,0
;B 4, 5, 7, 6, 2, 1
;R 1,2,6,7,5,4,0,-3,1,2
start:
        mov ESI,lengthOfB;stores the length of B for decremention
        mov EDI,0 
        
        redo1:;put B in R in reverse order 
            mov AL,[B+ESI-1];puts the elements of B in AL in reverse order
            mov [R+EDI],AL ; move in R the current element of B
            inc EDI
            dec ESI 
            cmp ESI, 0
            je lenESI;when ESI is 0 jumps to redo2 label
        loop redo1
        
        lenESI:
            mov ESI, lengthOfA
            
        redo2:;put elements from A in reverse order in R
            mov AL,[A+ESI-1]; puts the elements of A in AL in reverse order
            mov [R+EDI],AL ; move in R the current element of A
            inc EDI 
            dec ESI
            cmp ESI, 0
        loop redo2
            je endLoop;when ESI is 0 jumps to endLoop label 
        
        endLoop:
            push dword 0
            call exit