(ns ttool.utils
  (:require [clojure.pprint :as pp]))

(defn pp-str [x] (with-out-str (pp/pprint x)))
