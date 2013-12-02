(ns traversals.core
 (:refer-clojure :exclude [pop peek])
)

;; Given Code

(defrecord BNode [left data right])

(defrecord Queue [back front size])

(defn make-queue
  "Create an empty queue."
  []
  (Queue. nil nil 0)
)

(defn queue-size
  "Return the size of the queue."
  [queue]
  (:size queue)
)

(defn enqueue
  "Add an element to the back of a queue."
  [queue elt]
  (if (= (:size queue) 0) 
    (Queue. (:back queue) (cons elt (:front queue) ) 1)
  (Queue. (cons elt (:back queue)) (:front queue) (+ 1 (:size queue))))
)
(defn dequeue
  "Remove an element from the front of the queue.  Just return the new queue."
  [queue]
(if (= 0 (:size queue)) queue
  (if (= () (rest (:front queue))) 
    (Queue. nil (reverse (:back queue)) (-> queue :size dec))
    (Queue.  (:back queue) (rest (:front queue)) (-> queue :size dec)) 

    )
)
  )

(defn peek
  "Return the next element that will come out the front of the queue."
  [queue]
 (first (:front queue))
)



(defn add [t elt]
   (cond (nil? t)          (BNode. nil elt nil)
         (= elt (:data t)) t
         (< elt (:data t)) (BNode. (add (:left t) elt) (:data t) (:right t))
         :else             (BNode. (:left t) (:data t) (add (:right t) elt))))

;; A fast way to make trees is to use the code
;; (reduce add nil '(4 2 3 5 9))
;;
;; Use (reduce #(str "(" %1 " " %2 ")") "0" '(1 2 3)) to get an idea what it's doing.

;; # Your Code

(defn preorder 
  "Outputs a list containing the preorder traversal of the given tree." 
  [t]
(if (= nil t) '()
(concat (cons (:data t) nil) (preorder (:left t)) (preorder (:right t)))
  
))

(defn postorder 
  "Outputs a list containing the postorder traversal of the given tree." 
  [t]
(if (= nil t) '()
 (concat  (postorder (:left t)) (postorder (:right t)) (cons (-> t :data) nil))  
))

(defn inorder 
  "Outputs a list containing the in-order traversal of the given tree." 
  [t]
(if (= nil t) '()
(concat  (inorder (:left t)) (cons (:data t) nil)  (inorder (:right t)))  
))

(declare levelorder-aux)

(defn levelorder 
  "Outputs a list containing the level-order traversal of the given tree." 
  [t]
(when-not (nil? t) (levelorder-aux (enqueue (Queue. nil nil 0) t) '() ))
)

(defn levelorder-aux [q xx]
(if (empty? q) xx (levelorder-aux (enqueue (enqueue (dequeue q) (:left q)) (:right q) ) (cons (peek q) xx)))
)


(defn frontier 
  "Outputs a list containing the frontier of the given tree." 
  [t]
(cond
 (= nil t) '()

(and (:left t) (:right t)) (concat (frontier (:left t)) (frontier (:right t)))
(:left t) (frontier (:left t))
(:right t) (frontier (:right t))    
:ForeverAlone (cons (:data t) nil)

)
)

(defn map-tree
  "Create a new tree by applying the given function to all the elements."
  [f t]
(if t (BNode. (map-tree f (:left t)) (f (:data t)) (map-tree f (:right t))) nil)  


)
