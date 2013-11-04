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
              (should= 0 (queue-size (make-queue))))
          )
(describe "Stacks"

          (it "be able to push correctly"
              (let [s (push (make-stack) 10)]
              (should= (:size s) 1)
              )
          )

          (it "should not be able to have a negative size."
              (let [s (pop (make-stack))]
              (should= (:size s) 0)
              )
           )

          (it "should pop correctly"
              (let [s (pop(pop(push(push(push(push (make-stack) 10) 20) 30) 40)))]
              (should= (Stack. '(20 10) 2) s)
            )
          )

          )
(describe "Queues"

          (it "should increment the size."
              (should= (:size (enqueue (enqueue (enqueue (make-queue) 5) 10) 15)) 3)
          )
          (it "should never have a negative size."
              (should= (:size (dequeue (make-queue))) 0)
          )
          (it "should reverse and erase when flipping"
              (should= (:front (dequeue (enqueue (enqueue (enqueue (enqueue (make-queue) 5) 10) 15) 20))) '(10 15 20))
              (should= (:back (dequeue (enqueue (enqueue (enqueue (enqueue (make-queue) 5) 10) 15) 20))) nil)
          )
)
(run-specs)
