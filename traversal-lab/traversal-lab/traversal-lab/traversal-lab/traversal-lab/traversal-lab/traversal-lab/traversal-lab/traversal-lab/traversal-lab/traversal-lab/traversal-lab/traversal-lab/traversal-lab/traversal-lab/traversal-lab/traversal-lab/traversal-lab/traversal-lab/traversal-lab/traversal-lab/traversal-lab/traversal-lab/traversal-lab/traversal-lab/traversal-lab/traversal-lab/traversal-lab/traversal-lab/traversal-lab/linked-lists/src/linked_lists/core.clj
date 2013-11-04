(ns linked_lists.core)

;; # Introduction

;; # The List-----------

(defrecord Cons [car cdr])

;; # Insert at Beginning----------

(defn insert-at-beginning [elt xx] (Cons. elt xx))

;; # Insert at End-----------------

(defn insert-at-end [elt xx]
  (cond (empty? xx) (Cons. elt nil)
   :else (Cons. (:car xx) (insert-at-end elt (:cdr xx)))))

;; # Sorted Insert--------------

(defn sorted-insert [elt xx]
   (cond (empty? xx) (Cons. elt nil)
   (< elt (:car xx)) (Cons. elt xx)
   :else (Cons. (:car xx) (sorted-insert elt (:cdr xx)))))

;; # Search----------------
;;
(defn search [elt xx]
  (cond (empty? xx) false
   (= (:car xx) elt) true
   :else (search elt (:cdr xx))))

;; # Deletion--------------
;;
(defn delete [elt xx]
     (cond (empty? xx) nil
     (= (:car xx) elt) (:cdr xx)
     :else (Cons. (:car xx) (delete elt (:cdr xx)))))

;; # Delete all----------

(defn delete-all [elt xx]
  (cond (empty? xx) xx
  (= (:car xx) elt) (delete-all elt (:cdr xx))
  :else (Cons. (:car xx) (delete-all elt  (:cdr xx)))))

;; # Memory efficient delete------------

(defn efficient-delete [elt xx] 
  (cond (= (search elt xx) false) xx
  (empty? xx) nil
  (= (:car xx) elt) (:cdr xx)
  :else (Cons. (:car xx) (efficient-delete elt (:cdr xx)))))
