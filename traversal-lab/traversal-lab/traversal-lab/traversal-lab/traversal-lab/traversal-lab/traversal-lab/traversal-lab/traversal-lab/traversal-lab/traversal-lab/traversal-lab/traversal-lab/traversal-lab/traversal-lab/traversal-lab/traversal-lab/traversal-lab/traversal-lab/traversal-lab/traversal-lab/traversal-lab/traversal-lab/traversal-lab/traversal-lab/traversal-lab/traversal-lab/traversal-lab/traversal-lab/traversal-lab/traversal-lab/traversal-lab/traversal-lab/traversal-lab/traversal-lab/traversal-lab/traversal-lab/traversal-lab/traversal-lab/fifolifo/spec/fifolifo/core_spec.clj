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
    (it "should increment size"
      (should-not= 0 (:size (push (make-stack) 1))))
    )

(describe "Pop"
  (it "should not decrement the size when popping an empty stack"
              (should= 0 (:size (pop (make-stack)))))


  (it "should remove elements"
      (should-not= (:top (push (make-stack) 1))  (:top (pop (push (make-stack) 1)))))

  (it "should not reverse the elements the stack list"
      (should= (:top (push (make-stack) 1)) (:top (pop (push (push (make-stack) 1) 2))))))


(describe "The stack size"
  (it "should return the size of the stack."
       (should= 0 (stack-size (make-stack)))))



(describe "The queue declaration"

          (it "should create something."
              (should (make-queue)))

          (it "should have empty components."
              (should= (Queue. nil nil 0) (make-queue)))

          (it "should have a size of zero."
              (should= 0 (stack-size (make-stack))))


          )

(describe "Enqueue"
    (it "should increment size"
       (should-not= 0 (queue-size ( enqueue (make-queue) 1)))))

(describe "Dequeue"
         (it "should not decrement an empty queue"
            (should= 0 (:size (dequeue (make-stack)))))

    (it "should not return the same value"
          (should-not= (enqueue (make-queue) 1) (dequeue (enqueue (make-queue) 1))))

    (it "should reverse when flipping"
      (should= (cons 2 (cons 3 nil) ) (:front (dequeue (enqueue (enqueue (enqueue (make-queue) 1) 2) 3)))))

   (it "flipping should always erase back"
       (should= nil (:back (dequeue (enqueue (make-queue) 1)))))

)

(describe "Queues generally"
   (it "should not behave like stacks"
       (should-not= (enqueue (make-queue) 2) (dequeue (enqueue (enqueue  (make-queue) 2) 5)))))






(run-specs)





