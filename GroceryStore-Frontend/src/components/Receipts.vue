
<template>
  <div
    class="d-flex align-items-center justify-content-center"
    style="height: 800px;"
    id="Receipts"
  >
    <table>
        <div v-if="role=='customer' || role=='employee'">
            <h4>My Receipts</h4>
            <v-data-table  class="elevation-1" >
                <!-- <tr>
                        <td class="table-text">| Employee Name |</td>
                        <td class="table-text">| Day |</td>
                        <td class="table-text">| Start Time |</td>
                        <td class="table-text">| End Time |</td>
                </tr> -->
                <tr>
                    <td class="table-text">| Receipt Num | </td>
                    <td class="table-text">Receipt Status | </td>
                    <td class="table-text">Receipt Type | </td>
                </tr>

                <tr v-for="receipt in myReceipts" :key="receipt.num" >
                    <td class="table-text">{{receipt.receiptNum}}</td>
                    <td class="table-text">{{receipt.receiptStatus}}</td>
                    <td class="table-text">{{receipt.receiptType}}</td>
                </tr>
            </v-data-table>
        </div>

        <div v-if="role=='manager' || role=='employee'">
            <h4>All Receipts</h4>
            <select name="filter" id="filter" @change="filterToggle($event)">
                <option value="blank" key="blank"></option>
                <!-- <option value="Name" key="Name">Name</option>
                <option value="Username" key="Username">Username</option> -->
                <option value="Type" key="Type">Type</option>
                <option value="Status" key="Status">Status</option>
            </select>
            <tr v-if="filter=='Name'">
                <input
                    style="margin-top: 6px;"
                    type="text"
                    v-model="firstName"
                    placeholder="First Name"
                />
                <input
                    style="margin-top: 6px;"
                    type="text"
                    v-model="lastName"
                    placeholder="Last Name"
                />
                <button
                    class="btn btn-light"
                    @click="getEmployees(firstName, lastName);"
                >
                Search
                </button>
            </tr>
            <tr v-if="filter=='Username'">
                <input
                    style="margin-top: 6px;"
                    type="text"
                    v-model="username"
                    placeholder="Username"
                />
                <button
                    class="btn btn-light"
                    @click="getAccountByUsername(username);"
                >
                Search
                </button>
            </tr>
            <tr v-if="filter=='Type'" @change="getReceiptByType($event)">
                <select name="type" id="type">
                    <option value="Pickup" key="Pickup">Pickup</option>
                    <option value="Delivery" key="Delivery">Delivery</option>
                </select>
            </tr>
            <tr v-if="filter=='Status'">
                <select name="status" id="status" @change="getReceiptByStatus($event)">
                    <option value="Processed" key="Processed">Processed</option>
                    <option value="Transit" key="Transit">Transit</option>
                    <option value="Fulfilled" key="Fulfilled">Fulfilled</option>
                </select>
            </tr>
            
            <div  v-if="showReceipts">
                <v-data-table  class="elevation-1" >
                    <tr>
                        <td class="table-text">| Account Username | </td>
                        <td class="table-text">Receipt Num | </td>
                        <td class="table-text">Old Receipt Status | </td>
                        <td class="table-text">New Receipt Status | </td>
                        <td class="table-text">Receipt Type | </td>
                        <td class="table-text"/>
                    </tr>

                    <tr v-for="receipt in receipts" :key="receipt.receiptNum" >
                        <td class="table-text">{{receipt.cartDto.account.username}}</td>
                        <td class="table-text">{{receipt.receiptNum}}</td>
                        <!-- <td class="table-text">{{receipt.receiptStatus}}</td> -->
                        <td class="table-text">{{receipt.receiptStatus}}</td>
                        <select name="status" id="status" v-model="receiptStatus[receipt.receiptNum]">
                            <!-- <option v-for="s in statusOptions" :value="s.value" v-bind="">{{s.label}}</option> -->
                            <option value="Processed" key="Processed">Processed</option>
                            <option value="Transit" key="Transit">Transit</option>
                            <option value="Fulfilled" key="Fulfilled">Fulfilled</option>
                        </select>
                        <td class="table-text">{{receipt.receiptType}}</td>
                        <button
                            class="btn btn-light"
                            @click="updateReceipt(receipt.receiptNum, receipt.cartDto.id, receiptStatus[receipt.receiptNum], receipt.receiptType)"
                        >
                            Update
                        </button>
                    </tr>
                </v-data-table>
            </div>
        </div>

    </table>
  </div>
  
</template>
<script src="./receipts.js"></script>
<style scoped>
.input{
      width: 400px;
}
</style>
