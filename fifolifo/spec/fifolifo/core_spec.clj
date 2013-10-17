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
(describe "push"
          (it "should increment size" (should= 1 (stack-size (push (make-stack) 2))))
       )   

(describe "pop"

          (it "should not make negative size" (should= 0 (stack-size (pop (make-stack)))))
          (it "should remove elements" (should= (stack-size (make-stack)) (stack-size (pop (push (make-stack) 2)))))
          (it "should preserve order" (should= (push (push (push (make-stack) 1) 2) 3) (pop (push (push (push (push (make-stack) 1) 2) 3) 4))))


)

(describe "enqueue" 

          (it "should increment size" (should= 1 (queue-size (enqueue (make-queue) 3))))
  

)

(describe "dequeue"
(it "should not make size negative" (should= 0 (queue-size (dequeue (make-queue)))))

(it "should flip the order" (should= (peek (dequeue (enqueue (enqueue (enqueue (make-queue) 1) 2) 3))) 2)) 


(it "should delete the back" (should= nil (peek (dequeue (enqueue (make-queue) 2)))))

(it "should not behave like a stack" (should= 2 (dequeue (peek (enqueue (enqueue (enqueue (enqueue (make-stack) 1) 2) 3) 4) )))
)
)

(describe "inaction"

          (it "should have some tests at some point."
              (should true)))

(run-specs)
