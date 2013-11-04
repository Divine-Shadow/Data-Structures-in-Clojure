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
                (should=(Cons. 5 xx) (insert-at-beginning 5 xx) ))))

(describe "insert-at-end"
          (it "should work with empty list"
              (should= (Cons. 5 nil)(insert-at-end 5 nil)))

	  (it "should work with lists that have data"
	  (let [xx (Cons. 1 (Cons. 2 (Cons. 3 nil)))]
	  	(should= (Cons. 1 (Cons. 2 (Cons. 3 (Cons. 4 nil)))) (insert-at-end 4 xx))))
)

(describe "sorted insert"
          (it "should work with an empty list"
              (should= (Cons. 10 nil) (sorted-insert 10 nil)))

          (it "should work with lists that have data"
              (let [xx (Cons. 1 (Cons. 2 (Cons. 4 nil)))]
                (should= (Cons. 1(Cons. 2(Cons. 3(Cons. 4 nil)))) (sorted-insert 3 xx))))

         (it "should recycle data"
             (let [xx (Cons. 1 (Cons. 2 (Cons. 4 nil )))]
               (should= true (identical? (:cdr (:cdr xx)) (:cdr(:cdr(:cdr (sorted-insert 3 xx))))))))

)

(describe "search"
          (it "should know when list doesn't have the element"
           (let [xx (Cons. 1 (Cons. 2 (Cons. 3 nil)))]
            (should= false (search 5 xx))))

          (it "should know when list has the element"
           (let [xx (Cons. 1 (Cons. 2 (Cons. 3 nil)))]
             (should= true (search 1 xx))))
)

(describe "delete"
          (it "should work with empty lists"
              (should= nil (delete 4 nil)))
          (it "should work with sorted lists"
              (let [xx (Cons. 1 (Cons. 2 (Cons. 3 nil)))]
                (should= (Cons. 1 (Cons. 3 nil)) (delete 2 xx))))
          (it "should work with unsorted lists"
              (let [xx (Cons. 1(Cons. 4(Cons. 7 nil)))]
                (should= (Cons. 1(Cons. 7 nil)) (delete 4 xx))))
)

(describe "delete-all"
          (it "should work with empty list"
              (should= nil (delete-all 10 nil)))
          (it "should work with lists that have data"
              (let [xx (Cons. 1(Cons. 2(Cons. 2(Cons. 2(Cons. 3(Cons. 4 nil))))))]
                (should= (Cons. 1(Cons. 3(Cons. 4 nil))) (delete-all 2 xx))))
)

(describe "efficient-delete"
          (it "should work with empty list"
              (should= nil (efficient-delete 10 nil)))
          
           (it "should work with lists that have data"  
           (let [xx (Cons. 1(Cons. 2(Cons. 2(Cons. 3 nil))))]
                (should= (Cons. 1(Cons. 2(Cons. 3 nil))) (efficient-delete 2 xx))))

           (it "should return original list if elt doesn't exist"
               (let [xx (Cons. 1(Cons. 2(Cons. 3(Cons. 4 nil))))]
                 (should= true (identical? xx (efficient-delete 5 xx)))))
           
)
(run-specs)
