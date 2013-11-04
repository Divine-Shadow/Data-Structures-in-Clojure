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
  (it "should work for an empty dlist"
    (let [xx (dlist)]
      (insert-front xx 2)
      (should= '(2) (show-dlist xx))))

  (it "should set link to previous"
    (let [xx (dlist)]
      (insert-front xx 1)
      (insert-front xx 3)
      (should (identical? (-> xx d-sentinel d-prev d-next) (-> xx d-sentinel d-next d-prev)))))



  (it "should increment the number of elements "
          (let [xx (dlist)]
            (insert-front xx 2)
            (should= 1 (d-size xx))))


  (it "should return proper list "
          (let [xx (dlist)]
            (insert-front xx '1)
            (insert-front xx '2)
            (should= '(2 1) (show-dlist xx)))
      )
          )


(describe "insert-last"
   (it "should work for an empty dlist"
    (let [xx (dlist)]
      (insert-last xx 2)
      (should= '(2) (show-dlist xx))))


   (it "should work for a list that already has an element"
    (let [xx (dlist)]
      (insert-last xx 2)
      (insert-last xx 4)
      (should= '(2 4) (show-dlist xx))))

 (it "should return proper list "
          (let [xx (dlist)]
            (insert-last xx '1)
            (insert-last xx '2)
            (should= '(1 2) (show-dlist xx)))
      )




  (it "should increment the number of elements "
       (let [xx (dlist)]
         (insert-last xx 2)
          (should= 1 (d-size xx))))
          )


(describe "insert-sorted"
;;      (it "should insert in correct order"
;;            (let [xx (dlist)]
;;                (insert-front xx 30)
;;                (insert-front xx 31)
;;                (insert-front xx 32)
;;                (insert-front xx 37)
;;                (insert-front xx 38)
;;                (insert-sorted xx 333)
;;                (should= '(38 37 35 32 31 30) (show-dlist xx))))

      (it "shouldn't miss a back link"
          (let [xx (dlist)]
            (insert-front xx 10)
            (insert-front xx 30)
            (insert-sorted xx 20)
            (identical? (-> xx d-sentinel d-prev) (-> xx d-sentinel d-next d-prev d-prev)))

          ))



;;           )

;; (describe "it should do something"
;;           (it "should retrun stuff"
;;           (let [xx (dlist)]
;;                (insert-front xx 30)
;;                (insert-front xx 31)
;;                (insert-front xx 32)
;;                (insert-front xx 37)
;;                (insert-front xx 38)
;;                (insert-sorted xx 29)
;;             (should= 38 (first (show-dlist xx)))))
;;          )


;; (describe "Index forward"
;;           (it "shouldn't return nil"
;;               (let [xx (dlist)]
;;                 (insert-front xx 30)
;;                (insert-front xx 31)
;;                (insert-front xx 32)
;;                (insert-front xx 37)
;;                (should-not= nil (index-forward xx 30))))
;;           )


;; (describe "delete"
;;           (it "Assumes list is sorted"
;;               (let [xx (dlist)]
;;                (insert-front xx 30)
;;                (insert-front xx 31)
;;                (insert-front xx 32)
;;                (insert-front xx 37)
;;                (should= '(37 32 30) (delete xx 31))))
;;           )



(run-specs)
