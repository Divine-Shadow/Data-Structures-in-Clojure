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
                (should= (Cons. 5 xx) (insert-at-beginning 5 xx) )))
)

(describe "insert-at-end"
          (it "creates a cons cell"
              (should-not= nil (insert-at-end 10 nil)))
          
          (it "should work with empty lists"
              (should= (Cons. 10 nil) (insert-at-end 10 nil)))

          (it "should work with lists that have data"
              (let [xx (Cons. 10 (Cons. 20 (Cons. 30 nil)))]
                (should= (Cons. (:car xx) (insert-at-end 5 (:cdr xx))) (insert-at-end 5 xx))))
)

(describe "sorted-insert"
                                
          (it "creates a cons cell"
              (should-not= nil (sorted-insert 10 nil)))

          (it "should check if same memory location is shared"
              (let [xx (Cons. 2 (Cons. 5 nil))]
              (let [yy (sorted-insert 3 xx)]
                 (should (identical? (-> xx :cdr) (-> yy :cdr :cdr))))))

          (it "should work with empty lists"
              (should= (Cons. 10 nil) (sorted-insert 10 nil)))

          (it "should work when element is less than first element in list"
              (let [xx (Cons. 10 (Cons. 20 (Cons. 30 nil)))]
                (should= (Cons. 5 xx) (sorted-insert 5 xx))))

          (it "should work for any other element we want inserted"
              (let [xx (Cons. 10 (Cons. 25 (Cons. 30 nil)))]
                (should= (Cons. (:car xx) (sorted-insert 15 (:cdr xx))) (sorted-insert 15 xx))))

)
(describe "search"
          (it "should work with empty lists"
              (should-not (search 0 (Cons. nil nil))))

          (it "should work if first element is same as elt"
              (let [xx (Cons. 1 (Cons. 2 (Cons. 3 nil)))]
                (should (search 1 xx))))

          (it "should work any other element anywhere in the list"
              (let [xx (Cons. 2 (Cons. 3 (Cons. 1 nil)))]
                (should (search 1 (:cdr xx)))))
)

(describe "delete"
          (it "should work with empty lists"
              (should= (Cons. nil nil) (delete 1 (Cons. nil nil))))

          (it "should work if first element is same as eelt"
              (let [xx (Cons. 1 (Cons. 2 (Cons. 3 nil)))]
                (should= (:cdr xx) (delete 1 xx))))

          (it "should work with any other element to delete in the list"
              (let [xx (Cons. 2 (Cons. 1 (Cons. 3 nil)))]
                (should= (Cons. (:car xx) (delete 1 (:cdr xx))) (delete 1 xx))))
)

(describe "delete-all"
          (it "should work with an empty list"
              (should= (Cons. nil nil) (delete-all 1 (Cons. nil nil))))

          (it "should work if first element is same as elt"
              (let [xx (Cons. 1 (Cons. 2 (Cons. 3 nil)))]
                (should= (:cdr xx) (delete-all 1 (:cdr xx)))))

          (it "should delete all the copies of the elements in the list"
              (let [xx (Cons. 1 (Cons. 2 (Cons. 1 nil)))]
                (should= (delete-all 1 xx) (Cons. 2 nil))))

)


(describe "efficient-delete"

          (it "should return the original if elt to delete is not in the list"
              (let [xx (Cons. 2 (Cons. 3 (Cons. 4 (Cons. 2 nil))))]
                (should (identical? xx (efficient-delete 1 xx)))))
          (it "should delete all copies of the elements and if elt not present return original list not a copy"
              (let [xx (Cons. 3 (Cons. 2 (Cons. 1 (Cons. 1 nil))))]
                (should= (Cons. 3 (Cons. 2 (Cons. 1 nil))) (efficient-delete 1 xx))))
)
(run-specs)
