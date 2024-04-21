(ns ttool.main
  (:require
   [mount.core :as mount]
   [nrepl.server :as nrepl]
   [ttool.server :as server]))


(defn start-nrepl! [port]
  (nrepl/start-server
    :port port
    :bind "127.0.0.1"))

(defn -main [& args]
  (mount/start server/server)
  (start-nrepl! 4001))
