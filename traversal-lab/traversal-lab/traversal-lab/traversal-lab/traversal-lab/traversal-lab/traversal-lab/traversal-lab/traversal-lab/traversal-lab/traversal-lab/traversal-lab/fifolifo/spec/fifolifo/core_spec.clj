(ns fifolifo.core-spec
  (:refer-clojure :exclude [pop peek])
  (:require [speclj.core :refer :all]
            [fifolifo.core :refer :all])
  (:import [fifolifo.core Stack Queue]))

;; # The Tests
;;
;; We are going to use [spelj](https://github.com/slagyr/speclj) for our tests.
(def x (make-stack))
(def z (Stack. '( 1 2 3) 3))
(def a (Stack. '(1 2) 2))
(def q1 (make-queue))
(def q2 (Queue. '(10) '(1 2) 3))
(def q3 (Queue. '(1 2) '(10) 3))

(describe "The stack declaration"

          (it "should create something."
              (should (make-stack)))

          (it "should have empty components."
              (should= (Stack. nil 0) (make-stack)))
          
          (it "should have a size of zero."
              (should= 0 (stack-size (make-stack))))
          )

(describe "The Stack size" 
          (it "should return 0 when an empty list is created"
              (should= 0 (stack-size (make-stack)))
          )
)

(describe "The stack push"
          
          (it "should increment size"
              (should= 1  (stack-size (push x 1)))
          )
)

(describe "The stack pop"
          
          (it "should not return a negitive value if the stack is empty"
              (should= 0 (stack-size (pop x)))
              )
          (it "should remove elments"
               (let [xx (Stack. '(1 2 3) 3)]
                 (should= (Stack. '(2 3) 2) (pop xx)))
               )

           (it "it should not reverse elements"
               (should= (top a) (top z) 
           )     
     )
)


(describe "The queue declaration"

          (it "should create something."
              (should (make-queue)))

          (it "should have empty components."
              (should= (Queue. nil nil 0) (make-queue)))
          
          (it "should have a size of zero."
              (should= 0 (queue-size (make-queue))))
)
          
(describe "The enqueue fucntion"
          
          (it "should increment size"
              (should= 1 (queue-size (enqueue q1 10 )))
          )
)    

(describe "The dequeue function"
          (it "should not make a negative size"
              (should= 0 (queue-size (dequeue q1)))
          )

          
;;          (it "should  reverse when flipping"
  ;;           (should= (Queue '(1 2) '(10) 3) (reverse q2))
    ;;          )
          
    ;;      (it "should  dequeue aftr flipping"
  ;;            (should= (peek q3) (dequeue q2)) 
;;)
)

  ;;        (it "should not erase back when flipping"
    ;;          (should-not)
      ;;        )

;;)
(run-specs)
