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
          (it "should work with lists that have data"
              (let [xx (Cons. 10 (Cons. 20 (Cons. 30 nil)))]
                (should= (Cons. 10 (Cons. 20 (Cons. 30 (Cons. 5 nil)))) (insert-at-end 5 xx))))
)
(describe "sorted insert"
          (it "should insert data into lists with data"
              (let [xx (Cons. 1 (Cons. 2 (Cons. 4 nil)))]
                    (should= (Cons. 1 (Cons. 2 (Cons. 3 (Cons. 4 nil)))) (sorted-insert 3 xx))))
)

(describe "search"
          (it "should return truthy if found"
              (let [xx (Cons. 2 (Cons. 3 nil))]
                (should-not=  nil (search 3 xx))))
)

(describe "delete"
          (it "should remove one element from a list"
              (let [xx (Cons. 1 (Cons. 2 (Cons. 2 (Cons. 3 nil))))]
                (should= (Cons. 1 (Cons. 2 (Cons. 3 nil))) (delete 2 xx))))
)

(describe "delete-all"
          (it "should remove ALL same elements from a list."
               (let [xx (Cons. 1 (Cons. 2 (Cons. 2 (Cons. 3 nil))))]                            
                (should= (Cons. 1 (Cons. 2(Cons. 3 nil))) (delete 2 xx))))
)

(describe "efficient-delete"
          (it "should delete a copy of the element."
             (let [xx (Cons. 1 (Cons. 2 (Cons. 2 (Cons. 3 nil))))]                           
               (should= (Cons. 1 (Cons. 2 (Cons. 3 nil))) (delete 2 xx)) )
))
(run-specs)
