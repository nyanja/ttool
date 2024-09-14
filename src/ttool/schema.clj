(ns ttool.schema)

(def schema
  [{:db/ident       :user/id
    :db/valueType   :db.type/uuid
    :db/cardinality :db.cardinality/one
    :db/unique      :db.unique/identity}

   {:db/ident       :user/email
    :db/valueType   :db.type/string
    :db/cardinality :db.cardinality/one
    :db/unique      :db.unique/identity}

   {:db/ident       :user/created
    :db/valueType   :db.type/instant
    :db/cardinality :db.cardinality/one}

   {:db/ident       :user/name
    :db/valueType   :db.type/string
    :db/cardinality :db.cardinality/one}


   ;; Session
   {:db/ident       :session/id
    :db/valueType   :db.type/uuid
    :db/cardinality :db.cardinality/one
    :db/unique      :db.unique/identity}

   {:db/ident       :session/user
    :db/valueType   :db.type/ref
    :db/cardinality :db.cardinality/one}


   ;; OTP
   {:db/ident       :otp/id
    :db/valueType   :db.type/uuid
    :db/cardinality :db.cardinality/one
    :db/unique      :db.unique/identity}

   {:db/ident       :otp/code
    :db/valueType   :db.type/string
    :db/cardinality :db.cardinality/one}

   {:db/ident       :otp/email
    :db/valueType   :db.type/string
    :db/cardinality :db.cardinality/one}

   {:db/ident       :otp/phone
    :db/valueType   :db.type/string
    :db/cardinality :db.cardinality/one}

   {:db/ident       :otp/created
    :db/valueType   :db.type/instant
    :db/cardinality :db.cardinality/one}])
