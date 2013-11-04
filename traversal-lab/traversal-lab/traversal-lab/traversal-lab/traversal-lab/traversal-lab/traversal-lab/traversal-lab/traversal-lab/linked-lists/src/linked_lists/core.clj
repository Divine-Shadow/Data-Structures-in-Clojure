(ns linked_lists.core
  (:require clojure.test))

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

(defrecord Cons [car cdr])

;; # Insert at Beginning
;;
;; To insert at the beginning of the list, we create
;; a new cons cell and point it to the target list.

(defn insert-at-beginning "insert item at beginning" [elt xx] 
  (cond(empty? xx)(Cons. elt nil)
    :else (Cons. elt xx)))

;; # Insert at End

(defn insert-at-end [elt xx]
  (cond (= nil xx) (Cons. elt nil) 
    :else (Cons. (:car xx) (insert-at-end elt (:cdr xx)))))

;; # Sorted Insert

(defn sorted-insert [elt xx]
  (cond (= nil xx) (Cons. elt nil) 
    (< elt (:car xx)) (Cons. elt xx)
    :else (Cons. (:car xx) (sorted-insert elt (:cdr xx)))))

;; # Search
;
;; We name it `search` instead of `find` to avoid colliding with the built-in Clojure function.

(defn search [elt xx] 
  (cond (= nil xx) false 
    (= elt (:car xx)) true 
    :else (search elt (:cdr xx))))

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

(defn delete [elt xx]
  (cond(= nil xx) nil
    (= elt (:car xx)) (:cdr xx) 
    :else (Cons. (:car xx) (delete elt (:cdr xx)))))

;; # Delete all

(defn delete-all [elt xx]
  (cond (= nil (:car xx)) nil 
    (= elt (:car xx)) (delete-all elt (:cdr xx))
    :else (Cons. (:car xx) (delete-all elt (:cdr xx)))))

;; # Memory efficient delete

(defn efficient-delete [elt xx]
  (cond (search elt xx) (delete elt xx) 
    :else xx)) 

