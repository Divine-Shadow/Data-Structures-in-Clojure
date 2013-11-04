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
          )

(describe "Empty stacks"
          (it "should have zero size."
              (should= 0 (stack-size (make-stack))))

          (it "should increase size when something pushes to them."
              (should= 1 (stack-size (push (make-stack) 20))))

          (it "should stay empty when popping something from them."
              (should= 0 (stack-size (pop (make-stack)))))
          
          (it "should have a size of zero."
              (should= 0 (stack-size (make-stack))))
          
          (it "should have a nil top."
              (should-be nil? (top (make-stack))))
          )
(describe "The queue declaration"

          (it "should create something."
              (should (make-queue)))

          (it "should have empty components."
              (should= (Queue. nil nil 0) (make-queue)))
          
          
          )

(defn dequeue-all [q acc]
  (if (zero? (stack-size q))
    acc
    (recur (dequeue q) (cons (peek q) acc))))

(describe "The queue declaration"

          (it "should create something."
              (should (make-queue)))

          (it "should have empty components."
              (should= (Queue. nil nil 0) (make-queue)))
          )

(describe "Empty queues"
          (it "should have zero size."
              (should= 0 (queue-size (make-queue))))

          (it "should increase size when something pushes to them."
              (should= 1 (queue-size (enqueue (make-queue) 20))))

          (it "should stay empty when popping something from them."
              (should= 0 (queue-size (dequeue (make-queue)))))
          
          (it "should have a size of zero."
              (should= 0 (queue-size (make-queue))))
          
          (it "should have a nil top."
              (should-be nil? (peek (make-queue))))
          )
(defn pop-all [stk acc]
  (if (zero? (stack-size stk))
    acc
    (recur (pop stk) (cons (top stk) acc))))

(describe "Stacks"
          (it "should keep track of their size."
              (let [tstack (reduce push (make-stack) '(1 2 3 5 8))]
                (should= 5 (stack-size tstack))
                (should= 3 (-> tstack pop pop stack-size))
                (should= 0 (-> tstack pop pop pop pop pop pop stack-size))))

          (it "should pull things out in the correct order."
              (let [tstack (reduce push (make-stack) '(1 2 3 5 8))]
                (should= 8 (top tstack) )
                (should= 5 (stack-size tstack))
                (should= '(1 2 3 5 8) (pop-all tstack '())) ; pop-all will reverse the output
                ))

          (it "should be able to push things after popping."
              (let [tstack (-> (reduce push (make-stack) '(1 2 3 5 8)) pop pop (push 10) (push 20) (push 30) pop)]
                (should= 5 (stack-size tstack))
                (should= 3 (-> tstack pop pop stack-size))
                (should= 0 (-> tstack pop pop pop pop pop pop stack-size))
                (should= '(1 2 3 10 20) (pop-all tstack '()))) ; pop-all will reverse the output
              ))


(describe "Queues"
          (it "should keep track of their size."
              (let [tqueue (reduce enqueue (make-queue) '(1 2 3 5 8))]
                (should= 5 (queue-size tqueue))
                (should= 3 (-> tqueue dequeue dequeue queue-size))
                (should= 0 (-> tqueue dequeue dequeue dequeue dequeue dequeue dequeue queue-size))))

          (it "should pull things out in the correct order."
              (let [tqueue (reduce enqueue (make-queue) '(1 2 3 5 8))]
                (should= 1 (peek tqueue) )
                (should= 5 (queue-size tqueue))
                (should= '(8 5 3 2 1) (dequeue-all tqueue '())) ; dequeue-all will reverse the output
                ))

          (it "should be able to enqueue things after dequeueping."
              (let [tqueue (-> (reduce enqueue (make-queue) '(1 2 3 5 8)) dequeue dequeue (enqueue 10) (enqueue 20) (enqueue 30) dequeue)]
                (should= 5 (queue-size tqueue))
                (should= 3 (-> tqueue dequeue dequeue queue-size))
                (should= 0 (-> tqueue dequeue dequeue dequeue dequeue dequeue dequeue queue-size))
                (should= '(30 20 10 8 5) (dequeue-all tqueue '()))) ; dequeue-all will reverse the output
              ))

(run-specs)
