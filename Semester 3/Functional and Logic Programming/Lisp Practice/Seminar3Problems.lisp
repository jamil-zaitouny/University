;;oddList(listOfElements: List)
;;oddList(l1..ln){nil, n = 0
;;              l1 U isOdd(l2..ln), l1 % 2 = 1
;;              isOdd(l2..ln), otherwise
(defun oddList (listOfElements)
    (cond
        ((null listOfElements) nil)
        ((eq (mod (car listOfElements) 2) 1) (cons (car listOfElements) (oddList (cdr listOfElements))))
        (t (oddList (cdr listOfElements)))
    )
)
;;removeOdd(listOfElements: List)
;;removeOdd(l1..ln){nil, n = 0
;;                  l1 U removeOdd(l1..ln), l1 % 2 = 0
;;                  removeOdd(l2..ln), otherwise
(defun removeOdd (listOfElements)
    (cond
        ((null listOfElements) nil)
        ((eq (mod (car listOfElements) 2) 0) (cons (car listOfElements) (removeOdd (cdr listOfElements))))
        (t (removeOdd (cdr listOfElements)))
    )
)

;; isMountain(listOfElement:List, flag:int)
;; Flag dictates what the current trend of the list is, 0 is for increasing and 1 is for decreasing
;; isMountain(l1..ln,flag){ false, flag = 0 and n <= 1
;;                               true,  flag = 1 and n <= 1
;;                               isMountain(l2..ln, 0), l1 <= l2 and flag = 0
;;                               isMountain(l2..ln, 1), l1 >= l2 and flag = 1
;;                               isMountain(l2..ln, 1), l1 > l2 and flag = 1
;;                               false, otherwise

(defun isMountain (listOfElements flag)
    (cond
        ((and (or (null listOfElements) (eq (nth 1 listOfElements) nil)) (eq flag 0)) nil)
        ((and (or (null listOfElements) (eq (nth 1 listOfElements) nil)) (eq flag 1)) t)
        ((and (eq flag 0) (< (car listOfElements) (nth 1 listOfElements)) (isMountain (cdr listOfElements) 0)))
        ((and (eq flag 0) (>=  (car listOfElements) (nth 1 listOfElements))) (isMountain (cdr listOfElements) 1))
        ((and (eq flag 1) (> (car listOfElements) (nth 1 listOfElements))) (isMountain (cdr listOfElements) 1))
    )
)
;;isMountainMain(listOfElements:list)
;;isMoutainMain(l1..ln) {   False, n = 0
;;                      isMountainMain(l1..ln, 0)
(defun isMountainMain (listOfElements)
    (cond
        ((null listOfElements) nil)
        (t (isMountain listOfElements 0))
    )
)
;;removeMountains(listOfelements: List)
;;removeMountains(l1..ln){nil, n = 0
;;                        removeOdd(l1) U removeMountains, l1 = list, ismountain(oddList(l1))
;;                        l1 U removeMountains(l2..ln), otherwise
(defun removeMountains (listOfElements)
    (cond
        ((null listOfElements) nil)
        ((and (listp (car listOfElements)) (isMountainMain (oddList (car listOfElements)))) (cons (removeOdd (car listOfElements)) (removeMountains (cdr listOfElements))))
        (t (cons (car listOfElements) (removeMountains (cdr listOfElements))))
    )
)

(print (removeMountains (list 1 2 3 (list 1 2 3 2 1) 1)))