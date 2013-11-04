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

(describe "stack size"
	(it "should return if size is zero"
		(let [stk (Stack. nil 0)]
			(should= 0 (stack-size stk))))
	(it "should return size with data"
		(let [stk (Stack. '(1 2 3 4) 4)]
			(should= 4 (stack-size stk))))
)

(describe "push"
	(it "should work with empty list"
		(let [stk (Stack. nil 0)]
			(should= (Stack. '(1) 1) (push stk 1))))
	
	(it "should with list with data"
		(let [stk (Stack. '(1 2 3) 3)]
			(should= (Stack. '(4 1 2 3) 4) (push stk 4))))
)
			
(describe "pop"
	(it "should work with lists with no data"
		(let [stk (Stack. nil 0)]
			(should= (Stack. nil 0) (pop stk))))
	(it "should work with lists that have data"
		(let [stk (Stack. '(1 2 3 4) 4)]
			(should= (Stack. '(2 3 4) 3) (pop stk))))
)

(describe "top"
	(it "should work with empty list"
		(let [stk (Stack. nil 0)]
			(should= nil (top stk))))

	(it "should work with list with data"
		(let [stk (Stack. '(1 2 3 4 5) 5)]
			(should= 1 (top stk))))
)

(describe "The queue declaration"

          (it "should create something."
              (should (make-queue)))

          (it "should have empty components."
              (should= (Queue. nil nil 0) (make-queue)))
          
          (it "should have zero size"
	  	(let [yy (make-queue)]
			(should= 0 (:size yy))))
)

(describe "queue size"
	(it "should return 0 of make-queue"
		(let [yy (make-queue)]
			(should= 0 (queue-size yy))))
	(it "empty queue should have zero size"
		(let [yy (Queue. nil nil 0)]
			(should= 0 (queue-size yy))))
	(it "should return size of queue with data"
		(let [yy (Queue. nil '(1 2 3) 3)]
			(should= 3 (queue-size yy))))
)

(describe "enqueue"

	(it "should work with queues with no data"
		(let [yy (make-queue)]
			(should= (Queue. '(1) nil 1) (enqueue yy 1))))

	(it "should work with queues with data when back is nil"
		(let [yy (Queue. nil '(1 2 3) 3)]
			(should= (Queue. '(4) '(1 2 3) 4) (enqueue yy 4))))
	(it "should work with queues with data when front is nil"
		(let [yy (Queue. '(3 2 1) nil 3)]
			(should= (Queue. '(4 3 2 1) nil 4) (enqueue yy 4))))

	(it "should work with queues with data in both"
		(let [yy (Queue. '(4 3) '(1 2) 4)]
			(should= (Queue. '(5 4 3) '(1 2) 5) (enqueue yy 5))))
)

(describe "dequeue"
	(it "should return same queue if empty"
		(let [yy (Queue. nil nil 0)]
			(should= (Queue. nil nil 0) (dequeue yy))))

	(it "should work with data when front is nil"
		(let [yy (Queue. '(3 2 1) nil 3)]
			(should= (Queue. nil '(2 3) 2) (dequeue yy))))

	(it "should work with queue when back is nil"
		(let [yy (Queue. nil '(1 2 3) 3)]
			(should= (Queue. nil '(2 3) 2) (dequeue yy))))

	(it "should work with data in front and back"
		(let [yy (Queue. '(3) '(1 2) 3)]
			(should= (Queue. '(3) '(2) 2) (dequeue yy))))
	(it "should decrease in size"
		(let [yy (Queue. '(4) '(1 2 3) 4)]
			(should= 3 (:size (dequeue yy)))))
)

(describe "peek"
	(it "should return nil for empty queue"
		(should= nil (peek (Queue. nil nil 0))))
	(it "should detect first elt in queue"
		(let [yy (Queue. '(3) '(1 2) 3)]
			(should= 1 (peek yy))))
)

(describe "inaction"

          (it "should have some tests at some point."
              (should true)))

(run-specs)
