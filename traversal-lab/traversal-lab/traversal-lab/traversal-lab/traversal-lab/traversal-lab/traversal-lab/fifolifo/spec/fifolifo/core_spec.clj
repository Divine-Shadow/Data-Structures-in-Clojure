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
          
          ;;(it "should have a size of zero."
          ;;    (should= 0 (stack-size (make-stack))))
          )


(describe "The queue declaration"

          (it "should create something."
              (should (make-queue)))

          (it "should have empty components."
              (should= (Queue. nil nil 0) (make-queue)))
          
          (it "should have a size of zero."
              (should= 0 (queue-size (make-stack))))
          )

(describe "Push"

          (it "should change the first value in the stack."
              (let [x (Stack. '(1 2 3) 3)]
	        (should= '(5 1 2 3) (:top (push x 5)))))
	  (it "should incrememnt the top of the stack."
	      (let [x (Stack. '(1 2 3) 3)]
	        (should= 4 (:size (push x 5)))))
)

(describe "Pop"
	(it "should change the first value in the stack."
	    (let [x (Stack. '(1 2 3) 3)]
	        (should= '(2 3) (:top (pop x)))))
	(it "should decrement the top of the stack."
	    (let [x (Stack. '(1 2 3) 3)]
	        (should= 2 (:size (pop x)))))
	(it "should make size a negative number for an empty list"
	    (let [x (Stack. nil 0)]
	        (should= 0 (:size (pop x)))))
)

(describe "Top"
	(it "should return the top of the stack."
	    (let [x (Stack. '(1 2 3) 3)]
	      (should= 1 (top x))))
)

(describe "Enqueue"
	(it "should add elt to the back"
	  (should= '(1) (:back (enqueue (make-queue) 1))))
	(it "should increment the size."
	  (should= 1 (:size (enqueue (make-queue) 1))))
)

(describe "Dequeue"
	(it "should shrink front."
	  (let [x (Queue. '(1 2) '(3 4) 4)]
	    (should= '(4) (:front (dequeue x)))))
	(it "should work with nil front."
	  (let [x (Queue. '(3 4) nil 2)]
	    (should= '(3) (:front (dequeue x)))))
	(it "should make size zero for empty list"
	  (let [x (Queue. nil nil 0)]
	    (should= 0 (:size (dequeue x)))))
	(it "should erase back if flipped."
	  (let [x (Queue. '(3 4) nil 2)]
	    (should= nil (:back (dequeue x)))))
)

(describe "Peek"
	(it "should return the first value of the queue."
	  (let [x (Queue. '(1 2) '(3 4) 4)]
	    (should= 3 (peek x))))
	(it "should work for empty queues."
	  (should= nil (peek (make-queue))))
)

(run-specs)
