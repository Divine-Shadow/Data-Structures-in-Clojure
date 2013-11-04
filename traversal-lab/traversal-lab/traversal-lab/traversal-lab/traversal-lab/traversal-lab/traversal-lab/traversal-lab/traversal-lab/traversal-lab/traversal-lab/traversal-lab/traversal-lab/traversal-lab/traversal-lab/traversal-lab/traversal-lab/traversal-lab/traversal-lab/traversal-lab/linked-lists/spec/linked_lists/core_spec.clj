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
                (should= (Cons. 10 (Cons. 20 (Cons. 30 (Cons. 5 nil)))) (insert-at-end 5 xx))))
)

(describe "sorted insert"
          (it "creates a cons cell"
              (should-not= nil (sorted-insert 10 nil)))
          (it "should work with empty lists"
              (should= (Cons. 10 nil) (sorted-insert 10 nil)))
          (it "should work with lists that have data"
              (let [xx (Cons. 1 (Cons. 2 (Cons. 3 (Cons. 4 (Cons. 6 nil)))))]
                (should= (Cons. 1 (Cons. 2 (Cons. 3 (Cons. 4 (Cons. 5 (Cons. 6 nil)))))) (sorted-insert 5 xx)))))
            

(describe "delete"
          (it "should delete something from a list"
              (should= (Cons. 1 (Cons. 3 (Cons. 4 nil))) (delete 2 (Cons. 1 (Cons. 2 (Cons. 3 (Cons. 4 nil)))))))
)

(describe "search"
          (it "should let us know if there is something in a list"
              (should= true (search 2 (Cons. 1 (Cons. 3 (Cons. 2 (Cons. 5 nil)))))))
          (it "should not find anything if it is not in the list"
              (should= false (search 5 (Cons. 1 (Cons. 2 (Cons. 3 nil))))))
)

(describe "delete-all"
          (it "should delete all of a certain item in a list"
              (should= (Cons. 1 (Cons. 3 (Cons. 5 nil))) (delete-all 2 (Cons. 1 (Cons. 2 (Cons. 3 (Cons. 2 (Cons. 5 nil))))))))
          (it "should delete only one item when the items in the list only once"
              (should= (Cons. 1 (Cons. 3 (Cons. 4 nil))) (delete-all 2 (Cons. 1 (Cons. 2 (Cons. 3 (Cons. 4 nil)))))))
)

(describe "efficient-delete"
          (it "should delete an item in the list if it is found in the list"
              (should= (Cons. 1 (Cons. 3 (Cons. 4 nil))) (efficient-delete 2 (Cons. 1 (Cons. 2 (Cons. 3 (Cons. 4 nil)))))))
          (it "should return the original list if the element is not found in the list"
              (should= (Cons. 1 (Cons. 2 (Cons. 3 nil))) (efficient-delete 4 (Cons. 1 (Cons. 2 (Cons. 3 nil)))))))
(run-specs)
