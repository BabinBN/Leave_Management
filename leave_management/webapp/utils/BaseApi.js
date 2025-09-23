sap.ui.define([
    "leavemanagement/utils/AppConstant"
], function (AppConstant) {
    var reqtoken=null
    return {
        loginrequest: async function (URL,reqtoken) {
            try {
                const response = await fetch(URL, {
                    method: 'POST',
                    headers: {
                        "Content-Type": "application/json",
                        "Accept": "application/json",
                        "Authorization": "Bearer " + reqtoken
                    },
                    body: JSON.stringify({
                        "CompanyDB": "INFLEXION_P01",
                        "UserName": "USR004",
                        "Password": "Babin@2003"
                    })
                });
                const data = await response.json();
                return data.SessionId;
                console.log("Login Response:", data);
            } catch (err) {
                console.error("Error in login:", err);
                return null;
            }
        },
        PostloginToken: async function (URL, req) {
            try {
                const Resdt = await fetch(URL,
                    {
                        method: 'POST',
                        headers: {
                            "Content-Type": "application/json",
                            "Accept": "application/json"
                        },
                        body: JSON.stringify(req)
                    }

                )
                const response = await Resdt.json();
               reqtoken= response.token;
               return response;
            }
            catch (ex) {
                console.error(ex);
            }
        },

        postrequest: async function (URL, req) {
            try {
                // const token=await this.PostloginToken(AppConstant.URL.SignIn,req)
                const sessionId = await this.loginrequest(AppConstant.URL.LoginRequest,reqtoken);
                if (!sessionId) {
                    console.error("Login failed. Cannot proceed with POST request.");
                    return null;
                }

                const response = await fetch(URL, {
                    method: 'POST',
                    headers: {
                        "Content-Type": "application/json",
                        "Accept": "application/json",
                        "Authorization": "Bearer " + reqtoken
                    },
                    body: JSON.stringify(req)
                });

                const data = await response.json();
                return data;

            } catch (error) {
                console.error(error);
                return null;
            }
        }
    };
});
