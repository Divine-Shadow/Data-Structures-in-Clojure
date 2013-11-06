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


;;(describe "The queue declaration"
;;
;;          (it "should create something."
;;              (should (make-queue)))
;;
;;          (it "should have empty components."
;;              (should= (Queue. nil nil 0) (make-queue)))
;;          
;;          (it "should have a size of zero."
;;              (should= 0 (stack-size (make-stack))))
;;          )

(describe "stack size function"

          (it "should give the correct stack size"
              (should= 3 (stack-size (Stack. '(1 2 3) 3))))

          (it "should give 0 for nil"
              (should= 0 (stack-size (Stack. nil 0)))))

(describe "push function"
          (it "should push properly"
              (let [s (Stack. '(2 3) 2)]
                (should= (Stack. '(1 2 3) 3) (push s 1))))

          (it "should increment the size when pushed"
              (let [s (Stack. '(2 3) 2)]
                (should= 3 (stack-size (push s 1)))))

          (it "should add to the front of top"
              (let [s (Stack. '(2 3) 2)]
                (should= '(1 2 3) (:top (push s 1))))))

(describe "pop function"
          (it "should remove properly"
              (let [s (Stack. '(1 2 3) 3)]
                (should= (Stack. '(2 3) 2) (pop s))))

          (it "should decrement the size"
              (let [s (Stack. '(1 2 3) 3)]
                (should= 2 (stack-size (pop s)))))

          (it "should remove the front of the top"
              (let [s (Stack. '(1 2 3) 3)]
                (should= '(2 3) (:top (pop s)))))

          (it "should work with nil"
              (should= (Stack. nil 0) (pop (make-stack)))))

(describe "top function"
          (it "should return the top"
              (let [s (Stack. '(1 2 3) 3)]
                (should= 1 (top s))))

          (it "should return if size is 0"
              (should= nil (top (make-stack)))))

(describe "queue declaration"
          (it "should create something"
              (should (make-queue)))

          (it "should have empty components"
              (should= (Queue. nil nil 0) (make-queue)))

          (it "should have a size of zero"
              (should= 0 (stack-size (make-stack)))))

(describe "queue size function"
          (it "it should return the size of empty"
              (should= 0 (queue-size (make-queue))))

          (it "it should return the size"
              (should= 4 (queue-size (Queue. '(1 2 3) '(4) 4)))))

(describe "enqueue function"
          (it "it should add an element to the back"
              (let [s (Queue. '(1 2 3) nil 3)]
                (should= (Queue. '(5 1 2 3) nil 4) (enqueue s 5))))
         
          (it "it should add an element to an empty queue"
              (should= (Queue. '(1) nil 1) (enqueue (make-queue) 1))))

(describe "dequeue function"
          (it "should remove an element from the back"
              (let [s (Queue. '(1 2 3) nil 3)]
                (should= (Queue. nil '(2 1) 2) (dequeue s))))

          (it "should work with elements in front"
              (let [s (Queue. nil '(1 2 3) 3)]
                (should= (Queue. nil '(2 3) 2) (dequeue s))))

          (it "should work with elements in both"
              (let [s (Queue. '(1 2 3) '(4 5 6) 6)]
                (should= (Queue. '(1 2 3) '(5 6) 5) (dequeue s))))

          (it "should work with nil"
              (should= (make-queue) (dequeue (make-queue)))))

(describe "peek function"
          (it "should return the front if there is something in front"
              (let [s (Queue. '(1 2 3) '(4 5 6) 6)]
                (should= 4 (peek s))))

          (it "should return the last of the back if the front is nil"
              (let [s (Queue. '(1 2 3) nil 3)]
                (should= 3 (peek s))))

          (it "should return nil for when the fronts and backs are empty"
              (should= nil (peek (make-queue))))

          (it "should return the first of the front if back is nil"
              (let [s (Queue. nil '(1 2 3) 3)]
                (should= 1 (peek s)))))

(run-specs)