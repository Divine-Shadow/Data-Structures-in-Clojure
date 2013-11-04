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
          (it "should work with empty lists"
              (should= (Cons. 10 nil) (insert-at-end 10 nil)))
	  (it "should work with lists that have data"
	      (let [xx (Cons. 10 (Cons. 20 (Cons. 30 nil)))]
	        (should= (Cons. 10 (Cons. 20 (Cons. 30 (Cons. 5 nil)))) (insert-at-end 5 xx))))
)

(describe "sorted insert"
          (it "should work with empty lists"
              (should= (Cons. 5 nil) (sorted-insert 5 nil)))
	  (it "should work with lists that have data"
	      (let [xx (Cons. 10 (Cons. 20 (Cons. 30 nil)))]
	        (should= (Cons. 10 (Cons. 20 (Cons. 25 (Cons. 30 nil)))) (sorted-insert 25 xx))))
          (it "should recycle memory"
	    (let [xx (Cons. 10 (Cons. 20 (Cons. 30 nil)))]
	        (should= true (identical? (:cdr (:cdr xx)) (:cdr (:cdr (:cdr (sorted-insert 25 xx))))))))

)

(describe "search"
          (it "should work with empty lists"
              (should= false (search 5 nil)))
	  (it "should work with lists that have data, and the number is in the list"
	      (let [xx (Cons. 5 (Cons. 10 (Cons. 15 (Cons. 20 nil))))]
  	        (should= true (search 15 xx))))
          (it "should work with lists that have data, but the number is not in the list"
	      (let [xx (Cons. 5 (Cons. 10 (Cons. 15 (Cons. 20 nil))))]
  	        (should= false (search 21 xx))))
)

(describe "delete"
          (it "should return nil for an empty list"
              (should= nil (delete 1 nil)))
	  (it "should return the list without the argument"
	    (let [xx (Cons. 1 (Cons. 2 (Cons. 3 nil)))]
	      (should= (Cons. 1 (Cons. 3 nil)) (delete 2 xx))))
	  (it "shouldn't remove all occurances"
	    (let [xx (Cons. 1 (Cons. 2 (Cons. 3 (Cons. 2 nil))))]
	      (should= (Cons. 1 (Cons. 3 (Cons. 2 nil))) (delete 2 xx))))
)

(describe "delete-all"
          (it "should return nil for an empty list"
              (should= nil (delete-all 1 nil)))
	  (it "should return the list without the argument"
	     (let [xx (Cons. 1 (Cons. 2 (Cons. 3 (Cons. 2 nil))))]
	        (should= (Cons. 1 (Cons. 3 nil)) (delete-all 2 xx))))
)

(describe "efficient-delete"
	(it "should return nil for an empty list"
              (should= nil (efficient-delete 1 nil)))
	  (it "should return the list without the argument"
	    (let [xx (Cons. 1 (Cons. 2 (Cons. 3 nil)))]
	      (should= (Cons. 1 (Cons. 3 nil)) (efficient-delete 2 xx))))
          (it "shouldn't create a new list if no deletion"
	    (let [xx (Cons. 1 (Cons. 2 (Cons. 3 nil)))]
	      (should= true (identical? xx (efficient-delete 4 xx)))))
)
(run-specs)
