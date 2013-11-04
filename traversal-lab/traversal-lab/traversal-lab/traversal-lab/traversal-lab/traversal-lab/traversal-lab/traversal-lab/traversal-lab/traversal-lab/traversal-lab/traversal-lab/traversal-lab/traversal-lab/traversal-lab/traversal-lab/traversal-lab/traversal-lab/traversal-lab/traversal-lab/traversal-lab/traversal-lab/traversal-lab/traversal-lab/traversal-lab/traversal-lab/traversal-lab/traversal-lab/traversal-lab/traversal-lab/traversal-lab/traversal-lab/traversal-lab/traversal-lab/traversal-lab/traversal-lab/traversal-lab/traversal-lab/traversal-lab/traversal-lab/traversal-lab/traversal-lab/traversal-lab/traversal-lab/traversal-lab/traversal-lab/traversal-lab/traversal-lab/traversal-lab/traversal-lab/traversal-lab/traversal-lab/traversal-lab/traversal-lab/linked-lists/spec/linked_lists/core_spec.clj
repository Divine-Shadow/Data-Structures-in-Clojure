(ns linked_lists.core-spec
  (:require [speclj.core :refer :all]
            [linked_lists.core :refer :all])
  (:import [linked_lists.core Cons]))

;; # The Tests
;;
;; We are going to use [spelj](https://github.com/slagyr/speclj) for our tests.

;;passed grading script
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

          (it "should work with empty lists"
              (should= (Cons. 10 nil) (insert-at-end 10 nil)))

          (it "should work with lists that have data"
              (let [xx (Cons. 10 (Cons. 20 (Cons. 30 nil)))]
                (should= (Cons. 10 (Cons. 20 (Cons. 30 (Cons. 5 nil)))) (insert-at-end 5 xx))))
)

(describe "sorted insert"
          (it "creates a cons cell"
            (should-not= nil (sorted-insert 10 nil)))

          (it "should work with empty lists"
              (should= (Cons. 10 nil) (sorted-insert 10 nil)))

          (it "should work with a list"
              (should= (Cons. 10 (Cons. 20 (Cons. 30 nil))) (sorted-insert 20 (Cons. 10 (Cons. 30 nil))) ) )

          (it "should work when element is the lowest value"
              (let [xx (Cons. 10 (Cons. 20 (Cons. 30 nil)))]
                (should= (Cons. 5 (Cons. 10 (Cons. 20 (Cons. 30 nil)))) (sorted-insert 5 xx))))

          (it "should work when element is the greatest value"
              (let [xx (Cons. 10 (Cons. 20 (Cons. 30 nil)))]
                (should= (Cons. 10 (Cons. 20 (Cons. 30 (Cons. 35 nil)))) (sorted-insert 35 xx))))

          (it "should work when element is not the lowest nor the greatest value"
              (let [xx (Cons. 10 (Cons. 20 (Cons. 30 nil)))]
                (should= (Cons. 10 (Cons. 20 (Cons. 25 (Cons. 30 nil) ))) (sorted-insert 25 xx))))
          
          (it "should recycle memory"
              (let [xx (Cons. 10 (Cons. 20 (Cons. 30 (Cons. 44 (Cons. 55 nil)))))]
                (should= true (identical? (-> xx :cdr :cdr ) (-> (sorted-insert 25 xx) :cdr :cdr :cdr)))))
)


(describe "search"
          (it "should work with empty lists"
              (should= false (search 10 nil)))

          (it "should work with a list that has the element"
              (should= true (search 45 (Cons. 10 (Cons. 30 (Cons. 45 (Cons. 50 nil)))))))

          (it "should work with a list that DOES NOT have the element"
              (should= false (search 15 (Cons. 10 (Cons. 30 nil))) ) )

)

(describe "delete"
          (it "should return an empty list if the original list was empty"
              (should= nil (delete 4 nil)))
          
          (it "should delete the element from an unsorted list"
              (should= (Cons. 40 (Cons. 30 (Cons. 55 (Cons. 10 nil)))) (delete 4 (Cons. 40 (Cons. 30 (Cons. 55 (Cons. 4 (Cons. 10 nil)))))) ))

          (it "should return the original list if the element was not found"
              (let [xx (Cons. 10 (Cons. 20 (Cons. 30 (Cons. 33 (Cons. 40 (Cons. 50 nil))))))]
                (should= xx (delete 25 xx))))

          (it "should delete one copy of the element that is located in the beginning of the list"
              (let [xx (Cons. 10 (Cons. 20 (Cons. 30 (Cons. 33 (Cons. 40 (Cons. 50 nil))))))]
                (should= (Cons. 20 (Cons. 30 (Cons. 33 (Cons. 40 (Cons. 50 nil))))) (delete 10 xx) )))

          (it "should delete one copy of the element that is located in the middle of the list"
              (let [xx (Cons. 10 (Cons. 20 (Cons. 30 (Cons. 33 (Cons. 40 (Cons. 50 nil))))))]
                (should= (Cons. 10 (Cons. 20 (Cons. 30 (Cons. 40 (Cons. 50 nil))))) (delete 33 xx)) ))
          
          (it "should delete one copy of the element that is located at the end of the list"
              (let [xx (Cons. 10 (Cons. 20 (Cons. 30 (Cons. 33 (Cons. 40 (Cons. 50 nil))))))]
                (should= (Cons. 10 (Cons. 20 (Cons. 30 (Cons. 33 (Cons. 40 nil))))) (delete 50 xx)) ))
)

(describe "delete-all"
          (it "should return an empty list if the original list was empty"
              (should= nil (delete-all 4 nil)))

          (it "should delete all of the target elements from an unsorted list"
              (let [xx (Cons. 40 (Cons. 4 (Cons. 99 (Cons. 30 (Cons. 4 (Cons. 55 (Cons. 4 (Cons. 10 nil))))))))]
              (should= (Cons. 40 (Cons. 99 (Cons. 30 (Cons. 55 (Cons. 10  nil))))) (delete-all 4 xx) )))          
)

(describe "efficient-delete"
          (it "should return an empty list if the original list was empty"
              (should= nil (efficient-delete 4 nil)))

          (it "should return the original list if the element is not found"
              (let [xx (Cons. 40 (Cons. 4 (Cons. 99 (Cons. 30 (Cons. 4 (Cons. 55 (Cons. 4 (Cons. 10 nil))))))))]
              (should= true (identical? xx (efficient-delete 3 xx) ))))

          (it "should delete a copy of the target elements from an unsorted list"
              (let [xx (Cons. 40 (Cons. 4 (Cons. 99 (Cons. 30 (Cons. 4 (Cons. 55 (Cons. 4 (Cons. 10 nil))))))))]
              (should= (Cons. 40 (Cons. 99 (Cons. 30 (Cons. 4 (Cons. 55 (Cons. 4 (Cons. 10 nil))))))) (efficient-delete 4 xx) )))
          
)
(run-specs)
