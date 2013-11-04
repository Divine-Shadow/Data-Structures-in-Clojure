
(ns fifolifo.core-spec
  (:refer-clojure :exclude [pop peek])
  (:require [speclj.core :refer :all]
            [fifolifo.core :refer :all])
  (:import [fifolifo.core Stack Queue]))

;; # The Tests
;;
;; We are going to use [spelj](https://github.com/slagyr/speclj) for our tests.



(describe "The stack declaration (make-stack)"

          (it "should create something."
              (should (make-stack)))

          (it "should have empty components."
              (should= (Stack. nil 0) (make-stack)))
          
          (it "should have a size of zero."
              (should= 0 (stack-size (make-stack))))
          )

(describe "push"

          (it "should push an element onto a stack"
              (should= (Stack. (cons 20 (cons 10 nil)) 2) (push (push (make-stack) 10) 20)))
          
          (it "should work on an empty stack"
              (should= (Stack. (cons 10 nil) 1) (push (make-stack) 10)))

          (it "should recycle the end of the list"
              (let [end (push (push (make-stack) 30) 20)]
                (should (identical? (:top end) (-> (push end 10) :top rest)))))

          (it "should return a stack type element"
              (should= fifolifo.core.Stack (type (push (make-stack) 10))))
)

(describe "top"

          (it "should return the top element - not a list"
              (should= 20 (top (Stack. '(20 30 20) 3)))
              (should= 30 (top (Stack. '(30) 1))))

          (it "should return nil for an empty stack"
              (should= nil (top (make-stack))))
)

(describe "pop"
 
          (it "should return an empty stack (with empty list NOT NULL) for a stack with one item"
              (should= (Stack. () 0) (pop (Stack. '(10) 1))))

          (it "Should return an empty list for an empty list"
              (should= (Stack. () 0) (Stack. () 0)))

          (it "should return an stack type element"
              (should= fifolifo.core.Stack (type (pop (Stack. '(10 20) 2)))))
          (it "should decrement the counter"
              (should= 2 (-> (Stack. '(10 20 30) 3) pop stack-size))
              (should= 3 (-> (Stack. '(10 20 30 40) 4) pop stack-size))
              (should= 0 (-> (Stack. '(10) 1) pop stack-size)))

          (it "should recycle the end of the list"
              (let [end '(20 30 40)]
                (should (identical? end (-> (Stack. (cons 10 end) 4) pop :top)))))

          (it "of empty should have size of zero"
              (should= 0 (-> (Stack. '() 0) pop stack-size)))


)

(describe "The queue declaration"

          (it "should create something."
              (should (make-queue)))

          (it "should have empty components."
              (should= (Queue. nil nil 0) (make-queue)))
          
          (it "should have a size of zero."
              (should= 0 (queue-size (make-queue))))
          )

(describe "queue-size"
          (it "should return the size of the queue"
              (should= 3 (queue-size (Queue. () '(1 2 3) 3)))
              (should= 1 (queue-size (Queue. () '(1) 1)))
              (should= 0 (queue-size (Queue. () () 0))))
          
          (it "should return nil for a nil queue"
              (should= nil nil))
)

(describe "enqueue"
          (it "should add an element to front of the :back list"
              (should= (Queue. '(10 20) () 2) (enqueue (Queue. '(20) () 1) 10))
              (should= (Queue. '(10 20 30) '(40 50) 5) (enqueue (Queue. '(20 30) '(40 50) 4) 10))
              (should= (Queue. '(10) () 1) (enqueue (Queue. () () 0) 10)))
          
          (it "should increment the size"
              (should= 3 (queue-size (enqueue (Queue. '(10 20) () 2) 10)))
              (should= 4 (queue-size (enqueue (Queue. '(20) '(20 30) 3) 20))))
)

(describe "dequeue"
          
          (it "should decrement the counter"
              (should= 0 (queue-size (dequeue (Queue. '(10) () 1))))
              (should= 2 (queue-size (dequeue (Queue. '(10 20) '(30) 3)))))

          (it "of empty should have size zero"
              (should= 0 (-> (Queue. '(20) () 1) dequeue dequeue queue-size))
              (should= 0 (-> (Queue. nil '(20) 1) dequeue dequeue queue-size)))
         
          (it "should reverse the :back list and move it to :front w/out first item if :front is empty"
              (should= (Queue. nil '(20 30) 2) (dequeue (Queue. '(30 20 10) nil 3)))
              (should= (Queue. nil () 0) (dequeue (Queue. '(10) nil 1))))

          (it "should not touch the :back list if :front is not empty"
              (should= (Queue. '(21 13) '(8) 3) (dequeue (Queue. '(21 13) '(5 8) 4)))
              (should= (Queue. nil '(5 8) 2) (dequeue (Queue. nil '(3 5 8) 3))))             
)

(describe "stack-size"
          
          (it "should return 0 for an empty stack"
              (should= 0 (stack-size (make-stack))))

          (it "should return stack size for a stack"
              (should= 2 (stack-size (push (push (make-stack) 10) 30)))
              (should= 1 (stack-size (push (make-stack) 20))))
)

(run-specs)
