(ns linked_lists.core-spec
  (:require [speclj.core :refer :all]
            [linked_lists.core :refer :all])
  (:import [linked_lists.core Cons]))

;; # The Tests ;; ;; We are going to use
;;[spelj](https://github.com/slagyr/speclj) for our tests.


(describe "The record declaration"
          (it "should create something"
              (should (Cons. 10 20)))

          (it "should have a car"
              (should= 10 (:car (Cons. 10 20))))

          (it "should have a cdr"
              (should= 20 (:cdr (Cons. 10 20))))

          (it "should be chainable"
              (should= 40 (-> (Cons. 10 (Cons. 20 (Cons. 30 40))) :cdr
              :cdr :cdr))))

(describe "insert-at-beginning"
          (it "creates a cons cell"
              (should-not= nil (insert-at-beginning 10 nil)))

          (it "should work with empty lists"
              (should= (Cons. 10 nil) (insert-at-beginning 10 nil) ))

          (it "should work with lists that have data"
              (let [xx (Cons. 10 (Cons. 20 (Cons. 30 nil)))]
                (should= (Cons. 5 xx) (insert-at-beginning 5 xx)))))

(describe "insert-at-end"
	  (it "should work with lists that have data"
      	      (should= (Cons. 5 (Cons. 10 (Cons. 2 nil))) (insert-at-end 2 (Cons. 5 (Cons. 10 nil)))))

	  (it "should work with empty lists"
	      (should= (Cons. 2 nil) (insert-at-end 2 nil))))


(describe "sorted insert"
	  (it "should work with lists that have data"
	      (should= (Cons. 5 (Cons. 10 (Cons. 20 nil))) (sorted-insert 10 (Cons. 5 (Cons. 20 nil)))))

	  (it "should work with empty lists"
	      (should= (Cons. 2 nil) (sorted-insert 2 nil)))

	  (it "should create a pointer to the remaining of the list after inserting the element"
	      (let [xx (Cons. 1 (Cons. 2 (Cons. 4 nil)))]
	        (should= true (identical? (:cdr (:cdr xx)) (:cdr (:cdr (:cdr (sorted-insert 3 xx))))))))) 


(describe "search"
          (it "should work with empty lists"
	      (should= false (search 2 nil)))

	  (it "should declare if an element is in a list"
	      (should= true (search 5 (Cons. 4 (Cons. 5 (Cons. 3 nil))))))

	  (it "should declare if an element is not in a list"
	      (should= false (search 5 (Cons. 4 (Cons. 2 (Cons. 3 nil)))))))


(describe "delete"
          (it "should work with an empty list"
	      (should= nil (delete 3 nil)))

	  (it "should work with a list that has data if the element is not found"
	      (let [xx (Cons. 5 (Cons. 6 (Cons. 4 nil)))]
	        (should= xx (delete 3 xx))))

	  (it "should work with a list that has data if the element is found"
	      (let [xx (Cons. 5 (Cons. 6 (Cons. 4 nil)))]
	        (should= (Cons. 5 (Cons. 4 nil)) (delete 6 xx)))))


(describe "delete-all"
	  (it "should work with an empty list"
	      (should= nil (delete-all 3 nil)))

	  (it "should work with a list that has data if the element is not found"
	      (let [xx (Cons. 5 (Cons. 6 (Cons. 4 nil)))]
	        (should= xx (delete-all 3 xx))))

	  (it "should work with a list that has data if the element is found"
	      (let [xx (Cons. 5 (Cons. 6 (Cons. 5 (Cons. 2 nil))))]
	        (should= (Cons. 6 (Cons. 2 nil)) (delete-all 5 xx)))))

(describe "efficient-delete"
	  (it "should work with an empty list"
	      (should= nil (efficient-delete 3 nil)))

	  (it "should work with a list that has data if the element is not found"
	      (let [xx (Cons. 5 (Cons. 6 (Cons. 5 (Cons. 2 nil))))]
	        (should= true (identical? xx (efficient-delete 1 xx)))))

	  (it "should work with a list that has data if the element is found"
	      (let [xx (Cons. 1 (Cons. 2 (Cons. 3 (Cons. 2 nil))))]
	        (should= (Cons. 1 (Cons. 3 (Cons. 2 nil))) (efficient-delete 2 xx)))))



(run-specs)
