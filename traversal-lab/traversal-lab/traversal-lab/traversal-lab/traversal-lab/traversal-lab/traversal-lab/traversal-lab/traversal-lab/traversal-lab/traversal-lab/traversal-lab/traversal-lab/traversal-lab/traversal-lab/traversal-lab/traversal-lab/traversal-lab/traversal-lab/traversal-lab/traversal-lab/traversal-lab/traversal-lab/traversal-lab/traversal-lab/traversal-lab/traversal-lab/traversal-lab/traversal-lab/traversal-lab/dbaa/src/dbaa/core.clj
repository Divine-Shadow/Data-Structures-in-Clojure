(defrecord DList [sentinel size])
(defrecord DNode [prev data next])

(defn make-dnode [prev data next] (DNode. (atom prev) (atom data)  (atom next)))

(defn make-dlist []
   (let [sentinel (make-dnode nil 's nil)]
      (do
         (reset! (:prev sentinel) sentinel)
	 (reset! (:next sentinel) sentinel)
	 (DList. sentinel (atom 0)))))

(defn insert-front [xx elt]
   (let [node (make-dnode (:sentinel xx) elt @(-> xx :sentinel :next))]
      (do (reset! (-> xx :sentinel :next) node)
          (reset! (-> node :next deref :prev) node)
	  (swap! (:size xx) inc))))


(defn insert-end [xx elt]
   (let [node (make-dnode (-> xx :sentinel :prev deref) elt (:sentinel xx))]
      (do (reset! (-> xx :sentinel :prev) node)
          (reset! (-> node :prev deref :next) node)
	  (swap! (:size xx) inc))))

(def d (make-dlist))
(insert-front d 22)
(insert-end d 33)
(insert-end d 44)
(insert-front d 11)

;;helper function reverse step1 below-------------------
(defn r1 [n] "FROM 's, reverse one of the two directions of the pointers"
   (if (identical? @(-> n :prev) @(-> n :next)) (DNode. (:prev n) (:data n) (:next n))
   (do (reset! (:prev n) @(:next n))
       (r1 @(:next n)))))

;;helper function reverse step2 below-------------------
(defn r2 [n] "FROM 's, reverse the other direction of the pointers"
   (if (identical? (-> n :prev deref :next deref) (-> n :prev deref :prev deref))
       (do (reset! (-> n :prev deref :next) n)
           (r2 @(:prev n))) (DNode. (:prev n) (:data n) (:next n))))

(defn reverse-dlist! [xx]
"reverse WITHOUT temp, not sure if it's possible, cuz can't build a long enough double-list"
   (do (r1 (:sentinel xx))
       (r2 (:sentinel xx))))

;;helper function for reverse using temp
(defn reverse-temp-help [node n]
   (if (= n -1) (DNode. (:prev node) (:data node) (:next node))
   (let [ntemp (make-dnode @(:next node) nil @(:prev node))]
      (do (reset! (:prev node) @(:prev ntemp))
	  (reset! (:next node) @(:next ntemp))
	  (reverse-temp-help @(:next node) (- n 1))))))

(defn reverse-dlist!! [xx]
   (reverse-temp-help (:sentinel xx) @(:size xx)))
