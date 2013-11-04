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

(describe "Push"
	  (it "Should be size of n if I push n number elements"
	      (should= 4 (stack-size (push (push (push (push (make-stack) 4) 5) 2) 6))))
)

(describe "Top"
	  (it "should return the top of the stack."
	      (should= 9 (top (push (push (push (make-stack) 1) 7) 9))))
)

(describe "Pop"

	  (it "should return nil if in the case of an empty stack."
	      (should= (Stack. nil 0) (pop (make-stack))))
	  (it "should be size (n-1) if I push n elements and pop an element"
	      (should= 2 (stack-size (pop (push (push (push (make-stack) 1) 3) 2)))))
	  ;(it "should make the stack nil if I pop all the elements"
	   ;   (should= (Stack. nil 0) (pop (pop (pop (push (push (push (make-stack) 1) 3) 2))))))
	  (it "should change the old top to a new top."
	      (let [oldTop (top (push (push (push (push (make-stack) 2) 3) 5) 8))]
		(should-not= oldTop (top (pop (push (push (push (push (make-stack) 2) 3) 5) 8))))))
)



(describe "The queue declaration"

          (it "should create something."
              (should (make-queue)))
	  (it "should have empty components."
              (should= (Queue. nil nil 0) (make-queue)))
          (it "should have a size of zero."
              (should= 0 (stack-size (make-stack))))
)

(describe "Enqueue should"
	(it "add element to the back of the queue."
	     (should= 8 (first (:back (enqueue (make-queue) 8)))))
        (it "Have the front of the queue be empty when enqueue is run"
	     (should= nil (:front (enqueue (make-queue) 5))))
   	(it "increase size by n if I enqueue n elements."
	     (should= 3 (:size (enqueue (enqueue (enqueue (make-queue) 6) 7) 8))))

)

(describe "Dequeue should"

	(it "should work for queues that have data"
          (should= (Queue. (cons 5 (cons 4 nil)) '() 1)
                   (dequeue (Queue. (cons 5 (cons 4 nil)) (cons 4 nil) 2))))
  (it "should not do anything if the queue is empty"
                (let [queue (make-queue)]
                  (should= nil (peek (dequeue (dequeue queue))))))
)

(describe "peek test"

         (it "should work with empy queue"
             (let [queue (make-queue)]
               (should= nil (peek queue))))
         (it "should return the value that it will be deleted when dequeue is called"
             (let [queue (enqueue (enqueue (enqueue (make-queue) 5) 10) 15)]
               (should= 5 (peek queue))))
         (it "is the first additional test for above"
             (let [queue (enqueue (enqueue (enqueue (make-queue) 5) 10) 15)]
               (should= 10 (peek (dequeue queue)))))
         (it "is the second additional test for above"
             (let [queue (enqueue (enqueue (enqueue (make-queue) 5) 10) 15)]
               (should= 10 (peek (enqueue (dequeue queue) 20))))))




(run-specs)



