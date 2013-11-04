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

(describe "push"
	  
	  (it "should increment size."
	      (should= 2 (stack-size (push (Stack. (cons 5 nil) 1) 10))))
)

(describe "pop"

          (it "should work if empty."
              (should= 0 (stack-size (pop (Stack. nil 0)))))

	  (it "should remove elements."
	      (should= (Stack. (cons 1 nil) 1)  (pop (Stack. (cons 2 (cons 1 nil)) 2))))
)




(describe "The queue declaration"

          (it "should create something."
              (should (make-queue)))

          (it "should have empty components."
              (should= (Queue. nil nil 0) (make-queue)))
          
          (it "should have a size of zero."
              (should= 0 (stack-size (make-stack))))
          )

(describe "Enqueue"
	  
	  (it "should increment size."
	      (should= 1 (queue-size (enqueue (Queue. nil nil 0) 13 ))))
)

(describe "Dequeue"

          (it "should work if empty."
              (should= 0 (queue-size (dequeue (Queue. nil nil 0)))))
)


(run-specs)
