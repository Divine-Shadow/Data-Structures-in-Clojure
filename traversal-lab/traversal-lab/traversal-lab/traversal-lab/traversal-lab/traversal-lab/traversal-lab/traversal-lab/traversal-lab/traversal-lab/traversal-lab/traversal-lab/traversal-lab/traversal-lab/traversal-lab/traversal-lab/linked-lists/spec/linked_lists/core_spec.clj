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
              (let [xs (Cons. 10 (Cons. 20 (Cons. 30 (Cons. 40 nil))))]
                (should= xs (insert-at-end 40 (Cons. 10 (Cons. 20 (Cons. 30 nil)))))))
)

(describe "sorted insert"
          (it "creates a cons cell"
              (should-not= nil (sorted-insert 10 nil)))
          (it "should work with empty lists"
              (should= (Cons. 10 nil) (sorted-insert 10 nil)))
          (it "should work with lists that have data"
              (let [xs (Cons. 10 (Cons. 20 (Cons. 30 (Cons. 40 nil))))]
                (should= xs (sorted-insert 30 (Cons. 10 (Cons. 20 (Cons. 40 nil)))))))
          (it "should recycle memory"
              (let [xs (Cons. 10 (Cons. 30 (Cons. 40 nil)))]
                (should= true (identical? (:cdr xs) (:cdr (:cdr (sorted-insert 20 xs)))))))
)

(describe "search"
          (it "should work with empty lists"
              (should= false (search 10 nil)))
          (it "should return true if an element exists"
              (should= true (search 10 (Cons. 10 (Cons. 20 (Cons. 30 nil))))))
          (it "should return false if an element does not exist"
              (should= false (search 10 (Cons. 20 (Cons. 30 (Cons. 40 nil))))))
)

(describe "delete"
          (it "work with empty lists"
              (should= nil (delete 10 nil)))
          (it "should delete an element from a list if it exists"
              (should= (Cons. 10 (Cons. 30 nil)) (delete 20 (Cons. 10 (Cons. 20 (Cons. 30 nil))))))
          (it "should not delete all elements matching"
              (should= (Cons. 10 (Cons. 20 (Cons. 30 nil))) (delete 20 (Cons. 10 (Cons. 20 (Cons. 20 (Cons. 30 nil)))))))
)

(describe "delete-all"
          (it "work with empty lists"
              (should= nil (delete-all 10 nil)))
          (it "should delete all elements from a lists that match "
              (should= (Cons. 10 (Cons. 30 nil)) (delete-all 20 (Cons. 10 (Cons. 20 (Cons. 20 (Cons. 30 (Cons. 20 nil))))))))
)

(describe "efficient-delete"
          (it "work with empty lists"
              (should= nil (efficient-delete 10 nil)))
          (it "should return the original if the element does not exist"
              (let [xs (Cons. 10 (Cons. 20 (Cons. 30 nil)))]
                (should= true (identical? xs (efficient-delete 88 xs)))))
          (it "should delete one copy if at least one is found."
              (should= (Cons. 10 (Cons. 20 (Cons. 30 nil))) (efficient-delete 40 (Cons. 10 (Cons. 20 (Cons. 30 (Cons. 40 nil)))))))
)
(run-specs)
