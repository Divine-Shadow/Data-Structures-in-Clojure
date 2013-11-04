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
  [stk]
  (:size stk)
  )

(defn push
  "Push an element onto the beginning of a stack."
  [stk elt]

  (cond
    (empty? stk) (push (make-stack) elt)
    :else (Stack. (cons elt (:top stk)) (-> stk :size inc))))

(defn pop
  "Remove an element from the top of the stack.  Return the resulting stack."
  [stk]
  (cond
    (= (:size stk) 0) stk
    (empty? stk) (make-stack)
    :else (Stack. (-> stk :top rest) (-> stk :size dec)))
  )

(defn top
  "Return the top of the stack."
  [stk]
  (-> stk :top first)
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

(defn back-to-front [queue]
  (Queue. nil (reverse (:back queue)) (:size queue))
  )

(defn queue-size
  "Return the size of the queue."
  [queue]
  (cond
    (nil? (:size queue)) 0
    :else (:size queue))
  )

(defn enqueue
  "Add an element to the back of a queue."
  [queue elt]
  (Queue.
    (cons elt (:back queue))
    (:front queue)
    (inc (if (= (:size queue) nil)
                   0
                   (:size queue))
                 ))
  )

(defn dequeue
  "Remove an element from the back of the queue.  Just return the new queue."
  [queue]
  (cond
    (empty? queue) (make-queue)
    (= (:size queue) 0) queue
    (empty? (:front queue)) (dequeue (back-to-front queue))
    :else (Queue.
            (:back queue) ;;:back
            (-> queue :front rest) ;;:front
            (-> queue :size dec)) ;;:size
    ))

(defn peek
  "Return the next element that will come out the front of the queue."
  [queue]
  (cond
    (empty? queue) nil
    (= (:size queue) 0) nil
    (empty? (:front queue)) (peek (back-to-front queue))
    :else (-> queue :front first)
    ))
