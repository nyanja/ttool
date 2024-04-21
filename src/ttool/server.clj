(ns ttool.server
  (:require
   [mount.core :as mount]
   [ring.adapter.jetty :as j]
   [ring.middleware.reload :as rrel]
   [ring.middleware.resource :as rres]
   [ring.middleware.content-type :as rct]

   [ttool.routes :as routes]
   ))

(declare server)


(mount/defstate server
  :start (do
           (j/run-jetty #'routes/app {:port 4000})
           (prn "Started a server on a port 4000")))
