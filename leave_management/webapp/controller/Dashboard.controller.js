sap.ui.define([
        "sap/ui/core/mvc/Controller",
        "sap/ui/model/json/JSONModel",
        "leavemanagement/utils/BaseApi",
        "leavemanagement/utils/AppConstant"
], (Controller, JSONModel, BaseApi, AppConstant) => {
        "use strict";

        return Controller.extend("leavemanagement.controller.Dashboard", {
                onInit: function () {
                        var oRouter = this.getOwnerComponent().getRouter();
                        oRouter.attachRouteMatched(this.onRouteMatched, this);
                },

                onRouteMatched: function (oEvent) {
                        var sRouterName = oEvent.getParameter("name");
                        this.getView().byId("btnLeave").removeStyleClass("activeTab");
                        this.getView().byId("btnQuery").removeStyleClass("activeTab");
                        if (sRouteName === "LeaveRequest") {
                                this.getView().byId("btnLeave").addStyleClass("activeTab");
                        } else if (sRouteName === "Query") {
                                this.getView().byId("btnQuery").addStyleClass("activeTab");
                        }
                },
                onGoLeave: function () {
                        var oRouter = this.getOwnerComponent().getRouter();
                        oRouter.navTo("LeaveRequest");
                        this._setActiveTab("btnLeave");
                },
                onGoQuery: function () {
                        var oRouter = this.getOwnerComponent().getRouter();
                        oRouter.navTo("Query");
                        this._setActiveTab("btnQuery");
                }
        })
})