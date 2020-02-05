bits 32 ; assembling for the 32 bits architecture       


extern printf, scanf, fopen, fprintf, fclose
import printf msvcrt.dll
import scanf msvcrt.dll
import fopen msvcrt.dll
import fclose msvcrt.dll
import fprintf msvcrt.dll

global function      
; max = ESP + 4
; number2 = = ESP + 8
; number = ESP + 12
; format2 = ESP + 16
; format = ESP + 20
; message2 = ESP + 24
; message = ESP + 28
; n = ESP + 32
; fileName = ESP + 36
;writeTipe = ESP + 40
segment code use32 class=code public
    function:
        mov ECX , [ESP + 32]
        
        loop1:
            jecxz end

            mov EBX , ECX
            
            push dword [ESP + 24]
            call [printf]
            add ESP, 4 * 1  ; prints message
            
            push dword [ESP + 12]
            push dword [ESP + 24]
            call [scanf]
            add ESP , 4*2   ; 
            
            mov EAX , [ESP + 12]
            mov EDX , [EAX]
            mov EAX , EDX   ; puts the compared numbered in eax
            
            mov ECX , [ESP + 4]
            mov EDX , [ECX] ;puts the max in EDX
            
            cmp AX , DX     ; compares the numbers
            jg above        ; jumps if ax is greater than DX
            returnlabel:    ; lable to go to when changing the max value
            mov ECX , EBX
        loop loop1
        jmp end
        
        above : 
            mov EDX , [ESP + 4]
            mov [EDX], AX   ;  we change it to max if the number is higher
            jmp returnlabel

        end:
            push dword [ESP + 36]
            push dword [ESP + 40]
            call [fopen]
            
            push [esp + 4]
            call [fprintf]
            
            push dword eax
            call [fclose]
            
            mov EDX , [ESP + 4]
            push dword [EDX]
            push dword [ESP + 20]   ; we write the max number to the file
            call [printf]
            add ESP, 4 * 2

            ret
