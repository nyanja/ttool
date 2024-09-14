(ns ttool.graphql.handler
  (:require
   [cheshire.core                  :as json]
   [com.walmartlabs.lacinia        :as lacinia]
   [com.walmartlabs.lacinia.schema :as schema]
   [ttool.log                      :as log]))


(def schema
  (schema/compile
    {:queries
     {:helloWorld {:type    'String
                   :resolve (fn [_ _ _] "hello")}}}))


(defn graphql-handler [req]
  (let [body      (-> req :body slurp)
        parsed    (json/parse-string body true)
        query     (:query parsed)
        variables (:variables parsed)
        result    (lacinia/execute schema query nil variables)]

    (log/info "GraphQL " query)

    {:status 200
     :body   (json/generate-string result)}))
