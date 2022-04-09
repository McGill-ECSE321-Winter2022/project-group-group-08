import axios from "axios";
var config = require("../../config");
// var frontendUrl = "http://" + config.dev.host + ":" + config.dev.port;
// var backendUrl =
//   "http://" + config.dev.backendHost + ":" + config.dev.backendPort;

var frontendUrl;
var backendUrl;
if (process.env.NODE_ENV === "production") {
    console.log("prod env");
    frontendUrl = "https://" + config.build.host + ":" + config.build.port;
    backendUrl =
        "https://" + config.build.backendHost + ":" + config.build.backendPort;
} else if (process.env.NODE_ENV === "development") {
    console.log("dev env");
    frontendUrl = "http://" + config.dev.host + ":" + config.dev.port;
    backendUrl =
        "http://" + config.dev.backendHost + ":" + config.dev.backendPort;
}

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { "Access-Control-Allow-Origin": frontendUrl }
});
export default {
    name: "hello",
    created: function() {
        // Setup Grocery Store System
        AXIOS.post(
                "grocerystoresystem", {}, {
                    params: {
                        storename: "Whole Foods",
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
        // Create Person
        AXIOS.post(
                "/createPerson/" + "marwan.kanaan@mcgill.ca", {}, {
                    params: {
                        firstName: "Marwan",
                        lastName: "Kanaan",
                        phoneNumber: "9999999999",
                        address: "13 McConnell Engineering Building",
                        image: "https://pbs.twimg.com/profile_images/483697632003371008/YDdxZqsm_400x400.jpeg"
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
                    .then(response => {
                        AXIOS.post(
                                "/cart/", {}, {
                                    params: {
                                        date: "2022-04-07",
                                        accountUsername: "BigBoss"
                                    }
                                }
                            )
                            .then(response2 => {
                                sessionStorage.setItem("cartId", response2.data.id);
                            })
                            .catch(e => {
                                this.errorSignup = e.response.data;
                            });
                    })
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
            // Set Variables
            msg: "Welcome to your local Whole Foods",
            email: "group8@mail.mcgill.ca",
            adress: "123 McGill Avenue",
            number: "514-100-1313",
            errorOpeningsHours: "",
            openingsHours: [],
            validUser: sessionStorage.getItem("validUser")
        };
    },
    methods: {
        /**
         * getAllOpeningsHours
         * Set Opening Hours of Store
         */
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
        /**
         * goLogin
         * Redirect to login page
         */
        goLogin: function() {
            this.$router.push({ path: `/Login` });
        },
        /**
         * goSignUp
         * Redirect to signup page
         */
        goSignUp: function() {
            this.$router.push({ path: `/Signup` });
        }
    }
};