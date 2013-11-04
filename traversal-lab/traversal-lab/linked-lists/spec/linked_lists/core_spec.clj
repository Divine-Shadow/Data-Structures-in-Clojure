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

          (it "should work with lists with data"
              (let [xx (Cons. 1 (Cons. 2 (Cons. 3 nil)))]
                (should= (Cons. 1 (Cons. 2 (Cons. 3 (Cons. 4 nil)))) (insert-at-end 4 xx))))
)

(describe "sorted insert"
          (it "creates a cons cell"
              (should= (Cons. 10 nil) (sorted-insert 10 nil)))

          (it "should work with empty lists"
              (should= (Cons. 10 nil) (sorted-insert 10 nil)))
          
          (it "should work with non-empty lists that are sorted"
              (let [xx (Cons. 1 (Cons. 2 (Cons. 4 nil)))]
                (should= (Cons. 1 (Cons. 2 (Cons. 3 (Cons. 4 nil)))) (sorted-insert 3 xx))))

          (it "should work with non-empty lists that is not sorted"
              (let [xx (Cons. 3 (Cons. 1 (Cons. 4 (Cons. 7 nil))))]
                (should= (Cons. 2 (Cons. 3 (Cons. 1 (Cons. 4 (Cons. 7 nil))))) (sorted-insert 2 xx))))
          (it "should recycle memory"
              (let [xx (Cons. 1 (Cons. 5 (Cons. 6 (Cons. 7 nil))))]
                (should (identical? (:cdr xx) (:cdr (:cdr (sorted-insert 4 xx)))))))
          (it "should work with non-wmpty lists that are not sorted into the middle"
              (let [xx (Cons. 0 (Cons. 1 (Cons. 6 (Cons. 3 nil))))]
                (should= (Cons. 0 (Cons. 1 (Cons. 5 (Cons. 6 (Cons. 3 nil))))) (sorted-insert 5 xx))))
)

(describe "search"
          (it "should work with empty lists"
              (should= (search 5 nil) false))

          (it "should work with non-empty lists"
              (let [xx (Cons. 1 (Cons. 2 nil))]
                (should= true (search  2 xx))))
)

(describe "delete"
          (it "should work with empty lists"
              (should= (delete 2 nil) nil))

          (it "should work with non-empty lists"
              (let [xx (Cons. 1 (Cons. 2 (Cons. 3 nil)))]
                (should= (Cons. 1 (Cons. 3 nil)) (delete 2 xx))))

          (it "should only delete one copy"
              (let [xx (Cons. 1 (Cons. 1 (Cons. 2 (Cons. 3 nil))))]
                (should= (Cons. 1 (Cons. 2 (Cons. 3 nil))) (delete 1 xx))))

          (it "should work with lists that don't have the data"
              (let [xx (Cons. 1 (Cons. 2 (Cons. 3 nil)))]
                (should= xx (delete 6 xx))))
)

(describe "delete-all"
          (it "should work with empty lists"
              (should= nil (delete-all 2 nil)))

          (it "should work with non-empty lists with multiple of that data"
              (let [xx (Cons. 1 (Cons. 2 (Cons. 3 (Cons. 3 (Cons. 3 nil)))))]
                (should= (Cons. 1 (Cons. 2 nil)) (delete-all 3 xx))))

          (it "should work with lists that have 1 of the data"
              (let [xx (Cons. 1 (Cons. 2 nil))]
                (should= (Cons. 1 nil) (delete-all 2 xx))))

          (it "should work with list of element is not in the list"
              (let [xx (Cons. 1 (Cons. 2 (Cons. 3 nil)))]
                (should= xx (delete-all 5 xx))))
)

(describe "efficient-delete"
          (it "should work with empty lists"
              (should= (efficient-delete 5 nil) nil))
          
          (it "should work with lists that have data"
              (let [xx (Cons. 1 (Cons. 2 (Cons. 3 nil)))]
                (should= (Cons. 1 (Cons. 3 nil)) (efficient-delete 2 xx))))

          (it "should work with lists that dont have the correct data"
              (let [xx (Cons. 1 (Cons. 2 (Cons. 3 nil)))]
                (should (identical?  xx (efficient-delete 5 xx)))))
)
(run-specs)
