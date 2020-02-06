;;9
;; RemoveAtom(atomTorRemove, l1..ln){l1 U removeAtom(atomToRemove, l2..ln), l1 = atomToRemove
;;                                   removeAtom(atomToRemove, k2), l2 = atomToRemove and l2 is a number
;;                                   mapcar(removeAtom, (atomToRemove, listOfElements))       
(defun removeAtom (atomToRemove listOfElements)
    (cond
        ((and (numberp (car listOfElements)) (eq atomToRemove (car listOfElements))) (removeAtom atomToRemove (cdr listOfElements)))
        ((numberp (car listOfElements)) (cons (car listOfElements) (removeAtom atomToRemove (cdr listOfElements))))
        (
            (listp (car listOfElements)) (mapcar #'(lambda (sublist) (removeAtom atomToRemove sublist)) listOfElements) 
        )
))
(print (removeAtom 3 '( 1 2 3 ( 3 3 1 1 (1 5 3 (2 3 3 3 4))) (1 2 3 4))))