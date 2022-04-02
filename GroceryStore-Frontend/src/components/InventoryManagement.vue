<template> 
 <div class="d-flex align-items-center justify-content-center" style="height: 800px;" id = "InventoryManagement">
<h1> Inventory Management </h1>
  <table>
    <h2> Modify Items Attributes </h2> 
     <tr>
        <th>Item ID </th>
        <th>Name</th>
        <th>Price</th>
        <th>Point</th>
        <th>Return Policy</th>
        <th>Pickup</th>
        <th>In Store Quantity </th>
        <th>Update Item</th>
        <th>Delete Item</th>
      </tr>
   <tr v-for="item in items" :key="item.id">
                <td class="table-text">{{item.id}}</td>
               <input 
                style="margin-top: 6px;"
                type="text"
                v-model="itemName[item.id]"
                :placeholder="[[ item.name ]]"
               />
                <input 
                style="margin-top: 6px;"
                type="text"
                v-model="price[item.id]"
                :placeholder="[[ item.price ]]"
               />
               <input 
                style="margin-top: 6px;"
                type="text"
                v-model="point[item.id]"
                :placeholder="[[ item.point ]]"
               />
                <input 
                style="margin-top: 6px;"
                type="text"
                v-model="returnPolicy[item.id]"
                :placeholder="[[ item.returnPolicy ]]"
               />
                <input type="checkbox" id="pickup" v-model="item.pickup"/> 
                <input 
                style="margin-top: 6px;"
                type="text"
                v-model="inStoreQuantity[item.id]"
                :placeholder="[[ item.inStoreQuantity ]]"
               />                                                                                                                               
              <button
                class="btn btn-light"
                @click="updateItemAttributes(item.id, name[item.id], price[item.id], point[item.id], returnPolicy[item.id], item.pickup, inStoreQuantity[item.id]);"
                 >
                Update
                 </button>
                <button
                class="btn btn-light"
                @click="deleteItem();"
                 >
                Delete
                 </button>
                 
            </tr>  

    </table>


    <div id = "addItemToStore"> 
    <h2> Add Item to Inventory </h2>
    <p> Enter the id of the item you wish to add to the inventory: </p>
    <input
        style="margin-top: 6px;"
        type="text"
        v-model="itemId"
        placeholder="ID"
    />
    <p> Enter the name of the item you wish to add to the inventory: </p>
    <input
        style="margin-top: 6px;"
        type="text"
        v-model="itemName2"
        placeholder="Name"
    />
    <p> Enter the price of the item you wish to add to the inventory: </p>
    <input
        style="margin-top: 6px;"
        type="text"
        v-model="itemPrice"
        placeholder="Price"
    />
     <p> Enter the point of the item you wish to add to the inventory: </p>
    <input
        style="margin-top: 6px;"
        type="text"
        v-model="itemPoint"
        placeholder="Point"
    />
     <p> Enter the return policy of the item you wish to add to the inventory: </p>
    <input
        style="margin-top: 6px;"
        type="text"
        v-model="itemReturnPolicy"
        placeholder="Return Policy"
    />
    <p> Choose the pickup status of the item you wish to add to the inventory: </p>
     <input type="checkbox" id="pickup" v-model="itemPickup" placeholder= "Yes" /> 
     <p> Enter the in store quantity of the item you wish to add to the inventory: </p>
    <input
        style="margin-top: 6px;"
        type="text"
        v-model="itemInStoreQuantity"
        placeholder="In Store Quantity"
    />
    <button
        class="btn btn-light"
        @click="addItemToInventory(itemId, itemName, itemPrice, itemPoint, itemReturnPolicy, itemPickup, itemInStoreQuantity);"
    >
        Add Item to Store
    </button>

    </div>

 </div>

</template>

<style> </style>
<script src="./inventoryManagement.js"> </script>