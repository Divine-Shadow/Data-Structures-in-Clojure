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
          (it "should create a cons cell."
              (should-not= nil (insert-at-end 10 nil)))
          (it "should work with empty list."
              (should= (Cons. 10 nil) (insert-at-end 10 nil) ))
          (it "should add the elt at the end."
              (should= (Cons. 5 (Cons. 10 nil)) (insert-at-end 10 (Cons. 5 nil)))))

(describe "sorted insert"
          (it "should work with empty list."
              (should= (Cons. 5 nil) (sorted-insert 5 nil)))
          (it "should insert at the right place."
              (let [xx (Cons. 1 (Cons. 2 (Cons. 4 nil)))]
                (should= (Cons. 1 (Cons. 2 (Cons. 3 (Cons. 4 nil)))) (sorted-insert 3 xx))))
          (it "should recycle the memory"
              (let [xx (Cons. 10 (Cons. 20 (Cons. 30 (Cons. 40 (Cons. 50 nil)))))]
                (should= true (identical? (-> xx :cdr :cdr) (-> (sorted-insert 25 xx) :cdr :cdr :cdr )))))
          
)

(describe "search"
          (it "should work with empty list."
              (should= false (search 5 nil)))
          (it "should work with a list that have element."
             (let [xx (Cons. 1 (Cons. 2 (Cons. 3 (Cons. 5 (Cons. 7 (Cons. 9 nil))))))]
              (should= true (search 5 xx))))
          (it "should work with a list without the element."
             (let [xx (Cons. 1 (Cons. 2 (Cons. 3 nil)))] 
              (should= false (search 5 xx)))))

(describe "delete"
          (it "should work with empty list."
              (let [xx nil]
              (should= xx (delete 5 xx))))
          (it "should delete the element."
             (let [xx (Cons. 1 (Cons. 2 (Cons. 3 nil)))]
              (should= (Cons. 1 (Cons. 3 nil)) (delete 2 xx))))
          (it "should delte the element if the list is unsorted"
              (should= (Cons. 5 (Cons. 2 nil)) (delete 8 (Cons. 5 (Cons. 2 (Cons. 8 nil))))))
          (it "should work when the element is not in the list."
             (let [xx (Cons. 1 (Cons. 2 (Cons. 3 nil)))]
              (should= (Cons. 1 (Cons. 2 (Cons. 3 nil))) (delete 4 xx))))
          (it "should only delete the first it encounters if there are multple element"
              (let [xx (Cons. 1 (Cons. 2 (Cons. 2 (Cons. 3 nil))))]
                (should= (Cons. 1 (Cons. 2 (Cons. 3 nil))) (delete 2 xx))))
)


(describe "delete-all"
          (it "should work with empty list."
              (let [xx nil]
              (should= xx (delete-all 5 xx))))
          (it "should work if the element is in the list."
             (let [xx (Cons. 1 (Cons. 8 (Cons. 2 (Cons. 8 (Cons. 3 (Cons. 8 nil))))))]
              (should= (Cons. 1 (Cons. 2 (Cons. 3 nil))) (delete-all 8 xx))))
          (it "should work if the element is absent."
             (let [xx (Cons. 1 (Cons. 2 (Cons. 3 (Cons. 4 nil))))]
              (should= (Cons. 1 (Cons. 2 (Cons. 3 (Cons. 4 nil)))) (delete-all 5 xx))))
)

(describe "efficient-delete"
          (it "should work with empty list."
              (let [xx nil]
              (should= xx (efficient-delete 5 xx))))
          (it "should return the same list."
             (let [xx (Cons. 1 (Cons. 2 (Cons. 3 (Cons. 4 nil))))]
              (should= xx (efficient-delete 5 xx))))
          (it "should return the same as when u do a normal delete when it has the element."
             (let [xx (Cons. 1 (Cons. 2 (Cons. 3 (Cons. 4 nil))))]
              (should= (Cons. 1 (Cons. 2 (Cons. 4 nil))) (efficient-delete 3 xx))))
          (it "should recycle the memory"
              (let [xx (Cons. 10 (Cons. 20 (Cons. 30 (Cons. 40 (Cons. 50 nil)))))]
                (should= true (identical? xx (efficient-delete 25 xx)))))
)
(run-specs)
