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
(desribe "the stack declaration"
         (it "should create something."
              (should (make-stack)))

          (it "should have empty components."
              (should= (Stack. nil 0) (make-stack)))

          (it "should push a 10 and 20."
              (should= (push(push(make-stack)10)20))
          )
          (it "should have a size of 2"
              (should = 2 (stack-size(make-stack))))
          (it "should get the top of the stack values of 10"
              (should= 10 (top(make-stack)) ))
          (it "should pop off 2 values"
              (pop(pop(make-stack))))
          (it "should have a size of zero"
              (should = 0 (stack-size (make-stack))))

)


(describe "The queue declaration"

          (it "should create something."
              (should (make-queue)))

          (it "should have empty components."
              (should= (Queue. nil nil 0) (make-queue)))
          
          (it "should have a size of zero."
              (should= 0 (queue-size (make-queue))))
          )

(describe "The queue declaration"

          (it "should create something."
              (should (make-queue)))

          (it "should have empty components."
              (should= (Queue. nil nil 0) (make-queue)))

          (it "should add 5, 6 and 7 to the queue."
              (should= (enqueue(enqueue(enqueue(make-stack) 5) 6) 7) ))
          (it "should look at the top value which is 5"
              (should = 5 (peek(make-queue))))
          (it "should have a size of 3"
              (should = 2 (size(make-queue))))
          (it "should remove 2 values"
              ( should= (dequeue(dequeue(make-queue)))))
)


(run-specs)
