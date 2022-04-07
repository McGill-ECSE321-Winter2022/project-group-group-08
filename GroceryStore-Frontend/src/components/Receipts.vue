<template>
  <div
    class="d-flex align-items-center justify-content-center"
    style="height: 800px"
    id="Receipts"
  >
    <table>
      <div v-if="role == 'customer' || role == 'employee'">
        <h4>My Receipts</h4>
        <!-- Customer Receipt Data Table -->
        <v-data-table class="elevation-1">
          <th class="table-text">| Receipt Num |</th>
          <th class="table-text">Receipt Status |</th>
          <th class="table-text">Receipt Type |</th>

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
        <h4>All Receipts</h4>
        <label>Filter by: </label>
        <select name="filter" id="filter" @change="filterToggle($event)">
          <option value="blank" key="blank"></option>
          <option value="Type" key="Type">Type</option>
          <option value="Status" key="Status">Status</option>
          <!-- Search Filter Components -->
        </select>
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
<script src="./receipts.js"></script>
<style scoped>
.input {
  width: 400px;
}
td {
  width: 160px;
}
</style>
