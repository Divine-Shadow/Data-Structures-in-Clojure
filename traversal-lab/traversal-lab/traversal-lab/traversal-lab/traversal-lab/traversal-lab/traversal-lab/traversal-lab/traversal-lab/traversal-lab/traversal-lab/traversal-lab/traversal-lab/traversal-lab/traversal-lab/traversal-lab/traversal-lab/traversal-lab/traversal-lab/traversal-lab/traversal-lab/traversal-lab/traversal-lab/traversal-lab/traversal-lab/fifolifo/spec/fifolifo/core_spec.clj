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
              (should= 0 (stack-size (make-stack))))
          )

(describe "stack-size"
          
          (it "should work with empty stacks"
              (should= 0 (:size (make-stack))))
          
          (it "should work with stacks"
              (should= 3 (:size (Stack. '(1 2 3) 3))))
          
          )

(describe "push"

          (it "should work with empty stacks"
              (let [xx (make-stack)]
                (should= (Stack. '(1) 1) (push xx 1))))
          
          (it "should work with stacks with data"
              (let [xx (Stack. '(1 2 3) 3)]
                (should= (Stack. '(5 1 2 3) 4) (push xx 5))))

          )

(describe "pop"

          (it "should work with empty stacks"
              (let [xx (make-stack)]
                (should= (Stack. nil 0) (pop xx))))

          (it "should work with stacks with data"
              (let [xx (Stack. '(1 2 3) 3)]
                (should= (Stack. '(2 3) 2) (pop xx))))

          )

(describe "top"

          (it "should work with empty stacks."
              (let [xx (make-stack)]
                (should= nil (top xx))))

          (it "should work with stacks with data."
              (let [xx (Stack. '(1 2 3) 3)]
                (should= 1 (top xx))))

          )

(describe "queue-size"

          (it "should work with empty queues."
              (let [xx (make-queue)]
                (should= 0 (queue-size xx))))

          (it "should work with queues with data."
              (let [xx (Queue. '(3 2 1) '(1 2 3) 3)]
                (should= 3 (queue-size xx))))

          )

(describe "enqueue"

          (it "should work with empty queues."
              (let [xx (make-queue)]
                (should= (Queue. '(1) nil 1) (enqueue xx 1))))

          (it "should work with queues with data."
              (let [xx (Queue. '(3 2 1) '(1 2 3) 3)]
                (should= (Queue. '(4 3 2 1) '(1 2 3) 4) (enqueue xx 4))))

          )

(describe "dequeue"

          (it "should work with empty queues."
              (let [xx (make-queue)]
                (should= (make-queue) (dequeue xx))))

          (it "should work with queues with data."
              (let [xx (Queue. '(3 2 1) '(1 2 3) 3)]
                (should= (Queue. '(3 2 1) '(2 3) 2) (dequeue xx))))

          )

(describe "peek"

          (it "should work with empty queues."
              (let [xx (make-queue)]
                (should= nil (peek xx))))

          (it "should work with queues with data."
              (let [xx (Queue. '(3 2 1) '(1 2 3) 3)]
                (should= 1 (peek xx))))

          )

(run-specs)
