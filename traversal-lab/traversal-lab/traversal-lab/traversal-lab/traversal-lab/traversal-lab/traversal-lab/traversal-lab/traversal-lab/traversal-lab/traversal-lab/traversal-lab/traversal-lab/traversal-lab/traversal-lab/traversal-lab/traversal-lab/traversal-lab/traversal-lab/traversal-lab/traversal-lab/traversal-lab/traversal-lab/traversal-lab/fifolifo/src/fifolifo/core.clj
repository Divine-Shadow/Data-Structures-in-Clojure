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

(defrecord Stack [top  size])

(defn make-stack []
  (Stack.  nil 0))


(defn stack-size
  "Return the size of the stack."
  [stack]
  (:size stack)
  )
(defn push
  "Push an element onto the beginning of a stack."
  [stk elt]
  (Stack. (cons elt (:top stk)) (-> stk :size inc)))

(defn pop
  "Remove an element from the top of the stack.  Return the resulting stack."
  [stk]
  (if (empty? (:top stk)) stk 
      (Stack. (-> stk :top rest) (-> stk :size dec))))

(defn top
  "Return the top of the stack."
  [stk]
  (-> stk :top first))


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
  (:size queue))

(defn enqueue
  [queue elt]  (Queue. (cons elt (:back queue)) (:front queue) (-> queue :size inc)))

(defn queue-helper
  "This helper function flips the queues around if the first element is nil or empty, it's powerful since we need to use this logic in both dequeue and peek functions. By writing this we save up some lines of code and make it more simplified"
  [queue]
  (if (or (not (empty? (:front queue)))
          (empty? (:back queue)))
    queue
    (Queue. nil (reverse (:back queue)) (:size queue))))

(defn dequeue
  "Remove an element from the back of the queue.  Just return the new queue."
  [queue](if (zero? (:size queue)) queue
      (let [nuq (queue-helper queue)]
        (Queue. (:back nuq) (-> nuq :front rest) (-> nuq :size dec)))))
(defn peek
  "Return the next element that will come out the front of the queue."
  [queue]
  (-> queue queue-helper :front first))


