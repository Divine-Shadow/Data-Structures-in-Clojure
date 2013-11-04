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
          (it "creates a cons cell."
              (should-not= nil (insert-at-end 10 nil)))

          (it "should work with empty lists"
              (should= (Cons. 10 nil) (insert-at-end 10 nil)))

          (it "should work with lists that have data"
              (let [xx (Cons. 10 (Cons. 20 (Cons. 30 nil)))]
                (should= (Cons. 10 (Cons. 20 (Cons. 30 (Cons. 5 nil)))) (insert-at-end 5 xx)))))
              


(describe "sorted insert"
          (it "Should work with empty lists"
              (should= (Cons. 2 nil) (sorted-insert 2 nil)))
          (it "creates a cons cell"
              (should-not= nil (sorted-insert 10 nil)))
          (it "should should sort through the list"
              (let [xx (Cons. 5 (Cons. 6 (Cons. 8 nil)))]
                (should= (Cons. 5 (Cons. 6 (Cons. 7 (Cons. 8 nil)))) (sorted-insert 7 xx))))
)

(describe "search"
          (it "Should return false if list is empty."
              (should= false (search 5 nil)))
          (it "Should return true if elt is in the list"
              (let [xx (Cons. 1 (Cons. 3 (Cons. 5 nil)))]
                (should= true (search 3 xx))))
          (it "should work with lists that has data and elt not found"
              (let [xx (Cons. 10 (Cons. 20 (Cons. 30 nil)))]
                (should= false (search 50 xx))))
)
(describe "delete"
          (it "Should retun nil with empty lists."
              (should= nil (delete 5 nil)))
          (it "Should delete the selected elt."
              (let [xx (Cons. 2 (Cons. 4 (Cons. 6 nil)))]
                (should= (Cons. 2 (Cons. 4 nil)) (delete 6 xx))))
)

(describe "delete-all"
          (it "Should work with empty lists."
              (should= nil (delete-all 2 nil)))
          (it "should delete all copies of the elt"
              (let [xx (Cons. 1 (Cons. 2 (Cons. 2 (Cons. 3 (Cons. 4 nil)))))]
                (should= (Cons. 1 (Cons. 3 (Cons. 4 nil))) (delete-all 2 xx))))
          (it "should return the original list is elt was not found"
              (let [xx (Cons. 7 (Cons. 9 (Cons. 11 nil)))]
                (should= xx (delete-all 10 xx))))
)

(describe "efficient-delete"
          (it "Should work with empty lists."
              (should= nil (efficient-delete 5 nil)))
          (it "should delete the element if it finds it"
              (let [xx (Cons. 1 (Cons. 2 (Cons. 3 nil)))]
                (should= (Cons. 1 (Cons. 3 nil)) (efficient-delete 2 xx))))
          (it "should return the list if the element is not there"
              (let [xx (Cons. 4 (Cons. 6 (Cons. 8 nil)))]
                (should= xx (efficient-delete 5 xx))))
)
(run-specs)
