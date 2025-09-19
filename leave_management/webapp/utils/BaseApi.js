sap.ui.define([
    "leavemanagement/utils/AppConstant"
], function (AppConstant) {
    return {
        loginrequest: async function (URL) {
            try {
                let token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJiYWJpbkBnbWFpbC5jb20iLCJpYXQiOjE3NTgyODY2NDUsImV4cCI6MTc1ODI5MDI0NX0.x14j7tNBuaXItHrM-gx8yJW1E7ggYeUviv4Zodl9cXA"
                const response = await fetch(URL, {
                    method: 'POST',
                    headers: {
                        "Content-Type": "application/json",
                        "Accept": "application/json",
                        "Authorization": "Bearer " + token
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

        postrequest: async function (URL, req) {
            try {
                const sessionId = await this.loginrequest(AppConstant.URL.LoginRequest);
                if (!sessionId) {
                    console.error("Login failed. Cannot proceed with POST request.");
                    return null;
                }
                let token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJiYWJpbkBnbWFpbC5jb20iLCJpYXQiOjE3NTgyODY2NDUsImV4cCI6MTc1ODI5MDI0NX0.x14j7tNBuaXItHrM-gx8yJW1E7ggYeUviv4Zodl9cXA"
                const response = await fetch(URL, {
                    method: 'POST',
                    headers: {
                        "Content-Type": "application/json",
                        "Accept": "application/json",
                        "Authorization": "Bearer " + token
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
