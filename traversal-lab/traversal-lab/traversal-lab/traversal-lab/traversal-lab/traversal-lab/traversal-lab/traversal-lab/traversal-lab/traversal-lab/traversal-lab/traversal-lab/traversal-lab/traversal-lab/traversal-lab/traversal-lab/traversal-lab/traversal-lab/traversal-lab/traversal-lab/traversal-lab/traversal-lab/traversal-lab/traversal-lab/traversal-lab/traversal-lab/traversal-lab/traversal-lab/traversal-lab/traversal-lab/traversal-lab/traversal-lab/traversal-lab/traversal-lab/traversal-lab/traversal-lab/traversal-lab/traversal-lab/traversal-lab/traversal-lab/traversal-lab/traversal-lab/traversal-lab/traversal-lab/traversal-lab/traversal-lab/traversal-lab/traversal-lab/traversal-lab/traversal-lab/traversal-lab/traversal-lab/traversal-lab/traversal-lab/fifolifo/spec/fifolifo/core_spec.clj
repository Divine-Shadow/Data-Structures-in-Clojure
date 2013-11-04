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

(describe "The top function"
          
          (it "should return nil if the stack is empty"
              (should= nil (top (make-stack))))

          (it "should return the element at the top of a stack with data"
              (let [ss (push (push (push (make-stack) 3) 4) 5)]
              (should= 5 (top ss))))

          ;;(it "")
)

(describe "The push function"

          (it "should not return an empty stack"
              (should-not= (make-stack) (push (make-stack) 4)))

          (it "should add element at the top of the stack."
              (let [ss (push (make-stack) 3)]
              (should= 3 (top ss))))

          (it "should have the first added element at the end of the stack."
              (let [ss (push (push (push (make-stack) 3) 4) 5)]
              (should= 3  (top (pop (pop ss))))))

          ;;(it "should recycle the original stack when a new element is pushed into the stack."
            ;;  (let [ss (push (make-stack) 3)]
              ;;(should= true  (= ss (rest(:top (push ss 5)))))))

          (it "should show the correct size of the new stack."
              (should= 3 (stack-size (push (push (push (make-stack) 3) 4) 5))))

          ;;(it "")
)

(describe "The pop function"

          (it "should return the original stack if the stack is empty."
              (should= (make-stack) (pop (make-stack))))

          (it "should return an empty stack if the original stack only had one element."
              (should= true (empty? (:top (pop (push (make-stack) 4))))))

          (it "should remove the top element of the stack with data"
              (let [ss (push (push (push (make-stack) 3) 4) 5)]
               (should= (push (push (make-stack) 3) 4) (pop ss))))

          (it "should have the next element at the top in the returned stack."
              (let [ss (push (push (push (make-stack) 3) 4) 5)]
               (should= 4 (top (pop ss)))))
          
          (it "should show the correct size of the new stack"
              (let [ss (push (push (push (make-stack) 3) 4) 5)]
               (should= 2 (stack-size (pop ss)))))

)

(describe "The queue declaration"

          (it "should create something."
              (should (make-queue)))

          (it "should have empty components."
              (should= (Queue. nil nil 0) (make-queue)))
          
          (it "should have a size of zero."
              (should= 0 (queue-size (make-queue))))
)

(describe "The queue-size function"

          (it "should have size 0 if the queue is empty."
              (should= 0 (queue-size (make-queue))))

          (it "should not have size 0 if the queue isn't empty."
              (should-not= 0 (queue-size (enqueue (make-queue) 4))))

          (it "should show the size of the queue with data."
              (should= 3 (queue-size (enqueue (enqueue (enqueue (make-queue) 3) 4) 5))))

)

(describe "The enqueue function"

          (it "should not return an empty queue"
              (should-not= (make-queue) (enqueue (make-queue) 4)))

          (it "should add elements to the back of the queue"
              (should= 5 (first (:back (enqueue (enqueue (enqueue (make-queue) 4) 7) 5)))))
          
          (it "should have the 'oldest' element at the top."
              (should= 4 (peek (enqueue (enqueue (enqueue (make-queue) 4) 7) 5))))

          (it "should continue adding new elements to the back after a dequeue"
              (should= 5 (first (:back (enqueue (dequeue (enqueue (make-queue) 4)) 5)))))
          
          (it "should have recorded the correct size of the queue"
              (should= 3 (queue-size (enqueue (enqueue (enqueue (make-queue) 4) 7) 5))))
)

(describe "The dequeue function"
          (it "should check if back list is emptied."
              (should= nil (:back (dequeue (enqueue (make-queue) 6)))))

          
          (it "should not create negative queue sizes."
              (should= 0 (:size (dequeue (dequeue (enqueue (make-queue) 7))))))
)

;;(describe "The peek function")

;;(describe "The reverse function")

;;(describe "The transfer2front function"
          
  ;;        (it "should have no elements in the back list"
    ;;          (should= nil (back (transfer2front qq))))

      ;;    (it "should have data in the front list"
        ;;      (should-not= nil (front (transfer2front qq))))
          
          ;;(it "should have the front list populated with the reversed order of the original back list."
            ;;  (should= ))
          
          ;;(it "should "
            ;;  ))

(describe "The stack-size function"

          (it "should show that an empty stack has size 0."
              (should= 0 (stack-size (make-stack))))

          (it "should not have size 0 if the stack isn't empty."
              (let [ss (push (push (push (make-stack) 3) 4) 5)]
              (should-not= 0 (stack-size ss))))

          (it "should show the size of a stack with data."
              (let [ss (push (push (push (make-stack) 5) 6) 7)]
              (should= 3 (stack-size ss))))
)


(run-specs)
