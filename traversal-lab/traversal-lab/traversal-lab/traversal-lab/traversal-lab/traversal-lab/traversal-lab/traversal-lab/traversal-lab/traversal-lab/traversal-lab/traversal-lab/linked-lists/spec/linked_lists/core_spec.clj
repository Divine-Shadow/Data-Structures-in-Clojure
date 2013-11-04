(ns linked_lists.core-spec
  (:require [speclj.core :refer :all]
            [linked_lists.core :refer :all])
  (:import [linked_lists.core Cons]))

;; # The Tests
;;
;; We are going to use [spelj](https://github.com/slagyr/speclj) for our tests.

(def my-list (Cons. 2 (Cons. 6 (Cons. 15 (Cons. 24 nil)))))
(def my-mul-list (Cons. 2 (Cons. 2 (Cons. 15 (Cons. 24 nil)))))


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
          (it "creates a con cell at the end"
              (should (insert-at-end 10 nil)))
          
          (it "should work with empty lists"
              (should= (Cons. 10 nil) (insert-at-end 10 nil)))

          (it "should work with lists that have data"
                (should= 5  (-> (insert-at-end 5 my-list) :cdr :cdr :cdr :cdr :car))))

          

          

(describe "sorted insert"
          (it "returns the list when done"
              (should (sorted-insert 10 nil) ))
          
          (it "should work on empty lists"
             (should= (Cons. 10 nil) (sorted-insert 10 nil))
             )
          (it "should work with lists that have data"
              (should= 5 (-> (sorted-insert 5 my-list) :cdr :car))
          )
          
         ;; (it " should not recycle memory" 
           ;;   (should= ( (sorted-insert 5 my-list)(my-list)))
          
          ;;)
 )

(describe "search"
          (it "return true if the element is found"
              (should (search 2 my-list)))
          
          (it "return false if the elemnt is not found"
              (should-not (search 5 my-list)))
          
)

(describe "delete"
          (it "removes a con cell"
              (should= (Cons. 2 (Cons. 15 (Cons. 24 nil))) (delete 6 my-list))
           )
          
          ;;(it "removes all copies"
            ;;  (should= (Cons. 15 (Cons. 24 nil))(delete 2 my-mul-list))
            
            ;;)
          
         
          
)

(describe "delete-all"
          (it "should remove all elements from the list"
              (should=  (Cons. 6 ( Cons. 15 (Cons. 24 nil))) (delete-all 2 my-list))
           )
          
           (it "Make sure it removes all copies"
              (should=  (Cons. 15 (Cons. 24 nil)) (delete-all 2 my-mul-list))
          )
)

(describe "efficient-delete"
          (it "should remove a con cell"
              (should= (Cons. 2 (Cons. 15 (Cons. 24 nil))) (efficient-delete 6 my-list))
          )
          
          (it "does not return the original"
              (should (identical? my-list (efficient-delete 3 my-list)))
            
           )
)
(run-specs)
