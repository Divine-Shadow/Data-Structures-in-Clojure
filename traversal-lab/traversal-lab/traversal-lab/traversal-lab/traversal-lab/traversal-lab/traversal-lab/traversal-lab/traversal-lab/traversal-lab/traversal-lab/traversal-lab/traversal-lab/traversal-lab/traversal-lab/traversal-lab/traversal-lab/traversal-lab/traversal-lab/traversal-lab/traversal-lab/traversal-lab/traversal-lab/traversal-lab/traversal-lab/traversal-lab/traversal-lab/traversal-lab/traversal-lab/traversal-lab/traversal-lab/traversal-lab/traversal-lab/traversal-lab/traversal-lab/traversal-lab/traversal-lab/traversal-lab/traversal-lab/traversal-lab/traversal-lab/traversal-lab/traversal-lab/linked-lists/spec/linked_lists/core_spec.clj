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
              (should-not-be-nil (insert-at-beginning 10 nil)))

          (it "should work with empty lists"
              (should= (Cons. 10 nil) (insert-at-beginning 10 nil)))
          
          (it "should work with lists that have data"
              (let [xx (Cons. 20(Cons. 30 nil))]
                (should= (Cons. 10(Cons. 20(Cons. 30 nil))) (insert-at-beginning 10 xx)))))

(describe "insert-at-end"
          (it "creates a cons cell"
              (should-not-be-nil (insert-at-end 20 nil)))

          (it "should work with empty lists"
              (should= (Cons. 20 nil) (insert-at-end 20 nil)))
     
          (it "should work with lists that have data"
              (let [xx (Cons. 20(Cons. 30 (Cons. 40 nil)))]
              (should= (Cons. 20(Cons. 30 nil)) (insert-at-end 40 xx)))))   


(describe "sorted insert"
          (it "should work with empty lists"
              (should-not-be-nil (sorted-insert 20 nil)))

          (it "should insert an element into a sorted list"
              (let [xx (Cons. 10(Cons. 20(Cons. 40 nil)))]
  (should= (Cons. 10(Cons. 20(Cons. 30(Cons. 40 nil)))) (sorted-insert(30 xx))))))

(describe "search"
          (it "should work with empty lists"
                (should (search 1 nil)))

          (it "returns a value from a list"
              (let [xx (Cons. 20(Cons. 30(Cons. 40 nil)))]
                (should (search(30 xx) ))))

          (it "returns a false from a list that does not contain elt"
              (let [xx (Cons. 20(Cons. 30(Cons. 40 nil)))]
                (should (search 5 xx)))))

(describe "delete"
          (it "works with empty lists"
               (should-be-nil (delete 1 nil)))

          (it "should delete an element from a list"
               (let [xx (Cons. 30(Cons. 40(Cons. 50 nil)))]
               (should= (Cons. 40(Cons. 50 nil)) delete(30 xx))))
           
)

(describe "delete-all"
          (it "should work with empty lists"
               (should-be-nil (delete-all 1 nil)))

          (it "should delete every instance of elt"
               (let [xx (Cons. 40(Cons. 50(Cons. 50 nil)))]
               (should= (Cons. 40 nil) (delete-all 50 xx)))))


(describe "efficient-delete"
          (it "should work with empty lists"
               (should-be-nil (efficient-delete 5 nil)))

          (it "should delete every instance of elt"
               (let [xx (Cons. 10(Cons. 20(Cons. 30(Cons. 30 nil))))]
               (should= (Cons. 10(Cons. 20 nil)) (efficient-delete(30 xx)))))

          (it "should return original list if elt not present"
               (let [xx (Cons. 10(Cons. 20(Cons. 30 nil)))]
               (should-be-same xx (efficient-delete(5 xx))))))
(run-specs)
