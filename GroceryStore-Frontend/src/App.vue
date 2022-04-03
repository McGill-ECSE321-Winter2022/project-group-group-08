<script>
export default {
  name: "app",
  data() {
    return {
        customer: sessionStorage.getItem('role') == "customer",
        employee: sessionStorage.getItem('role') == "employee",
        manager: sessionStorage.getItem('role') == "manager",
        username: sessionStorage.getItem('username')
    };
  },
  methods: {
    logout: function() {
      sessionStorage.clear();
      this.customer = false;
      this.employee = false;
      this.manager = false;
    },
  }
};

  
</script>

<template>
  <div id="app">
    <div id="nav">
      <b-navbar toggleable="lg" type="dark" variant="dark">
        <b-navbar-brand href="#/">Whole Foods</b-navbar-brand>

        <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

        <b-collapse id="nav-collapse" is-nav>
          <b-navbar-nav>
            <b-nav-item href="#/browse">Browse</b-nav-item>
            <b-nav-item href="#/shifts" v-if="manager">Shifts</b-nav-item>
            <b-nav-item href="#/viewshifts" v-if="employee">My Shifts</b-nav-item>
            <b-nav-item href="#/itemInventory" v-if="manager">Items</b-nav-item>
            <b-nav-item href="#/viewemployees" v-if="manager">Employees</b-nav-item>
            <b-nav-item href="#/viewcustomers" v-if="manager">Customers</b-nav-item>
            <b-nav-item href="#/cart" v-if="customer || employee">Cart</b-nav-item>
            <b-nav-item href="#/Receipts" v-if="customer || employee || manager">Orders</b-nav-item>
          </b-navbar-nav>

          <!-- Right aligned nav items -->
          <b-navbar-nav class="ml-auto">
            <b-nav-item-dropdown right>
              <!-- Using 'button-content' slot -->
              <template #button-content>
                <em>User</em>
              </template>
              <b-dropdown-item href="#/Login" v-if="!customer && !employee && !manager"> Log in</b-dropdown-item>
              <b-dropdown-item :href="'#/Profile/'+username" v-if="customer || employee || manager">Profile</b-dropdown-item>
              <b-dropdown-item href="#/storeManagement" v-if="manager">Store</b-dropdown-item>
              <b-dropdown-item href="#/" @click="logout" v-if="customer || employee || manager">Sign Out</b-dropdown-item>
            </b-nav-item-dropdown>
          </b-navbar-nav>
        </b-collapse>
      </b-navbar>
    </div>
    <router-view></router-view>
  </div>
</template>


<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  /* margin-top: 60px; */
}

.navbar.navbar-dark.bg-dark{
    background-color: #00674B!important;
 }
</style>
