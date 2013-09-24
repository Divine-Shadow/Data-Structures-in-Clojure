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
          (it "Should contain the same first values, with the right value at the end"
              (should= (Cons. 1 (Cons. 2 (Cons. 3 nil)))
 (insert-at-end 3 (Cons. 1 (Cons. 2 nil)))))
)

(describe "sorted insert"
          (it "should add a value to the middle"
              (should= (Cons. 1 (Cons. 2 3)) (sorted-insert 2 (Cons. 1 3)
))))



(describe "search"
          (it "should find a value that exists"
              (should= true (search 3 (Cons. 1 (Cons. 2 (Cons. 3 4)
))   ))
)

         (it "should not find a value that doesn't exists"
              (should= false (search 9 (Cons. 1 (Cons. 2 (Cons. 3 4)
))   ))
)


)
(describe "delete"
          (it "Should remove an element"
              (should= (Cons. 1 3) (delete 2 (Cons. 1 (Cons. 2 3))))
)
                   (it "should find not remove an unspecified values"
              (should=  (Cons. 1 (Cons. 2 (Cons. 3 4)
)) (delete 7 (Cons. 1 (Cons. 2 (Cons. 3 4)
))   ))
)

)

(describe "delete-all"
           (it "should find not remove an unspecified values"
              (should=  (Cons. 1 (Cons. 2 (Cons. 3 4)
)) (delete-all 7 (Cons. 1 (Cons. 2 (Cons. 3 4)
))   ))
)

  (it "should remove all copies of a value"
              (should=  (Cons. 1 3)
 (delete-all 2 (Cons. 1 (Cons. 2 (Cons. 3 2)
))   ))
)


)

(describe "efficient-delete"
          (it "should return the original list if the item is not found"
              
(let [p (Cons. 1 (Cons. 2 (Cons. 3 nil)))]
(should (identical? p (efficient-delete 4 p))))
)

          (it "Should remove an element"
              (should= (Cons. 1 3) (efficient-delete 2 (Cons. 1 (Cons. 2 3)))))


)
(run-specs)
