<template> 
 <div class="d-flex align-items-center justify-content-center" style="height: 500px;" id = "InventoryManagement">
  <h1> Inventory Management </h1>
 <table>
      <h2>Change Items Attributes</h2>
      <tr>
          <select name="filter" id="filter">
            <option value="Name" key="Name">Name</option>
            <option value="Price" key="Price">Price</option>
            <option value="Point" key="Point">Point</option>
            <option value="ReturnPolicy" key="ReturnPolicy">Return Policy</option>
            <option value="Pickup" key="Pickup">Pickup</option>
             <option value="InStoreQuantity" key="InStoreQuantity">In Store Quantity</option>
          </select>
        <tr v-if="filter=='Name'">
            <input
                style="margin-top: 6px;"
                type="text"
                v-model="name"
                placeholder="Name"
            />
            <button
                class="btn btn-light"
                @click="updateItemName(name);"
            >
                Update Item Name
            </button>
        </tr>
        <tr v-if="filter=='Price'">
            <input
                style="margin-top: 6px;"
                type="text"
                v-model="price"
                placeholder="Price"
            />
            <button
                class="btn btn-light"
                @click="updateItemPrice(price);"
            >
                Update Item Price
            </button>
        </tr>
        <tr v-if="filter=='Point'">
            <input
                style="margin-top: 6px;"
                type="text"
                v-model="point"
                placeholder="Point"
            />
            <button
                class="btn btn-light"
                @click="updateItemPoint(point);"
            >
                Update Item Point
            </button>
        </tr>
        <tr v-if="filter=='ReturnPolicy'">
            <input
                style="margin-top: 6px;"
                type="text"
                v-model="returnPolicy"
                placeholder="Return Policy"
            />
            <button
                class="btn btn-light"
                @click="updateItemReturnPolicy(returnPolicy);"
            >
                Update Item Return Policy
            </button>
        </tr>
        <tr v-if="filter=='Pickup'">
            <select name="filter" id="filter" @change="getItemPickupStatus($event)">
                <option value="Yes" key="Yes">Yes</option>
                <option value="No" key="No">No</option>
            </select>
            <button
                class="btn btn-light"
                @click="updateItemPickupStatus();"
            >
                Update Item Pickup Status
            </button>
        </tr>
        <tr v-if="filter=='InStoreQuantity'">
            <input
                style="margin-top: 6px;"
                type="text"
                v-model="inStoreQuantity"
                placeholder="In Store Quantity"
            />
            <button
                class="btn btn-light"
                @click="updateItemInStoreQuantity(returnPolicy);"
            >
                Update Item Return Policy
            </button>
        </tr>

</table>
    <div id = "addItemToStore"> 
    <h2> Add Item to Inventory </h2>

    <p> Enter the name of the item to add to the inventory: </p>
    <input
        style="margin-top: 6px;"
        type="text"
        v-model="itemName"
        placeholder="Item Name"
    />
    <button
        class="btn btn-light"
        @click="addItemToInventory(itemName);"
    >
        Add Item to Store
    </button>

     <p> Enter the name of the item to remove: </p>
    <input
        style="margin-top: 6px;"
        type="text"
        v-model="itemToRemove"
        placeholder="Item to Remove"
    />
    <button
        class="btn btn-light"
        @click="removeItem(itemToRemove);"
    >
        Remove Item 
    </button>

    </div>

 </div>

</template>

<style> </style>
<script> </script>