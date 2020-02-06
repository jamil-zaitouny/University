(defun nrOccurences(element listOfElements)
    (cond
        ((null listOfElements) 0)
        ((eq (car listOfElements) element) (+ 1 (nrOccurences element(cdr listOfElements))))
        ((listp (car listOfElements)) (+ (nrOccurences element (car listOfElements)) (nrOccurences element (cdr listOfElements))))
        (t (nrOccurences element (cdr listOfElements)))
    )
)

(defun removeUnique (listOfElements)
    (cond 
        ((null listOfElements) nil)
        (       (listp (car listOfElements)) 
                (cons (removeUnique (car listOfElements)) (removeUnique (cdr listOfElements)))
        )
        (    (eq (nrOccurences (car listOfElements) listOfElements) 1)
             (cons (car listOfElements) (removeUnique (cdr listOfElements)))
        )
        (t (removeUnique (cdr listOfElements)))
    )
)

;; (print (removeUnique '(1 2 4 '(5 6 6 6 7) 4 5 6 7)))

;;removeIncreasingSequence(l1..ln) { nil, n = 0
;;                                   l1, n = 1
;;                                   l1 U l2, l1 >= l2, n = 2
;;                                   removeIncreasingSequence(l2..ln), l1 < l2 < l3
;;                                   removeIncreasingSequence(l3..ln), l1 < l2 >= l3
;;                                   l1 U removeIncreasing(l2..ln), otherwise
(defun removeIncreasing (listOfElements)
    (cond
        ((null listOfElements) nil)
        ((eq (nth 1 listOfElements) nil) (cons (nth 0 listOfElements) nil))
        ((and (< (car listOfElements) (nth 1 listOfElements)) (< (nth 1 listOfElements) (nth 2 listOfElements))) (removeIncreasing (cdr listOfElements)))
        ((< (car listOfElements) (nth 1 listOfElements)) (removeIncreasing (cddr listOfElements)))
        (t (cons (car listOfElements) (removeIncreasing (cdr listOfElements))))
    )
)

(print (removeIncreasing '(1 2 4 6 5 7 8 2 1 0 '(3 4 5))))