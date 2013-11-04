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
          (it "should return the size of the stack"
              (should= 0 (stack-size (Stack. nil 0)))
              (should= 5 (stack-size (push (push (push (push (push (Stack. nil 0) 1) 2) 3) 4) 5)))))
(describe "push"
          (it "should push elt to the top of empty  stack."                       
              (should= (Stack. (cons 30  nil) 1) 
                       (push (make-stack) 30 )))  
          (it "should push elt to the top of stack."
              (should= (Stack. (cons 30 (cons 20 nil)) 2) 
                       (push (Stack. (cons 20 nil) 1) 30 )))
          (it "should increase the size of the stack"
              (should= 1 (:size (push (make-stack) 39))))) 
(describe "Top declaration"
          (it "should look at the top of the Stack"
              (should= 30 (top (Stack. (cons 30 (cons 20 (cons 10 nil))) 3)))))
(describe "The queue declaration"

          (it "should create something."
              (should (make-queue)))

          (it "should have empty components."
              (should= (Queue. nil nil 0) (make-queue)))
          
          (it "should have a size of zero."
              (should= 0 (:size (make-queue))))
          )
(describe "the pop declaration"
          (it "should remove the top of stack"
              (should= (Stack. '() 0) (pop (push (make-stack) 1))))
          (it "should not make size negative"
              (should= 0 (stack-size (pop (make-stack))))))
(describe "enqueue"
          (it "should  not change the front"
              (should= 5 (peek (enqueue  (Queue. nil '(5 4 3 2 1) 5 ) 30))))
          (it "should increase the size"
              (should= 6 (:size (enqueue  (Queue. nil '(5 4 3 2 1) 5 ) 30)))))
(describe "dequeue"
          (it "should reduce the size of the queue"
              (should= 4 (:size (dequeue (Queue. '(1 2 3)  '(5 4) 5)))))
          (it "should not make the size negative"
              (should= 0 (queue-size (dequeue (make-queue)))))
          (it "should flip the order" 
              (should= 2 (peek (dequeue (enqueue (enqueue (make-queue) 1) 2)))))
          (it "should delete the back" 
              (should= nil (peek(dequeue (enqueue (make-queue) 30)))))
          (it "should not behave like a stack"
              (should= 2 (peek (dequeue (enqueue (enqueue (enqueue (enqueue (make-stack)1)2)3)4))))))

;;(describe "inaction"

  ;;        (it "should have some tests at some point."
    ;;          (should false)))

(run-specs)
