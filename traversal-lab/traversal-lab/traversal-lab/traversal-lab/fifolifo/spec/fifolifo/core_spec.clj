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

(describe "stack-size"
          
          (it "should work for an empty stack"
              (should= 0 (stack-size (Stack. nil 0))))

          (it "should work a stack of any size"
             (should= 3 (stack-size (Stack. (cons 2 (cons 3 (cons 4 nil))) 3))))
)

(describe "push"
          
             (it "should work for any size stack"
              (should= (Stack. (cons 5 (cons 2 nil)) 2) (push (Stack.(cons 2 nil) 1) 5)))
)


(describe "pop"
          
          (it "should work for empty stacks"
              (should= (make-stack) (pop (Stack. nil 0))))

          (it "should work for any size stack"
              (should= (Stack. (cons 2 (cons 3 nil)) 2) (pop (Stack. (cons 4 (cons 2 (cons 3 nil))) 3))))
)


(describe "top"

          (it "should work for an empty stack"
              (should= nil (top (Stack. nil 0))))

          (it "should work for any size stack"
              (should= 3 (top (Stack. (cons 3 (cons 2 nil)) 2))))

)
           


(describe "The queue declaration"

          (it "should create something."
              (should (make-queue)))

          (it "should have empty components."
              (should= (Queue. nil nil 0) (make-queue)))
          
          (it "should have a size of zero."
              (should= 0 (stack-size (make-stack))))
)

(describe "queue-size"

          (it "should work for empty queue"
              (should= 0 (queue-size (Queue. nil nil 0))))

          (it "should work for any size queue"
              (should= 2 (queue-size (Queue. 2 3 2))))
)

(describe "enqueue"
          
          (it "should increment the size"
              (should= 2 (:size (enqueue (Queue. (cons 2 nil) nil 1) 4))))

          (it "should work for any size queue"
            (should= (Queue. (cons 4 (cons 2 nil)) nil 2) (enqueue (Queue. (cons 2 nil) nil 1) 4)))



)

(describe "dequeue"
          
          (it "should work for empty stack"
              (should= (make-queue) (dequeue (Queue. nil nil 0))))

          (it "should work when front is empty"
              (should= (Queue. nil (cons 2 (cons 1 nil)) 2) (dequeue (Queue. (cons 1 (cons 2 (cons 3 nil))) nil 3))))

          (it "should work for any size queue"
              (should= (Queue. nil (cons 2 (cons 1 nil)) 2) (dequeue (Queue. nil (cons 3 (cons 2 (cons 1 nil))) 3))))
)

(describe "peek"

          (it "should work when front is empty"
              (should= 2 (peek (Queue. (cons 1 (cons 2 nil)) nil 2))))

          (it "should work for any size queue"
              (should= 2 (peek (Queue. nil (cons 2 (cons 1 nil)) 2))))

)
(run-specs)
