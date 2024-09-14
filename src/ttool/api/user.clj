(ns ttool.api.user
  (:require
   [ttool.db :as db]
   [ttool.utils :as u]))

(defn- generate-otp []
  (str (rand-int 999999)))


(defn send-otp
  [{{:keys [email phone]} :params}]
  (let [otp (generate-otp)]
    (println "OTP: " otp)
    (db/t [(cond-> {:otp/code otp}
             email (assoc :otp/email email)
             phone (assoc :otp/phone phone))])

    {:status 204}))


(comment
  (send-otp {:params {:email "asdf@asdf.sdf"}})

  (db/q '[:find ?e ?code ?email :where [?e :otp/code ?code] [?e :otp/email ?email]])

  (db/q '[:find ?e
          :in $ ?email ?phone ?code ?ten-mins-ago
          :where
          ['(get-some ?e :otp/email :otp/phone) (or email phone)]
          (or
            (and [?e :otp/email ?email]
                 [?e :otp/code ?code]
                 [?e :otp/created ?created]
                 [(>= ?created ?ten-mins-ago)])
            (and [?e :otp/phone ?phone]
                 [?e :otp/code ?code]
                 [?e :otp/created ?created]
                 [(>= ?created ?ten-mins-ago)]))]
    "lol@lol.lol" nil "38043" #inst "2025-01-01T00:00:00.000-00:00")


  )
