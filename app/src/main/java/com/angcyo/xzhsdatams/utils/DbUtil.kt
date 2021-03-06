package com.angcyo.xzproducems.utils

import com.angcyo.library.utils.L
import com.angcyo.xzhsdatams.bean.ProcBean
import net.sourceforge.jtds.jdbc.JtdsResultSet

/**
 * Created by angcyo on 2017-07-22.
 */
object DbUtil {
    fun test() {
        val connection = Jtds.connectDB()
        L.e("call: test -> ")
        System.out.println("ok... ${connection.isClosed} $connection")

//        System.out.println("${connection.databaseMajorVersion} ${connection.rmHost} ${connection.warnings}")

//        connection.createStatement()
//
//        val call = connection.prepareCall("{call UP_QUERY_PUR01(?)}")
//        //call.setInt()
//        System.out.println("call... $call")
//
//        call.setString(1, "1001")
//
////        call.registerOutParameter(2, Types.OTHER)
//
////        val execute = call.execute()
//        val execute: JtdsResultSet = call.executeQuery() as JtdsResultSet
//        execute.next()
//        val PID = execute.getString("PID")
//        val DGID = execute.getString("DGID")
//
//        connection.close()
//
//
//        System.out.println("ok...2 $execute $PID $DGID")
//
//        test2()
    }

