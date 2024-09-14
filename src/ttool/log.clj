(ns ttool.log
  (:import (org.slf4j LoggerFactory)))

(def logger (LoggerFactory/getLogger "ttool"))

(defn info  [& ss] (.info logger (apply str ss)))
(defn debug [& ss] (.debug logger (apply str ss)))
(defn error [& ss] (.error logger (apply str ss)))
(defn warn  [& ss] (.warn logger (apply str ss)))
