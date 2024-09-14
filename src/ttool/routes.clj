(ns ttool.routes
  (:require
   [reitit.ring :as ring]
   [ring.middleware.keyword-params :refer [wrap-keyword-params]]
   [ring.middleware.multipart-params :refer [wrap-multipart-params]]
   [ring.middleware.params :refer [wrap-params]]
   [reitit.ring.middleware.parameters :as parameters]

   [ttool.api.user :as api.user]
   [ttool.graphql.handler :as graphql-handler]))


(defn api-routes []
  [["/api"
    ["/status" {:get {:handler (fn [req] {:status 200 :body "OK"})}}]
    ["/gen-otp" {:post {:handler api.user/send-otp}}]]
   ["/graphql" {:post {:handler graphql-handler/graphql-handler}}]])


(def app
  (-> (ring/ring-handler
        (ring/router
          (api-routes)
          {:data {:middleware [parameters/parameters-middleware]}})
        (ring/create-default-handler {:not-found (fn [req]
                                                   {:status 404 :body "Not Found"} )}))
      ;; (wrap-keyword-params)
      ;; (wrap-params)
      ;; (wrap-multipart-params)
      ))
