(ns linked_lists.core-spec
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
(it "should work with empty lists"
              (should= (Cons. 10 nil) (insert-at-end 10 nil) ))
it "should work with lists that have data"
              (let [xx (Cons. 10 (Cons. 20 (Cons. 30 nil)))]
                (should= (Cons. xx 5) (insert-at-end 5 xx) )))




(describe "sorted insert"
 it "should work with empty lists"
              (should= (Cons. 10 nil) (sorted-insert 10 nil) )
it "should work for the first element"
              (let [xx (Cons. 10 (Cons. 20 (Cons. 30 nil)))]
                (should= (Cons. 5 xx) (sorted-insert 5 xx) ))
it "should work for the last  element"
              (let [xx (Cons. 10 (Cons. 20 (Cons. 30 nil)))]
                (should= (Cons. xx 35) (sorted-insert 35 xx) )))



(describe "search"
          it "should work with empty lists"
              (should= nil (sorted-insert 10 nil) )

              it "should work with lists that have don't have the element"
              (let [xx (Cons. 10 (Cons. 20 (Cons. 30 nil)))]
                (should= nil (search 5 xx) ))
it "should work with lists that have the element"
              (let [xx (Cons. 5 (Cons. 20 (Cons. 30 nil)))]
                (should= true (search 5 xx) )))




(describe "delete"
           it "should work with empty lists"
              (should= nil (delete 10 nil) )

              it "should work with lists that have don't have the element"
              (let [xx (Cons. 10 (Cons. 20 (Cons. 30 nil)))]
                (should= xx (delete 5 xx) ))
it "should work with lists that have the element"
              (let [xx (Cons. 5 (Cons. 20 (Cons. 30 nil)))]
                (should= (Cons. 20 (Cons. 30 nil)) (delete 5 xx) )))




(describe "delete-all"
                it "should work with empty lists"
              (should= nil (delete-all 10 nil) )

              it "should work with lists that have don't have the element"
              (let [xx (Cons. 10 (Cons. 20 (Cons. 30 nil)))]
                (should= xx (delete-all 5 xx) ))
it "should work with lists that have the element"
              (let [xx (Cons. 5 (Cons. 20 (Cons. 30 nil)))]
                (should= (Cons. 20 (Cons. 30 nil)) (delete-all 5 xx) ))

it "should work with lists that have the element more than once"
              (let [xx (Cons. 5 (Cons. 20 (Cons. 5 nil)))]
                (should= (Cons. 20 nil) (delete-all 5 xx) )))


(describe "efficient-delete"
        
              it "should work with lists that have don't have the element"
              (let [xx (Cons. 10 (Cons. 20 (Cons. 30 nil)))]
                (should= xx (efficient-delete 5 xx) ))
              it "should work with lists that have the element"
              (let [xx (Cons. 5 (Cons. 20 (Cons. 30 nil)))]
                (should= (Cons. 20 (Cons. 30 nil)) (efficient-delete 5 xx) ))
              it "should have the same memory location as original"
               (let [xx (Cons. 5 (Cons. 20 (Cons. 30 nil)))]
              (should equal? xx (efficient-delete 15 xx) ))
)
(run-specs)
