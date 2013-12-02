(ns traversals.core)

;; Given Code

(defrecord BNode [left data right])

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
