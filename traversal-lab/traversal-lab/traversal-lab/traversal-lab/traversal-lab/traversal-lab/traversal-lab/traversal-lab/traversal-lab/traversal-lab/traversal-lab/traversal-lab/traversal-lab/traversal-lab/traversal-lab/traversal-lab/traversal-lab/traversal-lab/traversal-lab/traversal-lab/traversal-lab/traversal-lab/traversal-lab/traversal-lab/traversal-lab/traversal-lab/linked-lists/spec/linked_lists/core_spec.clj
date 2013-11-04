(ns linked_lists.core-spec
  (:require [speclj.core :refer :all]
            [linked_lists.core :refer :all])
  (:import [linked_lists.core Cons]))

;; # The Tests
;;
;; We are going to use [spelj](https://github.com/slagyr/speclj) for our tests.

(def mylist (Cons. 1 (Cons. 2 (Cons. 3 (Cons. 4 (Cons. 5 nil))))))
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
                (should= (Cons. 5 xx) (insert-at-beginning 5 xx)))))

(describe "insert-at-end"
          (it "should insert at the end."
              (should= (Cons. 1 (Cons. 2 nil)) (insert-at-end 2 (Cons. 1 nil))))
)

(describe "sorted-insert"
          (it "should insert in the right place."
              (should= (Cons. 1 (Cons. 2 (Cons. 3 nil))) (sorted-insert 2 (Cons. 1 (Cons. 3 nil)))))
          (it "should insert at the end if greater than all elements."
              (should= (Cons. 1 (Cons. 2 (Cons. 3 nil))) (sorted-insert 3 (Cons. 1 (Cons. 2 nil)))))
          (it "should recycle the list"
              (should (identical? (:cdr mylist) (:cdr (:cdr (sorted-insert 1 mylist))))))
)

(describe "search"
          (it "returns true if it finds anything"
              (should (search 3 (Cons. 1 (Cons. 2 (Cons. 3 (Cons. 4 nil)))))))
          (it "should return nil if it doesn't find anything"
              (should-not (search 6 (Cons. 1 (Cons. 2 (Cons. 3 (Cons. 4 nil)))))))
)

(describe "delete"
          (it "should delete exactly one of the proper value."
              (should= (Cons. 1 (Cons. 2 nil)) (delete 2 (Cons. 1 (Cons. 2 (Cons. 2 nil))))))
          (it "should not delete more than one."
              (should-not= (Cons. 1 nil) (delete 2 (Cons. 1 (Cons. 2 (Cons. 2 nil))))))
)

(describe "delete-all"
          (it "should delete all of the correct value."
              (should= (Cons. 1 (Cons. 3 (Cons. 4 nil))) 
                  (delete-all 2 (Cons. 1 (Cons. 2 (Cons. 3 (Cons. 2 (Cons. 4 nil))))))))
          (it "should delete one of the proper value." 
                  (should= (Cons. 1 (Cons. 3 nil)) (delete 2 (Cons. 1 (Cons. 2 (Cons. 3 nil))))))
)

(describe "efficient-delete"
          (it "should delete"
              (should= (Cons. 1 (Cons. 3 nil)) (efficient-delete 2 (Cons. 1 (Cons. 2 (Cons. 3 nil))))))
          (it "should return the same list, not a copy"
              (should (identical? mylist (efficient-delete 6 mylist))))
)
(run-specs)
