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
          (it "should insert an element at the end"
              (should= (Cons. 1 (Cons. 2 (Cons. 3 nil))) (insert-at-end 3 (Cons. 1 (Cons. 2 nil)))))
(it "should work with empty lists"
              (should= (Cons. 10 nil) (insert-at-end 10 nil) ))
)

(describe "sorted-insert"
          (it "should sort and insert an item"
              (should= (Cons. 3 (Cons. 5 (Cons. 6 (Cons. 7 nil))))  (sorted-insert 5  (Cons. 3 (Cons. 6 (Cons. 7 nil))))))
 (it "should work will empty lists"
              (should= (Cons. 7 nil) (sorted-insert 7  nil)))
)

(describe "search"
          (it "should find an element in the list"
              (should (search 2 (Cons. 1 (Cons. 2 nil))) ))
   (it "should not find an element that is not there" (should= (search 4 (Cons. 1 (Cons. 2 nil))) false))
)

(describe "delete"
          (it "should remove an element from the list"
              (should= (Cons. 1 (Cons. 3 (Cons. 4 nil))) (delete 2  (Cons. 1 (Cons. 2 (Cons. 3 (Cons. 4 nil))))) ))
(it "should not remove an element from the list that is not called"
              (should-not= (Cons. 1 (Cons. 3 (Cons. 4 nil))) (delete 3  (Cons. 1 (Cons. 2 (Cons. 3 (Cons. 4 nil))))) ))
)

(describe "delete-all"
          (it "should remove every instance of an element from the list"
              (should= (Cons. 4 nil) (delete 2  (Cons. 2 (Cons. 2 (Cons. 4 nil)))) ))
(it "should not remove only one instance of an element from the list"
              (should-not= (Cons. 2 (Cons. 4 nil)) (delete 2  (Cons. 2 (Cons. 2 (Cons. 4 nil)))) ))
)

(describe "efficient-delete"
          (it "should use the search function before using the delete function"
              (should= (Cons. 1 (Cons. 3 (Cons. 4 nil))) (efficient-delete 2  (Cons. 1 (Cons. 2 (Cons. 3 (Cons. 4 nil))))) ))
)
(run-specs)
