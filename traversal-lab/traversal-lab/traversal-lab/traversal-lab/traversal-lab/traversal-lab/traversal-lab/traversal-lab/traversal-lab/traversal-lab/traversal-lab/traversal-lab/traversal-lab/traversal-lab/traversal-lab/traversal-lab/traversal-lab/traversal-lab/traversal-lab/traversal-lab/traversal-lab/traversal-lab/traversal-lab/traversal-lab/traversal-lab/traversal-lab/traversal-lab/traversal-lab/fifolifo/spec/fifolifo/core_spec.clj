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
              (should= 0 (queue-size (make-queue))))
          )

(describe "stack-size "

          (it "should work with an empty stack of size zero."
              (should= 0 (stack-size (make-stack))))
	     
	  (it " should work with an empty stack of size non zero."
	      (should= 2 (stack-size (Stack. nil 2))))
	   
	  (it "should work with a non empty stack."
	      (should= 1 (stack-size (push ( Stack. nil 0) 4))))

	  (it "pop of empty stack should be 0"
	      (should= 0 (stack-size ( pop(Stack. nil 0)))))

	  (it "should work after pop and size has decremented"
	      (should= 1 (stack-size (pop(push(push (Stack. nil  0) 1)2)))))
	    
	    ) 


(describe "top"
         (it "should have a nil top for empty stacks."
	    (should= nil  (:top (make-stack))))
	 
	 (it "should give the top of the stack."
	     (should= 1 (:top  (Stack. 1 2))))

;;	 (it "should work after pushing"
;;	     (should= 1 (:top (pop (push (push (make-stack) 1) 3) ))))

	    )

(describe "push" 
;;	(it "should add elements to the front "
;;	   (should= 1 (:top (push(make-stack) 1))))

	(it "should incrememnt size when adding an element"
	   (should= 2 (:size (push(push (Stack. nil 0)1)2))))
	   )
(describe "pop"

 ;;        (it "should remove the top element"
;;	      (should= 3  (top (pop(push(push(make-stack) 3) 1) ))))
         
	;; (it "should remove the top element"
	;;      (should= 3 (:top (pop(push(push(make-stack)3) 1)))))
	 (it "should give nil if an empty stack is popped"
	      (should= nil (top  (pop(make-stack)))))

	      )
	      
(describe "queue-size"
   	(it "should work for an empty queue of size 0"
	    (should= 0 (queue-size (make-queue))))

	(it "should work for an empty queue of size non-zero"
	    (should= 2 (queue-size (Queue. nil nil 2))))
        
	(it "should work after enquing"
	    (should= 1 (queue-size (enqueue (Queue. nil nil 0) 1 ))))

        (it "should work after dequing"
	    (should= 0 (queue-size (dequeue(enqueue (make-queue)2)))))
	
	(it "should work when dequeuing an empty queue"
	    (should= 0 (queue-size (make-queue))))
	   )

(describe "enqueue"
      
;;        (it "should add element to the back"
;;	     (should= 2 (:back (enqueue (enqueue(make-queue) 1  ) 2 ) )))
         
	 (it "should increment size"
	     (should= 2 (queue-size (enqueue (enqueue (make-queue) 1 ) 2))))
	   )

(describe "dequeue" 
          (it "should be nil if an empty stack is dequeued"
	     (should= nil  (:front (dequeue (make-queue)))))  
	   
	   (it "should decrememnt size" 
	      (should= 1 (queue-size (dequeue (enqueue (enqueue (make-stack)1)2)))))

	(it "should reverse when the front is empty"
	(should= 5 (peek(dequeue(dequeue(Queue. (cons 5 (cons 3 nil)) nil 2))))))
	

;;	(it "should erase back after turning it to front"
;;	              (should= nil (:back (dequeue(Queue. nil 5 1))))
;;		                    )
)

(run-specs)
