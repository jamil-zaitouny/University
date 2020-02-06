;;nodeLevelMain(listOfElements:List, node:Atom, currentLevel)
;;nodeLevelMain(l1..ln, node){ 
;;                             nodeLevel(l1..ln, node, 0)            
(defun nodeLevelMain (listOfElements node)
    (nodeLevel listOfElements node 0)
)
;;nodeLevel(l1..ln, node, currentLevel){
;;                                      currentLevel, node == l1
;;                                      nodeLevel(l2..ln, node, currentLevel), l1 != node and l1 is not a list
;;                                      mapcar(#nodeLevel(elements node (currentLevel + 1)) listOfElements), otherwise    

(defun nodeLevel(listOfElements node currentLevel)
    (cond
        ((eq node (car listOfElements)) currentLevel)
        ((numberp (car listOfElements)) (nodeLevel (cdr listOfElements) node currentLevel))
        ((listp (car listOfElements)) (mapcar #'(lambda (elements) (nodeLevel elements node (+ 1 currentLevel))) listOfElements))
    )
    
)