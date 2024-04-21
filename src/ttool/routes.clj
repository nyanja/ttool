(ns ttool.routes
  (:require
   [reitit.ring :as ring]))


(defn api-routes []
  ["/api"
   ["/status" {:get (fn [req] {:status 200 :body "OK"})}]])


(defn app [_]
  (ring/ring-handler
    (ring/router
      (api-routes))))
