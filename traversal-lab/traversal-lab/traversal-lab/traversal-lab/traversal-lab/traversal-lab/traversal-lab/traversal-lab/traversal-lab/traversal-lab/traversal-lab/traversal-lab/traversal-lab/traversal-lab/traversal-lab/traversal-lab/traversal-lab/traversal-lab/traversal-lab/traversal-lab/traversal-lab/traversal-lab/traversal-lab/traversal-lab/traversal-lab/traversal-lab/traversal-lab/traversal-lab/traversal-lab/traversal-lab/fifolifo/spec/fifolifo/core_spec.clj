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
              (should= (Stack. nil 0) (make-stack))
	      (should= nil (:top (make-stack)))
	      (should= 0 (:size (make-stack)))))
;;-----------------------------------------------------------
(describe "The stack-size"
	  
	  (it "should have a size 0 of if Stack is empty"
	      (should= 0 (stack-size (Stack. nil 0))))

	  (it "should return the size"
	      (should= 2 (stack-size (Stack. '(1 [:top (2 [:top nil] [:size 0])] [:size 1]) 2)))))
;;-----------------------------------------------------------
(describe "The push"
 	  
	  (it "should work with stack with a size of 0"
	      (should= (Stack. '(1) 1) (push (Stack. nil 0) 1)))
	  
	  (it "should work stack that have data"
	      (should= (Stack. '(3 4) 2) (push (Stack. '(4) 1) 3))))
;;-----------------------------------------------------------
(describe "The pop"

	  (it "should work with empty Stacks"
	      (should= (Stack. nil 0) (pop (make-stack))))
	  
	  (it "should work with Stacks that have data"
	      (should= (Stack. '(9 8 7) 3) (pop (Stack. '(10 9 8 7) 4))))
	  )

;;------------------------------------------------------------
(describe "The top"
	
	  (it "should work with empty Stacks"
	      (should= nil (top (Stack. nil 0))))

	  (it "should work with Stacks that have data"
	      (should= 1 (top (Stack. '(1 2 3) 3))))
	  )

;;-----------------------------------------------------------
(describe "The queue declaration"

          (it "should create something."
              (should (make-queue)))

          (it "should have empty components."
              (should= (Queue. nil nil 0) (make-queue)))
          
          (it "should have a size of zero."
              (should= 0 (stack-size (make-stack))))
          )

;;--------------------------------------------------------------
(describe "The queue-size"
	  (it "should work with empty Queues"
	      (should= 0 (queue-size (make-queue))))
	  
	  (it "should work with Queues that have data"
	      (should= 3 (queue-size (Queue. '(3 2) '(1) 3))))
	  )

;;-------------------------------------------------------------
(describe "The enqueue"
 	  (it "should work with empty Queues"
	      (should= (Queue. '(1) nil 1) (enqueue (make-queue) 1)))

	  (it "should work with Queues that have data"
	      (should= (Queue. '(4 3) '(1 2) 4) (enqueue (Queue. '(3) '(1 2) 3) 4)))
	  )

;;---------------------------------------------------------------
(describe "The dequeue"
	  (it "should work with empty Queues"
	      (should= (Queue. nil nil 0) (dequeue (make-queue))))

	  (it "should work if :front has elements"
	    (let [x (Queue. '(5 4 3) '(1 2) 5)]
	      (should= (Queue. '(5 4 3) '(2) 4) (dequeue x))))
	      
	  (it "should work if :front is empty"
	    (let [x (Queue. '(3 2 1) nil 3)]
	      (should= (Queue. nil '(2 3) 2) (dequeue x))))
	  )

;;----------------------------------------------------------------
(describe "The inaction"
	  (it "means I nailed it"
	      (should true)))

(run-specs)