    fun test2() {
        System.out.println("test2...")
        val connection = Jtds.connectDB()
        val call = connection.prepareCall("{call UP_WARN_PUR01()}")
        val execute: JtdsResultSet = call.executeQuery() as JtdsResultSet
        execute.next()
        val WARNMEMO = execute.getString("WARNMEMO")
        connection.close()
        System.out.println("test2 ok...2 $execute $WARNMEMO")
    }
//
//    /**登录*/
//    fun login(name: String, pw: String): LoginBean {
//        val result = LoginBean()
//        Jtds.prepareCall_set("UP_GET_USERPOWER", 2,
//                { jtdsCallableStatement ->
//                    jtdsCallableStatement.setString("USERID", name)
//                    jtdsCallableStatement.setString("PASSWORD", pw)
//                },
//                { jtdsResultSet ->
//                    if (jtdsResultSet.next()) {
//                        val GXID = jtdsResultSet.getInt("GXID")
//                        val IsModify = jtdsResultSet.getInt("IsModify")
//                        val PNAME7 = jtdsResultSet.getString("PNAME7")
//                        val IsAdmin = jtdsResultSet.getInt("IsAdmin")
//                        L.e("call: login -> $GXID:$PNAME7 $IsModify $IsAdmin")
//                        result.GXID = GXID
//                        result.IsModify = IsModify
//                        result.PNAME7 = PNAME7
//                        result.IsAdmin = IsAdmin
//                    } else {
//                        L.e("call: login -> 无数据")
//                    }
//                })
//
//        return result
//    }
//
//    /**订单完工数量*/
//    fun UP_WARN_QTY(): String {
//        var result: String = "0"
//        Jtds.prepareCall_set("UP_WARN_QTY", 0, null,
//                { jtdsResultSet ->
//                    if (jtdsResultSet.next()) {
//                        val WARNQTY = jtdsResultSet.getString("WARNQTY")
//                        L.e("call: UP_WARN_QTY -> $WARNQTY")
//                        result = WARNQTY
//                    } else {
//                        L.e("call: UP_WARN_QTY -> 无数据")
//                        result = "0"
//                    }
//                })
//
//        return result
//    }
//
//    /**订单完工提醒*/
//    fun UP_WARN_PUR01(): MutableList<String> {
//        var result: MutableList<String> = mutableListOf()
//        Jtds.prepareCall_set("UP_WARN_PUR01", 0, null,
//                { jtdsResultSet ->
//                    while (jtdsResultSet.next()) {
//                        val WARNQTY = jtdsResultSet.getString("WARNMEMO")
//                        L.e("call: UP_WARN_PUR01 -> $WARNQTY")
//                        result.add(WARNQTY)
//                    }
//                })
//
//        return result
//    }
//
//    /**查询订单完工状况*/
//    fun UP_QUERY_PUR01(DGID: String): MutableList<QueryBean> {
//        var result: MutableList<QueryBean> = mutableListOf()
//        Jtds.prepareCall_set("UP_QUERY_PUR01", 1,
//                { jtdsCallableStatement ->
//                    jtdsCallableStatement.setString(1, DGID)
//                },
//                { jtdsResultSet ->
//                    while (jtdsResultSet.next()) {
//                        val bean = QueryBean(
//                                jtdsResultSet.getString("DGID"),
//                                jtdsResultSet.getString("GXID"),
//                                jtdsResultSet.getString("PID"),
//                                jtdsResultSet.getString("PNAME1"),
//                                jtdsResultSet.getString("PNAME2"),
//                                jtdsResultSet.getString("PNAME3"),
//                                jtdsResultSet.getString("PNAME4"),
//                                jtdsResultSet.getString("PNAME5"),
//                                jtdsResultSet.getString("PNAME6"),
//                                jtdsResultSet.getString("PNAME7"),
//                                jtdsResultSet.getString("QTY1"),
//                                jtdsResultSet.getString("QTY2"),
//                                jtdsResultSet.getString("QTY3"),
//                                jtdsResultSet.getString("QTY4"),
//                                jtdsResultSet.getString("QTY5"),
//                                jtdsResultSet.getString("QTY6"),
//                                jtdsResultSet.getString("QTY7"),
//                                jtdsResultSet.getString("QTY8")
//                        )
//                        L.e("call: UP_WARN_PUR01 -> $bean")
//                        result.add(bean)
//                    }
//                })
//        return result
//    }
//
//    /**新增修改订单数据*/
//    @Deprecated("数据结构老了")
//    fun UP_MODI_PUR01_D1(
//            DGID: String, //--订单号
//            GXID: String, //--工序
//            PID: String, //--物料编码
//            PNAME1: String, //--名称
//            PNAME2: String, //--规格
//            PNAME3: String, //
//            PNAME4: String, //
//            PNAME5: String, //
//            PNAME6: String, //
//            QTY1: String, //--订单投产数量
//            QTY2: String, //--已完工数量
//            QTY3: String, //--返工数量
//            QTY4: String, //--当前投产数量即为还有多少未生产数量
//            QTY5: String, //--订单数量
//            QTY6: String, //
//            QTY7: String, //
//            USERID: String//--更新人
//    ): Boolean {
//        var result = Jtds.prepareCall_update("UP_MODI_PUR01_D1", 17,
//                { jtdsCallableStatement ->
//                    jtdsCallableStatement.setString("@DGID", DGID)
//                    jtdsCallableStatement.setInt("@GXID", GXID.toInt())
//                    jtdsCallableStatement.setString("@PID", PID)
//                    jtdsCallableStatement.setString("@PNAME1", PNAME1)
//                    jtdsCallableStatement.setString("@PNAME2", PNAME2)
//                    jtdsCallableStatement.setString("@PNAME3", PNAME3)
//                    jtdsCallableStatement.setString("@PNAME4", PNAME4)
//                    jtdsCallableStatement.setString("@PNAME5", PNAME5)
//                    jtdsCallableStatement.setString("@PNAME6", PNAME6)
//                    jtdsCallableStatement.setInt("@QTY1", QTY1.toInt())
//                    jtdsCallableStatement.setInt("@QTY2", QTY2.toInt())
//                    jtdsCallableStatement.setInt("@QTY3", QTY3.toInt())
//                    jtdsCallableStatement.setInt("@QTY4", QTY4.toInt())
//                    jtdsCallableStatement.setInt("@QTY5", QTY5.toInt())
//                    jtdsCallableStatement.setInt("@QTY6", QTY6.toInt())
//                    jtdsCallableStatement.setInt("@QTY7", QTY7.toInt())
//                    jtdsCallableStatement.setString("@USERID", USERID)
//                })
//        return result
//    }
//
//    /**
//     * @FVID INT,--原订单表明细ID号
//    @DGID NVARCHAR(20),--订单号
//    @GXID INT,--本工序
//    @PID NVARCHAR(20),--物料编码
//    @PNAME1 NVARCHAR(250),--名称
//    @PNAME2 NVARCHAR(250),--规格
//    @PNAME3 NVARCHAR(250),--型号
//    @PNAME4 NVARCHAR(250),
//    @PNAME5 NVARCHAR(250),订单子项备注
//    @PNAME6 NVARCHAR(250),录完数据时备注
//    @QTY1 INT,--订单投产数量
//    @QTY2 INT,--已完工数量
//    @QTY3 INT,--返工数量
//    @QTY4 INT,--当前投产数量即为还有多少未生产数量
//    @QTY5 INT,--订单数量
//    @QTY6 INT,
//    @QTY7 INT,
//    @XHNO1 INT,--上工序（*）
//    @isover int --是否完工（*），1表示没有后续工序
//    @nstate int--是否结案，1表示订单完工
//    @USERID NVARCHAR(20)--更新人
//     */
//    fun UP_MODI_PUR01_D1(orderBean: OrderBean, QTY2: String?, PNAME5: String?, isover: String, nstate: String): Boolean {
//        var result = Jtds.prepareCall_update("UP_MODI_PUR01_D1", 21,
//                { jtdsCallableStatement ->
//                    jtdsCallableStatement.setString("@FVID", orderBean.FVID)
//                    jtdsCallableStatement.setString("@DGID", orderBean.DGID)
//                    jtdsCallableStatement.setString("@GXID", LoginControl.gx2Bean.GXID /*orderBean.GXID*/)
//                    jtdsCallableStatement.setString("@PID", orderBean.PID)
//                    jtdsCallableStatement.setString("@PNAME1", orderBean.PNAME1)
//                    jtdsCallableStatement.setString("@PNAME2", orderBean.PNAME2)
//                    jtdsCallableStatement.setString("@PNAME3", orderBean.PNAME3)
//                    jtdsCallableStatement.setString("@PNAME4", orderBean.PNAME4)
//                    jtdsCallableStatement.setString("@PNAME5", PNAME5)
//                    jtdsCallableStatement.setString("@PNAME6", orderBean.PNAME6)
//                    jtdsCallableStatement.setString("@QTY1", orderBean.QTY1)
//                    jtdsCallableStatement.setString("@QTY2", QTY2)
//                    jtdsCallableStatement.setString("@QTY3", orderBean.QTY3)
//                    jtdsCallableStatement.setString("@QTY4", orderBean.QTY4)
//                    jtdsCallableStatement.setString("@QTY5", orderBean.QTY5)
//                    jtdsCallableStatement.setString("@QTY6", orderBean.QTY6)
//                    jtdsCallableStatement.setString("@QTY7", orderBean.QTY7)
//                    jtdsCallableStatement.setString("@XHNO1", LoginControl.gxBean.GXID)
//                    jtdsCallableStatement.setString("@isover", isover)
//                    jtdsCallableStatement.setString("@nstate", nstate)
//                    jtdsCallableStatement.setString("@USERID", orderBean.USERID)
//                })
//        return result
//    }

