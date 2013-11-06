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
          (it "should share memory"
              (let [p (Cons. 2 (Cons. 3 nil))]
                    (should (identical? (:cdr (insert-at-beginning 1 p)) p)))))
                        

(describe "insert-at-end"
          (it "insert at end of list and have same stuff in beginning"
              (should= (Cons. 1 (Cons. 2(Cons. 3 nil))) (insert-at-end 3 (Cons. 1 (Cons. 2 nil)))))
          (it "should share memory"
              (should (let [p (Cons. 2 (Cons. 3 nil))]
                        (identical? (:car (insert-at-end 1 p)) p))))



(describe "sorted insert"
          (it "puts elt in ascending order"
              (should= (Cons. 1 (Cons. 2 (Cons. 3 nil))) (insert-at-end 3 (Cons. 1(Cons. 2 nil)))))
 
          (it "should share memory"
                  (should (let [p (Cons. 2 (Cons. 3 nil))])
                        (identical? (:cdr (insert-at-beginning 1 p)) p))))

(describe "search"
          (it "say yes when finds elt"
              (should= true (search 3 (Cons. 1 (Cons. 2 (Cons. 3 (Cons. 4 nil ))))))
              (should= false (search 5 (Cons. 1 (Cons. 2 (Cons. 3 (Cons. 4 nil ))))))

))

(describe "delete"
          (it "deletes elt from list."
              (should= (Cons. 1 (Cons. 2 (Cons. 4 nil))) (delete 3 (Cons. 1 (Cons. 2 (Cons. 3 (Cons. 4 nil ))))))
))

(describe "delete-all"
          (it "delete all given elt in a list"
              (should= (Cons. 2 (Cons. 3 (Cons. 4 (Cons. 2 (Cons. 3 (Cons. 4 nil )))))) (delete-all 1 (Cons. 1 (Cons. 2 (Cons. 3 (Cons. 4 (Cons. 1 (Cons. 2 (Cons. 3 (Cons. 4 nil ))))))))))))


(describe "efficient-delete"
          (it "deletes first instance of element. Returns same list if element is not there"
                           (should= (Cons. 1 (Cons. 2 (Cons. 4 nil))) (delete 3 (Cons. 1 (Cons. 2 (Cons. 3 (Cons. 4 nil ))))))
                           (should= (Cons. 1 (Cons. 2 (Cons. 4 nil))) (delete 3 (Cons. 1 (Cons. 2 (Cons. 4 nil)))))
 ))
(run-specs)