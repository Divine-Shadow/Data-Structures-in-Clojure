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
              (should= 0 (stack-size (make-stack))))
)


(describe "The pop function"

         (it "should remove element at top of stack"
             (should= (push (push (push (make-stack) 10) 20) 30)
                      (pop (push (push (push (push (make-stack) 10) 20) 30) 40))))

         (it "should not make size negaitve"
             (should= 0 (:size (pop (make-stack)))))
         )


(describe "The enqueue function"
          (it "should increment size"
              (should= 1 (:size (enqueue (make-queue) 10))))
          )


(describe "The dequeue function"
          (it "should not make size negaitve"
              (should= 0 (:size (dequeue (make-queue)))))
          )

(run-specs)
