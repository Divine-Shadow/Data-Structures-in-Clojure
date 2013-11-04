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

	  (it "should return 0 for stack size 0."
	      (let [stk (Stack. nil 0)])
	      (should= 0 (stack-size (stk))))

	  (it "should return size of stack for none zero stacks."
	      (let [stk (Stack. '(1 2 3) 3)])
	      (should= 3 (stack-size (stk))))
)

(describe "push"

	  (it "should push a a piece of data to the first value in the stack"
	      (let [stk (Stack. '(1 2 3) 3)])
	      (should= '(4 1 2 3) (:top (push stk 5))))

	  (it "should increment the top of the stack"
	      (let [stk (Stack. '(1 2 3) 3)])
	      (should= 4 (:size (push stk 4))))
)

(describe "pop"

	  (it "should pop the piece of data at the first value in the stack"
	      (let [stk (Stack. '(1 2 3) 3)])
	      (should= '(2 3) (:top (pop stk))))
	  
	  (it "should decrement the top of the stack"
	      (let [stk (Stack. '(1 2 3) 3 )])
	      (should= (:size (pop stk))))

	  (it "should not make size a negative number for an empty list"
	      (let [stk (Stack. nil 0)])
	      (should= 0 (:size (pop x))))
)

(describe "top"
	 
	  (it "should return the top of the stack."
	      (let [stk (Stack. '(1 2 3) 3)])
	      (should= 1 (top stk)))
)

(describe "Enqueue"

	  (it "should add elt to the back of queue"
	      (should= '(1) (:back (enqueue (make-queue) 1))))

	  (it "should increment the size."
	      (should= 1 (:size (enqueue (make-queue) 1))))
)

(describe "Dequeue"

	  (it "should shrink front."
	      (let [que (Queue. '(1 2) '(3 4) 4)]
	      (should= '(4) (:front (dequeue que)))))

	  (it "should work with nil."
	      (let [que (Queue. '(1 2) nil 2)]
	      (should= '(1) (:front (dequeue que)))))

	  (it "should make size zero when list is empty"
	      (let [que (Queue. nil nil 0)]
	      (should= 0 (:size (dequeue que)))))

	  (it "should erase back when flipped"
	      (let [que (Queue. '(3 4) nil 2)]
	      (should= nil (:back (dequeue que)))))
)

(describe "Peek"

	  (it "should return the first value of the Queue."
	      (let [x (Queue. '(1 2) '(3 4) 4)]
	      (should= 3 (peek que))))

	  (it "should work for empty queues."
	      (should= nil (peek (make-queue))))
) 
	  


(run-specs)
