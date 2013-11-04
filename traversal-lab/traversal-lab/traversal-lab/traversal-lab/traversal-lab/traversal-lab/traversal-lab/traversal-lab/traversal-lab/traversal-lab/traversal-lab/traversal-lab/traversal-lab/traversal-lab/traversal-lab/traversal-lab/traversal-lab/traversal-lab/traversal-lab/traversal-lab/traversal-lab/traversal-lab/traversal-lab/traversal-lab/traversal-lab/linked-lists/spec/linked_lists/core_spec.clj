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
          (it "should work with empty lists"
              (should= (Cons. 1 nil) (insert-at-end 1 nil)))
          (it "should work with lists that have data"
              (let [xx (Cons. 1 (Cons. 2 (Cons. 3 nil)))]
                (should= (Cons. 1 (Cons. 2 (Cons. 3 (Cons. 4 nil)))) (insert-at-end 4 xx))))
)

(describe "sorted insert"
          (it "should work with empty lists"
              (should= (Cons. 10 nil) (sorted-insert 10 nil)))
          (it "should work with lists that have data"
              (let [xx (Cons. 1 (Cons. 3 (Cons. 4 nil)))]
                (should= (Cons. 1 (Cons. 2 (Cons. 3 (Cons. 4 nil)))) (sorted-insert 2 xx))))
          (it "should recycle memory when the element is being put at the front of the list"
              (let [xx (Cons. 2 (Cons. 3 (Cons. 4 nil)))]
                (should= (Cons. 1 xx) (sorted-insert 1 xx))))
          (it "should recycle memory"
              (let [xx (Cons. 2 (Cons. 3 (Cons. 4 nil)))]
                (should= true (identical? (:cdr xx) (:cdr (:cdr (sorted-insert 1 xx)))))))
)

(describe "search"
          (it "should work with empty lists"
              (should= false (search 4 nil)))
          (it "should work when the given element is at the first spot in the list"
              (let [xx (Cons. 1 (Cons. 2 (Cons. 3 nil)))]
                (should= true (search 1 xx))))
          (it "should work when the given element is not at the first spot in the list"
              (let [xx (Cons. 1 (Cons. 2 (Cons. 3 nil)))]
                (should= true (search 3 xx))))
          (it "should work when the given element isn't in the list"
              (let [xx (Cons. 1 (Cons. 2 (Cons. 3 nil)))]
                (should= false (search 4 xx))))
)

(describe "delete"
          (it "should work with empty lists"
              (should= nil (delete 5 nil)))
          (it "should work when the given element isn't in the list"
              (let [xx (Cons. 1 (Cons. 2 nil))]
                (should= (Cons. 1 (Cons. 2 nil)) (delete 5 xx))))
          (it "should work when the given element is at the first spot in the list"
              (let [xx (Cons. 5 (Cons. 6 (Cons. 7 nil)))]
                (should= (Cons. 6 (Cons. 7 nil)) (delete 5 xx))))
          (it "should work when the given element is not at the first spot in the list"
              (let [xx (Cons. 2 (Cons. 3 (Cons. 4 (Cons. 5 nil))))]
                (should= (Cons. 2 (Cons. 3 (Cons. 4 nil))) (delete 5 xx))))
          (it "should work when there are multiple instances of the element in the list"
              (let [xx (Cons. 2 (Cons. 5 (Cons. 5 (Cons. 6 nil))))]
                (should= (Cons. 2 (Cons. 5 (Cons. 6 nil))) (delete 5 xx))))
)

(describe "delete-all"
          (it "should work with empty lists"
              (should= nil (delete-all 5 nil)))
          (it "should work when the given element isn't in the list"
              (let [xx (Cons. 1 (Cons. 2 (Cons. 3 nil)))]
                (should= (Cons. 1 (Cons. 2 (Cons. 3 nil))) (delete-all 5 xx))))
          (it "should work when the given element is first in the list"
              (let [xx (Cons. 5 (Cons. 1 (Cons. 2 (Cons. 3 nil))))]
                (should= (Cons. 1 (Cons. 2 (Cons. 3 nil))) (delete-all 5 xx))))
          (it "should work when the given element is not at the first spot in the list"
              (let [xx (Cons. 1 (Cons. 2 (Cons. 3 (Cons. 5 nil))))]
                (should= (Cons. 1 (Cons. 2 (Cons. 3 nil))) (delete-all 5 xx))))
          (it "should work when there are multiple instances of the given element in the list"
              (let [xx (Cons. 5 (Cons. 1 (Cons. 2 (Cons. 5 nil))))]
                (should= (Cons. 1 (Cons. 2 nil)) (delete-all 5 xx))))
)

(describe "efficient-delete"
          (it "should work with empty lists"
              (should= nil (efficient-delete 5 nil)))
          (it "should work when the given element isn't in the list"
              (let [xx (Cons. 1 (Cons. 2 nil))]
                (should= true (identical? xx (efficient-delete 5 xx)))))
          (it "should work when the given element is at the first spot in the list"
              (let [xx (Cons. 5 (Cons. 6 (Cons. 7 nil)))]
                (should= (Cons. 6 (Cons. 7 nil)) (efficient-delete 5 xx))))
          (it "should work when the given element is not at the first spot in the list"
              (let [xx (Cons. 2 (Cons. 3 (Cons. 4 (Cons. 5 nil))))]
                (should= (Cons. 2 (Cons. 3 (Cons. 4 nil))) (efficient-delete 5 xx))))
          (it "should work when there are multiple instances of the element in the list"
              (let [xx (Cons. 2 (Cons. 5 (Cons. 5 (Cons. 6 nil))))]
                (should= (Cons. 2 (Cons. 5 (Cons. 6 nil))) (efficient-delete 5 xx))))
)
(run-specs)
