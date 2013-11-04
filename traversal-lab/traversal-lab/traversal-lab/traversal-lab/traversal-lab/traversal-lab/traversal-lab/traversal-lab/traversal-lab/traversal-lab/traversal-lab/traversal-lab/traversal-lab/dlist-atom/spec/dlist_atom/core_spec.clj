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
	(it "should work with empty list"
		(let [dx (dlist)]
			(insert-front dx 1)
				(should= '(1) (show-dlist dx))))
	(it "should work with data"
		(let [dx (dlist)]
			(insert-front dx 2)
			(insert-front dx 1)
				(should= '(1 2) (show-dlist dx))))

	(it "should increment in size"
		(let [xx (dlist)]
			(insert-front xx 0)
				(should= 1 (d-size xx))))
)

(describe "insert last"
	(it "should work with empty list"
		(let [dx (dlist)]
			(insert-last dx 1)
			(should= '(1) (show-dlist dx))))

	(it "should work with lists with data"
		(let [dx (dlist)]
			(insert-front dx 1)
			(insert-last dx 2)
				(should= '(1 2) (show-dlist dx))))
	
	(it "should increment in size"
		(let [dx (dlist)]
			(insert-last dx 0)
			(should= 1 (d-size dx))))
	
)

(describe "insert sorted"
	(it "should work with empty list"
		(let [xx (dlist)]
			(insert-sorted xx 1)
			(should= '(1) (show-dlist xx))))

	(it "should work with lists with data"
		(let [xx (dlist)]
			(insert-front xx 1)
			(insert-last xx 3)
			(insert-sorted xx 2)
			(should= '(1 2 3) (show-dlist xx))))
	
	(it "should increment in size"
		(let [xx (dlist)]
			(insert-sorted xx 1)
			(should= 1 (d-size xx))))

	(it "should work with edge case"
		(let [xx (dlist)]
			(insert-front xx 2)
			(insert-front xx 1)
			(insert-last xx 3)
			(insert-sorted xx 4)
			(should= '(1 2 3 4) (show-dlist xx))))
)
(describe "index forward"
	(it "should work with empty list"
		(let [xx (dlist)]
			(should= nil (index-forward xx 1))))
	(it "should work with list with data"
		(let [xx (dlist)]
			(insert-front xx 1)
			(insert-last xx 2)
			(insert-last xx 3)
			(should= 1 (index-forward xx 2))))
	(it "should work when elt doesn't exist"
		(let [xx (dlist)]
			(insert-front xx 1)
			(insert-last xx 2)
			(insert-last xx 3)
			(should= nil (index-forward xx 4))))
)

(describe "index backward"
	(it "should work with empty lists"
		(let [xx (dlist)]
			(should= nil (index-backward xx 1))))
	(it "should work with lists that have data"
		(let [xx (dlist)]
			(insert-front xx 1)
			(insert-last xx 2)
			(insert-last xx 3)
			(should= -2 (index-backward xx 2))))
	(it "should return nil when elt doesn't exist"
		(let [xx (dlist)]
			(insert-front xx 1)
			(insert-last xx 2)
			(insert-last xx 3)
			(should= nil (index-backward xx 4))))
	(it "should not return positive numbers"
		(let [xx (dlist)]
			(insert-front xx 1)
			(insert-last xx 2)
			(insert-last xx 3)
			(should= true (> 0 (index-backward xx 2)))))
	
)

(describe "list to dlist"
	(it "should work with empty lists"
		(let [xx '()]
			(should= (dlist) (list-to-dlist xx))))
	
	(it "should work with lists that have data"
		(let [x '(1 2 3)
		     xx (dlist)]
		     (insert-front 1 xx)
		     (insert-last 2 xx)
		     (insert-last 3 xx)
		     (should= true (identical? xx (list-to-dlist x)))))
)


(run-specs)
