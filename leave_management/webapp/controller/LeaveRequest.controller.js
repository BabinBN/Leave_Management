sap.ui.define([
    "sap/ui/core/mvc/Controller",
    "sap/ui/model/json/JSONModel",
    "leavemanagement/utils/BaseApi",
    "leavemanagement/utils/AppConstant"
], (Controller, JSONModel, BaseApi, AppConstant) => {
    "use strict";

    return Controller.extend("leavemanagement.controller.LeaveRequest", {
        onInit: function () {
            this.onbusyLoader();
            this.dtmodel();
            this.onRoutematched();

        },
        onRoutematched: function () {
            this.getView().setModel(new JSONModel(
                {
                    edit: true
                }
            ), "visible");
        },
        onReset: function () {
            this.dtmodel();
        },
        dtmodel: function () {
            let Leavemodel = new JSONModel({
                Code: "",
                Name: "",
                U_REQ: "S",
                U_FROM: null,
                U_TO: null,
                U_REASON: "",
                U_DAYS: ""
            })
            this.getView().setModel(Leavemodel, "leave")
        },

        onbusyLoader: function () {
            this.getView().setBusy(true);
            setTimeout(() => {
                this.getView().setBusy(false);
            }, 5000);
        },
        onApplyLeave: async function () {
            try {

                var leaveModelData = this.getView().getModel("leave").getData();


                var data = await BaseApi.postrequest(AppConstant.URL.Leaverequestadd, leaveModelData);
                console.log("Leave Request Response:", data);

            } catch (err) {
                console.error("Error in applying leave:", err);
            }

        }
    });
});