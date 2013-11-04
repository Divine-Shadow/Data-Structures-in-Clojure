(ns dlist-atom.core-spec
;(:refer-clojure :exclude [])
  (:require [speclj.core :refer :all]
            [dlist-atom.core :refer :all])
;(:import [dlist-atom.core ])
  )

;; # The Tests
;;
;; We are going to use [spelj](https://github.com/slagyr/speclj) for our tests.

(describe "The true"
          (it "should true"
              (should true)))

(describe "The insert at front function"
          (it "should insert an element at the front of empty list"
                (should= (show-dlist (insert-front (dlist) 5)) '(5)))

          (it "should insert element at front of populated list"
              (let [x (insert-front (dlist) 5)]
                (should= (show-dlist (insert-front x 10)) '(10 5))))

          (it "should insert-front multiple times"
              (should= (show-dlist (insert-front (insert-front (insert-front (dlist) 3) 2) 1)) '(1 2 3)))
)

(describe "The insert at last function"
          (it "should insert an element in an empty dlist"
              (should= (show-dlist (insert-last (dlist) 5)) '(5)))

          (it "should insert element at end of populated dlist"
              (let [x (insert-front (dlist) 5)]
                (should= (show-dlist (insert-last x 10)) '(5 10))))
          
          (it "should insert-last multiple times"
              (should= (show-dlist (insert-last (insert-last (insert-last (dlist) 3) 2) 1)) '(3 2 1)))
)

(describe "The sorted insert function"
          (it "should insert the smallest element"
              (should= (show-dlist (insert-sorted (insert-front (insert-front (dlist) 5) 3) 1)) '(1 3 5)))

          (it "should insert to an empty dlist"
              (should= (show-dlist (insert-sorted (dlist) 20)) '(20)))
          
          (it "should insert the largest element"
              (should= (show-dlist (insert-sorted (insert-front (insert-last (insert-front (dlist) 5) 10) 3) 50)) '(3 5 10 50)))

          (it "should insert to the middle"
              (should= (show-dlist (insert-sorted (insert-front (insert-front (dlist) 5) 2) 4)) '(2 4 5)))
)

(describe "The index-forward function"
          (it "should work with empty dlists"
              (should= (index-forward (dlist) 5) nil))
          
          (it "should work with populated dlists"
              (let [x (insert-front (insert-front (insert-front (dlist) 50) 20) 10)]
                (should= (index-forward x 20) 1)))

          (it "should work if element is not in the dlist"
              (let [x (insert-front (insert-front (insert-front (dlist) 50) 20) 10)]
                (should= nil (index-forward x 30))))
)

(describe "The index-backward function"
          (it "should work with empty dlists"
              (should= (index-backward (dlist) 5) nil))
          
          (it "should work with populated dlists"
              (let [x (insert-front (insert-front (insert-front (dlist) 50) 20) 10)]
                (should= (index-backward x 20) -2)))

          (it "should work if element is not found"
              (let [x (insert-front (insert-front (dlist) 50) 30)]
                (should= (index-backward x 20) nil)))
)

(describe "The list-to-dlist function"
          (it "should convert an empty list"
              (let [x '()]
                (should= (dlist) (list-to-dlist x))))

          (it "should convert populated lists"
              (let [x '(1 2 3)]
                (should= (list-to-dlist x) (insert-front (insert-front (insert-front (dlist) 3) 2) 1))))
)

(describe "The delete function"
          (it "should return original if element not found"
              (let [x (insert-front (insert-front (dlist) 10) 5)]
                (should= (delete x 20) x)))

          (it "should work with finding victim"
              (let [x (insert-front (insert-front (dlist) 10) 5)]
                (should= (delete x 5) (insert-front (dlist) 10))))
)

              
(run-specs)
