sap.ui.define([
        "sap/ui/core/mvc/Controller",
        "sap/ui/model/json/JSONModel",
        "leavemanagement/utils/BaseApi",
        "leavemanagement/utils/AppConstant",
        "sap/ui/core/Fragment"
], (Controller, JSONModel, BaseApi, AppConstant, Fragment) => {
        "use strict";

        return Controller.extend("leavemanagement.controller.Dashboard", {
                onInit: function () {
                        this._openLoginDialog();
                        this.signmdl();
                        var oRouter = this.getOwnerComponent().getRouter();
                        oRouter.attachRouteMatched(this.onRouteMatched, this);
                },
                signmdl: function () {
                        this.getView().setModel(new JSONModel({
                                        username: "",
                                        email: "",
                                        password: ""
                        }), "signUpModel")
                },
                onGoLeave: function () {
                        var oRouter = this.getOwnerComponent().getRouter();
                        oRouter.navTo("LeaveRequest");
                },
                onGoQuery: function () {
                        var oRouter = this.getOwnerComponent().getRouter();
                        oRouter.navTo("Query");
                },
                _openLoginDialog: async function () {
                        this.loginDialog = await Fragment.load({
                                name: "leavemanagement.view.fragment.Sign_in",
                                controller: this
                        });
                        this.getView().addDependent(this.loginDialog);
                        this.getView().setModel(new JSONModel({
                                email: "",
                                password: "",
                                keepme: false
                        }), "loginModel")
                        this.loginDialog.open();
                        this.getView().rerender();
                },
                onCloseDialog: function () {
                        this.loginDialog.close();

                },
                onLogin: async function () {
                        var odata = this.getView().getModel("loginModel").getData()
                        var reqtoken = await BaseApi.PostloginToken(AppConstant.URL.SignIn, odata)
                        if (reqtoken) {
                                this.onCloseDialog();
                        }

                },
                onSignUpGO: async function () {
                        this.onSignUpGO = await Fragment.load({
                                name: "leavemanagement.view.fragment.Sign_up",

                                controller: this
                        });

                        this.getView().addDependent(this.onSignUpGO);
                        this.onSignUpGO.open();
                        this.onCloseDialog();
                },
                onCloseSignUp: function () {
                        this.onSignUpGO.close();

                },
                onSignUpLog: async function () {
                        var signreq = this.getView().getModel("signUpModel").getData();
                        var req = await BaseApi.PostloginToken(AppConstant.URL.SignUp, signreq);
                        if (req) {
                                this.onCloseSignUp();
                                this._openLoginDialog();
                        }
                },
                onSignIn: function () {
                        this.onCloseSignUp();
                        this._openLoginDialog();


                }

        })
})