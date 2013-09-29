
(ns linked_lists.core)

;; # Introduction
;;
;; We are going to implement singly-linked lists using records.
;; They are not going to be as full-featured as Clojure's lists,
;; but that's not the point.
;;
;; The student will be given the record definition and a few sample
;; functions, and be asked to implement five more functions.

;; # The List
;;
;; The functions `first`, `rest`, and `next` are already taken.
;; We could override them, but it is likely that our own test cases
;; will want to use Clojure's built-in lists for comparison.  So
;; we will use the historic names.
;;
;; + `Cons` is the name of a pair.
;; + `car` is the name of the data element
;; + `cdr` is the name of the pointer to the next element
;;
;; We will use `nil` to represent an empty list.

(defrecord Cons [car cdr])


;; # Insert at Beginning
;;
;; To insert at the beginning of the list, we create
;; a new cons cell and point it to the target list.

(defn insert-at-beginning 
  "Create a new Cons with element `elt` and list `xx`."
  [elt xx]
  (Cons. elt xx))


;; # Insert at End

(defn insert-at-end 
  "Insert an element at the end of the list.  This will have to recopy
  the whole list."
  [elt xx] 

(if (= xx nil) (Cons. elt nil) 
(if (= (:cdr xx) nil) (Cons. (:car xx) (Cons. elt nil))
(Cons. (:car xx) (insert-at-end  elt (:cdr xx))))
)


)

;;(defn partial-reconstruct-with-append [iterations xx elt] 
;;(if (= iterations 0) elt
;;(Cons. (:car xx) (partial-reconstruct-with-append (- iterations 1) (:cdr xx) elt))))  


;; # Sorted Insert

(defn sorted-insert
  "Insert an element into a sorted list."
    [elt xx] 
(if (= xx nil) (Cons. elt nil);; if xx is empty, put the element up front
    (if (= (:car xx) nil) (if (> xx elt) (Cons. elt xx) (Cons. xx elt));;if there is only one element, make the cell
;; otherwise
        (if (> (:car xx) elt) (Cons. elt xx);;if its smaller than the first element do the obvious thing
;; otherwise
            (Cons. (:car xx) (sorted-insert elt (:cdr xx)))
            ;;(if (= (:car (:cdr xx)) nil);;if we are on the second to last cell
;; cdr being nil implies that xx is a number and not a list, and that it is a direct input
              ;;(if (= (:cdr xx) nil) 
                ;;(if (> xx elt) (Cons. elt xx) (Cons. xx elt))
                ;;(Cons. xx elt))
              ;;(if (> (:car (:cdr xx)) elt) (Cons. (:car xx) (Cons. elt (:cdr xx)))
                  ;;(Cons. (:car xx) (sorted-insert elt (:cdr xx)))))
            )
        )
    )
)
;; # Search
;;
;; We name it `search` instead of `find` to avoid colliding with the built-in Clojure function.

(defn search 
 ;; "Checks if `elt` is in `xx`."
[elt xx]
(if (= (:car xx) nil) (= xx elt)
(if (= elt (:car xx)) true (search elt (:cdr xx) 


))))

;;old version
;;  [elt xx]
;;  (if (= xx nil) nil
 ;; (if (= (:car xx) elt) 0 (do (def m (search elt (:cdr xx ))) (if (= m nil) nil (+ 1 m)))))


;; # Deletion
;;
;; We provide three versions of delete
;;
;; 1. Standard delete, which just deletes one copy
;; 1. Delete-all, which deletes everything, and
;; 1. efficient-delete, which returns the original list
;;    when the element we are trying to delete doesn't
;;    exist in the list.  Note: this is *memory* efficient,
;;    but we pay for this with *time* efficiency.

;;an auxilaty to the delete function, reconstruct reconstructs a list
(defn resconstruct [elt xx] )

(defn delete
  "Remove one copy of an element from the list.  This does not assume
  that the list was sorted."
  [elt xx]
 
(if (= xx nil) nil 
(if (= elt (:car xx)) (:cdr xx) (Cons. (:car xx) (delete elt (:cdr xx))))
)
)



; (if (= (:cdr (:cdr xx)) nil) (if (= elt (:cdr xx)) (:car xx) (if (= (:car xx) elt) (:cdr xx) xx ))
;(if (= (:car xx) elt) (:cdr xx) 
;(Cons. (:car xx) (delete elt (:cdr xx)))
;)))
;;
;;     old v
;;(if nil (do (def p (search elt xx)) p) xx (Cons.
 ;;(last (take (+ p 1) (iterate :cdr xx))))) 
;; # Delete all

(defn delete-all 
  "Delete all copies of elt from xx."
  [elt xx]
  
;(if (= (:cdr (:cdr xx)) nil) (if (= elt (:cdr xx)) (:car xx) (if (= (:car xx) elt) (:cdr xx) xx ))
;(if (= (:car xx) elt) (delete-all elt (:cdr xx)) 
;(Cons. (:car xx) (delete-all elt (:cdr xx)))
;)))


(if (= xx nil) nil 
    (if (= elt (:car xx)) (delete-all elt (:cdr xx)) (Cons. (:car xx) (delete-all elt (:cdr xx))))
)
)


;; # Memory efficient delete

(defn efficient-delete
  "Delete a copy of elt from xx, but if elt is not in xx, return the
  *original* xx instead of a copy.  It is acceptable to prescan the
  list."
[elt xx] 
(if (search elt xx) (delete elt xx) xx)
)
;(if (= (:cdr (:cdr xx)) nil) (if (= elt (:cdr xx)) (:car xx) (if (= (:car xx) elt) (:cdr xx) xx ))
;(if (= (:car xx) elt) (:cdr xx) 
;(Cons. (:car xx) (delete elt (:cdr xx)))
;))  
 
;xx)

;)



