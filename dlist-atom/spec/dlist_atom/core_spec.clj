(ns dlist-atom.core-spec
;  (:refer-clojure :exclude [])
  (:require [speclj.core :refer :all]
            [dlist-atom.core :refer :all])
;  (:import [dlist-atom.core ])
  )

;; # The Tests
;;
;; We are going to use [spelj](https://github.com/slagyr/speclj) for our tests.
(describe "creation" (it "should do stuff" (should (let [p (dlist)] p)))) 
(describe "creation" (it "should do stuff" (should (let [p (dlist)] (do p))))) 
(describe "creation" (it "should do stuff" (should (let [p (dlist)] (do (insert-last p 4)))))) 
(describe "The spec file"
          (it "should have some tests."
              (should true)))
(describe "insert-front"
          (it "should increment size"
              (let [p (dlist)] (should=  (+ 1 (d-size p)) (do (insert-front p 5) (d-size p)))) )

         ; (it "should set the previous link" (let [p (dlist)] (should= (do (insert-front p 2) (insert-front p 3) (show-dlist p)) '(3 2) )))

        ;  (it "should set the previousnext link" (let [p (dlist)] (should= (do (insert-front p 2) (insert-front p 3) (show-dlist-reverse p)) '(2 3) )))
        ;  (it "should not insert in back" (let [p (insert-front (dlist) 3) q (insert-front p 4)] (should= (first (show-dlist q)) 4)))
)

(describe "insert-back"
          
          (it "should increment size"
              (let [p (dlist)] (should= (+ 1 (d-size p)) (do (insert-last p 5) (d-size p)))) )

         ; (it "should set the previous link" (let [p (dlist)] (should= (do (insert-last p 2) (insert-last p 3) (show-dlist p)) '(2 3) )))

         ; (it "should set the next link" (let [p (dlist)] (should= (do (insert-last p 2) (insert-last p 3) (show-dlist-reverse p)) '(3 2) )))
         ; (it "should not insert in front" (let [p (insert-last (dlist) 3) q (insert-last p 4)] (should= (first (show-dlist-reverse q)) 3)))
)



(describe "insert-sorted"
          
                (it "should increment size"
              (let [p (dlist)] (should=  (+ 1 (d-size p)) (do (insert-sorted p 5) (d-size p)))) )

          (it "should set the previous link2" (let [p (dlist)] (should= (do (insert-last p 2) (insert-last p 5) (insert-sorted p 4) (show-dlist p)) '(2 4 5) )))

          (it "should set the previous link" (let [p (dlist)] (should= (do (insert-last p 2) (insert-last p 3) (insert-sorted p 5) (show-dlist-reverse p)) '(5 3 2) )))

          (it "should set the next link" (let [p (dlist)] (should= (do (insert-last p 2) (insert-last p 3) (insert-sorted p 1) (show-dlist-reverse p)) '(3 2 1) )))
)


(describe "Index-forward "

(it "should find the index" (let [p (dlist)] (should= (do (insert-last p 2) (insert-last p 3) (insert-last p 5) (index-forward p 3)) 1))) 
)
(describe" Index-backward"
(it "should find the index" (let [p (dlist)] (should= (do (insert-last p 2) (insert-last p 3) (insert-last p 5) (index-backward p 3)) -2)))
)

(describe "delete" 

(it "should make a deletion" (let [p (dlist)] (should= (do (insert-last p 2) (insert-last p 6) (insert-last p 5) (delete p 5) (show-dlist p)) '(2 6))))
(it "should make a deletion" (let [p (dlist)] (should= (do (insert-last p 2) (insert-last p 6) (insert-last p 5) (delete p 2) (show-dlist p)) '(6 5))))

)

(describe "reverse"
(it "should fix forward edges" (let [p (dlist)] (should= (do (insert-last p 2) (insert-last p 6) (insert-last p 5) (reverse p) (show-dlist p)) '(5 6 2))))
(it "should make a deletion" (let [p (dlist)] (should= (do (insert-last p 2) (insert-last p 6) (insert-last p 5) (reverse p ) (show-dlist-reverse p)) '(2 6 5))))
)

(describe "list-to-dlist" 
(it "should work going forward" (let [p (dlist)] (should= (do (insert-last p 2) (insert-last p 6) (insert-last p 5) (show-dlist p))  (show-dlist (list-to-dlist '(2 6 5))))))
(it "should work going backward" (let [p (dlist)] (should= (do (insert-last p 2) (insert-last p 6) (insert-last p 5) (show-dlist-reverse p))  (show-dlist-reverse (list-to-dlist '(2 6 5))))))

)


(run-specs)
