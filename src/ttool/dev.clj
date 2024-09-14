(ns ttool.dev
  (:require
   [cider.nrepl               :as cider]
   [mount.core                :as mount]
   [nrepl.server              :as nrepl]
   [refactor-nrepl.middleware :as refactor]
   [ttool.server]))


(defn start-nrepl! [port]
  (nrepl/start-server
    :port port
    :bind "localhost"
    :handler (->> (map resolve cider/cider-middleware)
              (into [#'refactor/wrap-refactor])
                  (apply nrepl/default-handler)))
  (println (str "Started nREPL server on port " port)))


(defn -main [& args]
  (mount/start)
  (start-nrepl! 8000))
