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
  (:size stack))
(defn push
  "Push an element onto the beginning of a stack."
  [stk elt]
  (Stack. (cons elt (:top stk)) (inc (:size stk))))
(defn pop
  "Remove an element from the top of the stack.  Return the resulting stack."
  [stk]
  (cond (< (:size stk) 2) (make-stack)
        :else (Stack. (cons (first (rest (:top stk))) (rest (rest (:top stk)))) (dec (:size stk)))))
(defn top
  "Return the top of the stack."
  [stk]
  (first (:top stk)))
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

(defn enqueue
  "Add an element to the back of a queue."
  [queue elt]
  (if (= (:front queue) nil) (Queue. nil elt 1)
    (Queue. (cons elt (:back queue)) (:front queue) (inc (:size queue)))))


(defn queue-size
  "Return the size of the queue."
  [queue]
  (:size queue))


(defn dequeue
  "Remove an element from the back of the queue.  Just return the new queue."
  [queue]
  (cond (< (:size queue) 2) (make-queue)
        :else (Queue. (rest (:back queue)) (:front queue) (dec (:size queue)))))

(defn peek
  "Return the next element that will come out the front of the queue."
  [queue]
  (cond (< (:size queue) 2) nil
        (= (:size queue) 2) (first (:back queue))
        :else (peek (Queue. (rest (:back queue)) (:front queue) (- (:size queue) 1)))))
