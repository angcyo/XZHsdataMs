package com.angcyo.xzhsdatams;

import com.angcyo.uiview.RApplication
import com.angcyo.xzproducems.utils.Jtds
import com.orhanobut.hawk.Hawk

/**
 * Created by angcyo on 2017-07-23.
 */
class App : RApplication() {

    companion object {
        var ip: String
            get() {
                return Hawk.get("ip", "112.30.130.222")
            }
            set(value) {
                Hawk.put("ip", value)
            }

        var port: String
            get() {
                return Hawk.get("port", "21006")
            }
            set(value) {
                Hawk.put("port", value)
            }

        var db_name: String
            get() {
                return Hawk.get("db_name", "HSDATA")
            }
            set(value) {
                Hawk.put("db_name", value)
            }

        var name: String
            get() {
                return Hawk.get("db_name", "xzsoft")
            }
            set(value) {
                Hawk.put("db_name", value)
            }

        var pw: String
            get() {
                return Hawk.get("db_pw", "xzsoft")
            }
            set(value) {
                Hawk.put("db_pw", value)
            }

        fun init() {
            Jtds.init("$ip:$port", db_name, name, pw)
        }
    }

    override fun onInit() {
        super.onInit()
        init()
    }
}
