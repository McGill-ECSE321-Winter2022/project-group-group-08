import axios from "axios";
var config = require("../../config");
var frontendUrl = "http://" + config.dev.host + ":" + config.dev.port;
var backendUrl =
    "http://" + config.dev.backendHost + ":" + config.dev.backendPort;
var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { "Access-Control-Allow-Origin": frontendUrl }
});
export default {
    name: "hello",
    created: function() {
        AXIOS.post(
                "grocerystoresystem", {}, {
                    params: {
                        storename: "My Local Shop",
                        address: "123 McGill Avenue",
                        employeeDiscount: "25"
                    }
                }
            )
            .then(response => {
                AXIOS.get("/getOpeningHours/" + response.data.storeName, {}, {})
                    .then(response => {
                        this.openingsHours = response.data;
                    })
                    .catch(e => {});
            })
            .catch(e => {});
        AXIOS.post(
                "/createPerson/" + "marwan.kanaan@mcgill.ca", {}, {
                    params: {
                        firstName: "Marwan",
                        lastName: "Kanaan",
                        phoneNumber: "9999999999",
                        address: "13 McConnell Engineering Building"
                    }
                }
            )
            .then(response => {
                AXIOS.post(
                        "/createAccount/" + "BigBoss", {}, {
                            params: {
                                password: "I am the best",
                                inTown: true,
                                totalPoints: 0,
                                personEmail: "marwan.kanaan@mcgill.ca"
                            }
                        }
                    )
                    .then(response => {})
                    .catch(e => {});

                AXIOS.post(
                        "/manager", {}, {
                            params: {
                                personEmail: "marwan.kanaan@mcgill.ca"
                            }
                        }
                    )
                    .then(response => {})
                    .catch(e => {});
            })
            .catch(e => {});
    },

    data() {
        return {
            msg: "Welcome to Your Local Grocery Store",
            email: "Email: group8@mail.mcgill.ca",
            adress: "Address: 123 McGill Avenue",
            number: "Call: 514-100-1313",
            errorOpeningsHours: "",
            openingsHours: []
        };
    },
    methods: {
        getAllOpeningsHours: function() {
            AXIOS.get("/getAllOpeningsHours")
                .then(response => {
                    // JSON responses are automatically parsed.
                    this.openingsHours = response.data;
                })
                .catch(e => {
                    this.errorOpeningsHours = e;
                });
        },
        goLogin: function() {
            this.$router.push({ path: `/Login` });
        },
        goSignUp: function() {
            this.$router.push({ path: `/Signup` });
        }
    }
};