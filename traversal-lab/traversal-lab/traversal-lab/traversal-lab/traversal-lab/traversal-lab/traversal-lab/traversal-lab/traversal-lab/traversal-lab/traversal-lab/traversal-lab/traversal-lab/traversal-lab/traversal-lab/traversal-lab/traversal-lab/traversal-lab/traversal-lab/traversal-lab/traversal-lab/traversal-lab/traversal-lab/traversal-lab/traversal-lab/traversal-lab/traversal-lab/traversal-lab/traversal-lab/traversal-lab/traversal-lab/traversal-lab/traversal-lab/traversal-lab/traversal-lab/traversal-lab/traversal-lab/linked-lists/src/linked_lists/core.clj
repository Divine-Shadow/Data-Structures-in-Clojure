(ns linked_lists.core)

(defrecord Cons [car cdr])
(defn insert-at-beginning [elt xx]
    (Cons. elt xx))

(defn sorted-insert[elt xx]
    (cond (empty? xx) (Cons. elt nil)
  	    (< elt (:car xx))(Cons. elt xx)
	       :else (Cons.(:car xx) (sorted-insert elt(:cdr xx) ))))

(defn insert-at-end [elt xx]
    (cond (empty? xx)(Cons. elt nil)
    :else(Cons.(:car xx) (insert-at-end elt(:cdr xx)))))

(defn search [elt xx]
    (cond (empty? xx) false
  	    (= elt (:car xx)) true
            :else (search elt (:cdr xx))))

(defn delete [elt xx]
    (cond (empty? xx) xx
  	    (= elt (:car xx)) (:cdr xx)
	    	  :else (Cons. (:car xx) (delete elt (:cdr xx)))))

(defn delete-all [elt xx]
    (cond (empty? xx) xx
      	    (= elt (:car xx)) (delete-all elt(:cdr xx))
	    	  :else (Cons. (:car xx) (delete-all elt (:cdr xx)))))

(defn efficient-delete [elt xx]
     (cond (empty? xx) nil
     (search elt xx) (delete elt xx)
 	 :else xx))    	       
