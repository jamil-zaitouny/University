;;10A
;; productSuperficial(listOfElements:List)
;; productSuperficial(l1..ln){1, n = 0
;;                            l1 * productSuperificial(l2..ln), l1 is number
;;                            productSuperficial(l2..ln), otherwise
(defun productSuperficial (listOfElements)
    (cond
        ((null listOfElements) 1)
        ((numberp (car listOfElements)) (* (car listOfElements) (productSuperficial (cdr listOfElements))))
        (t (productSuperficial (cdr listOfElements)))
    )
)

;; (print (productSuperficial '(1 2 3 '(1 2 3))))
;;10B
;;replaceFirstAppearence(listOfElements: List, oldElement:atom, newElement:atom )
;; replaceFirstAppearence(l1..ln, oldElement, newElement){nil, n = 0
;;                                                        newElement U replaceFirstAppearence(l2..ln), l1 = oldElement
;;                                                        l1 U replaceFirstAppearence(l2..ln), otherwise
(defun replaceFirstAppearence(listOfElements oldElement newElement)
    (cond
    ((null listOfElements) nil)
    ((eq oldElement (car listOfElements)) (cons newElement (cdr listOfElements)))
    (t (cons (car listOfElements) (replaceFirstAppearence (cdr listOfElements) oldElement newElement)))
    )
)

;; (print (replaceFirstAppearence '(1 2 3 4 5) 3 4))

;;10C
;;computeExpression(listOfElements:List)
;;computeExpression(l1..ln){nil, n = 0
;;                          apply(l1 computeExpression(l2..ln)), l1 = + or - or * or /
;;                          (l1 U l2) U computeExpression(l2..ln), otherwise
(defun computeExpression(listOfElements)
    (cond
        ((null listOfElements) '(0))
        (
            ;; condition it's an operator
            (or 
                (or (eq (car listOfElements) '+)
                    (or (eq (car listOfElements) '-)
                        (eq (car listOfElements) '*)))                
            )
            (apply (car listOfElements) (computeExpression (cdr listOfElements)))
        )
        ((null (nth 1 listOfElements)) (car listOfElements))
        (t (append (list (car listOfElements) (nth 1 listOfElements)) (computeExpression (cddr listOfElements))))
    )
)
(print (computeExpression (list '+ 1 2)))

;;10D
;;returnCount(listOfElements: List, element:atom)
;;element is the element we're counting in the list
;;returnCount(l1..ln, element){0, n = 0
;;                             1 + returnCount(l2..ln, element), l1 == element 
;;                             returnCount(l2..ln, element), otherwise
(defun returnCount (listOfElements element)
    (cond
        ((null listOfElements) 0)
        ((eq (car listOfElements) element) (+ 1 (returnCount (cdr listOfElements) element)))
        (t (returnCount (cdr listOfElements) element))
    )
)
;; (print (returnCount '(1 2 2 2) 2))

;;removeElement(listOfElements: List, element: Atom)
;;element is the item to be removed from the list of elements
;;removeElement(l1..ln, element){nil, n = 0
;;                                  removeElement(l2..ln), element == l1
;;                                  l1 U removeElement(l2..ln), otherwise
(defun removeElement (listOfElements element)
    (cond
    ((null listOfElements) nil)
    ((eq (car listOfElements) element) (removeElement (cdr listOfElements) element))
    (t (cons (car listOfElements) (removeElement (cdr listOfElements) element)))
    )
)
;; (print (removeElement '(1 1 1 1 1 2) 1))

;;countSublists(listOfElements:List)
;;countSublists(l1..ln){nil, n = 0
;;                      (l1 returnCount(l1)) U countSublists(removeElement(l2..ln, l1)), otherwise
(defun countSublists(listOfElements)
    (cond
        ((null listOfElements) nil)
        (t (cons 
         (list (car listOfElements) (returnCount listOfElements (car listOfElements)))
         (countSublists (removeElement listOfElements (car listOfElements)))))
    )
) 

(print (countSublists '(1 1 1 1 2 2 2 3 3)))