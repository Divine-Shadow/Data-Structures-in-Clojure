(ns linked_lists.core-spec
  (:require [speclj.core :refer :all]
            [linked_lists.core :refer :all])
  (:import [linked_lists.core Cons]))

;; # The Tests
;;
;; We are going to use [speclj](https://github.com/slagyr/speclj) for our tests.


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
              (should= (Cons. 10 nil) (insert-at-end 10 nil)))
          (it "should work with lists that have data"
              (let [xx (Cons. 10 (Cons. 20 (Cons. 30 nil)))] 
                (should= (insert-at-end 40 xx) (Cons. 10 (Cons. 20 (Cons. 30 (Cons. 40 nil)))))))
)

(describe "sorted insert"
          (it "creates a cons cell"
              (should-not= nil (sorted-insert 10 nil)))
          (it "should work with empty lists"
              (should= (Cons. 10 nil) (sorted-insert 10 nil)))
          (it "should work with lists that have data"
              (should= (sorted-insert 40 (Cons. 10 (Cons. 20 (Cons. 30 (Cons. 50 nil)))))
                                         (Cons. 10 (Cons. 20 (Cons. 30 (Cons. 40 (Cons. 50 nil)))))))
          (it "should recycle memory"
              (let [xx (Cons. 10 (Cons. 20 (Cons. 30 nil)))]
                (should(identical? xx (:cdr (sorted-insert 5 xx))))))
)

(describe "search"
          (it "should work with empty lists"
              (should= false (search 10 nil)))
          (it "should work with lists that have data"
              (should= true (search 20 (Cons. 10 (Cons. 20 (Cons. 30 nil))))))
)

(describe "delete"
          (it "should work with empty lists"
              (should= nil (delete 10 nil)))
          (it "should work with lists that have data"
              (should= (Cons. 10 (Cons. 20 (Cons. 30 nil))) 
                       (delete 40 (Cons. 10 (Cons. 20 (Cons. 30 (Cons. 40 nil)))))))
          (it "should not remove all copies"
              (should-not= (Cons. 10 (Cons. 20 nil)) 
                           (delete 40 (Cons. 40 (Cons. 10 (Cons. 20 (Cons. 40 nil)))))))
)

(describe "delete-all"
           (it "should work with empty lists"
              (should= nil (delete-all 10 nil)))
           (it "should work with lists that have data"
               (should= (Cons. 10 (Cons. 20 nil)) 
                        (delete-all 40 (Cons. 40 (Cons. 10 (Cons. 20 (Cons. 40 (Cons. 40 nil))))))))
)

(describe "efficient-delete"
          (it "should work with empty lists"
              (should= nil (efficient-delete 10 nil)))
          (it "should work with lists that have data"
              (should= (Cons. 10 (Cons. 20 (Cons. 30 nil))) 
                       (efficient-delete 40 (Cons. 10 (Cons. 20 (Cons. 30 (Cons. 40 nil)))))))
          (it "should return original if item does not exist"
              (let [xx (Cons. 10 (Cons. 20 (Cons. 30 nil)))]
                (should(identical? (efficient-delete 40 xx) xx))))
)
(run-specs)
