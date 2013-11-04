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
	 (it "should work with empty stacks."
	     (should= 0 (stack-size (make-stack))))

	 (it "should work with nonempty stacks."
	     (should= 1 (stack-size (push (make-stack) 3))))

	 (it "should work with nonempty stacks."
	     (should= 2 (stack-size (push (push (make-stack) 3) 5)))))

(describe "push" 
	(it "should work with empty stacks"
	    (should= (Stack. (cons 5 nil)  1) (push (make-stack) 5)))

	(it "should work with empty stacks"
	    (should= (Stack. (cons 8 nil) 1) (push (make-stack) 8)))

	(it "should work with empty stacks"
	    (should= (Stack. (cons 3 nil) 1) (push (make-stack)  3)))

	(it "should work with nonempty stacks"
	    (should= (Stack. (cons 4 (cons 3 (make-stack))) 2) (push (Stack. (cons 3 (make-stack)) 1) 4)))) 

(describe "pop" 
        (it "should work with empty stacks"
	    (should= (Stack. nil 0) (pop (Stack. nil 0))))

	(it "should work with nonempty stacks"
	    (should= (Stack. (cons 2 nil) 1) (pop (Stack. (cons 5 (cons 2 nil)) 2)))))

(describe "top"
	(it "should work with empty stacks"
	    (should= nil (top (Stack. nil 0))))

	(it "should work with nonempty stacks"
	    (should= 5 (top (Stack. (cons 5 (Stack. (cons 4 (make-stack)) 2)) 3)))))

(describe "queue size"
	(it "should work with empty queues"
	    (should= 0 (queue-size (make-queue))))
	
	(it "should work with nonempty stacks"
	    (should= 3 (queue-size (Queue. (cons 5 nil) (cons 4 (cons 3 nil)) 3)))))

(describe "enqueue"
	(it "should work with empty queues"
	    (should= (Queue. (cons 2 nil) nil 1) (enqueue (make-queue) 2)))

	(it "should work with nonempty queues"
	    (should= (Queue. (cons 4 (cons 5 nil)) (cons 2 nil) 3) (enqueue (Queue. (cons 5 nil) (cons 2 nil) 2) 4))))

(describe "dequeue"
	(it "should work with empty queues"
	    (should= (Queue. nil nil 0) (dequeue (make-queue))))
	
	(it "should work for queues that have data"
	    (should= (Queue. (cons 5 (cons 4 nil)) '()  1) (dequeue (Queue. (cons 5 (cons 4 nil)) (cons 4 nil) 2))))
        (it "should work if the front is empty"
	    (should= (Queue. nil (cons 4 (cons 5 nil)) 2) (dequeue (Queue. (cons 5 (cons 4 (cons 3 nil))) nil  3)))))
(describe "peek"
	(it "should work with empty queues"
	    (should= nil (peek (make-queue))))

	(it "should work with nonempty queues"
	    (should= 4 (peek (Queue. (cons 1 (cons 3 nil)) (cons 4 (cons 6 nil)) 4))))
	(it "should work if the front is empty"
	    (should= 6 (peek (Queue. (cons 3 (cons 6 nil)) nil 2)))))

(run-specs)
