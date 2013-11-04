(ns dlist-atom.core-spec
;  (:refer-clojure :exclude [])
  (:require [speclj.core :refer :all]
            [dlist-atom.core :refer :all])
;  (:import [dlist-atom.core ])
  )

;; # The Tests
;;
;; We are going to use [spelj](https://github.com/slagyr/speclj) for our tests.

(describe "Insert front"
	(it "should insert something."
		(let [xx (dlist)]
			(insert-front xx 0)
			(should= '(0) (show-dlist xx))))
	(it "should increment size."
		(let [xx (dlist)]
			(insert-front xx 0)
			(should= 1 (d-size xx))))
	(it "should work with list-to-dlist."
		(let [xx (list-to-dlist '(1 2 3))]
         (insert-front xx 0)
			(should= '(0 1 2 3) (show-dlist xx))))
   (it "should set correct previous link"
      (let [xx (list-to-dlist '(1 2 3))]
         (do (insert-front xx 0)
         (should= (d-sentinel xx) (-> xx d-sentinel d-next d-prev)))))
)

(describe "insert back"
	(it "should insert something."
		(let [xx (dlist)]
			(insert-last xx 0)
			(should= '(0) (show-dlist xx))))
	(it "should increment size."
		(let [xx (dlist)]
			(insert-last xx 0)
			(should= 1 (d-size xx))))
)

(describe "insert sorted"
	(it "should insert something."
		(let [xx (dlist)]
			(insert-last xx 0)
			(should= '(0) (show-dlist xx))))
	(it "should increment size."
		(let [xx (dlist)]
			(insert-last xx 0)
			(should= 1 (d-size xx))))
	(it "should insert in sorted order"
		(let [xx (dlist)]
			(insert-sorted xx 2)
			(insert-sorted xx 0)
			(insert-sorted xx 1)
			(should= '(0 1 2) (show-dlist xx))))
)

(describe "index forward"
	(it "should work for empty lists"
		(let [xx (dlist)]
			(should= nil (index-forward xx 1))))
	(it "should work for longer lists"
		(let [xx (dlist)]
			(insert-front xx 1)
			(insert-front xx 0)
			(should= 1 (index-forward xx 1))))
)

(describe "index backward"
	(it "should work for empty lists"
		(let [xx (dlist)]
			(should= nil (index-backward xx 1))))
	(it "should work for longer lists"
		(let [xx (list-to-dlist '(1 2 3))]
			(should= -2 (index-backward xx 2))))
)

(describe "delete"
	(it "should work for empty lists"
		(let [xx (dlist)]
			(delete xx 0)
			(should= 0 (d-size xx))))
	(it "should work with longer lists"
		(let [xx (list-to-dlist '(0 1 2 3))]
			(delete xx 2)
			(should= '(0 1 3) (show-dlist xx))))
	(it "should dec size if elt does exist"
		(let [xx (list-to-dlist '(0 1 2 3))]
			(delete xx 2)
			(should= 3 (d-size xx))))
	(it "should work when the elt doesn't exist"
		(let [xx (list-to-dlist '(0 1 2 3))]
			(delete xx 5)
			(should= '(0 1 2 3) (show-dlist xx))))
	(it "shouldn't dec size if elt doesn't exist"
		(let [xx (list-to-dlist '(0 1 2 3))]
			(delete xx 5)
			(should= 4 (d-size xx))))
)

(describe "reverse"
	(it "should work for empty lists"
		(let [xx (dlist)]
			(reverse xx)
			(should= '() (show-dlist xx))))
	(it "should work for longer lists"
		(let [xx (list-to-dlist '(0 1 2 3))]
			(reverse xx)
			(should= '(3 2 1 0) (show-dlist xx))))
)

(describe "show-dlist-reverse"
	(it "should work for empty lists"
		(let [xx (dlist)]
			(should= '() (show-dlist-reverse xx))))
	(it "should work for longer lists"
		(let [xx (list-to-dlist '(0 1 2 3))]
			(should= '(3 2 1 0) (show-dlist-reverse xx))))
)

(run-specs)
