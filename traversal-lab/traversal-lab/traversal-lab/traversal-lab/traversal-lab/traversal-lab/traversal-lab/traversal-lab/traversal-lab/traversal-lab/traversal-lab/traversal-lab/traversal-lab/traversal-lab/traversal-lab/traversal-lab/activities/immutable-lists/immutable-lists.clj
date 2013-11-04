(def length [xx]
  (if (empty? xx)   ; this is how you check for nil in Clojure
     0
     (+ 1 (length (rest xx)))))  ;; It cannot be done in O(1) because this 
                                 ;; algorithm goes through each item in the list.
                                 ;; The time complexity of this list is O(n).

(def phonebook '({:key "emergency" :value "911"}
                 {:key "jenni" :value "867-5309"}))

(defn find [key xs] 
  (:value (first (filter #(= (% :key) key) xs))))

(find "jenni" phonebook)
(find "billy" phonebook)

(def add [key value xs] (cons {:key key :value value} xs))
(def newbook (add "empire" "588-2300" phonebook))

(find "empire" phonebook)
(find "empire" newbook)
