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
  nil)


;; # Insert at End

(defn insert-at-end 
  "Insert an element at the end of the list.  This will have to recopy
  the whole list."
  [elt xx]
  nil)

;; # Sorted Insert

(defn sorted-insert
  "Insert an element into a sorted list."
  [elt xx]
  nil)

;; # Search
;;
;; We name it `search` instead of `find` to avoid colliding with the built-in Clojure function.

(defn search 
  "Checks if `elt` is in `xx`."
  [elt xx]
  nil)

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
  "Remove one copy of an element from the list.  This does not assume
  that the list was sorted."
  [elt xx]
     nil)

;; # Delete all

(defn delete-all 
  "Delete all copies of elt from xx."
  [elt xx]
  nil)

;; # Memory efficient delete

(defn efficient-delete
  "Delete a copy of elt from xx, but if elt is not in xx, return the
  *original* xx instead of a copy.  It is acceptable to prescan the
  list."
  [elt xx] 
  nil)


