<script>
import axios, { Axios } from "axios";
var config = require("../../config");

var frontendUrl = "http://" + config.dev.host + ":" + config.dev.port;
var backendUrl = "" + config.build.backendHost;

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { "Access-Control-Allow-Origin": frontendUrl }
});

export default {
    name: "receipts",

    created: function() {
        // Initializing receipts from backend
        AXIOS.get("/receipt/all")
            .then(response => {
                this.receipts = response.data;
                for (receipt in receipts) {
                    receiptStatus[receipt.receiptNum] = receipt.receiptStatus;
                }
            })
            .catch(e => {
                this.errorReceipts = e;
            });
        AXIOS.get("/receipt/getWithCart", {
                params: {
                    cartId: sessionStorage.getItem("cartId")
                }
            })
            .then(response => {
                this.myReceipts = response.data;
            })
            .catch(e => {
                this.errorReceipts = e;
            });
    },
    data() {
        return {
            // Set Variables
            myReceipts: [],
            receipts: [],
            receiptStatus: [],
            errorReceipts: "",
            filter: "",
            showReceipts: true,
            role: sessionStorage.getItem("role")
        };
    },

    methods: {
        /**
         * updateReceipt
         * Update receipt information
         * @param {*} receiptNum
         * @param {*} cartId
         * @param {*} receiptStatus
         * @param {*} receiptType
         */
        updateReceipt: function(receiptNum, cartId, receiptStatus, receiptType) {
            AXIOS.patch(
                    "/receipt/update/" + receiptNum, {}, {
                        params: {
                            cartId: cartId,
                            receiptStatus: receiptStatus,
                            receiptType: receiptType
                        }
                    }
                )
                .then(response => {
                    AXIOS.get("/receipt/all")
                        .then(response2 => {
                            this.receipts = response2.data;
                            for (receipt in receipts) {
                                receiptStatus[receipt.receiptNum] = receipt.receiptStatus;
                            }
                        })
                        .catch(e => {
                            this.errorReceipts = e;
                        });
                })
                .catch(error => {
                    var errorMsg = error;
                    if (error.response) {
                        errorMsg = error.response.data;
                    }
                });
        },
        /**
         * getReceiptByCart
         * Takes a cart ID and returns the corresponding receipt
         * @param {*} cartId
         */
        getReceiptByCart: function(cartId) {
            AXIOS.patch(
                    "/receipt/getReceiptsByCart/", {}, {
                        params: {
                            cartId: cartId
                        }
                    }
                )
                .then(response => {
                    this.receipts = response.data;
                    for (receipt in receipts) {
                        receiptStatus[receipt.receiptNum] = receipt.receiptStatus;
                    }
                })
                .catch(error => {
                    var errorMsg = error;
                    if (error.response) {
                        errorMsg = error.response.data;
                    }
                });
        },
        /**
         * getReceiptByStatus
         * Given a receipt status, returns the corresponding receipts
         * @param {*} event
         */
        getReceiptByStatus: function(event) {
            AXIOS.get("/receipt/getWithStatus/", {
                    params: {
                        status: document.getElementById("status").value
                    }
                })
                .then(response => {
                    this.receipts = response.data;
                    for (receipt in receipts) {
                        receiptStatus[receipt.receiptNum] = receipt.receiptStatus;
                    }
                })
                .catch(error => {
                    var errorMsg = error;
                    if (error.response) {
                        errorMsg = error.response.data;
                    }
                });
        },
        /**
         * getReceiptByType
         * Given a receipt type, returns the corresponding receipts
         * @param {*} event
         */
        getReceiptByType: function(event) {
            AXIOS.get("/receipt/getWithType/", {
                    params: {
                        type: event.target.value
                    }
                })
                .then(response => {
                    this.receipts = response.data;
                    for (receipt in receipts) {
                        receiptStatus[receipt.receiptNum] = receipt.receiptStatus;
                    }
                })
                .catch(error => {
                    var errorMsg = error;
                    if (error.response) {
                        errorMsg = error.response.data;
                    }
                });
        },
        /**
         * getAccountByUsername
         * Takes a username and returns the corresponding account
         * @param {*} username
         */
        getAccountByUsername: function(username) {
            AXIOS.get("/getAccountByUsername/" + username, {})
                .then(response => {
                    AXIOS.get(
                            "/receipt/getReceiptsByUsername/", {}, {
                                params: {
                                    username: response.data.username
                                }
                            }
                        )
                        .then(response => {
                            this.receipts = response.data;
                            for (receipt in receipts) {
                                receiptStatus[receipt.receiptNum] = receipt.receiptStatus;
                            }
                        })
                        .catch(error => {
                            var errorMsg = error;
                            if (error.response) {
                                errorMsg = error.response.data;
                            }
                        });
                })
                .catch(error => {
                    var errorMsg = error;
                    if (error.response) {
                        errorMsg = error.response.data;
                    }
                });
        },
        /**
         * filterToggle
         * Resets the pages data when the filter button is used
         * @param {*} event
         */
        filterToggle(event) {
            this.filter = event.target.value;
            if (this.filter == "blank") {
                this.showReceipts = true;
                AXIOS.get("/receipt/all")
                    .then(response2 => {
                        this.receipts = response2.data;
                        for (receipt in receipts) {
                            receiptStatus[receipt.receiptNum] = receipt.receiptStatus;
                        }
                    })
                    .catch(e => {
                        this.errorReceipts = e;
                    });
            }
            if (this.filter == "Name") {
                this.showReceipts = false;
            } else {
                this.showReceipts = true;
            }
        }
    }
};
</script>