    /**
     * 添加记录 proc_add（参数：
    @ProductType AS nvarchar ,  --型号
    @Lshou AS nvarchar ,  --厘数
    @bianchang AS nvarchar , --边长
    @MainTemp1 AS decimal , --主机温度1
    @MainTemp2 AS decimal ,--主机温度2
    @MainTemp3 AS decimal ,--主机温度3
    @MainTemp4 AS decimal ,--主机温度4
    @MainTemp5 AS decimal ,--主机温度5
    @Mainspeed AS decimal ,--主机转速
    @SUPPTemp11 AS decimal ,--辅机1温度1
    @SUPPTemp12 AS decimal ,--辅机1温度2
    @SUPPTemp13 AS decimal ,--辅机1温度3
    @SUPPTemp14 AS decimal ,--辅机1温度4
    @SUPPTemp15 AS decimal ,--辅机1温度5
    @SUPPTemp21 AS decimal ,--辅机2温度1
    @SUPPTemp22 AS decimal ,--辅机2温度2
    @SUPPTemp23 AS decimal ,--辅机2温度3
    @SUPPTemp24 AS decimal ,--辅机2温度4
    @SUPPTemp25 AS decimal ,--辅机2温度5
    @SUPPspeed1 AS decimal ,--辅机1转速
    @SUPPspeed2 AS decimal ,--辅机2转速
    @Memob AS text ,  --备注
    @Pict01 AS text  --图片
    ）
     */

