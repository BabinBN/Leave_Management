sap.ui.define([
    "sap/ui/core/mvc/Controller",
    "sap/ui/model/json/JSONModel",
    "leavemanagement/utils/BaseApi",
    "leavemanagement/utils/AppConstant"
], (Controller, JSONModel, BaseApi, AppConstant) => {
    "use strict";

    return Controller.extend("leavemanagement.controller.Query", {
 onInit: function () {
            this.oModel();

        },
        onToggleTable: function () {
            var oTable = this.byId("resultTable");
            var bVisible = !oTable.getVisible();
            oTable.setVisible(bVisible);
        },
        oModel: function () {
            var obj = new JSONModel({
                SqlCode: "",
                SqlName: "",
                SqlText: ""
            })
            this.getView().setModel(obj, "query")

        },
        onExecute: async function () {
            var excutedata = this.getView().getModel("query").getData();
            var data = await BaseApi.postrequest(AppConstant.URL.PostQuery, excutedata);
            console.log("PostQuery Response:", data);
             const Id=data.SqlCode
             let URL=AppConstant.URL.executeQuery.replace("id",Id)
             var resdata = await BaseApi.postrequest(URL,"");

        }


    })
});