(ns dlist-atom.core-spec
   (:refer-clojure :exclude [peek pop])
   (:require [speclj.core :refer :all]
             [dlist-atom.core :refer :all])
 ;  (:import [dlist-atom.core ])
   )
 
 ;; # The Tests
 ;;
 ;; We are going to use [spelj](https://github.com/slagyr/speclj) for our tests
 
 (describe "insert-front"
         (it "should increment size"
                 (let [xx (dlist)]
                         (insert-front xx 20)
                         (should= 1 (d-size xx))))
 
         (it "should set prev link"
                 (let [xx (dlist)]
                         (insert-front xx 20)
                         (insert-front xx 30)
                         (should= '(30 20) (show-dlist xx))))
 )
 
 (describe "insert-last"
         (it "should insert at the end not front"
                 (let [xx (dlist)]
                 (insert-front xx 20)
                 (insert-last xx 30)
                 (should= '(20 30) (show-dlist xx))))
 )
 
 (describe "insert-sorted"
         (it "should back-link"
                 (let [xx (dlist)]
                 (insert-front xx 30)
                 (insert-front xx 20)
                 (insert-sorted xx 25)
                 (should= 20 (-> xx d-sentinel d-next d-next d-prev d-data))
                 (should= 30 (-> xx d-sentinel d-next d-next d-next d-data))
                 (should= 25 (-> xx d-sentinel d-next d-next d-data))
                 (should= 25 (-> xx d-sentinel d-next d-next d-next d-prev d-data))))
 )
 
 (describe "index-forward"
        (it "should return nil if the element is missing"
                 (let [xx (dlist)]
                 (insert-front xx 20)
                (should= nil (index-forward xx 30))))
 )
 
 (describe "index-backward"
         (it "should return 0 at first position"
                 (let [xx (dlist)]
                 (insert-front xx 20)
                 (> 0 (index-backward xx 20))))
         (it "should return negative numbers"
                 (let [xx (dlist)]
                 (insert-last xx 1)
                 (insert-last xx 2)
                 (insert-last xx 3)
                 (should= -1 (index-backward xx 3)))) 

 )
 
 (describe "delete"
         (it "should not assume sorted list"
                 (let [xx (dlist)]
                 (insert-front xx 30)
                 (insert-front xx 20)
                 (delete xx 30)               
                 (should= '(20) (show-dlist xx))))
 )
 
 (describe "reverse"
         (it "should fix back edges"
                 (let [xx (dlist)]
                 (insert-front xx 20)
                 (insert-front xx 30)
                 (reverse xx)
                 (should= 30 (-> xx d-sentinel d-prev d-data))))

         (it "should reverse a list"
                (let [xx (dlist)]
                (insert-last xx 1)
                (insert-last xx 2)
                (insert-last xx 3)
                (insert-last xx 4)
                (insert-last xx 5)
                (reverse xx)
                (should= '(5 4 3 2 1) (show-dlist xx))))
 )
 
 (describe "list-to-dlist"
         (it "should keep pointers steady"
                 (let [xx (cons 10 (cons 20 nil))
                       x (list-to-dlist xx)]
                 (should= 20 (-> x d-sentinel d-prev d-data))))
 )
 
 (run-specs)
