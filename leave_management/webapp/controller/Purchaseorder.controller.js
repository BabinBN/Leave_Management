sap.ui.define([
    "sap/ui/core/mvc/Controller",
    "sap/ui/model/json/JSONModel",
    "leavemanagement/utils/BaseApi",
    "leavemanagement/utils/AppConstant",
    "sap/ui/core/Fragment"
], (Controller, JSONModel, BaseApi, AppConstant, Fragment) => {
    "use strict";

    return Controller.extend("leavemanagement.controller.Purchaseorder", {

        onInit: function () {
            var oData = new JSONModel({
                itemsmdl: [
                    { ItemCode: "", ItemDescription: "", Quantity: "", Price: "", VatGroup: "V5", DiscountPercent: "", GrossPrice: "", UoMCode: "", Currency: "" }
                ]
            });
            this.getView().setModel(oData, "DocumentLines");
            this.PurchaseOrderMdl();
        },

        onAddRow: function () {
            var oModel = this.getView().getModel("PurchaseOrderMdl");
            var aLines = oModel.getProperty("/DocumentLines");
            aLines.push({
                ItemCode: "",
                ItemDescription: "",
                Quantity: "",
                Price: "",
                GrossPrice: "",
                VatGroup: "V5",
                DiscountPercent: "",
                UoMCode: "",
                Currency: ""
            });
            oModel.setProperty("/DocumentLines", aLines);
        },

        onDeleteRow: function () {
            var oTable = this.byId("idProductsTable");
            var oModel = this.getView().getModel("PurchaseOrderMdl");
            var aLines = oModel.getProperty("/DocumentLines");

            var aSelected = oTable.getSelectedItems();
            aSelected.forEach(function (oItem) {
                var iIndex = parseInt(oItem.getBindingContext("PurchaseOrderMdl").getPath().split("/").pop(), 10);
                aLines.splice(iIndex, 1);
            });

            oModel.setProperty("/DocumentLines", aLines);
            oTable.removeSelections(true);
        },
        onValueHelpRequested: async function (oEvent) {
            var sInputValue = oEvent.getSource().getValue();
            this._pValueHelpDialog = this._pValueHelpDialog || await Fragment.load({
                id: this.getView().getId(),
                name: "leavemanagement.view.fragment.BusinessPartner",
                controller: this
            });
            this.getView().addDependent(this._pValueHelpDialog);
            this._pValueHelpDialog.open();
        },
        onBPChoose: function (oEvent) {
            var oTable = this.byId("bpTable");

            var oSelectedItem = oTable.getSelectedItem();

            if (oSelectedItem) {

                var oContext = oSelectedItem.getBindingContext("Bpmdl");
                var oData = oContext.getObject();
                // this.byId("cardcode").setValue(oData.BPCode);
                // this.byId("cardName").setValue(oData.BPName);
                var oModel = this.getView().getModel("PurchaseOrderMdl")
                oModel.setProperty("/CardCode", oData.BPCode)
                oModel.setProperty("/CardName", oData.BPName)

                this._pValueHelpDialog.close();
            }

        },

        PurchaseOrderMdl: function () {
            this.getView().setModel(new JSONModel({
                CardCode: "",
                CardName: "",
                Cancelled: "N",
                DocNum: "",
                NumAtCard: "",
                DocType: "I",
                DocTotal: 0.0,
                DocDate: "",
                DocDueDate: "",
                Address: "",
                BPL_IDAssignedToInvoice: "1",
                VATRegNum: "",
                Department: "",
                Email: "",
                Branch: "",
                DocCurrency: "",
                DocumentStatus: "O",
                DocumentLines: [
                    {
                        ItemCode: "",
                        ItemDescription: "",
                        Quantity: "",
                        Price: "",
                        GrossPrice: "",
                        VatGroup: "V5",
                        DiscountPercent: "",
                        UoMCode: "",
                        Currency: ""
                    }
                ]

            }), "PurchaseOrderMdl")
        },
        onValueUnitRequest: async function (oEvent) {
            var sInputValue = oEvent.getSource().getValue();
            this._currentContext = oEvent.getSource().getBindingContext("PurchaseOrderMdl");
            this._Unit = this._Unit || await Fragment.load({
                id: this.getView().getId(),
                name: "leavemanagement.view.fragment.UomvalueHelp",
                controller: this
            });
            this.getView().addDependent(this._Unit);
            this._Unit.open();
        },
        onUnitChoose: function (oEvent) {
            var oTable = this.byId("unitTable");
            var oSelectedItem = oTable.getSelectedItem();

            if (oSelectedItem) {
                var oUomData = oSelectedItem.getBindingContext("uoMmdl").getObject();
                var oModel = this.getView().getModel("PurchaseOrderMdl");
                var sPath = this._currentContext.getPath();
                oModel.setProperty(sPath + "/UoMCode", oUomData.Name);
                this._Unit.close();
            }

        },
        onValueItemRequest: async function (oEvent) {
            this._currentItemContext = oEvent.getSource().getBindingContext("PurchaseOrderMdl");
            this._Item = this._Item || await Fragment.load({
                id: this.getView().getId(),
                name: "leavemanagement.view.fragment.ItemMaster",
                controller: this
            });
            this.getView().addDependent(this._Item);
            this._Item.open();
        },
        onItemChoose: function () {
            var oTable = this.byId("ItmTable");
            var oSelectedItem = oTable.getSelectedItem();

            if (oSelectedItem) {
                var oItemData = oSelectedItem.getBindingContext("Itemmdl").getObject();
                var oModel = this.getView().getModel("PurchaseOrderMdl");
                var sPath = this._currentItemContext.getPath();
                oModel.setProperty(sPath + "/ItemCode", oItemData["Item No."]);
                oModel.setProperty(sPath + "/ItemDescription", oItemData["Item Description"]);
                oModel.setProperty(sPath + "/Currency", oItemData["Currency"]);
                this._Item.close();
            }
        },
        onPressSave: async function () {
            var oDataPO = this.getView().getModel("PurchaseOrderMdl").getData()
            function formatDateToDDMMYYYY(isoDate) {
                var d = new Date(isoDate);
                var day = String(d.getDate()).padStart(2, '0');
                var month = String(d.getMonth() + 1).padStart(2, '0');
                var year = d.getFullYear();
                return day + '.' + month + '.' + year;
            }

            oDataPO.DocDate = formatDateToDDMMYYYY(oDataPO.DocDate);
            oDataPO.DocDueDate = formatDateToDDMMYYYY(oDataPO.DocDueDate);
            var poResponse = await BaseApi.postrequest(AppConstant.URL.PUrchaseOrder, oDataPO)
            // that.byId("footerError").setText(poResponse);
            // that.byId("footerError").setVisible(true);
            console.log("Purchase Order Response:", poResponse);
            this.PurchaseOrderMdl();
        },
        onPressCancel: function () {
            var oRoute = this.getOwnerComponent().getRouter();
            oRoute.navTo("Dashboard")
        }
    }
    )
})