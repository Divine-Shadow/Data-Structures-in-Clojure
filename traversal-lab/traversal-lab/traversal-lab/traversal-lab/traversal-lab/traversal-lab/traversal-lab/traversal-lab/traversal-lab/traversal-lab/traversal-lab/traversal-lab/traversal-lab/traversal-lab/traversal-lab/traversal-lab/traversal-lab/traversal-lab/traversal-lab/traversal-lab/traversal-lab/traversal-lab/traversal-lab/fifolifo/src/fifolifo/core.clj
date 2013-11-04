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

(defrecord Stack [data])

(defn make-stack
  "Create an empty stack."
  []
  (Stack. 0 nil))

(defn stack-size
  "Return the size of the stack."
  [stack]
  nil)

(defn push
  "Push an element onto the beginning of a stack."
  [s elt]
  (swap! (:data s) #(cons elt %)))

(defn pop
  "Remove an element from the top of the stack.  Return the resulting stack."
  [s]
  (swap! (:data s) rest))

(defn top
  "Return the top of the stack."
  [s]
  (first @(:data s)))

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
  (size))

(defn enqueue
  "Add an element to the back of a queue."
  [queue elt]
  (Queue. (cons elt (:back queue)) (:front queue)(-> queue :size inc)))

(defn rev-seq
  [xx]
  (if (empty? xx)
    xx
    (concat
      (rev-seq (rest xx))
      (list (first xx)))))

(defn dequeue
  "Remove an element from the back of the queue.  Just return the new queue."
  [queue n]
  (cond (= size 0) n
        (empty? front) (Queue. nil (rest (rev-seq back)) (dec size))
  :else (Queue. back (rest front) (dec size))))

(defn peek
  "Return the next element that will come out the front of the queue."
  [queue]
  nil)

