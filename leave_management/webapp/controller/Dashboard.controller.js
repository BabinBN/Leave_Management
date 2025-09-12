sap.ui.define([
    "sap/ui/core/mvc/Controller",
    "sap/ui/model/json/JSONModel",
    "leavemanagement/utils/BaseApi",
    "leavemanagement/utils/AppConstant"
], (Controller, JSONModel, BaseApi, AppConstant) => {
    "use strict";

    return Controller.extend("leavemanagement.controller.Dashboard", {

    onGoLeave:function()
    {
            var oRouter=this.getOwnerComponent().getRouter();
            oRouter.navTo("LeaveRequest");
    },
    onGoQuery:function()
    {
            var oRouter=this.getOwnerComponent().getRouter();
            oRouter.navTo("Query");
    }
    })})