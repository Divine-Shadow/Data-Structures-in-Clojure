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
)

(describe "insert-at-end"
         (it "creates a cons cell"
              (should-not= nil (insert-at-end 10 nil)))

          (it "should work with empty lists"
              (should= (Cons. 10 nil) (insert-at-end 10 nil) ))
          
          (it "should insert data correctly"
              (let [xx (Cons. 10 (Cons. 20 (Cons. 30 nil)))]
			     (let [yy (Cons. 10 (Cons. 20 (Cons. 30 (Cons. 40 nil))))]
                (should= yy (insert-at-end 40 xx) ))))
)

(describe "sorted insert"
          (it "creates a cons cell"
              (should-not= nil (sorted-insert 10 nil)))

          (it "should work with empty lists"
              (should= (Cons. 10 nil) (sorted-insert 10 nil) ))
          
          (it "should insert data correctly"
              (let [xx (Cons. 10 (Cons. 20 (Cons. 50 nil)))]
			  (let [yy (Cons. 10 (Cons. 20 (Cons. 30 (Cons. 50 nil))))]
                (should= yy (sorted-insert 30 xx) ))))
		  (it "should recycle memory" 
			  (let [yy (Cons. 10 (Cons. 20 (Cons. 30 (Cons. 50 (Cons. 60 nil)))))]
			  (should (identical? (:cdr (:cdr (sorted-insert 15 yy))) (:cdr yy)))))
)

(describe "search"
          (it "should work with empty lists"
              (should-not (search 5 nil)))
          (it "should return nil if element doesnt exist"
              (should-not (search 20 (Cons. 12 (Cons. 24 (Cons. 36 nil))))))
          (it "should return true if element does exist"
              (should (search 24 (Cons. 12 (Cons. 24 (Cons. 36 nil))))))

)

(describe "delete"
          (it "should work with empty lists"
              (should-not (delete 5 nil)))
          (it "should delete only one copy"
           (let [xx (Cons. 10 (Cons. 20 (Cons. 50 (Cons. 30 nil))))]
			  (let [yy (Cons. 10 (Cons. 20 (Cons. 30 (Cons. 50 (Cons. 30 nil)))))]
           (should= xx (delete 30 yy)))))
)

(describe "delete-all"
          (it "should work with empty lists"
              (should-not (delete 5 nil)))
          (it "should delete one copy"
           (let [xx (Cons. 10 (Cons. 20 (Cons. 50 nil)))]
			  (let [yy (Cons. 10 (Cons. 20 (Cons. 30 (Cons. 50 nil))))]
           (should= xx (delete-all 30 yy)))))
          (it "should delete more than one copy"
           (let [xx (Cons. 20 (Cons. 50 nil))]
			  (let [yy (Cons. 30 (Cons. 20 (Cons. 30 (Cons. 50 nil))))]
           (should= xx (delete-all 30 yy)))))

)

(describe "efficient-delete"
          (it "should work with empty lists"
              (should-not (delete 5 nil)))
          (it "should delete only one copy"
           (let [xx (Cons. 10 (Cons. 20 (Cons. 50 nil)))]
			  (let [yy (Cons. 10 (Cons. 20 (Cons. 30 (Cons. 50 nil))))]
           (should= xx (delete 30 yy)))))
          (it "should return the original list if elt doesn't exist)"
           (let [xx (Cons. 10 (Cons. 20 (Cons. 50 nil)))]
           (should (identical? xx (efficient-delete 5 xx)))))
)
(run-specs)
