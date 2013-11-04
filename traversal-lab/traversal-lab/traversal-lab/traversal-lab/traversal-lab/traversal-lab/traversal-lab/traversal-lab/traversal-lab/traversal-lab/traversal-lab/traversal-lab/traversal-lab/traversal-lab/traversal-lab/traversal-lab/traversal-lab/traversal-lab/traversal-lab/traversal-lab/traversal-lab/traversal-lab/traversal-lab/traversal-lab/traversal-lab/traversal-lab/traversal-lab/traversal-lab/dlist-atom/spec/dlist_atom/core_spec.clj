(ns dlist-atom.core-spec
;  (:refer-clojure :exclude [])
  (:require [speclj.core :refer :all]
            [dlist-atom.core :refer :all])
;  (:import [dlist-atom.core ])
  )

;; # The Tests
;;
;; We are going to use [spelj](https://github.com/slagyr/speclj) for our tests.


(describe "insert-front"
          (it "should work with an empty list"
	  (let [xx (dlist)]
	  (insert-front xx 5)
              (should= '(5)(show-dlist xx))))
	      
	      )

(run-specs)
