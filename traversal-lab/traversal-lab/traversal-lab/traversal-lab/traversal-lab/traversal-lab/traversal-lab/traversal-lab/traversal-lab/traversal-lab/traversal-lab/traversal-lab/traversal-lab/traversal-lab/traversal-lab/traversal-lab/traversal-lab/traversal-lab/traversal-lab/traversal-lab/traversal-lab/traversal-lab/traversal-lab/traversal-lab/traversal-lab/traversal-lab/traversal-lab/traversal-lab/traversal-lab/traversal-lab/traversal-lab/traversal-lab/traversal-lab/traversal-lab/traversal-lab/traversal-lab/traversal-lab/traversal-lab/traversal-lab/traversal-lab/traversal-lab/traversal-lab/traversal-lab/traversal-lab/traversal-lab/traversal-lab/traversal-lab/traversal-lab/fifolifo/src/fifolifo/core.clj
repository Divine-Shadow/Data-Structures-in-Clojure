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

(defn aset! [a x] (swap! a (constantly x)))
(defn make-stack
  "Create an empty stack."
  []
  (Stack. (atom nil) 0))
;;  (Stack. nil 0))
;;  (Stack. (atom 0) (vec (map (fn [_] (atom 0)) (range 10)))))

(defn stack-size
  "Return the size of the stack."
  [stack]
  (:size stack))

(defn push
  "Push an element onto the beginning of a stack."
  [stk elt]
  (Stack. (cons elt (:top stk)) (-> stk :size inc)))
;;  (Stack. (cons elt stk) (-> stk :size inc)))
;;  (aset! ((stk :size) @(stk :top)) elt)
;;  (swap! (:top stk) inc))

(defn pop
  "Remove an element from the top of the stack.  Return the resulting stack."
  [stk]
  (cond (= 0 (:size stk)) stk
        :else (Stack. (rest (:top stk)) (-> stk :size dec))))
;;  (swap! (:top stk) dec)
;;  ((:size stk) @(:top stk)))

(defn top
  "Return the top of the stack."
  [stk]
  (first (:top stk)))
;;  ((:size stk) (- @(:top stk) 1)))

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

;;  (Queue. (atom 0) (atom 0)
;;          (vec (map (fn [_] (atom 0)) (range 10)))))

(defn queue-size
  "Return the size of the queue."
  [queue]
  (:size queue))

(defn enqueue
  "Add an element to the back of a queue."
  [queue elt]
  (Queue. (cons elt (:back queue)) (:front queue) (-> queue :size inc)))

;;  (aset! ((:size queue) @(:back queue)) elt)
;;  (swap! (:back queue) #(mod (inc %) 10)))

(defn dequeue
  "Remove an element from the back of the queue.  Just return the new queue."
  [queue]
  (cond (= 0 (:size queue)) queue
        (empty? (:front queue)) (Queue. nil (rest (reverse (:back queue))) (-> queue :size dec))
        :else (Queue. (:back queue) (rest (:front queue)) (-> queue :size dec))))

;;  (let [the-top (top queue)]
;;    (swap! (:front queue) #(mod (inc %) 10))
;;    (the-top)))

(defn peek
  "Return the next element that will come out the front of the queue."
  [queue]
  (cond (empty? (:front queue)) (first (reverse (:back queue)))
        :else (first (:front queue))))
;;  @((:size queue) @(:front queue)))
