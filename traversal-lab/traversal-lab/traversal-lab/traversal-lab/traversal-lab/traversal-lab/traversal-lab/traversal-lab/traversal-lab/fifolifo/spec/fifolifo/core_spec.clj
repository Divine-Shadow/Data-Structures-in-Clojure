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

(describe "the size call"
	  (it "should return zero if the stack is empty."
	      (should= 0 (stack-size (Stack. nil 0))))

	  (it "should return the size"
	      (let [xx (cons 10 (cons 11 nil))]
	      (should= 2 (stack-size (Stack. xx 2))))))

(describe "The push call"
	  (it "should still work when the stack is empty."
	      (should= (Stack. (cons 5 nil) 1) (push (Stack. nil 0) 5)))

	  (it "should push an element to the front of the stack"
	      (let [xx (cons 10 (cons 11 nil))]
	      (should= (Stack. (cons 1 xx) 3) (push (Stack. xx 2) 1))))

	  (it "should share memory."
	      (let [xx (cons 10 (cons 11 nil))]
	      (should (identical? (Stack. (cons 1 xx) 3) (push (Stack. xx 2) 1))))))

(describe "The pop call"
	  (it "should return nil when the stack is empty."
	      (let [xx nil]
	      (should= nil (pop (Stack. xx 0)))))
	  (it "should pop an element off the stack"
	      (let [xx (cons 10 (cons 11 nil))]
	      (should= (Stack. (cons 11 nil) 1) (pop (Stack. xx 2)))))
	  (it "should share memory."
	      (let [xx (cons 10 (cons 11 nil))]
	      (should (identical? (Stack. (cons 11 nil) 1) (pop (Stack. xx 2)))))))

(describe "The top call"
	  (it "should return nil if the stakc is empty."
	      (should= nil (top (Stack. nil 0))))
	  (it "should return the top of the stack only."
	      (let [xx (cons 10 (cons 11 nil))]
	      (should= 10 (top (Stack. xx 2))))))

(describe "The queue declaration."
          (it "should create something."
              (should (make-queue)))

          (it "should have empty components."
              (should= (Queue. nil nil 0) (make-queue)))
          
          (it "should have a size of zero."
              (should= 0 (stack-size (make-stack))))
          )

(describe "return the size of the queue."
	  (it "should return 0 if the queue is empty."
	      (should= 0 (queue-size (Queue. nil nil 0))))
	  (it "should return only the size of the queue"
	      (should= 4 (queue-size (Queue. (cons 10 (cons 11 nil)) (cons 2 (cons 3 (cons 1 nil))) 4)))))

(describe "The enqueue call."
	  (it "should still work with an empty queue."
	      (should= (Queue. (cons 2 nil) nil 1) (enqueue (Queue. nil nil 0) 2)))
	  (it "should add an element to the back of the queue"
	      (let [xx (cons 10 (cons 11 nil))] 
	      (let [yy (cons 3 (cons 1 nil))]
	      (let [zz 4]
	      (let [aa 2]
	      (should= (Queue. (cons aa yy) xx (inc zz)) (enqueue (Queue. yy xx zz) aa))))))))

(describe "The dequeue call."
          (it "should return the empty queue if the queue is empty."
	      (should= (Queue. nil nil 0) (dequeue (Queue. nil nil 0))))
	  
	  (it "should still work if the back of the queue is empty."
	      (let [xx (cons 10 (cons 11 nil))] 
	      (let [yy nil]
	      (let [zz 4]
	      (let [aa 2]
	      (should= (Queue. yy xx (dec zz)) (dequeue (Queue. yy (cons aa xx) zz))))))))
	  
	  (it "should take element from the back of the queue"
	      (let [xx (cons 10 (cons 11 nil))] 
	      (let [yy (cons 3 (cons 1 nil))]
	      (let [zz 4]
	      (let [aa 2]
	      (should= (Queue. yy xx (dec zz)) (dequeue (Queue. yy (cons aa xx) zz))))))))

	  (it "should share memeory"
	      (let [xx (cons 10 (cons 11 nil))] 
	      (let [yy (cons 3 (cons 1 nil))]
	      (let [zz 4]
	      (let [aa 2]
	      (should (identical? (Queue. yy xx (dec zz)) (dequeue (Queue. yy (cons aa xx) zz))))))))))

(describe "The peek call."
	  (it "should return nil if fed an empty queue."
	      (should= nil (peek (Queue. nil nil 0))))

	  (it "should return the first element."
	      (let [xx (cons 10 (cons 11 nil))] 
	      (let [yy (cons 3 (cons 1 nil))]
	      (let [zz 4]
	      (should= 10 (peek (Queue. yy xx zz))))))))

(describe "inaction"
          (it "should have some tests at some point."
              (should true)))

(run-specs)
