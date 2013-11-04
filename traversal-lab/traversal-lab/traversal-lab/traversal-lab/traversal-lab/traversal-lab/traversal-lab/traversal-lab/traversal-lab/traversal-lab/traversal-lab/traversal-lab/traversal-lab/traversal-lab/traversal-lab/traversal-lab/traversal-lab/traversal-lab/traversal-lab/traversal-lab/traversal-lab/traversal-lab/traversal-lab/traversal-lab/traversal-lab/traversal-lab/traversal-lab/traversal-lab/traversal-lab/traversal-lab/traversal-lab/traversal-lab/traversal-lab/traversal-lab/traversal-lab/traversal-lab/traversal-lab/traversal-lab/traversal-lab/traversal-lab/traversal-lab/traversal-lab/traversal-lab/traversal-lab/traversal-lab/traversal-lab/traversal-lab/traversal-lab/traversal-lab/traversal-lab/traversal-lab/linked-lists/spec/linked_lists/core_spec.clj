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

          (it "creates a cons cell"
              (should-not= nil (insert-at-end 10 nil)))

          (it "should work with lists that have data"
                (should= (Cons. 10 (Cons. 20 (Cons. 30 nil))) (insert-at-end 30 (Cons. 10 (Cons. 20 nil)))))

          (it "should work with empty lists"
              (should= (Cons. 10 nil) (insert-at-end 10 nil)))
)

(describe "sorted insert"
          (it "should work with empty lists."
              (should= (Cons. 10 nil) (sorted-insert 10 nil)))
          
          (it "should work when item belongs at beginning (smallest)"
              (should= (Cons. 10 (Cons. 20 (Cons. 30 nil))) (sorted-insert 10 (Cons. 20 (Cons. 30 nil)))))

          (it "should work when item belongs at end (largest)"
              (should= (Cons. 10 (Cons. 20 (Cons. 30 nil))) (sorted-insert 30 (Cons. 10 (Cons. 20 nil)))))
          (it "should work with a value in the middle"
              (should= (Cons. 10 (Cons. 20 (Cons. 30 (Cons. 40 nil)))) (sorted-insert 20 (Cons. 10 (Cons. 30 (Cons. 40 nil))))))

          (it "should recycle memory."
              (let [end (Cons. 30 (Cons. 40 nil))]
                (let [inserted (sorted-insert 20 (Cons. 10 end))]
                  (should= (Cons. 10 (Cons. 20 (Cons. 30 (Cons. 40 nil)))) inserted)
                  (should (identical? (-> inserted :cdr :cdr ) end))))) 
                    
)

(describe "search"
          
          (it "should return false for an empty set"
              (should-not (search 10 nil)))

          (it "should return false if value not in list"
              (should-not (search 10 (Cons. 30 (Cons. 3 (Cons. 4 nil))))))

          (it "should return true when value is first element"
              (should (search 10 (Cons. 10 (Cons. 20 (Cons. 30 nil))))))

          (it "should return true when value is last element"
              (should (search 10 (Cons. 30 (Cons. 20 (Cons. 10 nil))))))

          (it "should return true when multiple values"
              (should (search 10 (Cons. 20 (Cons. 10 (Cons. 20 (Cons. 10 nil)))))))

          (it "should return true when element in middle of unordered list"
              (should (search 10 (Cons. 30 (Cons. 234 (Cons. 10 (Cons. 394 (Cons. 2 nil))))))))

)
(describe "delete"

          (it "should return nil for a nil list"
              (should= nil (delete 10 nil)))

          (it "should work when elt at beginning"
              (should= (Cons. 30 (Cons. 40 nil)) (delete 20 (Cons. 20 (Cons. 30 (Cons. 40 nil))))))

          (it "should work when elt at end"
              (should= (Cons. 20 (Cons. 30 nil)) (delete 40 (Cons. 20 (Cons. 30 (Cons. 40 nil))))))

          (it "should delete only first elt"
              (should= (Cons. 20 (Cons. 40 (Cons. 30 nil))) (delete 30 (Cons. 20 (Cons. 30 (Cons. 40 (Cons. 30 nil)))))))

          (it "should return copy of list if no instances are found"
                    (should= (Cons. 20 (Cons. 30 (Cons. 40 nil))) (delete 346 (Cons. 20 (Cons. 30 (Cons. 40 nil))))))

)

(describe "delete-all"

          (it "should return nil for a nil list"
              (should= nil (delete-all 10 nil)))

          (it "should work when elt at beginning"
              (should= (Cons. 30 (Cons. 40 nil)) (delete-all 20 (Cons. 20 (Cons. 30 (Cons. 40 nil))))))

          (it "should work when elt at end"
              (should= (Cons. 20 (Cons. 30 nil)) (delete-all 40 (Cons. 20 (Cons. 30 (Cons. 40 nil))))))

          (it "should delete all instances of elt found"
              (should= (Cons. 20 (Cons. 40 nil)) (delete-all 30 (Cons. 20 (Cons. 30 (Cons. 40 (Cons. 30 nil)))))))

          (it "should return copy of list if no instances are found"
              (should= (Cons. 20 (Cons. 30 (Cons. 40 nil))) (delete-all 346 (Cons. 20 (Cons. 30 (Cons. 40 nil))))))

)

(describe "efficient-delete"

          (it "should return nil for a nil list"
              (should= nil (efficient-delete 10 nil)))

          (it "should work when elt at beginning"
              (should= (Cons. 30 (Cons. 40 nil)) (efficient-delete 20 (Cons. 20 (Cons. 30 (Cons. 40 nil))))))

          (it "should work when elt at end"
              (should= (Cons. 20 (Cons. 30 nil)) (efficient-delete 40 (Cons. 20 (Cons. 30 (Cons. 40 nil))))))

          (it "should delete only first elt"
              (should= (Cons. 20 (Cons. 40 (Cons. 30 nil))) (efficient-delete 30 (Cons. 20 (Cons. 30 (Cons. 40 (Cons. 30 nil)))))))

          (it "should return original list if elt is not in list"
              (should 
               (let [xx (Cons. 10 (Cons. 20 (Cons. 30 nil)))]
                 (identical? xx (efficient-delete 40 xx)))))
)
(run-specs)
