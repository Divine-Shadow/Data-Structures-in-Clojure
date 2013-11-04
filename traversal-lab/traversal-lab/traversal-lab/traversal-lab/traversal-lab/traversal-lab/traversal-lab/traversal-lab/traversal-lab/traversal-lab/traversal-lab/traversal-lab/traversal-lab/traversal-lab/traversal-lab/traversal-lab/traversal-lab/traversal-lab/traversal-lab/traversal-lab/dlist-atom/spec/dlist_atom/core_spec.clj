(ns dlist-atom.core-spec
;  (:refer-clojure :exclude [])
  (:require [speclj.core :refer :all]
            [dlist-atom.core :refer :all])
;  (:import [dlist-atom.core ])
  )

;; # The Tests
;;
;; We are going to use [spelj](https://github.com/slagyr/speclj) for our tests.


(describe "insert-front"
          (it "should work with an empty list"
              (let [xx (dlist)]
                (insert-front xx 2)
                (should= '(2) (show-dlist xx))))
          (it "should work with lists with data"
              (let [xx (dlist)]
                (insert-front xx 2)
                (insert-front xx 3)
                (should= '(3 2) (show-dlist xx))))
          (it "should increment size"
              (let [xx (dlist)]
                (insert-front xx 2)
                (should= 1 @(:size xx)))))
(describe "insert-last"
          (it "should work with an empty list"
              (let [xx (dlist)]
                (insert-last xx 2)
                (should= '(2) (show-dlist xx))))
          (it "should work with lists with data"
              (let [xx (dlist)]
                (insert-front xx 2)
                (insert-last xx 3)
                (should= '(2 3) (show-dlist xx)))))

(describe "insert-sorted"
          (it "should work with an empty list"
              (let [xx (dlist)]
                (insert-sorted xx 1)
                (should= '(1) (show-dlist xx))))
          (it "should work with a list with data"
              (let [xx (dlist)]
                (insert-sorted xx 1)
                (insert-sorted xx 3)
                (insert-sorted xx 2)
                (should= '(1 2 3) (show-dlist xx)))))

(describe "index-forward"
          (it "should work if the element is found in the list"
              (let [xx (dlist)]
                (insert-front xx 1)
                (insert-front xx 2)
                (insert-front xx 3)
                (should= 0 (index-forward xx 3))))
          (it "should return nil for empty lists"
              (let [xx (dlist)]
                (should= nil (index-forward xx 2))))
          (it "should return nil if the element is not found"
              (let [xx (dlist)]
                (insert-front xx 1)
                (should= nil (index-forward xx 2)))))
(describe "index-backward"
          (it "should return negative numbers"
              (let [xx (dlist)]
                (insert-front xx 1)
                (insert-front xx 2)
                (insert-front xx 3)
                (should= -1 (index-backward xx 1))))
          (it "should return the middle numbers as negatives"
              (let [xx (dlist)]
                (insert-front xx 1)
                (insert-front xx 2)
                (insert-front xx 3)
                (should= -2 (index-backward xx 2))))
          (it "should return nil if not there"
              (let [xx (dlist)]
                (insert-front xx 2)
                (should= nil (index-backward xx 1))))
          (it "should return nil if an empty list"
              (let [xx (dlist)]
                (should= nil (index-backward xx 2)))))          
(describe "delete"
          (it "should delete from the front of the list"
              (let [xx (dlist)]
                (insert-front xx 1)
                (insert-front xx 2)
                (insert-front xx 4)
                (insert-front xx 3)
                (delete xx 3)
                (should= '(4 2 1) (show-dlist xx))))
          (it "should delete from the middle of the list"
              (let [xx (dlist)]
                (insert-front xx 1)
                (insert-front xx 2)
                (insert-front xx 4)
                (insert-front xx 3)
                (delete xx 2)
                (should= '(3 4 1) (show-dlist xx)))))
(describe "show-dlist-reverse"
          (it "should reverse a dlist"
              (let [xx (dlist)]
                (insert-front xx 1)
                (insert-front xx 2)
                (insert-front xx 3)
                (insert-front xx 4)
                (should= '(1 2 3 4) (show-dlist-reverse xx))))
          (it "should reverse a non-sorted dlist"
              (let [xx (dlist)]
                (insert-front xx 1)
                (insert-front xx 4)
                (insert-front xx 2)
                (insert-front xx 3)
                (should= '(1 4 2 3) (show-dlist-reverse xx)))))

(run-specs)
