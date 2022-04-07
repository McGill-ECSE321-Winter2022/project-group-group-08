<template>
  <div
    class="d-flex align-items-center justify-content-center"
    style="height: 100%"
    id="InventoryManagement"
  >
    <table>
      <br>
      <h1>Inventory Management</h1>
      <br>
      <h4>Modify Items Attributes</h4>
      <br>
      <!-- Data Table for Items -->
      <v-data-table class="elevation-1">
        <th>Item ID</th>
        <th>Name</th>
        <th>Image</th>
        <th>Price</th>
        <th>Point</th>
        <th>Return Policy</th>
        <th>Pickup</th>
        <th>In Store Quantity</th>
        <th>Update Item</th>
        <th>Delete Item</th>
        <tbody>
          <tr v-for="item in items" :key="item.id">
            <td class="table-text">{{ item.id }}</td>
            <td>
              <input
                style="margin-top: 6px"
                type="text"
                v-model="itemName[item.id]"
                :placeholder="[[item.name]]"
              />
            </td>
            <td>
              <input
                style="margin-top: 6px"
                type="text"
                v-model="itemImage[item.id]"
                :placeholder="[[item.image]]"
              />
            </td>
            <td>
              <input
                style="margin-top: 6px"
                type="text"
                v-model="price[item.id]"
                :placeholder="[[item.price]]"
              />
            </td>
            <td>
              <input
                style="margin-top: 6px"
                type="text"
                v-model="point[item.id]"
                :placeholder="[[item.point]]"
              />
            </td>
            <td>
              <input
                style="margin-top: 6px"
                type="text"
                v-model="returnPolicy[item.id]"
                :placeholder="[[item.returnPolicy]]"
              />
            </td>
            <td>
              <input type="checkbox" id="pickup" v-model="item.pickup" />
            </td>
            <td>
              <input
                style="margin-top: 6px"
                type="text"
                v-model="inStoreQuantity[item.id]"
                :placeholder="[[item.inStoreQuantity]]"
              />
            </td>
            <td>
              <button
                class="btn btn-light"
                @click="
                  updateItemAttributes(
                    item.id,
                    itemName[item.id],
                    itemImage[item.id],
                    price[item.id],
                    point[item.id],
                    returnPolicy[item.id],
                    item.pickup,
                    inStoreQuantity[item.id],
                    item.name,
                    item.image,
                    item.price,
                    item.point,
                    item.returnPolicy,
                    item.inStoreQuantity
                  )
                "
              >
                Update
              </button>
            </td>
            <td>
              <button class="btn btn-light" @click="deleteItem(item.id)">
                Delete
              </button>
            </td>
          </tr>
        </tbody>
      </v-data-table>

    <!-- Add Item Component -->
      <div id="addItemToStore">
         <br>
        <h4>Add Item to Inventory</h4>
         <br>
        <v-data-table>
          <tr>
            <td>
              <p>Name:</p>
            </td>
            <td>
              <input
                style="margin-top: 6px"
                type="text"
                v-model="newItemName"
                placeholder="Name"
              />
            </td>
          </tr>
          <tr>
            <td>
              <p>Image:</p>
            </td>
            <td>
              <input
                style="margin-top: 6px"
                type="text"
                v-model="newItemImage"
                placeholder="Image"
              />
            </td>
          </tr>
          <tr>
            <td>
              <p>Price:</p>
            </td>
            <td>
              <input
                style="margin-top: 6px"
                type="text"
                v-model="itemPrice"
                placeholder="Price"
              />
            </td>
          </tr>

          <tr>
            <td>
              <p>Point:</p>
            </td>
            <td>
              <input
                style="margin-top: 6px"
                type="text"
                v-model="itemPoint"
                placeholder="Point"
              />
            </td>
          </tr>

          <tr>
            <td>
              <p>Return Policy:</p>
            </td>
            <td>
              <input
                style="margin-top: 6px"
                type="text"
                v-model="itemReturnPolicy"
                placeholder="Return Policy"
              />
            </td>
          </tr>

          <tr>
            <td>
              <p>Available for pickup:</p>
            </td>
            <td>
              <input
                type="checkbox"
                id="pickup"
                v-model="itemPickup"
                placeholder="Yes"
              />
            </td>
          </tr>

          <tr>
            <td>
              <p>Quantity in Store:</p>
            </td>
            <td>
              <input
                style="margin-top: 6px"
                type="text"
                v-model="itemInStoreQuantity"
                placeholder="In Store Quantity"
              />
            </td>
          </tr>
          <tr>
            <button
              class="btn btn-light"
              @click="
                addItemToInventory(
                  newItemName,
                  newItemImage,
                  itemPrice,
                  itemPoint,
                  itemReturnPolicy,
                  itemPickup,
                  itemInStoreQuantity
                )
              "
            >
              Add Item
            </button>
             <br>
              <br>
               <br>
                <br>
          </tr>
        </v-data-table>
      </div>
    </table>
     <br>
      <br>
  </div>
</template>

<style>
</style>
<script src="./inventoryManagement.js">
</script>