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
(describe "push"
          (it "should work for empty lists."
              (let [xx (make-stack)]
                (should= (Stack. (cons 2 nil) 1) (push xx 2))))
          (it "should work for lists with data."
              (let [xx (Stack. (cons 2 nil) 1)]
                (should= (Stack. (cons 1 (cons 2 nil)) 2) (push xx 1)))))
(describe "pop"
          (it "should return an empty stack for empty lists."
              (let [xx (make-stack)]
                (should= (make-stack) (pop xx))))
          (it "should correctly decrement size when element is removed"
              (let [xx (Stack. (cons 2 (cons 4 nil)) 2)]
                (should= (Stack. (cons 4 nil) 1) (pop xx)))))

;(describe "top"
;	  (it "should correclty return the top of the stack"
;	      (let [xx (Stack. (cons 2 (cons 4 nil)) 2)]
;		(should= (cons 2 (cons 4 nil)) (top xx)))))

(describe "The queue declaration"

          (it "should create something."
              (should (make-queue)))

          (it "should have empty components."
              (should= (Queue. nil nil 0) (make-queue)))

          (it "should have a size of zero."
              (should= 0 (stack-size (make-stack)))))

(describe "enqueue"
          (it "should work for queues with empty components"
              (let [xx (make-queue)]
                (should= (Queue. (cons 4 nil) nil 1) (enqueue xx 4))))
          (it "should work for queues with data"
              (let [xx (Queue. (cons 4 nil) (cons 5 nil) 2)]
                (should= (Queue. (cons 2 (cons 4 nil)) (cons 5 nil) 3) (enqueue xx 2))))
          (it "should work for queues with empty front"
              (let [xx (Queue. (cons 4 (cons 2 nil)) nil 2)]
                (should= (Queue. (cons 4 (cons 4 (cons 2 nil))) nil 3) (enqueue xx 4)))))
(describe "dequeue"
          (it "should work for queues with empty components"
              (let [xx (make-queue)]
                (should= (make-queue) (dequeue xx))))
          (it "should work for queues with an empty front component"
              (let [xx (Queue. (cons 4 (cons 5 nil)) nil 2)]
                (should= (Queue. nil (cons 4 nil) 1) (dequeue xx))))
          (it "should work for queues with front and back components having data"
              (let [xx (Queue. (cons 4 (cons 5 nil)) (cons 2 (cons 4 nil)) 4)]
                (should= (Queue. (cons 4 (cons 5 nil)) (cons 4 nil) 3) (dequeue xx))))

          (it "should work for queues with only the front has data"
              (let [xx (Queue. nil (cons 2 (cons 4 nil)) 2)]
                (should= (Queue. nil (cons 4 nil) 1) (dequeue xx)))))

(run-specs)