    fun proc_add(procBean: ProcBean): Boolean {
        var result = Jtds.prepareCall_update("proc_add", 24,
                { jtdsCallableStatement ->
                    jtdsCallableStatement.setString("@ProductType", procBean.ProductType)
                    jtdsCallableStatement.setString("@Lshou", procBean.Lshou)
                    jtdsCallableStatement.setString("@bianchang", procBean.bianchang)
                    jtdsCallableStatement.setInt("@MainTemp1", procBean.MainTemp1)
                    jtdsCallableStatement.setInt("@MainTemp2", procBean.MainTemp2)
                    jtdsCallableStatement.setInt("@MainTemp3", procBean.MainTemp3)
                    jtdsCallableStatement.setInt("@MainTemp4", procBean.MainTemp4)
                    jtdsCallableStatement.setInt("@MainTemp5", procBean.MainTemp5)
                    jtdsCallableStatement.setInt("@Mainspeed", procBean.Mainspeed)
                    jtdsCallableStatement.setInt("@SUPPTemp11", procBean.SUPPTemp11)
                    jtdsCallableStatement.setInt("@SUPPTemp12", procBean.SUPPTemp12)
                    jtdsCallableStatement.setInt("@SUPPTemp13", procBean.SUPPTemp13)
                    jtdsCallableStatement.setInt("@SUPPTemp14", procBean.SUPPTemp14)
                    jtdsCallableStatement.setInt("@SUPPTemp15", procBean.SUPPTemp15)
                    jtdsCallableStatement.setInt("@SUPPTemp21", procBean.SUPPTemp21)
                    jtdsCallableStatement.setInt("@SUPPTemp22", procBean.SUPPTemp22)
                    jtdsCallableStatement.setInt("@SUPPTemp23", procBean.SUPPTemp23)
                    jtdsCallableStatement.setInt("@SUPPTemp24", procBean.SUPPTemp24)
                    jtdsCallableStatement.setInt("@SUPPTemp25", procBean.SUPPTemp25)
                    jtdsCallableStatement.setInt("@SUPPspeed1", procBean.SUPPspeed1)
                    jtdsCallableStatement.setInt("@SUPPspeed2", procBean.SUPPspeed2)
                    jtdsCallableStatement.setInt("@Qyspeed1", procBean.Qyspeed1)
                    jtdsCallableStatement.setString("@Memob", procBean.Memob)
                    jtdsCallableStatement.setString("@Pict01", procBean.Pict01)
                })
        return result
    }

    fun proc_modi(id: Int, procBean: ProcBean): Boolean {
        var result = Jtds.prepareCall_update("proc_Modi", 25,
                { jtdsCallableStatement ->
                    jtdsCallableStatement.setInt("@id", id)
                    jtdsCallableStatement.setString("@ProductType", procBean.ProductType)
                    jtdsCallableStatement.setString("@Lshou", procBean.Lshou)
                    jtdsCallableStatement.setString("@bianchang", procBean.bianchang)
                    jtdsCallableStatement.setInt("@MainTemp1", procBean.MainTemp1)
                    jtdsCallableStatement.setInt("@MainTemp2", procBean.MainTemp2)
                    jtdsCallableStatement.setInt("@MainTemp3", procBean.MainTemp3)
                    jtdsCallableStatement.setInt("@MainTemp4", procBean.MainTemp4)
                    jtdsCallableStatement.setInt("@MainTemp5", procBean.MainTemp5)
                    jtdsCallableStatement.setInt("@Mainspeed", procBean.Mainspeed)
                    jtdsCallableStatement.setInt("@SUPPTemp11", procBean.SUPPTemp11)
                    jtdsCallableStatement.setInt("@SUPPTemp12", procBean.SUPPTemp12)
                    jtdsCallableStatement.setInt("@SUPPTemp13", procBean.SUPPTemp13)
                    jtdsCallableStatement.setInt("@SUPPTemp14", procBean.SUPPTemp14)
                    jtdsCallableStatement.setInt("@SUPPTemp15", procBean.SUPPTemp15)
                    jtdsCallableStatement.setInt("@SUPPTemp21", procBean.SUPPTemp21)
                    jtdsCallableStatement.setInt("@SUPPTemp22", procBean.SUPPTemp22)
                    jtdsCallableStatement.setInt("@SUPPTemp23", procBean.SUPPTemp23)
                    jtdsCallableStatement.setInt("@SUPPTemp24", procBean.SUPPTemp24)
                    jtdsCallableStatement.setInt("@SUPPTemp25", procBean.SUPPTemp25)
                    jtdsCallableStatement.setInt("@SUPPspeed1", procBean.SUPPspeed1)
                    jtdsCallableStatement.setInt("@SUPPspeed2", procBean.SUPPspeed2)
                    jtdsCallableStatement.setInt("@Qyspeed1", procBean.Qyspeed1)
                    jtdsCallableStatement.setString("@Memob", procBean.Memob)
                    jtdsCallableStatement.setString("@Pict01", procBean.Pict01)
                })
        return result
    }

