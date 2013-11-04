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
          )


(describe "The queue declaration"

          (it "should create something."
              (should (make-queue)))

          (it "should have empty components."
              (should= (Queue. nil nil 0) (make-queue)))
          
          (it "should have a size of zero."
              (should= 0 (queue-size (make-queue))))
          )

(describe "stack-size"

          (it "should return the size of an empty stack"
              (should= 0 (stack-size (make-stack))))

          (it "should return the size of a list with data"
              (should= 1 (stack-size (Stack. '(3) 1)))))

(describe "push"
          
          (it "should push an element onto the beginning of an empty stack"
              (should= (Stack. '(2) 1) (push (make-stack) 2)))
          (it "should push an element onto the beginning of a stack with data"
              (should= (Stack. '(3 2) 2) (push (Stack. '(2) 1) 3))))

(describe "pop"
          
          (it "should do nothing for an element with an empty stack"
              (should= (Stack. nil 0) (pop (make-stack))))
          (it "should remove the element from the top of a stack with more than one element"
              (should= (Stack. '(2) 1) (pop (Stack. '(3 2) 2)))))

(describe "top"
          
          (it "should return the top of an empty stack"
              (should= nil (top (make-stack))))
          (it "should return the top of a stack with a single element"
              (should= 1 (top (Stack. '(1) 1))))
          (it "should return the top of a stack with multiple elements"
              (should= 3 (top (Stack. '(3 2) 2)))))

(describe "queue-size"
          
          (it "should return the size of an empty queue"
              (should= 0 (queue-size (make-queue))))
          (it "should return the size of a queue with data"
              (should= 1 (queue-size (Queue. '(2) nil 1)))))

(describe "enqueue"
          
          (it "should add an element to the back of an empty queue"
              (should= (Queue. '(1) nil 1) (enqueue (make-queue) 1)))
          (it "should add an element to the back of a queue with data"
              (should= (Queue. '(1 2) nil 2) (enqueue (Queue. '(2) nil 1) 1)))
          (it "should add an element to the back of a queue with data already in the front"
              (should= (Queue. '(1 2) '(3) 3) (enqueue (Queue. '(2) '(3) 2) 1))))

(describe "dequeue"
          
          (it "should do nothing to an empty queue"
              (should= (Queue. nil nil 0) (dequeue (make-queue)))))

(describe "peek"
          
          (it "should return the next element that will come out of the front of an empty queue"
              (should= nil (peek (make-queue))))
          (it "should return the next element that will come out of the front of a queue with data"
              (should= 1 (peek (Queue. nil '(1) 1)))))

(run-specs)
