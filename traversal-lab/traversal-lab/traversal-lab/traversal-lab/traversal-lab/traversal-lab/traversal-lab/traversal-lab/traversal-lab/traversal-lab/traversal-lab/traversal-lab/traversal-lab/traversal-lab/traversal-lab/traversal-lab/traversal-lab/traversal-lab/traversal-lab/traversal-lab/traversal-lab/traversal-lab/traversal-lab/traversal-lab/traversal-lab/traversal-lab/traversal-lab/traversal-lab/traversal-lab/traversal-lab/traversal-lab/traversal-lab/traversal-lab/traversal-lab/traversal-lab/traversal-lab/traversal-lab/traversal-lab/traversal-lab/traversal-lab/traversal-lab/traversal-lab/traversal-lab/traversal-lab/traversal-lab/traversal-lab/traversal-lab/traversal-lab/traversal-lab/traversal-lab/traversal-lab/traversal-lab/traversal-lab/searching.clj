(def t {:d 4
        :l {:d 2 :l {:d 1} :r {:d 3}}
        :r {:d 6 :l {:d 5 :r 7}}})

(defn dfs[t elt]
  (if (nil? t) false
    (or (= elt (:d t))
        (dfs (:l t) elt)
        (dfs (:r t) elt))))

(dfs t 8)

(defn pre)