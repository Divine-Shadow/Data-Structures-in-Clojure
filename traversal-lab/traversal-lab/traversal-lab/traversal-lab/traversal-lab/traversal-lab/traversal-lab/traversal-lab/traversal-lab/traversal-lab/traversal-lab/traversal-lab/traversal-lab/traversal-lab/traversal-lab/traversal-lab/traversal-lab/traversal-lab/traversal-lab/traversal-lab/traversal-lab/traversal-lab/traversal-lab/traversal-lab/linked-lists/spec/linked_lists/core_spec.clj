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
          (it "creates a cons cell."
              (should-not= nil(insert-at-end 10 nil)))

          (it "should work with empty lists"
              (should= (Cons. 10 nil) (insert-at-end 10 nil)))

          (it "should work with lists that have data"
              (should=(Cons. 10 nil)(insert-at-end 10 nil)))
)

(describe "sorted insert"
          (it "creates a cons cell"
              (should-not= nil (sorted-insert 10 nil)))

          (it "should work with empty lists"
              (should= (Cons. 10 nil) (sorted-insert 10 nil) ))
          
          (it "should put the element at the beginning if it's the smallest."
              (let [xx (Cons. 10 (Cons. 20 (Cons. 30 nil)))]
                (should= (Cons. 5 xx) (sorted-insert 5 xx) )))

          (it "should put the element at in the middle in the average case."
              (let [xx (Cons. 10 (Cons. 20 (Cons. 30 (Cons. 40 nil))))
                    yy (sorted-insert 25 xx)]
                (should= (Cons. 10 (Cons. 20 (Cons. 25 (Cons. 30 (Cons. 40 nil))))) yy)))

          (it "should not duplicate the remainder of the list after the insertion point."
              (let [xx (Cons. 10 (Cons. 20 (Cons. 30 (Cons. 40 nil))))
                    yy (sorted-insert 25 xx)]
                (should (identical? (-> xx :cdr :cdr) (-> yy :cdr :cdr :cdr)))))
          
          (it "should put the element at the end if it's the last element."
              (should= (Cons. 10 (Cons. 20 (Cons. 30 nil))) (sorted-insert 30 (Cons. 10 (Cons. 20 nil))))))




(describe "search"
          (it "should find things that are in the list."
              (should (search 4 (Cons. 2 (Cons. 4 (Cons. 10 nil)))))
              (should (search 10 (Cons. 2 (Cons. 4 (Cons. 10 nil)))))
              (should (search 2 (Cons. 2 (Cons. 4 (Cons. 10 nil))))))

          (it "should not find things that are not in the list."
              (should-not (search 4 nil))
              (should-not (search 4 (Cons. 2 nil)))
              (should-not (search 4 (Cons. 2 (Cons. 6 nil)))))
          
)

(describe "delete"
                    (let [xx (Cons. 2 (Cons. 4 (Cons. 2 (Cons. 10 nil))))]
            (it "should delete things once that are in the list."
                (should= (Cons. 2 (Cons. 2 (Cons. 10 nil))) (delete 4 xx))
                (should= (Cons. 4 (Cons. 2 (Cons. 10 nil))) (delete 2 xx))
                (should= (Cons. 2 (Cons. 4 (Cons. 2 nil))) (delete 10 xx))
                ))
            
          (let [xx (Cons. 2 (Cons. 4 (Cons. 2 (Cons. 10 nil))))]
            (it "should not delete that are not in the list."
                (should= nil (delete 10 nil))
                (should= xx (delete 1 xx))
                (should= xx (delete 23 xx)))
            )

)

(describe "delete-all"
          (let [xx (Cons. 2 (Cons. 4 (Cons. 2 (Cons. 10 nil))))]
            (it "should delete things completely that are in the list."
                (should= (Cons. 2 (Cons. 2 (Cons. 10 nil))) (delete-all 4 xx))
                (should= (Cons. 4 (Cons. 10 nil)) (delete-all 2 xx))
                (should= (Cons. 2 (Cons. 4 (Cons. 2 nil))) (delete-all 10 xx))
                ))

          (let [xx (Cons. 2 (Cons. 4 (Cons. 2 (Cons. 10 nil))))]
            (it "should not delete-all that are not in the list."
                (should= nil (delete-all 10 nil))
                (should= xx (delete-all 1 xx))
                (should= xx (delete-all 23 xx)))
            )

)

(describe "efficient-delete"
                    (let [xx (Cons. 2 (Cons. 4 (Cons. 2 (Cons. 10 nil))))]
            (it "should delete things once that are in the list."
                (should= (Cons. 2 (Cons. 2 (Cons. 10 nil))) (efficient-delete 4 xx))
                (should= (Cons. 4 (Cons. 2 (Cons. 10 nil))) (efficient-delete 2 xx))
                (should= (Cons. 2 (Cons. 4 (Cons. 2 nil))) (efficient-delete 10 xx))
                ))

          (let [xx (Cons. 2 (Cons. 4 (Cons. 2 (Cons. 10 nil))))]
            (it "should not duplicate a list if the victim is not in it."
                (should= nil (efficient-delete 10 nil))
                (should (identical? xx (efficient-delete 1 xx)))
                (should (identical? xx (efficient-delete 23 xx))))
            )
          
)
(run-specs)
