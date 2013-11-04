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

(describe "insert-at-end"
          (it "creates a con cell"
              (should-not= nil (insert-at-end 5 nil)))

	  (it "should work with empty lists"
	      (should= (Cons. 10 nil) (insert-at-end 10 nil)))

	  (it "should work with lists that have data"
	    (let [xx (Cons. 10 (Cons. 20 (Cons. 30 nil)))]
	      (should= (Cons. 10 (Cons. 20 (Cons. 30. (Cons. 5 nil)))) (insert-at-end 5 xx))))
)

(describe "sorted- insert"
          (it "creates a con cell"
              (should-not= nil (sorted-insert 5 nil)))

	  (it "should work with empty lists"
              (should= (Cons. 10 nil) (sorted-insert 10	nil)))

          (it "should work with lists that have data"
	    (let [xx (Cons. 1 (Cons. 2 (Cons. 30 nil)))]
              (should= (Cons. 1(Cons. 2 (Cons. 5 (Cons. 30 nil)))) (sorted-insert 5 xx))))
            
)

(describe "search"
          (it "should return nil on an empty list"
              (should = nil (search 5 nil)))

	  (it "should return nil on a list not containing elt"
	    (let [xx (Cons. 10 (Cons. 20 (Cons. 30 nil)))]
	      (should= nil (search 5 xx))))

	  (it "should return true on a list containin elt"
	    (let [xx (Cons. 10 (Cons. 20 (Cons. 30 nil)))]
	      (should= true (search 10 xx))))
)

(describe "delete"
          (it "should return nil for an empty list"
	      (should= nil (delete 5 nil)))

	  (it "should return nil on a list not contain elt"
	    (let [xx (Cons. 10 (Cons. 20 (Cons. 30 nil)))]
	      (should= nil (delete 5 xx))))

	  (it "should return the list with a single copy of elt"
	    (let [xx (Cons. 10 (Cons. 20 (Cons. 30 nil)))]
	      (should = (Cons. 10 (Cons. 30 nil)) (delete 20 xx))))
)

(describe "delete-all"
          (it "should return nil for an empty list"
	      (should= nil (delete-all 5 nil)))

	  (it "should return nil for a list not containing elt"
	    (let [xx (Cons. 10 (Cons. 20 (Cons. 30 nil)))]
	      (should = nil (delete-all 5 xx))))

	  (it "should return the list with all elements equal to elt deleted for a list containing elt"
	    (let [xx (Cons. 10 (Cons. 20 (Cons. 30 (Cons. 20 nil ))))]
	      (should = (Cons. 10 (Cons. 30 nil)) (delete-all 20 xx))))
)

(describe "efficient-delete"
          (it "should return nil for an empty list"
	      (should = nil (efficient-delete 5 nil)))

	  (it "should return the original list xx if elt is not found in xx"
	    (let [xx (Cons. 10 (Cons. 20 (Cons. 30 nil)))]
	      (should= (identical? xx (Cons. 10 (Cons. 20 (Cons. 30 nil))))  (efficient-delete 5 xx))))

	  (it "should return a list with a single element elt deteled from the list if the list contains elt"
	    (let [xx (Cons. 10 (Cons. 20 (Cons. 30 nil)))]
	      (should= (Cons. 10 (Cons. 30 nil)) (efficient-delete 20 xx))))
)
(run-specs)
