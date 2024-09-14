(ns ttool.server
  (:require
   [mount.core :as mount]
   [ring.adapter.jetty :as j]
   [ring.middleware.reload :as rrel]

   [ttool.routes :as routes]))

(declare server)


(defn prod? []
  (-> (System/getenv)
      (get "ENV")
      (= "production")))


(def handler
  (cond-> routes/app
    (not (prod?)) (rrel/wrap-reload)))


(mount/defstate server
  :start (let [server (j/run-jetty handler {:port 4000 :join? false})]
           (println "Started a server on a port 4000")
           server)

  :stop (do (prn "Stopping the server")
            (.stop server)))
