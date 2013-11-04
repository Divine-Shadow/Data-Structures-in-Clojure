(ns fifolifo.core-spec
 0;136;0c (:refer-clojure :exclude [pop peek])
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
(describe "push function"
           (it "should increment size"
               (let [q1 (Stack. nil 0)]
                 (should= 2 ((stack-size) push(push q1 10)20))))
           (it "should push an element to top of stack"
               (let [q1 (Stack. nil 0)] 
                 (should = '(10) (push q1 10)))))
(describe "pop function"
           (it "should decrement size"
               (let [q1 (Stack. nil 0)]
                 (should= 1 ((push q1 1) (push q1 2) (pop q1))))))
           (it "should remove an element"
               (let [q1 (Stack. nil 0)]
                 (should = '(10) ((push q1 10) (push q1 50) (pop q1)))))             

(describe "The queue declaration"

          (it "should create something."
              (should (make-queue)))

          (it "should have empty components."
              (should= (Queue. nil nil 0) (make-queue)))
          
          (it "should have a size of zero."
              (should= 0 (stack-size (make-stack))))
          )
(describe "The enqueue function"
          (it "should increment size"
             (let [q1 (Queue. nil nil 0)]
              (should= 1 (enqueue q1 10))))
          (it "should add to the back"
             (let [q1 (Queue. nil nil 0)]
              (should= '(5) (enqueue q1 5)(:back q1))))
(describe "The dequeue function"
          (it "should decrement size"
              (let [q1 (Queue. nil nil 0)]
               (should= 1 (enqueue q1 1)(enqueue q1 2)(dequeue q1)(:size q1))))
          (it "should remove from the back"
              (let [q1 (Queue. 10 nil 1)]
               (should= (Quene. nil nil 0) (enqueue q1))))
          (it "should remove from the front if back is empty"
              (let [q1 (Queue. nil 10 1)]
               (should= (Queue nil nil 0) (enqueue q1))))             

(run-specs)
