(ns fifolifo.core-spec
  (:refer-clojure :exclude [pop peek])
  (:require [speclj.core :refer :all]
            [fifolifo.core :refer :all])
  (:import [fifolifo.core Stack Queue]))

;; # The Tests
;;
;; We are going to use [spelj](https://github.com/slagyr/speclj) for our tests.


(describe "The stack declaration"

          (it "should create something."
              (should (make-stack)))

          (it "should have empty components."
              (should= (Stack. nil 0) (make-stack)))

          (it "should have a size of zero."
              (should= 0 (stack-size (make-stack))))

          (it "should push elts"
              (let [xx (Stack. (cons 2 nil) 1)]
                (should= (Stack. (cons 3 (cons 2 nil)) 2) (push xx 3))))


          )

(describe "The pop function"

          (it "should work when empty."
              (let [xx (make-stack)]
                (should= (Stack. nil 0) (pop xx))))

          (it "should decrement size."
              (let [xx (Stack. (cons 3 (cons 2 nil)) 2)]
                (should= (Stack. (cons 2 nil) 1) (pop xx))))

          (it "should not reorder elements."
              (let [xx (Stack. (cons 4 (cons 3 (cons 2 (cons 1 nil)))) 4)]
                (should= (Stack. (cons 3 (cons 2 (cons 1 nil))) 3) (pop xx))))
          )

(describe "The queue declaration"

          (it "should create something."
              (should (make-queue)))

          (it "should have empty components."
              (should= (Queue. nil nil 0) (make-queue)))

          (it "should have a size of zero."
              (should= 0 (queue-size (make-queue))))
          )

(describe "The dequeue function"

          (it "should work when empty."
              (let [xx (make-queue)]
                (should= (Queue. nil nil 0) (dequeue xx))))

          (it "should reverse properly."
              (let [xx (Queue. (cons 30 (cons 20 (cons 10 nil))) nil 3)]
                (should= (Queue. nil (cons 20 (cons 30 nil)) 2) (dequeue xx)))
          )
          )

(describe "The enqueue function"

          (it "should add elements."
              (let [xx (make-queue)]
                (should= (Queue. (cons 2 nil) nil 1) (enqueue xx 2))))
          )



(run-specs)









