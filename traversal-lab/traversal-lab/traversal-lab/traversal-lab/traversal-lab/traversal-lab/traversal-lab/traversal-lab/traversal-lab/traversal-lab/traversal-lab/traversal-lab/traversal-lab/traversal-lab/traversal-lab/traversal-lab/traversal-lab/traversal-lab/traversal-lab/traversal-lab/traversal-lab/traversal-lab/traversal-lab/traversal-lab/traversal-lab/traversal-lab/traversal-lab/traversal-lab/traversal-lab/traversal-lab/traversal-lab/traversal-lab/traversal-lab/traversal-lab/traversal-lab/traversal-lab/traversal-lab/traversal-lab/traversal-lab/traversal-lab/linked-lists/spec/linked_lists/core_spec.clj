(ns linked_lists.core-spec
  (:require [speclj.core :refer :all]
            [linked_lists.core :refer :all])
  (:import [linked_lists.core Cons]))

;; # The Tests
;;
;; We are going to use [spelj](https://github.com/slagyr/speclj) for our tests.


(describe "The record declaration"
          (it "should create something"
              (should (Cons. 10 20)))

          (it "should have a car"
              (should= 10 (:car (Cons. 10 20))))

          (it "should have a cdr"
              (should= 20 (:cdr (Cons. 10 20))))

          (it "should be chainable"
              (should= 40 (-> (Cons. 10 (Cons. 20 (Cons. 30 40))) :cdr :cdr :cdr))))

(describe "insert-at-beginning"
          (it "creates a cons cell"
              (should-not= nil (insert-at-beginning 10 nil)))

          (it "should work with empty lists"
              (should= (Cons. 10 nil) (insert-at-beginning 10 nil) ))
          
          (it "should work with lists that have data"
              (let [xx (Cons. 10 (Cons. 20 (Cons. 30 nil)))]
                (should= (Cons. 5 xx) (insert-at-beginning 5 xx) ))))

(describe "insert at the end"
          (it "should work for a list."
              (should = insert at the end(5 [1 2 3 4])))
	  (it "should work for an empty list"
	      (should = insert-at-end(5 nil)))
)
(describe "sorted insert"
          (it "should work for an empty list"
              (should =(sorted-insert(12 nil))))
	  (it "should work for a list"
	  	(should = (sorted-insert(3 [1 2 3 4 5 6 7 8]))))
)

(describe "search"
          (it "should return true if element is found."
              (should = (search(2 [1 2 3 4 5 2]))))
	  (it "should return false if element is not there"
	  	(should = (search(2 nil))))
)

(describe "delete"
          (it "should remove only one instant of the value."
              (should= (delete(2 [2 2 2 2 1]))))
	  (it "should do nothing if element is not there"
	  	(should =(delete(2 [1 3 4]))))
	  (it "should return nil for an empty list"
	  	(should = (delete( 1 nil))))
)

(describe "delete-all"
          (it "should delete all instants of elt"
              (should= (delete-all(10 [10 10 10 13 12]))))
	  (it "should return a nil for an empty list
	  	(should = (delete-all(10 nil))))
	  (it "should not delete if the element is not there"
	  	(should = (delee-all(2 [1 3 4 5]))))
)

(describe "efficient-delete"
          (it "should remove if the element is found."
              (should = (efficient-delete(2 [2 2 2 2]))))
	  (it "should return original list if element is not found"
	  	(should= (efficient-delete(1 [2 3 4 5 6]))))
	  (it "should return nil for an empty list"
	  	(should = (efficient-delete(1 nil))))
)
(run-specs)
