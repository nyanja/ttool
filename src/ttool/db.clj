(ns ttool.db
  (:require [datomic.api :as d]
            [mount.core :as mount]
            [ttool.schema :as schema]))

(declare conn db)

(def db-uri "datomic:dev://localhost:4334/ttool?password=pw")


(mount/defstate conn
  :start (d/connect db-uri))

(mount/defstate db
  :start (d/db conn))


(defn q [query & args] (apply d/q query db args))

(defn t [data] (d/transact conn data))



(comment
  (do
    (mount/stop)
    (d/delete-database db-uri)
    (d/create-database db-uri)
    (mount/start)
    @(d/transact conn schema/schema)))
