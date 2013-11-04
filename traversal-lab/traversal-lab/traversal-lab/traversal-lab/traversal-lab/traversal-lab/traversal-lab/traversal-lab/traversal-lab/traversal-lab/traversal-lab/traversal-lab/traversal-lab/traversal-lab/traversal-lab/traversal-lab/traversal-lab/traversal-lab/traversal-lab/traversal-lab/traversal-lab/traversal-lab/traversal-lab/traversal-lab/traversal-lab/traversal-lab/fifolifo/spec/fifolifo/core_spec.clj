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
          (it "should increment size"
              (should= 1 (stack-size (push  (make-stack) 1))))
)

(describe "pop"
          (it "should not make negative size" 
              (should= 0 (stack-size (pop  (make-stack))))) 
          (it "should decrement size"
              (should= 1 (stack-size (pop  (push  (push  (make-stack) 1) 2)))))
          (it "should not reverse"
              (should= (Stack. '(2 3 4) 3) (pop (Stack. '(1 2 3 4) 4))))
)
(describe "The queue declaration"

          (it "should create something."
              (should (make-queue)))

          (it "should have empty components."
              (should= (Queue. nil nil 0) (make-queue)))
          
          (it "should have a size of zero."
              (should= 0 (stack-size (make-stack))))
          )
(describe "enqueue"
          (it "should increment size"
              (should= 1 (queue-size (enqueue (make-queue) 1))))
)
(describe "dequeue"
          (it "should not make size negative"
              (should= 0 (queue-size (dequeue  (make-queue)))))
          (it "should reverse when flipping"
              (should= (Queue. nil '(2 3 4) 3) (dequeue (Queue. '(4 3 2 1) nil 4))))
)

(run-specs)