    /**
     * 搜索记录  proc_search（参数：@ProductType AS nvarchar ,  --型号，
    @Lshou AS nvarchar ,  --厘数，
    @Bianchang AS nvarchar  --边长）
     */

    fun proc_search(ProductType: String = "", Lshou: String = "", Bianchang: String = ""): MutableList<ProcBean> {
        var result: MutableList<ProcBean> = mutableListOf()
        L.e("call: proc_search -> $ProductType $Lshou $Bianchang")

        fun onResult(jtdsResultSet: JtdsResultSet) {
            //L.e("查询结果1:" + jtdsResultSet.getInt(0))

            while (jtdsResultSet.next()) {
//                        for (i in 1..30) {
//                            L.e("查询结果2:" + jtdsResultSet.getString(i))
//                        }
                val bean = ProcBean(
                        jtdsResultSet.getInt("Id"),
                        jtdsResultSet.getString("DhNo"),
                        jtdsResultSet.getString("ProductType"),
                        jtdsResultSet.getString("Lshou"),
                        jtdsResultSet.getString("bianchang"),
                        jtdsResultSet.getInt("MainTemp1"),
                        jtdsResultSet.getInt("MainTemp2"),
                        jtdsResultSet.getInt("MainTemp3"),
                        jtdsResultSet.getInt("MainTemp4"),
                        jtdsResultSet.getInt("MainTemp5"),
                        jtdsResultSet.getInt("Mainspeed"),
                        jtdsResultSet.getInt("SUPPTemp11"),
                        jtdsResultSet.getInt("SUPPTemp12"),
                        jtdsResultSet.getInt("SUPPTemp13"),
                        jtdsResultSet.getInt("SUPPTemp14"),
                        jtdsResultSet.getInt("SUPPTemp15"),
                        jtdsResultSet.getInt("SUPPTemp21"),
                        jtdsResultSet.getInt("SUPPTemp22"),
                        jtdsResultSet.getInt("SUPPTemp23"),
                        jtdsResultSet.getInt("SUPPTemp24"),
                        jtdsResultSet.getInt("SUPPTemp25"),
                        jtdsResultSet.getInt("SUPPspeed1"),
                        jtdsResultSet.getInt("SUPPspeed2"),
                        jtdsResultSet.getInt("Qyspeed1"),
                        jtdsResultSet.getString("Memob"),
                        jtdsResultSet.getString("Pict01")
                )
                result.add(bean)
            }
        }

        if (Lshou.isEmpty() && Bianchang.isEmpty()) {
            Jtds.prepareCall_set("proc_query", 3,
                    { jtdsCallableStatement ->
                        jtdsCallableStatement.setString("ProductType", ProductType)
                        jtdsCallableStatement.setString("Lshou", "")
                        jtdsCallableStatement.setString("Bianchang", "")
                    },
                    { jtdsResultSet ->
                        onResult(jtdsResultSet)
                    })
        } else {
            Jtds.prepareCall_set("proc_query", 3,
                    { jtdsCallableStatement ->
                        jtdsCallableStatement.setString("ProductType", ProductType)
                        jtdsCallableStatement.setString("Lshou", Lshou)
                        jtdsCallableStatement.setString("Bianchang", Bianchang)
                    },
                    { jtdsResultSet ->
                        onResult(jtdsResultSet)
                    })
        }

        return result
    }

    /**
     * 删除记录 proc_del（参数：
    @id as int,  -- id
    @Password AS nvarchar  --密码
    )
     * */

    fun proc_del(id: Int, Password: String = ""): Boolean {
        var result = Jtds.prepareCall_update("proc_del", 2,
                { jtdsCallableStatement ->
                    jtdsCallableStatement.setInt("@id", id)
                    jtdsCallableStatement.setString("@Password", Password)
                })
        return result
    }

