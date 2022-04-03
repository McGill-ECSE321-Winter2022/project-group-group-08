
<template>
  <div
    class="d-flex align-items-center justify-content-center"
    style="height: 500px"
    id="viewcustomers"
  >
    <table>
      <h2>View Customers</h2>
      <tr>
        <!-- !!! Implement if have time -->
        <!-- Search Filter Component -->
        <!-- <select name="filter" id="filter" @change="filterToggle($event)">
          <option value="blank" key="blank"></option>
          <option value="CustomerID" key="customerID">Customer ID</option>
          <option value="Name" key="name">Name</option>
          <option value="Email" key="email">Email</option>
        </select> -->
      </tr>

      <tr v-if="filter == 'CustomerID'">
        <input
          style="margin-top: 6px"
          type="text"
          v-model="customerID"
          placeholder="Customer ID"
        />
        <button
          size="sm"
          class="filter button"
          @click="getCustomerByID(customerID)"
        >
          Search
        </button>
      </tr>

      <tr v-if="filter == 'Name'">
        <input
          style="margin-top: 6px"
          type="text"
          v-model="firstName"
          placeholder="First Name"
        />
        <input
          style="margin-top: 6px"
          type="text"
          v-model="lastName"
          placeholder="Last Name"
        />
        <button
          class="filter button"
          @click="getCustomerByName(firstName, lastName)"
        >
          Search
        </button>
      </tr>

      <tr v-if="filter == 'Email'">
        <input
          style="margin-top: 6px"
          type="text"
          v-model="email"
          placeholder="Email"
        />
        <button class="filter button" @click="getCustomerByEmail(email)">
          Search
        </button>
      </tr>
      
      <!-- Data Table Component -->
      <v-data-table class="elevation-1">
        <th>Customer ID</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Customer ID</th>
        <th>Tier</th>
        <th>Ban Status</th>
        <tbody>
          <tr v-for="customer in customers" :key="customer.id">
            <td class="table-text">{{ customer.id }}</td>
            <td class="table-text">{{ customer.person.firstName }}</td>
            <td class="table-text">{{ customer.person.lastName }}</td>
            <td class="table-text">{{ customer.person.email }}</td>
            <td>
              <select name="newTier" id="newTier" v-model="customer.tierClass">
                  <option value="Bronze" key="Bronze">Bronze</option>
                  <option value="Silver" key="Silver">Silver</option>
                  <option value="Gold" key="Gold">Gold</option>
              </select>
            </td>
            <td><input type="checkbox" id="banned" v-model="customer.ban" /></td>
            <td>
            <button
              class="ban button"
              @click="banCustomer(customer.id,customer.tierClass,customer.ban,customer.person.email,newTier)"
            >
              Update
            </button>
            </td>
          </tr>
        </tbody>
      </v-data-table>
    </table>
  </div>
</template>

<script src="./viewcustomers.js"></script>

<style scoped>
.input {
  width: 400px;
}
</style>
