(ns linked_lists.core-spec
  (:require [speclj.core :refer :all]
            [linked_lists.core :refer :all])
  (:import [linked_lists.core Cons]))

;; # The Tests
;;
;; We are going to use [spelj](https://github.com/slagyr/speclj) for our tests.

(def longlist (Cons. 1 (Cons. 2 (Cons. 3 (Cons. 4 (Cons. 5 (Cons. 6 (Cons. 7 nil))))))))

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
          (it "should insert an item at the end"
              (should= (Cons. 5 nil) 
                       (-> (insert-at-end 5 (Cons. 1 (Cons. 2 (Cons. 3 (Cons. 4 nil))))) :cdr :cdr :cdr :cdr)))
          )

(describe "sorted insert"
          (it "should insert an item in the correct location"
              (should= (Cons. 1 (Cons. 2 (Cons. 3 (Cons. 4 (Cons. 5 nil)))))
                       (sorted-insert 4 (Cons. 1 (Cons. 2 (Cons. 3 (Cons. 5 nil)))))
                       ))
          (it "should put an item at the end if it doesnt fit in the middle" 
              (should= (Cons. 1 (Cons. 2 (Cons. 3 (Cons. 5 (Cons. 6 nil)))))
                       (sorted-insert 6 (Cons. 1 (Cons. 2 (Cons. 3 (Cons. 5 nil)))))
                       ))
          (it "should recycle memory"
              (should (identical? 
                        (-> longlist :cdr :cdr :cdr)
                        (-> (sorted-insert 3 longlist) :cdr :cdr :cdr :cdr))
                      )
              )
)
(describe "search"
          (it "should find an item"
              (should (search 5 longlist)))
          (it "should return nil, if no item exists" 
              (should-not (search 18 longlist)))
          )
          

(describe "delete"
          (it "delete a single item"
              (should= (Cons. 1 (Cons. 2 (Cons. 3 (Cons. 4 (Cons. 5 (Cons. 7 nil))))))
                       (delete 6 longlist)
                       ))
          (it "should not delete multiple items"
              (should-not= (Cons. 1 (Cons. 2 (Cons. 3 (Cons. 4 (Cons. 5 (Cons. 7 nil))))))
                           (delete 6 (Cons. 1 (Cons. 2 (Cons. 3 (Cons. 6 (Cons. 4 (Cons. 5 (Cons. 6 (Cons. 7 nil))))))))))
                           )
)

(describe "delete-all"
          (it "delete a single item"
              (should= (Cons. 1 (Cons. 2 (Cons. 3 (Cons. 4 (Cons. 5 (Cons. 7 nil))))))
                       (delete-all 6 longlist)
                       ))
          (it "should delete multiple items"
              (should= (Cons. 1 (Cons. 2 (Cons. 3 (Cons. 4 (Cons. 5 (Cons. 7 nil))))))
                           (delete-all 6 (Cons. 1 (Cons. 2 (Cons. 3 (Cons. 6 (Cons. 4 (Cons. 5 (Cons. 6 (Cons. 7 nil))))))))))
              )
)

(describe "efficient-delete"
          (it "should return the same list rather than a copy"
              (should (identical? longlist (efficient-delete 43 longlist )))
              )
          (it "delete a single item"
              (should= (Cons. 1 (Cons. 2 (Cons. 3 (Cons. 4 (Cons. 5 (Cons. 7 nil))))))
                       (efficient-delete 6 longlist)
                       ))
          (it "should not delete multiple items"
              (should-not= (Cons. 1 (Cons. 2 (Cons. 3 (Cons. 4 (Cons. 5 (Cons. 7 nil))))))
                           (efficient-delete 6 (Cons. 1 (Cons. 2 (Cons. 3 (Cons. 6 (Cons. 4 (Cons. 5 (Cons. 6 (Cons. 7 nil))))))))))
              )
          )
(run-specs)
