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
          
          (it "should have the proper size after pushes."
              (should= 3 (:size (push 3 (push 2 (push 1 (make-stack))))))
          )
          
          (it "should push elements to the beginning of the stack."
              (should= (Stack. '(3 2 1) 3) (push 3 (push 2 (push 1 (make-stack)))))
          )
          
          (it "should 'pop' elements from the top of the stack."
              (should= (Stack. '(1) 1) (pop (pop (Stack. '(3 2 1) 3)))))
          
          (it "should keep the order when popping elements."
              (should= (Stack. '(4 3 2 1) 4) (pop (Stack. '(5 4 3 2 1) 5))))

          (it "should keep the same queue of an empty 'pop'-ed stack."
              (should= (make-stack) (pop (make-stack))))
          
          (it "should have a size of 0 when an empty stack is poped."
              (should= 0 (:size (pop (make-stack)))))

          (it "should return the 'top' of the stack."
              (should= 3 (:top (Stack. '(3 2 1) 3))))


(describe "The queue declaration"

          (it "should create something."
              (should (make-queue)))

          (it "should have empty components."
              (should= (Queue. nil nil 0) (make-queue)))
          
          (it "should have a size of zero."
              (should= 0 (stack-size (make-stack))))
          )

          (it "should increase size when enqueue."
              (should= 3 (:size (enqueue (enqueue (enqueue (make-queue) 1) 2) 3))))

          (it "should keep size zero upon dequeue of empty queue."
              (should= 0 (:size (dequeue (make-queue)))))

          (it "should reverse if flip needed in dequeue."
              (should= (Queue. '(1 2 3 4) nil 4) (Queue. nil '(5 4 3 2 1) 5)))

(run-specs)
