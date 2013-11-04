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

(describe "should get stack size"
          (it "should get the size of a non-empty stack"
              (let[xx (make-stack)]
                (should= 3 (stack-size (push (push (push xx 1) 2) 3))))
              )
          (it "should be zero for empty stacks"
              (let[xx (make-stack)]
                (should= 0 (stack-size (make-stack))))
              )
          (it "should-not return nil for empty stacks"
              (let[xx (make-stack)]
                (should-not= nil (stack-size (make-stack))))
              )

 )

(describe "should push to stacks"
          (it "should push to empty stack"
              (let[xx (make-stack)]
                (should= (Stack. '(1) 1) (push xx 1))))
          (it "should push to non-empty stack"
              (let[xx (push (make-stack) 1)]
                (should= (Stack. '(2 1) 2) (push xx 2))))
)

(describe "should pop stacks"
          (it "should-not pop empty stacks"
              (let[xx (make-stack)]
                   (should-not= (Stack. nil -1) (pop xx))
                   (should= (Stack. nil 0) (pop xx))))
          (it "should pop non-empty stacks"
              (let[xx (push (push (push (make-stack) 1) 2) 3)]
                (should= (Stack. '(2 1) 2) (pop xx))))
 )

(describe "The queue declaration"

          (it "should create something."
              (should (make-queue)))

          (it "should have empty components."
              (should= (Queue. nil nil 0) (make-queue)))

          (it "should have a size of zero."
              (should= 0 (stack-size (make-stack))))
 )


(describe "Should enqueue elements"
          (it "should enqueue empty list"
              (let[xx (make-queue)]
                (should= (Queue. '(1) nil 1) (enqueue xx 1))))
          (it "should enqueue non-empty list"
              (let[xx (enqueue (make-queue) 1)]
                (should= (Queue. '(2 1) nil 2) (enqueue xx 2))))
)

(describe "Should dequeue elements"
          (it "should dequeue empty front with reverse back"
              (let[xx (Queue. '(1 2 3) nil  3)]
                (should= (Queue. nil '(2 1)  2) (dequeue xx))))
          (it "should dequeue when front is not empty"
              (let[xx (Queue. '(1 2 3) '(4 5) 2)]
                (should= (Queue. '(1 2 3) '(5) 1) (dequeue xx))))
 )

(describe "Enqueue And Dequeue Should Make Sense"
          (it "size should not remain same after enqueue"
              (let[xx (make-queue)]
                (should-not= (:size (enqueue xx 1)) 0)))
          (it "size not remain the same after dequeue"
              (let[xx (Queue. '(1 2 3) nil 3)]
                (should-not= (:size (dequeue xx)) 3)))
          (it "size should decrease after dequeue"
              (let[xx (Queue. '(1 2 3) nil 3)]
                (should= (:size (dequeue xx)) 2)))
          (it "size should increase after enqueue"
              (let[xx (Queue. '(1 2 3) nil 3)]
                (should= (:size (enqueue xx 3)) 4)))
          (it "the back after enqueue should add the element"
              (let[xx (Queue. '(1 2 3) nil 3)]
                (should= (:back (enqueue xx 4)) '(4 1 2 3))))
          (it "dequeue should not make size negative"
              (let[xx (make-queue)]
                (should-not= (:size (dequeue xx)) -1)))
)

(describe "Push and Pop Should Make Sense"
        (it "size should increase in size after push"
            (let[xx (make-stack)]
              (should= (:size (push xx 1)) 1)))
        (it "size should not remain the same size after push"
            (let[xx (Stack. '(1 2 3) 3)]
              (should-not= (:size (push xx 1)) 3)))
       (it "size should decrease after pop"
           (let[xx (Stack. '(1 2 3) 3)]
             (should= (:size (pop xx)) 2)))
       (it "size should not remain the same after pop"
           (let[xx (Stack. '(1 2 3) 3)]
             (should-not= (:size (pop xx)) 3)))
       (it "should not pop a stack with size 0"
           (let[xx (make-stack)]
             (should-not= (:size xx) -1)))
       (it "should not pop a stack with size 0 (test 2)"
           (let[xx (make-stack)]
             (should= (Stack. nil 0) xx)))
)

;(describe "inaction"

;          (it "should have some tests at some point."
;              (should false)))

(run-specs)
