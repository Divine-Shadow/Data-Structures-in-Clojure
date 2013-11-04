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
          (it "should insert an element into the front of a dlist"
	     (def x (list-to-dlist '(2 3 4 5)))
	     (insert-front x 1)
	     (should= '(1 2 3 4 5) (show-dlist x))
	     (should= 5 (d-size x))
	     (let [y (-> x d-sentinel d-next)]
	     (should= 'sentinel (-> y d-prev d-data))
	     (should= 1 (-> y d-next d-prev d-data))))

)

(describe "insert-last" 
	  (it "should insert an element into the back of a dlist"
	      (def x (list-to-dlist '(1 2 3 4)))
	      (insert-last x 5)
	      (should= '(1 2 3 4 5) (show-dlist x)))

)
              
(describe "insert-sorted"
	  (it "should insert an element correctly in a sorted list"
	      (def x (list-to-dlist '(1 2 4 5)))
	      (insert-sorted x 3)
	      (should= '(1 2 3 4 5) (show-dlist x))
	      (should= '(5 4 3 2 1) (show-dlist-reverse x)))

)

(describe "index-forward"
          (it "gives the index of an element going forward"
	      (def x (list-to-dlist '(1 2 3 4 5)))
              (should= 2 (index-forward x 3))
	      (should= nil (index-forward x 6)))

)

(describe "index-backward"
          (it "should give the index of an element going backward"
	      (let [xx (list-to-dlist '(1 2 3 4 5))]
	      (should= -3 (index-backward xx 3))))

)				 
				 
(describe "list-to-dlist"
          (it "should convert a list to a dlist"
              (let [xx '(1 2 3 4 5)]
              (should= '(1 2 3 4 5) (show-dlist (list-to-dlist xx)))))

)

(describe "delete"
          (it "should delete an element from a dlist"
	      (def x (list-to-dlist '(1 2 3 4 5)))
	      (delete x 3)
              (should= '(1 2 4 5) (show-dlist x)))

)

(describe "reverse"
          (it "should reverse the elements in a dlist"
	      (def x (list-to-dlist '(1 2 3 4 5)))
	      (reverse x)
	      (should= '(5 4 3 2 1) (show-dlist x)))

)

(describe "show-dlist-reverse"
          (it "should show the reverse of a dlist"
	      (let [xx (list-to-dlist '(1 2 3 4 5))]
	      (should= '(5 4 3 2 1) (show-dlist-reverse xx))))

)

	  

(run-specs)
