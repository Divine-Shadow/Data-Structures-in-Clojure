(ns dlist-atom.core-spec
;  (:refer-clojure :exclude [])
  (:require [speclj.core :refer :all]
            [dlist-atom.core :refer :all])
;  (:import [dlist-atom.core ])
  )

;; # The Tests
;;
;; We are going to use [spelj](https://github.com/slagyr/speclj) for our tests.


(describe "The spec file"
          (it "should have some tests."
              (should true)))

(describe "insert-front"
	  (it "should insert an element into the front of a Dlist"
	      (let [xx (dlist)]
	      	 (insert-front xx 2)
		 (insert-front xx 4)
		 (should= '(4 2) (show-dlist xx))))

	  (it "should keep the dlist in the correct order after insertion"
	      (let [xx (dlist)]
	      	(insert-front xx 3)
		(insert-front xx 2)
	        (should= '(3 2) (show-dlist-reverse xx)))))

(describe "insert-last"
	  (it "should insert an element at the back of a Dlist"
	      (let [xx (dlist)]
	      	(do  (insert-front xx 2)
		     (insert-front xx 1)
		     (insert-last xx 5)
		(should= '(1 2 5) (show-dlist xx))))))

(run-specs)
