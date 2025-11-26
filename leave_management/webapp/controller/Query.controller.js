sap.ui.define([
    "sap/ui/core/mvc/Controller",
    "sap/ui/model/json/JSONModel",
    "leavemanagement/utils/BaseApi",
    "leavemanagement/utils/AppConstant",
    "sap/ui/export/Spreadsheet"
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
            const Id = data.SqlCode
            //const Id = "Q1-10"
            let URL = AppConstant.URL.executeQuery.replace("id", Id)
            var resdata = await BaseApi.postrequest(URL, "");
            this.onResponseLoad(resdata)
        },
        onFind: async function () {
            // this.getView().byId("txtquery").setVisible(false);
            // this.getView().byId("qName").setVisible(false);

            var queryId = this.getView().byId("qid").getValue();
            let URL = AppConstant.URL.executeQuery.replace("id", queryId)
            var resdata = await BaseApi.postrequest(URL, "");
            this.onResponseLoad(resdata)
        },
        onResponseLoad: function (oResponse) {
            var oTable = this.byId("resultTable");

            // set model
            var oModel = new JSONModel(oResponse);
            this.getView().setModel(oModel);

            // clear old columns/items
            oTable.removeAllColumns();
            oTable.removeAllItems();

            // get keys from first record
            var aKeys = Object.keys(oResponse.value[0]);

            // create columns
            aKeys.forEach(function (sKey) {
                oTable.addColumn(new sap.m.Column({
                    header: new sap.m.Text({ text: sKey })
                }));
            });

            // create template row
            var oTemplate = new sap.m.ColumnListItem();
            aKeys.forEach(function (sKey) {
                oTemplate.addCell(new sap.m.Text({ text: "{" + sKey + "}" }));
            });

            // bind items to value[]
            oTable.bindItems({
                path: "/value",
                template: oTemplate
            });

            // show table
            oTable.setVisible(true);
        },
        onExport: function () {
            var oTable = this.getView().byId("resultTable");   // your sap.m.Table
            var oModel = oTable.getModel();                    // bound JSONModel
            var aData = oModel.getProperty("/value");          // assuming response is in "/value"

            if (!aData || aData.length === 0) {
                sap.m.MessageToast.show("No data available to export");
                return;
            }

            // 🔹 Generate column definitions dynamically based on keys in first object
            var aCols = Object.keys(aData[0]).map(function (sKey) {
                return {
                    label: sKey,       // Column name in Excel
                    property: sKey     // Field name in JSON
                };
            });

            // 🔹 Spreadsheet settings
            var oSettings = {
                workbook: { columns: aCols },
                dataSource: aData,
                fileName: "Export.xlsx"
            };

            var oSheet = new sap.ui.export.Spreadsheet(oSettings);
            oSheet.build().then(function () {
                sap.m.MessageToast.show("Excel exported successfully!");
            }).finally(function () {
                oSheet.destroy();
            });
        },
        onNavBack:function()
        {
            
            var oRoute= this.getOwnerComponent().getRouter();
            oRoute.navTo("Dashboard")
        }





    })
});