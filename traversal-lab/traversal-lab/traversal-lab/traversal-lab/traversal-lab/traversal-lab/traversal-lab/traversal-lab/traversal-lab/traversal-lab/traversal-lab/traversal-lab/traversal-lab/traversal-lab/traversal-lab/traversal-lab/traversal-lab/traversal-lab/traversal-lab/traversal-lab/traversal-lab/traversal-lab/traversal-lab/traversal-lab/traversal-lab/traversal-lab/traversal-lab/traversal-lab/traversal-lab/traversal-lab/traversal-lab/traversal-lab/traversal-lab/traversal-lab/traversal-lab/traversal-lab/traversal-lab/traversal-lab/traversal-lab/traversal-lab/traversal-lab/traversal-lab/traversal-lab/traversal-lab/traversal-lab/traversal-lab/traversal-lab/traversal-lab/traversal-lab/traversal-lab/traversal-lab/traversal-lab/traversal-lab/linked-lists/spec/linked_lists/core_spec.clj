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

          (it "should work with empty lists"
              (should= (Cons. 10 nil) (insert-at-end 10 nil)))

         (it "should work with lists that have data"
              (let [xx (Cons. 10 (Cons. 20 (Cons. 30 nil)))]
                (should-not= (:cdr (Cons. 30 nil)) (insert-at-end 40 xx))))
)

(describe "sorted insert"
          (it "creates a cons cell"
              (should-not= nil (sorted-insert 10 nil)))
          (it "should work with empty lists"
              (should= (Cons. 10 nil) (sorted-insert 10 nil)))
          (it "recycles the memory"
              (let [xx (Cons. 10 (Cons. 20 (Cons. 30 nil)))]
              (should (identical? (:cdr xx ) (:cdr (:cdr (sorted-insert 15 xx)))))))
          (it "should work with lists that have data"
              (let [xx (Cons. 10 (Cons. 20 (Cons. 30 nil)))]
               (should= (Cons. 10 (Cons. 15 (Cons. 20 (Cons. 30 nil)))) (sorted-insert 15 xx)))))

(describe "search"
	  (it "should return the element"
	      (let [xx (Cons. 10 (Cons. 20 (Cons. 30 nil)))]
	      	(should= true (search 10 xx))))
	  (it "should return nil"
	      (let [xx (Cons. 10 (Cons. 20 (Cons. 30 nil)))]
	        (should= false (search 40 xx))))
)

(describe "delete"
          (it "should return without the deleted element"
              (let [xx (Cons. 10 (Cons. 20 (Cons. 30 nil)))]
<<<<<<< HEAD
              (should= (Cons. 10 (Cons. 30 nil)) (delete 20 xx)))))
           (it "should not return the same list"
              (let [xx (Cons. 10 (Cons. 20 (Cons. 30 nil)))]
              (should-not= xx (delete 20 xx))))
=======
              (should= (Cons. 10 (Cons. 30 nil)) (delete 20 xx))))
          (it "should not delete every element"
              (let [xx (Cons. 10 (Cons. 20 (Cons. 30 (Cons. 20 nil))))]
              (should= (Cons. 10 (Cons. 30 (Cons. 20 nil))) (delete 20 xx))))
           (it "should not return the same list"
              (let [xx (Cons. 10 (Cons. 20 (Cons. 30 nil)))]
              (should-not= xx (delete 20 xx)))))
>>>>>>> c3bba9936b2a14d8fd4e8c7c57837b236daec793

(describe "delete-all"
          (it "should delete every number of that kind in the list"
              (let [xx (Cons. 10 (Cons. 20 (Cons. 30 (Cons. 40 (Cons. 20 nil)))))]
                (should= (Cons. 10 (Cons. 30 (Cons. 40 nil))) (delete-all 20 xx))))
)

(describe "efficient-delete"
<<<<<<< HEAD
          (it "should return nil if search return false"
              (let [xx (Cons. 10 (Cons. 20 (Cons. 30 (Cons. 40 (Cons. 50 nil)))))]
                (should= xx (efficient-delete 60 xx))))
=======
          (it "should return the list if search return false"
              (let [xx (Cons. 10 (Cons. 20 (Cons. 30 (Cons. 40 (Cons. 50 nil)))))]
                (should (identical? xx (efficient-delete 60 xx)))))

>>>>>>> c3bba9936b2a14d8fd4e8c7c57837b236daec793
)
(run-specs)
