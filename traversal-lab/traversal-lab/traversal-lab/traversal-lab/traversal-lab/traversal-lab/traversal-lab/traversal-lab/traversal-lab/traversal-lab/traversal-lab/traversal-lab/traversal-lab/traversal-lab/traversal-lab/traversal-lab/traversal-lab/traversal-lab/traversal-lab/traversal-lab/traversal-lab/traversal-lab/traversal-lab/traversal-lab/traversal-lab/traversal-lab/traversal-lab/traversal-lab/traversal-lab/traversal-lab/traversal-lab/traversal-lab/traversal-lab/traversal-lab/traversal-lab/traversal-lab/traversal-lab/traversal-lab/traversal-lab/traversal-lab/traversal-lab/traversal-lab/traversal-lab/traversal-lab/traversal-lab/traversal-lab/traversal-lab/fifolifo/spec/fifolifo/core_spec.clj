(ns fifolifo.core-spec
  (:refer-clojure :exclude [pop peek])
  (:require [speclj.core :refer :all]
            [fifolifo.core :refer :all])
  (:import [fifolifo.core Stack Queue]))

;; # The Tests
;;
;; We are going to use [spelj](https://github.com/slagyr/speclj) for our tests.

(def xx (Stack. '(1 3 4 5 7 9 10 11 13) 9))

(describe "The stack declaration"

          (it "should create something."
              (should (make-stack)))

          (it "should have empty components."
              (should= (Stack. nil 0) (make-stack)))

          (it "should have a size of zero."
              (should= 0 (stack-size (make-stack))))
          )

(describe "Push"
          (it "should add an item to the stack"
              (should= (Stack. '(44 1 3 4 5 7 9 10 11 13) 10)
                       (push xx 44)
                       )
              )
          )

(describe "Pop"
          (it "should remove an item from the top of the stack"
              (should= (Stack. '(3 4 5 7 9 10 11 13) 8)
                       (pop xx)
                       )
              )
          )

(describe "Top"
          (it "should return the top item in the stack"
              (should= 1
                       (top xx)
                       )
              )
          )
;Queue Section
(def que (enqueue (enqueue (enqueue (enqueue (make-queue) 1) 2) 3) 4))

(describe "The queue declaration"

          (it "should create something."
              (should (make-queue)))

          (it "should have empty components."
              (should= (Queue. nil nil 0) (make-queue)))

          (it "should have a size of zero."
              (should= 0 (queue-size (make-queue))))
		  (it "should increment size of queue"
			  (should= 5 (queue-size (enqueue que 5))))
		  (it "shouldn't make size negative"
		      (should= 0 (queue-size (dequeue (make-queue)))))
		  (it "not behave like stack"
				(should-not= 8 (peek (enqueue que 8))))
		  (it "should erase back"
		      (should= nil (:back (dequeue que))))
          )

(run-specs)
