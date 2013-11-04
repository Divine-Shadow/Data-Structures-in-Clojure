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

(describe "The stack size function"
          (it "should give the correct stack size"
              (should= 3 (stack-size (Stack. '(1 2 3) 3))))
          (it "should give 0 for nill"
              (should= 0 (stack-size (Stack. nil 0))))
)

(describe "The push function"
          (it "should push correctly"
              (let [s (Stack. '(2 3) 2)]
                (should= (Stack. '(1 2 3) 3) (push s 1))))
          (it "should increment size when pushed"
              (let [s (Stack. '(2 3) 2)]
                (should= 3 (stack-size (push s 1)))))
          (it "should add to front of top"
              (let [s (Stack. '(2 3) 2)]
                (should= '(1 2 3) (:top (push s 1)))))
)

(describe "the pop function"
          (it "should remove correctly"
              (let [s (Stack. '(1 2 3) 3 )]
                (should= (Stack. '(2 3) 2) (pop s))))
          (it "should dec the size"
              (let [s (Stack. '(1 2 3) 3)]
                (should= 2 (stack-size (pop s)))))
          (it "should remove the front of the top"
              (let [s (Stack. '(1 2 3) 3)]
                (should= '(2 3) (:top (pop s)))))
          (it "should work with nil"
              (should- (Stack. nil 0) (pop (make-stack))))
)

(describe "the top func"
          (it "should return the top"
              (let [s (Stack. '(1 2 3) 3)]
                (should= 1 (:top s))))
          (it "should return if size is 0"
              (should= nil (top (make-stack))))
)


(describe "The queue declaration"

          (it "should create something."
              (should (make-queue)))

          (it "should have empty components."
              (should= (Queue. nil nil 0) (make-queue)))
          
          (it "should have a size of zero."
              (should= 0 (stack-size (make-stack))))
)

(describe "the que size func"
          (it "should return the size of empty"
              (should= 0 (queue-size (make-queue))))
          (it "should return the size"
              (should= 4 (queue-size (Queue. '(1 2 3) '(4) 4))))
)

(describe "the enqueue funct"
          (it "should add an elt to the back"
              (let [s (Queue. '(1 2 3) nil 3)]
                (should= (Queue. '(5 1 2 3) nil 4) (enqueue s 5))))

)

(run-specs)
