(ns dlist-atom.core-spec
   (:refer-clojure :exclude [])
   (:require [speclj.core :refer :all]
            [dlist-atom.core :refer :all])
   (:import [dlist-atom.core ]))

;; # The Tests
;;
;; We are going to use [spelj](https://github.com/slagyr/speclj) for our tests.

(describe "dnode"
	(it "should work with single argument"
	   (let [n (dnode 1)]
	   (should= nil @(:prev n))
	   (should= 1 @(:data n))
	   (should= nil @(:next n))))
	
	(it "should work with 3 argument"
	   (let [n (dnode 1 2 3)]
	   (should= 1 @(:prev n))
	   (should= 2 @(:data n))
	   (should= 3 @(:next n)))))

(describe "dlist"
	(it "create something"
	   (should (dlist)))
	   
	(it "each part should coordinate with design"
	   (should= 0 @(:size (dlist)))
	   (should= 'sentinel (-> (dlist) :sentinel :prev deref :data deref))
	   (should= 'sentinel (-> (dlist) :sentinel :data deref))
	   (should= 'sentinel (-> (dlist) :sentinel :next deref :data deref))))


(describe "insert-front"
	(it "should work with empty dlist"
	(let [n (dlist)]
	   (insert-front n 12)
	(should= 1 @(:size n))
	(should= 12 (-> n d-sentinel d-next d-data))
	(should= 'sentinel (-> n d-sentinel d-next d-next d-data))
	(should= 12 (-> n d-sentinel d-prev d-data))
	(should= 'sentinel (-> n d-sentinel d-prev d-prev d-data))))
	
	(it "should work with other dlists"
	(let [n (list-to-dlist '(2 3 4))] (insert-front n 100)
	(should= 4 @(:size n))
	(should= '(100 2 3 4) (show-dlist n))
	(should= 'sentinel (-> n d-sentinel d-next d-prev d-data)))))
	

(describe "insert-last"
	(it "should work with empty dlist"
	(let [n (dlist)] (insert-last n 'a)
	(should= 1 @(:size n))
	(should= 'a (-> n d-sentinel d-next d-data))
	(should= 'sentinel (-> n d-sentinel d-next d-next d-data))))

	(it "should work with other dlists"
	(let [nn (list-to-dlist '(11 22 33))] (insert-last nn 44)
	(should= 4 @(:size nn))
	(should= '(11 22 33 44) (show-dlist nn)))))


(describe "insert-sorted"
	(it "should work with empty dlist"
	(let [n (dlist)] (insert-sorted n 0)
	(should= 1 @(:size n))
	(should= 0 (-> n d-sentinel d-next d-data))
	(should= 'sentinel (-> n d-sentinel d-next d-next d-data))
	(should= 0 (-> n d-sentinel d-prev d-data))))

	(it "should work with other dlist"
	(let [nn (list-to-dlist '(12 23 34))]
	(insert-sorted nn 10)
	(should= '(10 12 23 34) (show-dlist nn))
	(should= 12 (-> nn d-sentinel d-next d-next d-next d-prev d-data))
	(insert-sorted nn 20)
	(should= '(10 12 20 23 34) (show-dlist nn))
	(should= 20 (-> nn d-sentinel d-prev d-prev d-prev d-data))
	(insert-sorted nn 30)
	(should= '(10 12 20 23 30 34) (show-dlist nn))
	(should= 30 (-> nn d-sentinel d-prev d-prev d-data))
	(insert-sorted nn 40)
	(should= '(10 12 20 23 30 34 40) (show-dlist nn))
	(should= 40 (-> nn d-sentinel d-prev d-data)))))


(describe "index-forward"
	(it "should work with empty dlist"
	(should= nil (index-forward (dlist) 3)))
	
	(it "should work with other dlist"
	(let [qq (list-to-dlist '(11 22 33 44))]
	(should= 0 (index-forward qq 11))
	(should= 1 (index-forward qq 22))
	(should= 2 (index-forward qq 33))
	(should= 3 (index-forward qq 44))
	(should= nil (index-forward qq 55)))))


(describe "index-backward"
	(it "should work with empty dlist"
	(should= nil (index-backward (dlist) 1000)))

	(it "should work with other dlists"
	(let [q (list-to-dlist '(2 5 8))]
	(should= -3 (index-backward q 2))
	(should= -2 (index-backward q 5))
	(should= -1 (index-backward q 8))
	(should= nil (index-backward q 11)))))


(describe "list-to-dlist"
	(it "should work with empty dlists"
	(let [t (list-to-dlist '())]
	(should= 0 @(:size t))
	(should= 'sentinel (-> t d-sentinel d-data))
	(should= 'sentinel (-> t d-sentinel d-next d-data))))

	(it "should work with other dlists"
	(let [tt (list-to-dlist '(1 10 100))]
	(should= 3 @(:size tt))
	(should= '(1 10 100) (show-dlist tt)))))


(describe "delete"
	(it "should work with empty dlists"
	(let [e (dlist)] (delete e 2)
	(should= 0 @(:size e))
	(should= 'sentinel (-> e d-sentinel d-next d-data))
	(should= 'sentinel (-> e d-sentinel d-next d-next d-data))))

	(it "should work with other dlists"
	(let [e (list-to-dlist '(1 11 111))] (delete e 1)
	(should= '(11 111) (show-dlist e))
	(should= 2 @(:size e))))

	(it "should kill the muggle who assume input sequences are sorted"
	(let [e (list-to-dlist '(1 5 1 5 2 1 5 1 5))] (delete e 2)
	(should= '(1 5 1 5 1 5 1 5) (show-dlist e))
	(should= 8 @(:size e)))))


(describe "reverse"
	(it "should work with empty dlists"
	(let [r (dlist)]
	(should= 0 @(:size r))
	(should= 'sentinel (-> r d-sentinel d-next d-data))
	(should= 'sentinel (-> r d-sentinel d-next d-next d-data))))


	(it "should work with other dlists"
	(let [r (list-to-dlist '(2 22 222))] (reverse r)
	(should= 3 @(:size r))
	(should= '(222 22 2) (show-dlist r)))))


(describe "show-dlist-reverse"
	(it "should work with empty dlists"
	(should= '() (show-dlist-reverse (dlist))))

	(it "should work with other dlists"
	(let [s (list-to-dlist '(1 2 3 4))]
	(should= '(4 3 2 1) (show-dlist-reverse s)))))

(run-specs)
