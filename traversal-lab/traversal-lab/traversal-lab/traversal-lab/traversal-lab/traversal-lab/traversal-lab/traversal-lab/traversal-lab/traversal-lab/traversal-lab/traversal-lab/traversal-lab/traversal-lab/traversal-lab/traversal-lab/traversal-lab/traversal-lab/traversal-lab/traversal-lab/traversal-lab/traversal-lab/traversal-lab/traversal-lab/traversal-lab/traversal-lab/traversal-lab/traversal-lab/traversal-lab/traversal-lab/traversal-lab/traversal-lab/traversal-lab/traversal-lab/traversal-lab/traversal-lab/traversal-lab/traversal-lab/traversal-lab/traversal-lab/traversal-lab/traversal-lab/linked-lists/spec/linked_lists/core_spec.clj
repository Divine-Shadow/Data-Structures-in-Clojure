(ns linked_lists.core-spec
  (:require [speclj.core :refer :all]
            [linked_lists.core :refer :all])
  (:import [linked_lists.core Cons]))

;; # The Tests
;;
;; We are going to use [spelj](https://github.com/slagyr/speclj) for our tests.


(describe "The record declaration" ;describes behavior -> Cons record
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
              (let [xx (Cons. 10 (Cons. 20 (Cons. 30 nil)))] ;creates a value we can refer to again (xx)
                (should= (Cons. 5 xx) (insert-at-beginning 5 xx) ))))

(describe "insert-at-end"
          (it "should work for empty lists"
              (let [xx nil]
                (should= (Cons. 5 xx) (insert-at-end 5 xx))))
          (it "should work with lists that have data"
              (let [xx (Cons. 10 (Cons. 20 nil))]
                (should= (Cons. 10 (Cons. 20 (Cons. 40 nil))) (insert-at-end 40 xx))))
              
)

(describe "sorted-insert"
          (it "should work for empty lists"
              (let [xx nil]
                (should= (Cons. 5 xx) (sorted-insert 5 xx))))
          (it "should recycle memory"
              (let [xx (Cons. 2 (Cons. 4 nil))]
                (should= true (identical? xx (:cdr (sorted-insert 1 xx))))))
          (it "should work for lists with data where element must be inserted in the front"
              (let [xx (Cons. 4 (Cons. 5 nil))]
                (should= (Cons. 2 xx) (sorted-insert 2 xx))))
          (it "should work for lists with data where the element must be inserted somewhere in the middle"
              (let [xx (Cons. 4 (Cons. 7 (Cons. 9 nil)))]
                (should= (Cons. 4 (Cons. 7 (Cons. 8 (Cons. 9 nil)))) (sorted-insert 8 xx))))
          (it "should work for lists with data where the element must be inserted at the end"
              (let [xx (Cons. 4 (Cons. 5 nil))]
                (should= (Cons. 4 (Cons. 5 (Cons. 7 nil))) (sorted-insert 7 xx))))
          (it "should work for lists with data where there are multiple elements which are the same"
              (let [xx (Cons. 4 (Cons. 5 (Cons. 5 nil)))]
                (should= (Cons. 4 (Cons. 5 (Cons. 5 (Cons. 5 nil)))) (sorted-insert 5 xx))))
)

(describe "search"
          (it "should work for empty lists"
              (let [xx nil]
                (should= false (search 5 xx))))
          (it "should work for lists with data which do not have the element"
              (let [xx (Cons. 2 (Cons. 4 nil))]
                (should= false (search 5 xx))))
          (it "should work for lists with data which have the element"
              (let [xx (Cons. 10 (Cons. 20 (Cons. 1 (Cons. 2 nil))))]
                (should= true (search 10 xx))))
          (it "should work for lists with data which do not include the element"
              (let [xx (Cons. 1 (Cons. 2 nil))]
                (should= false (search 5 xx))))
)

(describe "delete"
          (it "should work for an empty list"
              (let [xx nil]
                (should= nil (delete 2 xx))))
          (it "should return xx if elt is not in xx"
              (let [xx (Cons. 2 (Cons. 4 nil))]
                (should= xx (delete 5 xx))))
          (it "should work for lists with data where there is a single occurrence of elt"
              (let [xx (Cons. 2 (Cons. 4 nil))]
                (should= (Cons. 4 nil) (delete 2 xx))))
          (it "should work for lists with data where there are multiple occurrences of elt"
              (let [xx (Cons. 2 (Cons. 4 (Cons. 4 nil)))]
                (should= (Cons. 2 (Cons. 4 nil)) (delete 4 xx))))
)

(describe "delete-all"
          (it "it should work for an empty list"
              (let [xx nil]
                (should= nil (delete-all 2 xx))))
          (it "should return xx if elt is not in xx"
              (let [xx (Cons. 2 (Cons. 4 nil))]
                (should= xx (delete-all 5 xx))))
          (it "should work for lists with data where there is a single occurrence of elt"
              (let [xx (Cons. 2 (Cons. 4 nil))]
                (should= (Cons. 4 nil) (delete-all 2 xx))))
          (it "should work for lists with data where there are multiple occurrences of elt"
              (let [xx (Cons. 2 (Cons. 2 (Cons. 4 nil)))]
                (should= (Cons. 4 nil) (delete-all 2 xx))))
)

(describe "efficient-delete"
          (it "should work for an empty list"
              (let [xx nil]
                (should= true (identical? xx (efficient-delete 2 xx)))))
          (it "should return xx if elt is not in xx"
              (let [xx (Cons. 2 (Cons. 4 nil))]
                (should= true (identical? xx (efficient-delete 5 xx)))))
          (it "should work for lists with data where there is a single occurrence of elt"
              (let [xx (Cons. 2 (Cons. 4 nil))]
                (should= (Cons. 4 nil) (efficient-delete 2 xx))))
          (it "should work for lists with data where there are multiple occurrences of elt"
              (let [xx (Cons. 2 (Cons. 4 (Cons. 4 nil)))]
                (should= (Cons. 2 (Cons. 4 nil)) (efficient-delete 4 xx))))
)
(run-specs)
