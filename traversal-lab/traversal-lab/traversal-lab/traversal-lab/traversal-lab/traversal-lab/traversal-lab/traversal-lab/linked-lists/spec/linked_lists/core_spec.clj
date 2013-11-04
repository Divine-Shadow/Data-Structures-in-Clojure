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
                (should= (Cons. 5 xx) (insert-at-beginning 5 xx)))))

(describe "insert-at-end"
          (it "creates a cons cell"
              (should-not= nil (insert-at-end 10 nil)))
          
          (it "should work with empty lists"
              (should= (Cons. 10 nil) (insert-at-end 10 nil)))
          
          (it "should work with lists that have data"
              (let [xx (Cons. 10 (Cons. 20 (Cons. 30 nil)))]
                (should= (Cons. xx 40) (insert-at-end 40 nil))))

(describe "sorted insert"
          (it "creates a cons cell"
              (should= nil (sorted-insert 10 nil)))

          (it "should work with empty lists"
              (should= (Cons. 10 nil)  (sorted-insert 10 nil)))

          (it "should work with lists that has data"
              (let [xx (Cons. 5 (Cons. 15 (Cons. 20)))]
                (should= (Cons. 5 (Cons. 10 (Cons. 15 (Cons. 20)))) (sorted-insert 10 xx))))

(describe "search"
          (it ""
              
)

(describe "delete"
          (it "delete a present element"
              (should= (Cons. 1 nil) (delete 3 (Cons. 1 (Cons. 3 nil)))))

          (it "only delete one element"
              (should= (Cons. 3 nil) (delete 3 (Cons. 3 (Cons. 3 nil)))))
          
          (it "should not delete non-present elements"
              (should= (Cons. 1 (Cons.  2 (Cons. 3 nil))) (delete 8 (Cons. 1 (Cons. 2 (Cons. 3 nil))))))
              
)

(describe "delete-all"
           (it "delete a present element"
              (should= (Cons. 1 nil) (delete-all 3 (Cons. 1 (Cons. 3 nil)))))

           (it "only delete all elements"
              (should= (Cons. nil nil) (delete-all 3 (Cons. 3 (Cons. 3 nil)))))
          
           (it "should not delete non-present elements"
              (should= (Cons. 1 (Cons.  2 (Cons. 3 nil))) (delete-all 8 (Cons. 1 (Cons. 2 (Cons. 3 nil))))))
              
)

(describe "efficient-delete"
          (it "should preseve memory"
              (let [y (Cons. 1 (Cons. 2 (Cons. 3)))]
                (should (identical? (efficient-delete 5 y) y)))) 
)
(run-specs)