    /**
     * 修改密码
     * proc_modipass（参数：
    @oldpassword AS nvarchar ,  --旧密码
    @newpassword AS nvarchar   --新密码
    )
     */
    fun proc_modipass(oldpassword: String = "", newpassword: String = ""): Boolean {
        var result = Jtds.prepareCall_update("proc_modipass", 2,
                { jtdsCallableStatement ->
                    jtdsCallableStatement.setString("@oldpassword", oldpassword)
                    jtdsCallableStatement.setString("@newpassword", newpassword)
                })
        return result
    }

//    /**取订单数据*/
//    fun UP_GET_DGID(DGID: String /*订单号*/ /*, GXID: Int *//*工序*/): MutableList<OrderBean> {
//        var result: MutableList<OrderBean> = mutableListOf()
//        Jtds.prepareCall_set("UP_GET_DGID", 2,
//                { jtdsCallableStatement ->
//                    jtdsCallableStatement.setString("DGID", DGID)
//                    jtdsCallableStatement.setInt("GXID", LoginControl.gxBean.GXID.trim().toInt())
//                },
//                { jtdsResultSet ->
//                    while (jtdsResultSet.next()) {
////                        for (i in 1..30) {
////                            L.e("call: UP_GET_DGID -> $i ${jtdsResultSet.getString(i)}")
////                        }
//
//                        if ("999".equals(jtdsResultSet.getString("DGID"), ignoreCase = true)) {
//                            return@prepareCall_set
//                        }
//
//                        val bean = OrderBean(
//                                jtdsResultSet.getString("FVID"),
//                                jtdsResultSet.getString("DGID"),
//                                jtdsResultSet.getString("GXID"),
//                                jtdsResultSet.getString("PID"),
//                                jtdsResultSet.getString("PNAME1"),
//                                jtdsResultSet.getString("PNAME2"),
//                                jtdsResultSet.getString("PNAME3"),
//                                jtdsResultSet.getString("PNAME4"),
//                                jtdsResultSet.getString("PNAME5"),
//                                jtdsResultSet.getString("PNAME6"),
//                                jtdsResultSet.getString("QTY1"),
//                                jtdsResultSet.getString("QTY2"),
//                                jtdsResultSet.getString("QTY3"),
//                                jtdsResultSet.getString("QTY4"),
//                                jtdsResultSet.getString("QTY5"),
//                                jtdsResultSet.getString("QTY6"),
//                                jtdsResultSet.getString("QTY7"),
//                                jtdsResultSet.getString("DATE1"),
//                                jtdsResultSet.getString("ADDDATE"),
//                                jtdsResultSet.getString("USERID"),
//                                jtdsResultSet.getString("USERNAME")
//                        )
////                        val bean = OrderBean(
////                                jtdsResultSet.getString(1),
////                                jtdsResultSet.getString(2),
////                                jtdsResultSet.getString(3),
////                                jtdsResultSet.getString(4),
////                                jtdsResultSet.getString(5),
////                                jtdsResultSet.getString(6),
////                                jtdsResultSet.getString(7),
////                                jtdsResultSet.getString(8),
////                                jtdsResultSet.getString(9),
////                                jtdsResultSet.getString(10),
////                                jtdsResultSet.getString(11),
////                                jtdsResultSet.getString(12),
////                                jtdsResultSet.getString(13),
////                                jtdsResultSet.getString(14),
////                                jtdsResultSet.getString(15),
////                                jtdsResultSet.getString(16),
////                                jtdsResultSet.getString(17),
////                                jtdsResultSet.getString(18),
////                                jtdsResultSet.getString(19),
////                                jtdsResultSet.getString(20),
////                                jtdsResultSet.getString(1)
////                        )
//                        //L.e("call: UP_GET_DGID -> $bean")
//                        result.add(bean)
//                    }
//                })
//
//        return result
//    }
//
//    /**
//     * 取工序
//     */
//    fun UP_GET_SYSDICT(): MutableList<GxBean> {
//        var result: MutableList<GxBean> = mutableListOf()
//        Jtds.prepareCall_set("UP_GET_SYSDICT", 0,
//                null,
//                { jtdsResultSet ->
//                    while (jtdsResultSet.next()) {
//                        val bean = GxBean(
//                                jtdsResultSet.getString("GXID"),
//                                jtdsResultSet.getString("PNAME7")
//                        )
//                        L.e("call: UP_GET_SYSDICT -> $bean")
//                        result.add(bean)
//                    }
//                })
//
//        return result
//    }
//
//    fun demo() {
//
//    }

}
