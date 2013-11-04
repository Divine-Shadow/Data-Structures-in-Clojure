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
              (should-not=  nil (insert-at-beginning 10 nil)))

          (it "should work with empty lists"
              (should= (Cons. 10 nil) (insert-at-beginning 10 nil) ))
          
          (it "should work with lists that have data"
              (let [xx (Cons. 10 (Cons. 20 (Cons. 30 nil)))]
                (should= (Cons. 5 (Cons. 10 (Cons. 20 (Cons. 30 nil)))) (insert-at-beginning 5 xx) ))))

(describe "insert-at-end"
          (it "creates a cons cell"
              (should-not=  nil (insert-at-end 10 nil)))
          (it "handles empty lists"
              (let [xx nil]
              (should= (Cons. 10 nil) (insert-at-end 10 xx) )))
          (it "handles full lists"
              (let [xx (Cons. 10 (Cons. 20 (Cons. 30 nil)))]
                (should= (Cons. 10 (Cons. 20 (Cons. 30 (Cons. 5 nil))))(insert-at-end 5 xx))))
)

(describe "sorted-insert"
         
          (it "should create a cons cell"
              (should-not= nil (sorted-insert 10 nil)))
          (it "should insert an element in the middle if the average"
              (should= (Cons. 2 (Cons. 4 (Cons. 5 (Cons. 6 (Cons. 8 nil))))) (sorted-insert 5 (Cons. 2 (Cons. 4 (Cons. 6 (Cons. 8 nil)))) ) ))
          (it "should work with empty lists"
              (should= (Cons. 10 nil) (sorted-insert 10 nil) ))
          (it "should work with lists that have data"
              (let [xx (Cons. 10 (Cons. 20 (Cons. 30 nil)))]
                (should= (Cons. 5 (Cons. 10 (Cons. 20 (Cons. 30 nil)))) (sorted-insert 5 xx))))
         (it "should insert an element in the beginning if its the smallest element"
             (should= (Cons. 5 (Cons. 10 (Cons. 20 nil))) (sorted-insert 5 (Cons. 10 (Cons. 20 nil)) )))
          (it "should insert an element in the end if its the biggest element"
             (should= (Cons. 5 (Cons. 10 (Cons. 20 nil))) (sorted-insert 20 (Cons. 5 (Cons. 10 nil)))))

)
              

(describe "search"
          (it "should work with empty lists"
             (let [xx nil]
             (should= false (search 10 xx))))
          (it "should work with lists that have data"
              (let [xx (Cons. 10 (Cons. 20 (Cons. 30 nil)))]
                (should= true (search 30 (Cons. 10 (Cons. 20 (Cons. 30 nil)))))))
          (it "should return false if not found"
              (should= false (search 4 (Cons. 2 (Cons. 3 nil)) ))))
(describe "delete"
          
           (it "should retrn an  empty list if the original were empty"
               (let [xx nil]
              (should= nil (delete 10 xx))))
           (it "should work with lists that have data"
              (let [xx (Cons. 10 (Cons. 20 (Cons. 30 nil)))]
                (should= (Cons. 10 (Cons. 30 nil)) (delete 20 xx))))
           (it "should return the original list if the element wasn't found"
               (let [xx (Cons. 10 (Cons. 20 (Cons. 30 nil)))]
                 (should= (Cons. 10 (Cons. 20 (Cons. 30 nil))) (delete 5 xx)))))


(describe "delete-all"
          (it "should handle empty lists"
              (let [xx nil]
                (should= nil (delete-all 10 xx))))
          (it "should work with list that have data"
              (let [xx (Cons. 10 (Cons. 20 (Cons. 30 (Cons. 40 (Cons. 10 nil)))))]
                (should= (Cons. 20 (Cons. 30 (Cons. 40 nil))) (delete-all 10 xx))))

          (it "should return copy of the list of element not found"
              (let [xx (Cons. 10 (Cons. 20 (Cons. 30 nil)))]
                (should= xx (delete-all 5 xx))))
)

(describe "efficient-delete"
  (it "should work with empty lists"
             (let [xx nil]
              (should= nil (efficient-delete 10 xx))))
  (it "should work with lists that have data"
              (let [xx (Cons. 10 (Cons. 20 (Cons. 30 nil)))]
                (should= (Cons. 10 (Cons. 30 nil)) (efficient-delete 20 xx))))
 
 (it "should work when element does not belong to list"
                (let [xx (Cons. 10 (Cons. 20 (Cons. 30 nil)))]
                  (should= xx (efficient-delete 40 xx)))))
(run-specs)


