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
              (should= (Cons. 10 nil) (insert-at-beginning 10 nil)))
          
          (it "should work with lists that have data"
              (let [xx (Cons. 10 (Cons. 20 (Cons. 30 nil)))]
                (should= (Cons. 5 xx) (insert-at-beginning 5 xx)))))

(describe "insert-at-end"
          (it "creates a cons cell"
              (should-not= nil (insert-at-end 10 nil)))

          (it "should work with empty lists"
              (should= (Cons. 10 nil) (insert-at-end 10 nil)))

          (it "should work with lists that have data"
              (let [xx (Cons. 10 (Cons. 20 (Cons. 30 nil)))]
                (should= (Cons. 10 (Cons. 20 (Cons. 30 (Cons. 5 nil)))) (insert-at-end 5 xx)))))
(describe "sorted-insert"
          (it "creats a cons cell"
              (should-not=  nil (sorted-insert 10 nil)))

          (it "should work with empty lists"
              (should= (Cons. 10 nil) (sorted-insert 10 nil)))

          (it "should work with lists that have data"
              (let [xx (Cons. 10 (Cons. 20 (Cons. 30 nil)))]
                (should= (Cons. 10 (Cons. 20 (Cons. 25 (Cons. 30 nil)))) (sorted-insert 25 xx)))))

(describe "search"
          (it "work with empty list"
              (should= false (search 5 nil)))

          (it "should work with lists that has data and its found"
              (let [xx (Cons. 10 (Cons. 20 (Cons. 30 nil)))]
                (should= true (search 20 xx))))

          (it "should work with lists that has data and its not found"
              (let [xx (Cons. 10 (Cons. 20 (Cons. 30 nil)))]
                (should= false (search 50 xx)))))

(describe "delete"
          (it "should work with empty lists"
              (should= nil (delete 10 nil)))

          (it "should work with lists that have data and its found"
              (let [xx (Cons. 10 (Cons. 20 (Cons. 30 nil)))]
                (should= (Cons. 20 (Cons. 30 nil)) (delete 10 xx)))))

(describe "delete-all"
          (it "should work with empty lists"
              (should= nil (delete 10 nil)))
          
          (it "should work with lists that has data and its found"
              (let [xx (Cons. 10 (Cons. 20 (Cons. 20 (Cons. 30 nil))))]
                (should= (Cons. 10 (Cons. 30 nil)) (delete-all 20 xx))))

          (it "should work with lists that has data and its not found"
              (let [xx (Cons. 10 (Cons. 20 (Cons. 30 nil)))]
                (should= xx (delete-all 50 xx)))))

(describe "efficient-delete"
          (it "should work with empty lists"
              (should= nil (efficient-delete 10 nil)))

          (it "should work when number isn't found in list"
              (let [xx (Cons. 10 (Cons. 20 (Cons. 30 nil)))]
                (should= xx (efficient-delete 40 xx))))

          (it "should work when number is found in list"
              (let [xx (Cons. 10 (Cons. 20 (Cons. 30 nil)))]
                (should= (Cons. 10 (Cons. 30 nil)) (efficient-delete 20 xx)))))

(run-specs)
