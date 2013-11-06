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

(describe "make-stack"

	(it "should create an empty stack"
		(should= (Stack. nil 0) (make-stack)))

)

(describe "stack-size" 

	(it "should return the size of a stack"
	    (let [xx (push (make-stack) 1)]
		(should= 1 (stack-size xx))))

)

(describe "push"
	
	(it "should add an element to a stack"
	    (let [xx (push (make-stack) 1)]
		(should= 1 (stack-size xx))))		

	(it "should have the element pushed on the top"
	    (let [xx (push (make-stack) 1)]
	        (should= 1 (top xx))))

)

(describe "pop" 

	(it "should remove an element from the stack"
	    (let [xx (push (push (make-stack) 1) 2)]
		(should= 1 (stack-size (pop xx)))))

	(it "should remove the top element from the stack"
	    (let [xx (push (push (make-stack) 1) 2)]
	        (should= 1 (top (pop xx)))))

	(it "should not have a negative stack size"
	    (let [xx (make-stack)]
	        (should= 0 (stack-size (pop xx)))))
)

(describe "top" 

	(it "should return the top element from a stack"
	    (let [xx (push (make-stack) 1)]
		(should= 1 (top xx))))

)

(describe "The queue declaration"

          (it "should create something."
              (should (make-queue)))

          (it "should have empty components."
              (should= (Queue. nil nil 0) (make-queue)))
          
          (it "should have a size of zero."
              (should= 0 (queue-size (make-queue))))
          )

(describe "make-queue"

	(it "should create an empty queue"
		(should= (Queue. nil nil 0) (make-queue)))

)

(describe "queue-size"

	(it "should return the size of the queue"
	    (let [xx (enqueue (make-queue) 1)] 	
		(should= 1 (queue-size xx))))

)

(describe "enqueue"

	(it "should add an element to the back of a queue"
	    (let [xx (make-queue)]	
		(should= (Queue. '(1) nil 1) (enqueue xx 1))))

)

(describe "dequeue"

	(it "should remove an element from the front of the queue"
	    (let [xx (enqueue (make-queue) 1)]        
		(should= (Queue. nil () 0) (dequeue xx))))

	(it "should not have a negative stack size"
	    (let [xx (make-queue)]
	        (should= 0 (queue-size (dequeue xx)))))

	(it "should reverse when flipping 1"
	    (let [xx (Queue. '(1 2 3 4) '() 4)]
	        (should= '(3 2 1) (:front (dequeue xx)))))

)

(describe "peek" 

	(it "should return the next element that will come out the front of the queue"
	    (let [xx (enqueue (make-queue) 1)]
		(should= 1 (peek xx))))

)

(run-specs)