<template>
  <div
    class="d-flex align-items-center justify-content-center"
    style="height: 800px"
    id="Receipts"
  >
    <table>
      <div v-if="role == 'customer' || role == 'employee'">
        <h2>My Receipts</h2>
        <!-- Customer Receipt Data Table -->
        <v-data-table class="elevation-1">
          <th class="table-text">Receipt Num</th>
          <th class="table-text">Receipt Status</th>
          <th class="table-text">Receipt Type</th>

          <tbody>
            <tr v-for="receipt in myReceipts" :key="receipt.num">
              <td class="table-text">{{ receipt.receiptNum }}</td>
              <td class="table-text">{{ receipt.receiptStatus }}</td>
              <td class="table-text">{{ receipt.receiptType }}</td>
            </tr>
          </tbody>
        </v-data-table>
      </div>

      <!-- View ALL Receipts Component -->
      <div v-if="role == 'manager' || role == 'employee'">
        <h2>All Receipts</h2>
        <label>Filter by: </label>
        
        <select name="filter" id="filter" @change="filterToggle($event)">
          <option value="blank" key="blank"></option>
          <option value="Type" key="Type">Type</option>
          <option value="Status" key="Status">Status</option>
          <!-- Search Filter Components -->
        </select>
        <br>
        <br>
        <div align="center">
          <tr v-if="filter == 'Type'" @change="getReceiptByType($event)">
            <select name="type" id="type">
              <option value="blank" key="blank"></option>
              <option value="Pickup" key="Pickup">Pickup</option>
              <option value="Delivery" key="Delivery">Delivery</option>
            </select>
          </tr>
          <tr v-if="filter == 'Status'">
            <select
              name="status"
              id="status"
              @change="getReceiptByStatus($event)"
            >
              <option value="blank" key="blank"></option>
              <option value="Processed" key="Processed">Processed</option>
              <option value="Transit" key="Transit">Transit</option>
              <option value="Fullfilled" key="Fullfilled">Fullfilled</option>
            </select>
          </tr>
        </div>

        <!-- Display ALL Receipts Data Table -->
        <div v-if="showReceipts">
          <v-data-table class="elevation-1">
            <th class="table-text">Account Username </th>
            <th class="table-text">Receipt Num</th>
            <th class="table-text">Old Receipt Status</th>
            <th class="table-text">New Receipt Status</th>
            <th class="table-text">Receipt Type</th>

            <tbody>
              <tr v-for="receipt in receipts" :key="receipt.receiptNum">
                <td class="table-text">
                  {{ receipt.cartDto.account.username }}
                </td>
                <td class="table-text">{{ receipt.receiptNum }}</td>
                <td class="table-text">{{ receipt.receiptStatus }}</td>
                <select
                  name="status"
                  id="status"
                  v-model="receiptStatus[receipt.receiptNum]"
                >
                  <option value="Processed" key="Processed">Processed</option>
                  <option value="Transit" key="Transit">Transit</option>
                  <option value="Fullfilled" key="Fullfilled"
                    >Fullfilled</option
                  >
                </select>
                <td class="table-text">{{ receipt.receiptType }}</td>
                <button
                  class="btn btn-light"
                  @click="
                    updateReceipt(
                      receipt.receiptNum,
                      receipt.cartDto.id,
                      receiptStatus[receipt.receiptNum],
                      receipt.receiptType
                    )
                  "
                >
                  Update
                </button>
              </tr>
            </tbody>
          </v-data-table>
        </div>
      </div>
    </table>
  </div>
</template>
<style scoped>
.input {
  width: 400px;
}
td {
  width: 160px;
}
</style>
