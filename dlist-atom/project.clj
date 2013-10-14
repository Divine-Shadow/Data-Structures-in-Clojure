(defproject fifolifo "0.1.0-SNAPSHOT"
  :description "CS 331 Programming Assignment &mdash; Doubly Linked Lists with Atoms"
  :url "http://mccarthy.cs.iit.edu/cs331/assignments/dlist-atom"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]]
  :profiles {:dev {:dependencies [[speclj "2.7.5"]]}}
  :plugins [[speclj "2.7.5"]]
  :test-paths ["spec/"])
