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
              (make-stack))

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

(describe "more tests"

        (it "should increment size"
                (should= 1 (:size (push (make-stack) 20))))

        (it "should not make size negative"
                (should= 0 (:size (pop (make-stack)))))

        (it "pop should remove elements"
                (let [xx (Stack. (cons 2 (cons 4  nil)) 2)]
                (should= (Stack. (cons 4 nil) 1)(pop xx))))

        (it "pop should not reverse elements"
                (let [xx (Stack. (cons 2 (cons 4 nil)) 2)]
                (should-not= (Stack. (cons 4 (cons 2 nil)) 2) (pop xx))))

        (it "enqueue increments size"
                (should= 1 (:size (enqueue (make-queue) 20))))

        (it "dequeue makes no size negative"
                (should= 0 (:size (dequeue (make-queue)))))

        (it "queues are NOT stacks"
                (should-not= (make-stack) (make-queue)))


)


(run-specs)
