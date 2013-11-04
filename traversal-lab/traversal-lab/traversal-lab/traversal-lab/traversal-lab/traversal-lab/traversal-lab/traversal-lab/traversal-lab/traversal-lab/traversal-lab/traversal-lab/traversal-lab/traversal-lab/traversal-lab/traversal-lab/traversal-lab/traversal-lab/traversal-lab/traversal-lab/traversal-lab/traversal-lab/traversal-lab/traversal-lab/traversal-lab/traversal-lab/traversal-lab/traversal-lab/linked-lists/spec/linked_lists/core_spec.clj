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
          (it "creates a cons cell"
              (should-not = nil(insert-at-end 10 nil)))
	      
	  (it "should work with empty lists"
	       (should = (Cons. 10 nil) (insert-at-end 10 nil))
	       
	  (it "should work with lists that have data"
	       (let [xx ( Cons. 20 ( Cons. 30 nil))) ]
	       (should= ( Cons. 5 xx) (insert-at-end 5 xx) ))))
)

(describe "sorted insert"
          (it "should create a cons cell"
              (should-not =  nil(sorted-insert 10 nil))
	  
	  (it "should work with empy lists"
	       (should = (Cons. 10 nil) (sorted-insert 10 ni))
	       
	  (it "should work with lists that have data" 
	       (let [xx ( Cons. 20 ( Cons. 30 nil ))) ]
	       (should =  (Cons. 5 xx) sorted-insert 5 xx)))))

(describe "search"
          (it "should work with empty lists."
              (should = (Cons. 10 nil) (search 10 nil))
	      
	  (it "should work with lists that have data but does not contain the element being searched for"
	       (let [xx ( Cons. 20 ( Cons. 30 nil)))]
	       (should = (Cons. 5 xx) search 5 xx))
	       
	   (it "should work with lists that have the data and contains the element being searched for"
	        (let [xx ( Cons. 20 ( Cons.30 nil)))]
		(should = (Cons. 30)search 30 xx))))
)

(describe "delete"
(it "should work with empty lists."
	               (should = (Cons. 10 nil) (delete 10 nil))
)

(describe "delete-all"
          (it "never had a test case."
              (should nil))
)

(describe "efficient-delete"
          (it "never had a test case."
              (should nil))
)
(run-specs)
