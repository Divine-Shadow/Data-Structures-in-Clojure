(ns fifolifo.core
  (:refer-clojure :exclude [pop peek])
)

;; # Introduction
;;
;; We are going to implement stacks and double ended queues in
;; using Clojure lists and our own record types.
;;
;; The student will be given the record definition and a few sample
;; functions, and be asked to implement the given stub functions and
;; the tests.

;; # The Stack
;;
;; Stacks are extremely easy to implement using lists.  This parts
;; should be very simple.  We use a record as a wrapper, though.

(defrecord Stack [top size])

(defn make-stack
  "Create an empty stack."
  []
  (Stack. nil 0))

(defn stack-size
  "Return the size of the stack."
  [stack]
  (if (= (:top stack) 0) 0 (:size stack))
 )

(defn push
  "Push an element onto the beginning of a stack."
  [stk elt]
  (Stack. (cons elt (:top stk)) (inc (:size stk)))
  )

(defn pop
  "Remove an element from the top of the stack.  Return the resulting stack."
  [stk]
  (if (or (= (:size stk) 0) (= (:size stk) 1)) (Stack. nil 0) (Stack. (rest (:top stk)) (dec (:size stk))))
  )


(defn top
  "Return the top of the stack."
  [stk]
  (if (= (:size stk) 0) nil (first (:top stk)))
  )

;; # Doubly-ended queues
;;
;; Queues are a little bit more tricky to implement with persistent lists.
;; The trick is to provide two lists, one representing the front, and one
;; representing the back.  You enqueue elements by placing them in the back
;; list, and dequeue elements by taking them from the front list.
;;
;; If the front list is empty, you reverse the back list and use that as the
;; front list.

(defrecord Queue [back front size])

(defn make-queue
  "Create an empty queue."
  []
  (Queue. nil nil 0))

(defn queue-size
  "Return the size of the queue."
  [queue]
  (if (= (:size queue) 0) 0 (:size queue))
  )

(defn enqueue
  "Add an element to the back of a queue."
  [queue elt]
  (Queue. (cons elt (:back queue)) (:front queue) (inc (:size queue))))

(defn dequeue
  "Remove an element from the back of the queue.  Just return the new queue."
  [queue]
  (cond (= (:size queue) 0) queue
        (= (:front queue) nil)  (Queue. nil (first (cons (rest (reverse (:back queue))) (:front queue))) (dec (:size queue)));the back of the list is going into the front of the list thus (()) is happening to avoid just get the inside () by using first
        :else (Queue. (:back queue) (rest (:front queue)) (dec (:size queue)))
  )
)


(defn peek
  "Return the next element that will come out the front of the queue."
  [queue]
  (if (empty? (:front queue)) (first (reverse (:back queue))) (first (:front queue)))
 )
