(ns bst.core)

;; # Introduction
;;
;; In this lab you get to write a BST like the one we did in class, only
;; this time it is a dictionary structure and not a set.
;; As such, the "data" element from before will have a key and value instead.

(defrecord BST [root size])
(defrecord BNode [left key value right])
(declare make-tree)
(defn make-node
  ([key value]  (make-node nil key value nil))
  ([left key value right] (BNode. left key value right))
  )
 
(defn make-tree []
  (BST. nil 0))

;; # Size
;;
;; A warmup function.

(defn size "Return the size of the tree."
  [t]
  (:size t)
)

;; # Add
;;
;; The nodes will be entered into the tree on the basis of their key.
;; If someone tries to add a key that is already there, we replace the value
;; with the new entry.

(defn add "Add a key and value to the BST."
  [bst nu-key nu-val]

(cond  (= bst (make-tree)) (BST. (make-node nu-key nu-val) 1)
       (= nil (:root bst)) (add (make-tree) nu-key nu-val)
       :be-that-way (let [currentNode (-> bst :root) currentKey (-> bst :root :key)]

(cond

 (= nu-key currentKey) (BST. (make-node (:left currentNode )  nu-key nu-val (:right currentNode) ) (-> bst size inc))
 (< nu-key currentKey) (BST. (make-node (add (:left currentNode) nu-key nu-val) currentKey (:value currentNode) (:right currentNode)) (-> bst size inc))
 :OnOtherSide  (BST. (make-node (:left currentNode) currentKey (:value currentNode) (add (:right currentNode) nu-key nu-val)) (-> bst size inc))

 

)
)
)
)
;; # Find
;;
;; We need two versions of find.  The first one takes a key and returns the
;; value.  The second takes a value and returns the key.  Note that the second
;; version of the function must search the entire tree!  If the search item is not
;; there, return nil.

(defn find "Look for a key and return the corresponding value."
  [bst look-key] 
 (let [currentNode (-> bst :root) currentKey (-> bst :root :key)]

(cond
  (= (make-tree) bst) nil
(= nil (:root bst)) nil
 (= look-key currentKey) (:value currentNode)
 (< look-key currentKey)  (find (:left currentNode) look-key)
 :OnOtherSide  (find (:right currentNode) look-key)



)
)
)

(defn find-key "Look for a value and return the corresponding key."
  [bst look-value] 


(let [currentNode (-> bst :root) currentValue (-> bst :root :value)]

(cond
  (= bst nil) nil
(= nil (:root bst)) nil
 (= look-value currentValue) (:key currentNode)
 :beThatWay (let [leftFind (find-key (:left currentNode) look-value)] (if leftFind leftFind (find-key (:right currentNode) look-value)))
)

)
)


;;# Predecesor given a node, returns a node that is the greatest predecssor
(defn rightmost [tnode]
(if (:right tnode) (rightmost (-> tnode :right :root)) tnode)

)

(defn getPred [tnode]
(rightmost (-> tnode :left :root)
))

;; # Delete
;;
;; Similiarly, we have two versions of delete.  Please use the predecessor node if
;; you need to delete a child with two elements.

(defn delete [bst victim]


  (let [currentNode (-> bst :root) currentKey (-> bst :root :key)]

    (cond
     (= bst (make-tree)) nil
     (= nil (:root bst)) nil
     (< victim currentKey) (BST. (BNode. (delete (:left currentNode) victim)  currentKey (:value currentNode)         (:right currentNode))         (-> bst size dec))
     (> victim currentKey) (BST. (BNode.         (:left currentNode)          currentKey (:value currentNode) (delete (:right currentNode) victim)) (-> bst size dec))
     :foundYou!  




     (cond
      (= nil (:left currentNode))  (:right currentNode)
      (= nil (:right currentNode)) (:left currentNode)
      :wellShit (let [predecessor (getPred currentNode)] (BST. (BNode. (delete (:left currentNode) (:key predecessor)) (:key predecessor) (:value predecessor) (:right currentNode)) (-> bst size dec)))

     )
    


 
   ))
)

(defn delete-value [bst victim]
  (delete bst (find-key bst victim))
  )

;; # Map Tree
;;
;; This function takes a tree t and maps a function f over it.
;; If your tree is ((x 3 x) 5 ((x 7 x) 6 x)), then (map-tree t inc)
;; will return ((x 4 x) 6 ((x 8 x) 7 x))

(defn map-tree
  [t f] 
(if (= (:root t) nil) t (BST. (BNode. (map-tree (:left t) f) (-> t :root :key) (f (-> t :root :value)) (map-tree (:right t) f)) (size t))) 


)
