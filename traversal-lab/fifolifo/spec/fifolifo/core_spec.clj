0(ns fifolifo.core-spec
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

(describe "The stack size function"
          (it "should return the size of the stack"
              (should= 1 (stack-size (Stack. (cons 2 nil) 1))))
          (it "should return empty if the stack is empty"
            (should= 0 (stack-size (make-stack)))                    
          ))

(describe "The push function"
          (it "should add the element to the front"
              (should= 5 (top (push (Stack. nil 0) 5)))
          )
          (it "should increase the stack size" 
              (should= 5 (stack-size (push (Stack. nil 4) 2)))
           )
)



(describe "The pop function"
          (it "should not be negative when an empty is poped"
                (should= 0 (stack-size(pop(make-stack))))
           )
          (it "should remove elements"
               (should-not= 5 (top(pop(Stack. (cons 5 nil) 1))))
           )
          (it "should remove elements in order"
              (should= 3 (top(pop(Stack. (cons 5 (cons 3 nil)) 1))))
              )

 )





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
            (should= 1 (queue-size(enqueue(make-queue) 3)))
              )
         
          
          )
(describe "The dequeue function"
          (it "should leave the size zero if dequeueing an empty function"
              (should= 0 (queue-size(dequeue(make-queue))))
              )
       
          (it "should erase back after turning it to front"
              (should= nil (:back (dequeue(Queue. (cons 3 nil) nil 1))))
              )
(it "should not remove the last element in first"
              (should= 5 (peek (dequeue(Queue. (cons 3 nil) (cons 2 (cons 5 nil)) 1))))
              )          


          ) 
          

(run-specs)
