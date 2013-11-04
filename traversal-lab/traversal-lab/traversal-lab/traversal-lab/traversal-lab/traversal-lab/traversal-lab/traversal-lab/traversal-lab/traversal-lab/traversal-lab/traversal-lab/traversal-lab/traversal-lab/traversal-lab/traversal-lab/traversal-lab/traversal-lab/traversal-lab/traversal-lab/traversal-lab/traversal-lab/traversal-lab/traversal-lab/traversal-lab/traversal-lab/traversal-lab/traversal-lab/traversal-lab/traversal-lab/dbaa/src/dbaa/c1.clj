
(defrecord ListZip [before after])
(defn mke-list-zip [x] (ListZip. '() x))

(defn current [z] (first (:after z)))

(defn forward [z] 
   (ListZip. (cons (-> z :after first) (:before z))
             (rest (:after z))))
(defn backward [z]
   (ListZip. (rest (:after z))
             (cons (-> z :before first) (:after z))))


(defn search [xx elt]
   (cond (empty? (:after xx)) 'NONE
       (= elt (first (:after xx)))
       (do (if (< 3 (count (:after xx))) (def x (cons elt (cons (first (rest (:after xx))) (cons (first (rest (rest (:after x)))) (first (rest (rest (rest (:after xx)))))))))
           (def x (:after xx)))
           (cond (< 2 (count (:before xx))) (cons (first (:before xx)) (cons (first (rest (:before xx))) (cons (first (:before xx)) x)))
           (= 2 (count (:before xx))) (cons (last (:before xx)) (cons (first (:before xx)) x))
	   (= 1 (count (:before xx))) (cons (first (:before xx)) x)
	   :else x))
    :else (search (ListZip. (cons (first (:after xx)) (:before xx)) (rest (:after xx))) elt)))





