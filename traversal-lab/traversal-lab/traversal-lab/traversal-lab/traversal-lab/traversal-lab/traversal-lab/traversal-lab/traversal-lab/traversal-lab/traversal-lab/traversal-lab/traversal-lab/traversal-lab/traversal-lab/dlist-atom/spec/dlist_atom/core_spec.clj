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
              (should false)))

(run-specs)
