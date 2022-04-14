<script>
import axios from "axios";
var config = require("../../config");

var frontendUrl = "http://" + config.dev.host + ":" + config.dev.port;
var backendUrl = "" + config.build.backendHost;

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { "Access-Control-Allow-Origin": frontendUrl }
});

export default {
  data() {
    return {
      // Set Variables
      items: [],
      cart: null,
      items: null,
      quant2: [],
      quantityNum: null
    };
  },

  created() {
    var hard = sessionStorage.getItem("username");

    // Initialize items from backend
    AXIOS.get("item/all/")
      .then(response => {
        this.items = response.data;
      })
      .catch(e => {});

    // Get Person
    AXIOS.get("cart/getWithUsername", {
      params: {
        username: hard
      }
    })
      .then(response => {
        this.cart = response.data;
        sessionStorage.setItem("cartId", response.data.id);
      })
      .catch(e => {
        AXIOS.post("cart", {
          params: {
            date: Date()
              .toISOString()
              .slice(0, 10),
            accountUsername: hard
          }
        })
          .then(response => {
            this.cart = response.data;
            sessionStorage.setItem("cartId", response.data.id);
          })
          .catch(e => {});
      });
  },

  props: {
	  // Set Props
    curId: Number,
    itemImage: String,
    itemName: String,
    itemPrice: Number,
	role: String,
	itemPoints: Number,
  },

  methods: {
	/**
	 * addToCartZ
	 * Add item to user cart
	 * @param {*} input 
	 */
    addToCartZ: function(input) {
      AXIOS.get("quantity/cartId/" + sessionStorage.getItem("cartId"))
        .then(response => {
          this.quant2 = response.data;
          var found = false;
          for (let i = 0; i < this.quant2.length; i++) {
            if (this.quant2[i].item.id == this.curId) {
              this.items = this.quant2[i].item;
              this.quantityNum = this.quant2[i].count;

              this.quantityNum = this.quantityNum + 1;
              AXIOS.patch(
                "/quantity/update/" + this.quant2[i].id,
                {},
                {
                  params: {
                    count: this.quantityNum,
                    itemId: input,
                    cartId: this.cart.id
                  }
                }
              )
                .then(response => {})
                .catch(e => {});
              found = true;
              break;
            }
          }

          if (!found) {
            AXIOS.post(
              "/quantity/",
              {},
              {
                params: {
                  count: 1,
                  itemId: input,
                  cartId: sessionStorage.getItem("cartId")
                }
              }
            )
              .then(response => {})
              .catch(e => {});
          }
        })
        .catch(e => {});
    }
  }
};

</script>
<template>
  <div class="grid-item">
    <div class="container">
      <div class="card asics">
        <div class="wrapper">
          <!-- View Item -->
          <p class="price">Price: ${{ itemPrice }}</p>
		  <!-- Add to Cart Button-->
          <button
            v-if="role == 'employee' || role == 'customer'"
            class="btn btn-light"
            @click="addToCartZ(curId)"
          >
            Add to cart
          </button>
        </div>


		<!-- Item Image -->
        <i class="info fas fa-info-circle"></i>
        <div class="imgBx">
          <img :src="itemImage" style="max-width: 70%;" />
        </div>
        <div class="contentBx">
          <h3>organic</h3>
          <h2>{{ itemName }}</h2>
          <div class="tzt"><p>WRewards: {{ itemPoints }}</p></div>
        </div>
      </div>
    </div>
  </div>
</template>
<style scoped>
* {
  margin: 0;
  padding: 0;
  font-family: Quicksand;
}
.tzt {
  color: #464646;
}

h3 {
  color: #351500;
  font-size: 1.3em;
}
.wrapper {
  display: grid;
  grid-template-columns: 50% 50%;
  background-color: #fff;
}
.price {
  top: 1em;
  right: 1em;
  color: rgb(56, 56, 56);
  background-color: rgb(255, 255, 255);
  border: 0px rgba(255, 255, 255, 0.4) solid;
  border-bottom: 0px rgba(40, 40, 40, 0.35) solid;
  border-right: 0px rgba(40, 40, 40, 0.35) solid;
  padding: 5px 10px;
  font-size: 1.3em;
  font-weight: 800;
}

.container {
  padding: 20px 20px;
}
.container .card {
  width: 280px;
  height: 350px;
  border-radius: 20px;
  background: linear-gradient(315deg, #9dbb9e, #5ba06a);
  overflow: hidden;
}
.container .card:before {
  content: " ";
  left: 0;
  width: 100%;
  height: 100%;
  background: #a4c78d;
  clip-path: circle(150px at 80% 20%);
  transition: 0.5s ease-in-out;
}
.container .card:hover:before {
  clip-path: circle(300px at 80% -20%);
}
.container .card:after {
  content: "";
  position: absolute;
  font-size: 12em;
  font-weight: 800;
  font-style: italic;
  color: rgb(255, 0, 0);
}

.container .card .imgBx {
  position: absolute;
  top: 40%;
  transform: translateY(-50%);
  z-index: 200;
  width: 100%;
  height: 220px;
  transition: 0.5s ease-in-out;
}
.container .card .imgBx:hover  {
   color: white;
  top: 35%;
}
.container .card .imgBx img {
  position: absolute;
  top: 40%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 270px;
}
.container .card .contentBx {
  position: absolute;
  bottom: 10%;
  width: 100%;
  height: 100px;
  text-align: center;
  transition: 1s;
  z-index: 10;
}
.container .card:hover .contentBx {
  height: 35%;
  color: white;
}
.container .card .contentBx h2 {
  position: relative;
  font-weight: 600;
  letter-spacing: 1px;
  color: #000000;
}
/* Shoe Size */
.container .card .contentBx .size,
.container .card .contentBx .color {
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 8px 20px;
  transition: 0.5s;
  opacity: 0;
  visibility: hidden;
}

.container .card .contentBx .size h3,
.container .card .contentBx .color h3 {
  color: #fff;
  font-weight: 300;
  font-size: 14px;
  text-transform: uppercase;
  letter-spacing: 2px;
  margin-right: 10px;
}
.container .card .contentBx .size span {
  width: 26px;
  height: 26px;
  text-align: center;
  line-height: 26px;
  font-size: 14px;
  display: inline-block;
  color: #111;
  background: #fff;
  margin: 0 5px;
  transition: 0.5s;
  cursor: pointer;
}

/* Shoe color */
.container .card .contentBx .color span {
  width: 20px;
  height: 20px;
  background: #ff0;
  margin: 0 5px;
  cursor: pointer;
}

/* Button/ */
.button {
  display: inline-block;
  padding: 10px 20px;
  background: rgb(140, 219, 127);
  margin-top: 10px;
  text-decoration: none;
  font-weight: 600;
  color: #111;
  opacity: 0;
  transform: translateY(50px);
  transition: 0.5s;
  border: none;
}

.text {
  display: none;
  color: #fff;
  padding: 2em;
  z-index: 299;
  position: relative;
  backdrop-filter: blur(20px);
  background-color: rgba(0, 0, 0, 0.2);
  height: 100%;
}
</style>
