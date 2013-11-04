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
              (should-not= nil (insert-at-end 10 nil)))
          (it "should work with lists that have data"
	    (let [xx (Cons. 10 (Cons. 20 (Cons. 30 nil)))]
	    (let [yy (Cons. 10 (Cons. 20 (Cons. 30 (Cons. 40 nil))))]
              (should= (insert-at-end 40 xx) yy)))))

(describe "sorted insert"
          (it "creates a cons cell"
              (should-not= nil (sorted-insert 10 nil)))
          (it "should work with lists that have data."
	      (let [yy (Cons. 10 (Cons. 20 (Cons. 30 (Cons. 40 nil))))]
              (let [xx (Cons. 10 (Cons. 20 (Cons. 40 nil)))]
	      (should= (sorted-insert 30 xx) yy))))
)

(describe "search"
          (it "should return true if the number is in the list."
	      (let [xx (Cons. 10 (Cons. 20 (Cons. 30 (Cons. 40 nil))))]
              (should= (search 40 xx) true)))
          (it "should return false if the number is not in the list."
	      (let [xx (Cons. 10 (Cons. 20 (Cons. 30 (Cons. 40 nil))))]
              (should= (search 4 xx) false)))
)

(describe "delete"
          (it "should not delete anything if the value is not in the list."
	      (let [xx (Cons. 10 nil)]
	      (should= (delete 1 xx) xx)))
          (it "should delete a value from a full list."
	      (let [xx (Cons. 10 (Cons. 20 (Cons. 30 (Cons. 40 nil))))]
              (let [yy (Cons. 10 (Cons. 20 (Cons. 40 nil)))]
	      (should= (delete 30 xx) yy))))
	  (it "should only delete one value from a list."
	      (let [xx (Cons. 10 (Cons. 20 (Cons. 30 (Cons. 40 (Cons. 30 nil)))))]
              (let [yy (Cons. 10 (Cons. 20 (Cons. 40 (Cons. 30 nil))))]
	      (should= (delete 30 xx) yy))))
)

(describe "delete-all"
          (it "should not delete anything if the value is not in the list."
	      (let [xx (Cons. 10 nil)]
	      (should= (delete-all 1 xx) xx)))
          (it "should delete all values in linked list."
	      (let [xx (Cons. 10 (Cons. 20 (Cons. 30 (Cons. 40 (Cons. 30 nil)))))]
              (let [yy (Cons. 10 (Cons. 20 (Cons. 40 nil)))]
	      (should= (delete-all 30 xx) yy))))
)

(describe "efficient-delete"
          (it "return list when the value is not in the list."
	      (let [xx (Cons. 10 (Cons. 20 (Cons. 30 (Cons. 40 nil))))]
              (should= (efficient-delete 2 xx) xx)))
	  (it "should delete a value from a full list."
	      (let [xx (Cons. 10 (Cons. 20 (Cons. 30 (Cons. 40 nil))))]
              (let [yy (Cons. 10 (Cons. 20 (Cons. 40 nil)))]
	      (should= (efficient-delete 30 xx) yy))))
	  (it "should only delete one value from a list."
	      (let [xx (Cons. 10 (Cons. 20 (Cons. 30 (Cons. 40 (Cons. 30 nil)))))]
              (let [yy (Cons. 10 (Cons. 20 (Cons. 40 (Cons. 30 nil))))]
	      (should= (efficient-delete 30 xx) yy))))
)

(run-specs)
