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

(defn find-last [xx]
  (if (= (:cdr xx) nil) (:car xx) 
    (find-last (rest xx))))

(defn find-nth [n xx]
  (if (= n 0) (:car xx)
    (find-nth (- n 1) xx)))


;; # Insert at Beginning
;;
;; To insert at the beginning of the list, we create
;; a new cons cell and point it to the target list.

(defn insert-at-beginning [elt xx]
  (Cons. elt xx))

;; # Insert at End

(defn insert-at-end 
  [elt xx]
  (cond (empty? xx) (Cons. elt nil)
        (= (:cdr xx) nil) (Cons. (:car xx) (Cons. elt nil))
        :else (Cons. (:car xx) (insert-at-end elt (:cdr xx))))
)
;; # Sorted Insert

(defn sorted-insert
  [elt xx]
  (cond (empty? xx) (Cons. elt nil)
        (< elt (:car xx)) (Cons. elt xx)
	:else (Cons. (:car xx) (sorted-insert elt (:cdr xx))))
)

;; # Search
;;
;; We name it `search` instead of `find` to avoid colliding with the built-in Clojure function.

(defn search 
  "Checks if `elt` is in `xx`."
  [elt xx]
  (cond (empty? xx) false
      (= (:car xx) elt) true
      :else (search elt (:cdr xx)))
)

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

(defn delete
  [elt xx]
     (cond (empty? xx) nil
       (and (empty? (:cdr xx)) (= (:car xx) elt)) nil
       (= (:car xx) elt) (Cons. (:car (:cdr xx)) (:cdr (:cdr xx)))
       :else (Cons. (:car xx)(delete elt (:cdr xx))))
)

;; # Delete all

(defn delete-all 
  [elt xx]
  (cond (empty? xx) nil
    (and (empty? (:cdr xx)) (= (:car xx) elt)) nil
    (= (:car xx) elt) (Cons. (:car (:cdr xx)) (delete-all elt (:cdr (:cdr xx))))
    :else (Cons. (:car xx) (delete-all elt (:cdr xx))))
)

;; # Memory efficient delete

(defn scan-for [elt xx]
  (cond (empty? xx) false
    (= (:car xx) elt) true
    :else (scan-for elt (:cdr xx))
  )
)

(defn efficient-delete
  "Delete a copy of elt from xx, but if elt is not in xx, return the
  *original* xx instead of a copy.  It is acceptable to prescan the
  list."
  [elt xx]
  (if  (scan-for elt xx) (delete elt xx)
    xx
  )
)



